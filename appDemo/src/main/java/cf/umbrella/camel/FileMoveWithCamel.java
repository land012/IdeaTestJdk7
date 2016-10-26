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
public class FileMoveWithCamel {

    private static final Logger log = LoggerFactory.getLogger(FileMoveWithCamel.class);

    public static void main(String[] args) throws Exception {
        CamelContext camel = new DefaultCamelContext();
        camel.addRoutes(new RouteBuilder() {
            /**
             * 将目录 inbox 下的文件移动到 outbox 下
             * 仅在启动时执行这个方法
             * @throws Exception
             */
            @Override
            public void configure() throws Exception {
                log.info("executing");
                from("file:d:/_log/inbox/?delay=10000").to("file:d:/_log/outbox");
            }
        });

        camel.start();

        while (true) {
            TimeUnit.SECONDS.sleep(100);
        }
    }
}
