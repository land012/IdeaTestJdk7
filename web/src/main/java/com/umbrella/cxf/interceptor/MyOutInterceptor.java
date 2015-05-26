package com.umbrella.cxf.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 大洲 on 14-11-25.
 */
public class MyOutInterceptor extends AbstractPhaseInterceptor<Message> {
    public MyOutInterceptor(String phase) {
        super(phase);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        /*
         * http 头信息，最外层是Map，Map 的值是 List
         * 但是在获取这个List元素时，所有元素都存在第一个位置（即 list.get(0)），以逗号隔开
         */
        Map map = new HashMap();
        List<String> list = new ArrayList<String>();
        list.add("l1");
        list.add("l2");
        map.put("k1", list);
        message.put(Message.PROTOCOL_HEADERS, map);

        // 不能用这种方式传递信息，服务端拿不到
//        message.put("k1", "这里是要传输的信息");
    }
}
