package com.umbrella.util;

import com.umbrella.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 大洲 on 14-11-13.
 */
public class ConvertUtil {

    public static void main(String[] args) {
//        String str1 = "USER2_NA2ME_4HA3HA1";
//        System.out.println(getPropertyName(str1));

        List<Map> list1 = new ArrayList<Map>();
        Map map1 = new HashMap();
        map1.put("ID", new BigDecimal(123456));
        map1.put("USER_NAME", "tom");
        map1.put("BIRTH_DAY", new Timestamp(new Date().getTime()));
        map1.put("GENDER", new BigDecimal(0));
        map1.put("URGENCY_CONTACT_NAME", "tomFather");
        list1.add(map1);

        List<User> list2 = null;
        try {
            list2 = mapToVo(list1, User.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(list2==null || list2.size()==0) {
            System.out.println("没有记录");
        } else {
            for(User u : list2) {
                System.out.println(u.getId() + "," + u.getUserName() + "," + u.getBirthDay() + "," + u.getGender() + "，" + u.getUrgencyContactName());
            }
        }
    }

    /**
     * 从 Map 得到 Vo
     * Map 里的 key 里下划线隔开 对应 Vo 中的属性名为驼峰标识
     * @param list
     * @param clazz
     * @param <E>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static <E> List<E> mapToVo(List<Map> list, Class<E> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if(list==null || list.size()==0) return null;
        List<E> res = new ArrayList<E>(list.size());
        for(Map m : list) {
            E bean = clazz.newInstance();
            for(Map.Entry e : (Set<Map.Entry>)m.entrySet()) {
                String pName = getPropertyName((String)e.getKey());
                BeanUtils.setProperty(bean, pName, e.getValue());
            }
            res.add(bean);
        }
        return res;
    }

    /**
     * 要求 name 只能由字母数字和下划线组成，且下划线不能连续出现，下划线不能出现在字符串首尾
     * @param name USER_NAME
     * @return userName
     */
    private static String getPropertyName(String name) {
        if(name==null) return null;
        String temp = name.toLowerCase();
        StringBuilder res = new StringBuilder();
        for(int i=0; i<temp.length(); i++) {
            String sub1 = temp.substring(i, i+1);
            if("_".equals(sub1)) {
                String sub2 = temp.substring(i+1, i+2).toUpperCase();
                res.append(sub2);
                i++;
            } else {
                res.append(sub1);
            }
        }
        return res.toString();
    }

    /**
     * 返回 Unicode 编码，类似 \u0012\u0393
     * @param str
     * @return
     */
    public static String getUnicode(String str) {
        StringBuilder res = new StringBuilder();
        char[] arr = str.toCharArray();
        for(char c : arr) {
            res.append("\\u").append(Integer.toHexString(c).toUpperCase());
        }
        return res.toString();
    }
}
