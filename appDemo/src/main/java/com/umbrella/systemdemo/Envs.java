package com.umbrella.systemdemo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.umbrella.util.ProcessUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2018/2/27.
 */
public class Envs {

    private static Logger log = LoggerFactory.getLogger(Envs.class);

    @Test
    public void hello() {
        try {
            log.info("============================= hello begin =================================");
            String str0 = "echo $JAVA_HOME";
            ProcessUtil.cmdNonOut(new File("."),
                    60,
                    true,
                    true,
                    "/bin/bash", "-c", str0);

            TimeUnit.SECONDS.sleep(3);

            String str1 = "echo $K111";
            Map<String, String> envs = new HashMap<>();
            envs.put("K111", "V111");
            ProcessUtil.cmdNonOut(new File("."),
                    envs,
                    60,
                    true,
                    true,
                    "/bin/bash", "-c", str1);

            TimeUnit.SECONDS.sleep(3);

            // 获取不到前面的步骤的环境变量
            String str2 = "echo $K111";
            ProcessUtil.cmdNonOut(new File("."),
                    60,
                    true,
                    true,
                    "/bin/bash", "-c", str2);

            TimeUnit.SECONDS.sleep(3);

            String str3 = "export K111=V222 && echo $K111";
            ProcessUtil.cmdNonOut(new File("."),
                    60,
                    true,
                    true,
                    "/bin/bash", "-c", str3);

            TimeUnit.SECONDS.sleep(3);

            // 获取不到前面步骤的环境变量
            str2 = "echo $K111";
            ProcessUtil.cmdNonOut(new File("."),
                    60,
                    true,
                    true,
                    "/bin/bash", "-c", str2);

            log.info("============================= hello end =================================");
        } catch (Exception e) {
            log.error("exception=", e);
        }
    }

}
