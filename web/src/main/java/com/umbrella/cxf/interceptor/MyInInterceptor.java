package com.umbrella.cxf.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ¥Û÷ﬁ on 14-11-25.
 */
public class MyInInterceptor extends AbstractPhaseInterceptor<Message> {
    public MyInInterceptor(String phase) {
        super(phase);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        System.out.println("=============== MyInInterceptor begin =============================================");
        System.out.println(message.getId() + "#" + message.getDestination().getMessageObserver());
        System.out.println(message.getExchange().getInMessage() + "#" + message.getExchange().getInFaultMessage());
        System.out.println(message.getExchange().getOutMessage() + "#" + message.getExchange().getOutFaultMessage());
        Map map = (Map)message.get(Message.PROTOCOL_HEADERS);
        for(Map.Entry entry : (Set<Map.Entry>)map.entrySet()) {
            System.out.format("%s : ", entry.getKey());
            List<String> list = (List<String>)entry.getValue();
            System.out.print("list.size()=" + list.size() + "   ");
            for(String s : list) {
                System.out.print(s + " °Ù ");
            }
            System.out.println();
        }
//        System.out.println("===================" + message.get("k1") + "===================");
        System.out.println("=============== MyInInterceptor end =============================================");
    }
}
