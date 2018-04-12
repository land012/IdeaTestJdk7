package com.umbrella.util;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2018/2/28.
 */
public class ProcessUtil {

    private static Logger log = LoggerFactory.getLogger(ProcessUtil.class);

    public static int cmdNonOut( String... cmds) {
        return cmdNonOut(new File("."), 60, false, true, cmds);
    }

    public static int cmdNonOut(File workDir, String... cmds) {
        return cmdNonOut(workDir, 60, false, true, cmds);
    }

    public static int cmdNonOut(File workDir,
                                long timeout,
                                boolean stdoutLog,
                                boolean stderrLog,
                                String... cmds) {
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(workDir);
        pb.command(cmds);
        try {
            Process process = pb.start();
            new Thread(new ReadThreed(process.getInputStream(), stdoutLog)).start();
            new Thread(new ReadThreed(process.getErrorStream(), stderrLog)).start();
            boolean wait = process.waitFor(timeout, TimeUnit.SECONDS);
            if (wait) {
                int exitValue = process.exitValue();
                log.info("{} exitValue {}", cmds, exitValue);
                return exitValue;
            } else {
                log.error("process expire {}", cmds);
                process.destroy();
                return -1;
            }
        } catch (InterruptedException | IOException e) {
            log.error("process start exception", e);
        }

        return -1;
    }

    public static int cmdNonOut(File workDir,
                                Map<String, String> envs,
                                long timeout,
                                boolean stdoutLog,
                                boolean stderrLog,
                                String... cmds) {
        ProcessBuilder pb = new ProcessBuilder();
        if (MapUtils.isNotEmpty(envs)) {
            Map<String, String> lEnvs = pb.environment();
            for (Map.Entry<String, String> entry : envs.entrySet()) {
                lEnvs.put(entry.getKey(), entry.getValue());
            }
        }

        pb.directory(workDir);
        pb.command(cmds);
        try {
            Process process = pb.start();
            new Thread(new ReadThreed(process.getInputStream(), stdoutLog)).start();
            new Thread(new ReadThreed(process.getErrorStream(), stderrLog)).start();
            boolean wait = process.waitFor(timeout, TimeUnit.SECONDS);
            if (wait) {
                int exitValue = process.exitValue();
                log.info("{} exitValue {}", cmds, exitValue);
                return exitValue;
            } else {
                log.error("process expire {}", cmds);
                process.destroy();
                return -1;
            }
        } catch (InterruptedException | IOException e) {
            log.error("process start exception", e);
        }

        return -1;
    }

    static class ReadThreed implements Runnable {

        private InputStream inputStream;
        private boolean isLog;

        public ReadThreed(InputStream inputStream, boolean isLog) {
            this.inputStream = inputStream;
            this.isLog = isLog;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (isLog) {
                        log.info(line);
                    }
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    /**
     * 返回 shell 的标准输出和错误输出
     * @param workDir
     * @param cmds
     * @return
     */
    public static Map<String, String> cmdHasOut(File workDir, String charset, String... cmds) {
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(workDir);
        pb.command(cmds);
        Map<String, String> res = new HashMap<>();
        res.put("exitcode", "-1");
        try {
            Process process = pb.start();
            FutureTask<String> stdoutFt = new FutureTask(new ReadCallable(process.getInputStream(), charset));
            FutureTask<String> stderrFt = new FutureTask(new ReadCallable(process.getErrorStream(), charset));
            new Thread(stdoutFt).start();
            new Thread(stderrFt).start();
            boolean wait = process.waitFor(60, TimeUnit.SECONDS);
            if (wait) {
                int exitValue = process.exitValue();
                log.info("{} exitValue {}", cmds, exitValue);

                String stdout = stdoutFt.get();
                String stderr = stderrFt.get();
                res.put("exitcode", String.valueOf(exitValue));
                res.put("stdout", stdout);
                res.put("stderr", stderr);
                return res;
            } else {
                log.error("process expire, " + cmds);
                process.destroy();
                return res;
            }
        } catch (InterruptedException | IOException e) {
            log.error("process start exception", e);
        } catch (ExecutionException e) {
            log.error("ex,", e);
        }
        return res;
    }

    static class ReadCallable implements Callable<String> {

        private InputStream inputStream;
        private String charset;

        public ReadCallable(InputStream inputStream, String charset) {
            this.inputStream = inputStream;
            this.charset = charset;
        }

        @Override
        public String call() throws Exception {
            StringBuilder res = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    res.append(line);
                    res.append("\n");
                    if (res.length() > 1000000) {
                        break;
                    }
                }
            } catch (IOException e) {
                log.error("", e);
            }
            return res.toString();
        }
    }
}
