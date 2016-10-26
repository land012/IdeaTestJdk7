package cf.umbrella.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by xudazhou on 2016/10/18.
 */
public class FileProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Hello World ");
        Map<String, Object> map = exchange.getProperties();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            log.info(entry.getKey() + ":" + entry.getValue());
        }
    }
}
