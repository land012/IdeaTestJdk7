package cf.umbrella.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xudazhou on 2017/8/31.
 */
public class SocketServerDemo {

    private static Logger log = LoggerFactory.getLogger(SocketServerDemo.class);

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1122, 3);
            while (true) {
                Socket socket = server.accept();
                log.info("{}:{}", socket.getInetAddress().getHostAddress(), socket.getPort());
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = is.read(buf)) > 0) {
                    String req = new String(buf, 0, len, "gbk");
                    log.info("{} | {}", len, req);
                    if ("a".equals(req)
                            || "b".equals(req)
                            || "c".equals(req)) {
                        os.write(("hello " + req).getBytes("gbk"));
                        os.flush();
                    }
                }
                log.info("len={}", len); // -1

//                if (req.indexOf("0") >= 0) {
//                    socket.
//                }
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
