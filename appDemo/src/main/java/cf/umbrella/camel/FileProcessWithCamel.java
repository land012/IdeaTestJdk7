package cf.umbrella.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/10/18.
 */
public class FileProcessWithCamel {

    private static final Logger log = LoggerFactory.getLogger(FileProcessWithCamel.class);

    public static void main(String[] args) throws Exception {
        CamelContext camel = new DefaultCamelContext();
//        camel.addRoutes(new RouteBuilder() {
//            /**
//             * 将目录 inbox 下的文件移动到 outbox 下
//             * 仅在启动时执行这个方法
//             * 每10秒检查一次 inbox目录，如果有新添加的文件，才会执行 FileProcessor
//             * @throws Exception
//             */
//            @Override
//            public void configure() throws Exception {
//                log.info("executing");
//                from("file:d:/_log/inbox/?delay=10000")
//                        .process(new FileProcessor())
//                        .to("file:d:/_log/outbox");
//            }
//        });

        camel.addRoutes(new RouteBuilder() {
            /**
             * 时刻监视 inbox 目录，当添加新文件时，会立即执行 process，将文件移动到 outbox 下，
             * 不会删除 inbox 下的文件
             * 每移动一个文件，执行一次 process
             * @throws Exception
             */
            @Override
            public void configure() throws Exception {
                log.info("executing");
                from("file:d:/_log/inbox/?noop=true")
                        .process(new FileProcessor())
                        .to("file:d:/_log/outbox");
            }
        });

        camel.start();

        while (true) {
            TimeUnit.SECONDS.sleep(100);
        }
    }
}
