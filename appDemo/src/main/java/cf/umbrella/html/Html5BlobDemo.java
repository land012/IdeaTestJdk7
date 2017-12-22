package cf.umbrella.html;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by xudazhou on 2017/9/28.
 */
public class Html5BlobDemo {

    private static Logger log = LoggerFactory.getLogger(Html5BlobDemo.class);

    @Test
    public void test1() throws Exception {
        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\video111.mp4");
        try {
            for (int i=1; i<1000; i++) {
                URL url = new URL("http://ip57747833.ahcdn.com/key=u9qXie7MMusom0d19TCRlA,end=1506704523/state=bc6i/reftag=48423857/media=hls/ssd8/121/8/50203368.mp4/seg-"+i+"-v1-a1.ts");
                URLConnection conn = url.openConnection();
                InputStream is = conn.getInputStream();

                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    buf = new byte[1024];
                }

                fos.flush();
                is.close();
            }
            fos.close();
        } catch (FileNotFoundException e) {
            log.info("filnotfound", e);
            fos.close();
        } catch (Exception e) {
            log.info("---", e);
        }
    }
}
