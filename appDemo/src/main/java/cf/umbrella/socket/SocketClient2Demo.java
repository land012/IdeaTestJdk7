package cf.umbrella.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多线程顺序使用同一个 socket
 * 只有写没有读
 * Created by xudazhou on 2017/8/31.
 * 没有问题，可以通信
 */
public class SocketClient2Demo {

    private static Logger log = LoggerFactory.getLogger(SocketClient2Demo.class);

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 1122);
            ExecutorService exec = Executors.newSingleThreadExecutor();
            exec.submit(new MyThread(client, "a"));
            exec.submit(new MyThread(client, "b"));
            exec.submit(new MyThread(client, "c"));
            exec.shutdown();
            try {
                exec.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.error("", e);
            }
            client.close();
//            client.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    static class MyThread implements Runnable {

        private Socket socket;
        private String name;

        public MyThread(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                String str1 = Thread.currentThread().getName();
                log.info("i am {}", str1);

                OutputStream os = socket.getOutputStream();

                os.write(this.name.getBytes("gbk"));
                os.flush();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("", e);
                }

                os.write(str1.getBytes("gbk"));
                os.flush();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }
}
