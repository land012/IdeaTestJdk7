package cf.umbrella.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 多个客户端线程并发使用同一个 socket
 * Created by xudazhou on 2017/8/31.
 * Server端报 Connect reset
 */
public class SocketClientDemo {

    private static Logger log = LoggerFactory.getLogger(SocketClientDemo.class);

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 1122);
            client.setSoTimeout(3 * 1000);
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();
            new Thread(new MyThread(is, os)).start();
            new Thread(new MyThread(is, os)).start();
            new Thread(new MyThread(is, os)).start();
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                log.error("", e);
            }
//            client.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    static class MyThread implements Runnable {

        private InputStream is;
        private OutputStream os;

        public MyThread(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
        }

        @Override
        public void run() {
            try {
                String str1 = Thread.currentThread().getName();
                log.info("i am {}", str1);

                os.write(str1.getBytes("gbk"));
                os.flush();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.error("", e);
                }

                os.write(str1.getBytes("gbk"));
                os.flush();

                byte[] buf = new byte[1024];
                int len = is.read(buf);

//                os.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }
}
