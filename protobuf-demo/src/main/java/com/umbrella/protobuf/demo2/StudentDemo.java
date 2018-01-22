package com.umbrella.protobuf.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.*;
import com.googlecode.protobuf.format.JsonFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xudazhou on 2017/3/7.
 */
public class StudentDemo {

    private static Logger log = LoggerFactory.getLogger(StudentDemo.class);

    /**
     * json to pb
     * 有子对象
     * @throws Exception
     */
    @Test
    public void test1_json2pb() throws Exception {
        Teacher t1 = new Teacher();
        t1.setTid(1);
        t1.setTname("kakashi");

        Student s1 = new Student();
        s1.setSid(2);
        s1.setSname("naruto");
        s1.setTeacher(t1);

        ObjectMapper om = new ObjectMapper();
        String json1 = om.writeValueAsString(s1);
        System.out.println(json1); // {"sid":2,"sname":"naruto","teacher":{"tid":1,"tname":"kakashi"}}

        JsonFormat jf = new JsonFormat();
        StudentSample.Student.Builder builder1 = StudentSample.Student.newBuilder();
        jf.merge(json1, ExtensionRegistry.getEmptyRegistry(), builder1);
        StudentSample.Student s2 = builder1.build();
        /*
         * sid: 2
         * sname: "naruto"
         * teacher {
         *   tid: 1
         *   tname: "kakashi"
         * }
         */
        System.out.println(s2.toString());
    }

    /**
     * json中没有子对象
     * @throws Exception
     */
    @Test
    public void test2_json2pb() throws Exception {
        String json1 = "{\"sid\":2,\"sname\":\"naruto\"}";
        StudentSample.Student.Builder builder1 = StudentSample.Student.newBuilder();
        JsonFormat jf = new JsonFormat();
        jf.merge(json1, ExtensionRegistry.getEmptyRegistry(), builder1);
        StudentSample.Student s1 = builder1.build();
        /*
         * sid: 2
         * sname: "naruto"
         */
        System.out.println(s1);
        System.out.println(s1.hasTeacher()); // false
        StudentSample.Teacher t1 = s1.getTeacher();
        System.out.println(t1 == null); // false
        System.out.println(t1.getTid()); // 0
    }

    /**
     * 子对象为空
     * @throws Exception
     */
    @Test
    public void test3_json2pb() throws Exception {
        String json1 = "{\"sid\":2,\"sname\":\"naruto\",\"teacher\":{}}";
        StudentSample.Student.Builder builder1 = StudentSample.Student.newBuilder();
        JsonFormat jf = new JsonFormat();
        jf.merge(json1, ExtensionRegistry.getEmptyRegistry(), builder1);
        StudentSample.Student s1 = builder1.build();
        /*
         * sid: 2
         * sname: "naruto"
         * teacher {
         * }
         */
        System.out.println(s1);
        System.out.println(s1.hasTeacher()); // true
        StudentSample.Teacher t1 = s1.getTeacher();
        System.out.println(t1 == null); // false
        System.out.println(t1.getTid()); // 0
    }

    /**
     * proto对象 反序列化 另一个proto对象的子对象
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        StudentSample.Teacher.Builder bt = StudentSample.Teacher.newBuilder();
        bt.setTid(1);
        bt.setTname("kakashi");
        StudentSample.Teacher t1 = bt.build();
        StudentSample.Student.Builder bs = StudentSample.Student.newBuilder();
        bs.setSid(2);
        bs.setSname(ByteString.copyFromUtf8("naruto"));
        bs.setTeacher(t1);
        StudentSample.Student s1 = bs.build();

        StudentSample.Teacher t2 = s1.getTeacher();

        TeacherSample.Teacher t3 = TeacherSample.Teacher.parseFrom(t2.toByteArray());
        /*
         * tid: 1
         * tname: "kakashi"
         */
        System.out.println(t3.toString());
    }

    /**
     * 子对象转json
     * @throws Exception
     */
    @Test
    public void test5_pb2json() throws Exception {
        StudentSample.Teacher.Builder bt = StudentSample.Teacher.newBuilder();
        bt.setTid(1);
        bt.setTname("kakashi");
        StudentSample.Teacher t1 = bt.build();
        StudentSample.Student.Builder bs = StudentSample.Student.newBuilder();
        bs.setSid(2);
        bs.setSname(ByteString.copyFromUtf8("naruto"));
        bs.setTeacher(t1);
        StudentSample.Student s1 = bs.build();

        StudentSample.Teacher t2 = s1.getTeacher();

        JsonFormat jf = new JsonFormat();
        System.out.println(jf.printToString(t2)); // {"tid": 1,"tname": "kakashi"}
    }

    /**
     * 反射
     * @throws Exception
     */
    @Test
    public void test6_descriptor() throws Exception {
        StudentSample.Teacher.Builder tb1 = StudentSample.Teacher.newBuilder();
        tb1.setTid(2);
        tb1.setTname("kakashi");
        StudentSample.Student.Builder sb1 = StudentSample.Student.newBuilder();
        sb1.setSid(3);
        sb1.setSname(ByteString.copyFrom("naruto", "utf-8"));
        sb1.addHobbies("reading");
        sb1.addHobbies("fishing");
        sb1.addCourses(ByteString.copyFromUtf8("chinese"));
        sb1.addCourses(ByteString.copyFromUtf8("english"));
        sb1.setTeacher(tb1);
        StudentSample.Student s1 = sb1.build();

        StudentSample.Student s2 = (StudentSample.Student) recurs(s1);
        System.out.println(s2);
    }

    static Message recurs(Message message) throws Exception {
        Descriptors.Descriptor d1 = message.getDescriptorForType();
        List<Descriptors.FieldDescriptor> fdList = d1.getFields();

        Message.Builder b = message.toBuilder();

        for (Descriptors.FieldDescriptor fd : fdList) {
            if (fd.isRepeated()) {
                if (fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.BYTE_STRING) {
                    List<ByteString> list1 = (List<ByteString>) message.getField(fd);
                    List<ByteString> list2 = new ArrayList<>();
                    for (ByteString bs1 : list1) {
                        list2.add(ByteString.copyFrom("haha".getBytes("utf-8")));
                    }
                    b.setField(fd, list2);
                } else if (fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
                    LazyStringList list1 = (LazyStringList) message.getField(fd);
                    LazyStringList list2 = new LazyStringArrayList();
                    Iterator<String> iterator1 = list1.iterator();
                    while (iterator1.hasNext()) {
                        iterator1.next();
                        list2.add("heihei");
                    }
                    b.setField(fd, list2);
                } else if (fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    List<Message> list1 = (List<Message>) message.getField(fd);
                    List<Message> list2 = new ArrayList<>();
                    for (Message m : list1) {
                        Message m2 = recurs(m);
                        list2.add(m2);
                    }
                    b.setField(fd, list2);
                }
            } else {
                if (fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.BYTE_STRING) {
                    b.setField(fd, ByteString.copyFrom("haha".getBytes("UTF-8")));
                } else if (fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
                    b.setField(fd, "heihei");
                } else if (fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    Message m2 = recurs((Message)message.getField(fd));
                    b.setField(fd, m2);
                }
            }
        }
        return b.build();
    }

    /**
     * set Builder
     */
    @Test
    public void test7() {
        StudentSample.Teacher.Builder tBuilder = StudentSample.Teacher.newBuilder();
        tBuilder.setTid(3);
        tBuilder.setTname("kakashi");

        StudentSample.Student.Builder sBuilder = StudentSample.Student.newBuilder();
        sBuilder.setSid(5);
        sBuilder.setTeacher(tBuilder); // 可以set Teacher 或 Teacher的Builder

        StudentSample.Student s1 = sBuilder.build();
        /*
        sid: 5
        teacher {
          tid: 3
          tname: "kakashi"
        }
         */
        System.out.println(s1);

        StudentSample.Student.Builder s2Builder = s1.toBuilder();
        StudentSample.Teacher.Builder t2Builder = s1.getTeacher().toBuilder();
        t2Builder.setTname("orichimaru");
//        s2Builder.setTeacher(t2Builder); // 不加这句，不会更改
        StudentSample.Student s2 = s2Builder.build();
        /*
        sid: 5
        teacher {
          tid: 3
          tname: "kakashi"
        }
         */
        System.out.println(s2);
    }

    /**
     * 通过 builder 获取成员变量的 builder 时，修改会生效，不必 set
     */
    @Test
    public void test8() {
        StudentSample.Teacher.Builder tBuilder = StudentSample.Teacher.newBuilder();
        tBuilder.setTid(3);
        tBuilder.setTname("kakashi");

        StudentSample.Student.Builder sBuilder = StudentSample.Student.newBuilder();
        sBuilder.setSid(5);
        sBuilder.setTeacher(tBuilder); // 可以set Teacher 或 Teacher的Builder

        StudentSample.Student s1 = sBuilder.build();
        /*
        sid: 5
        teacher {
          tid: 3
          tname: "kakashi"
        }
         */
        System.out.println(s1);

        StudentSample.Student.Builder s2Builder = s1.toBuilder();
        // 通过 builder 获取子对象的 builder
        StudentSample.Teacher.Builder t2Builder = s2Builder.getTeacherBuilder();
        t2Builder.setTname("orichimaru");
        StudentSample.Student s2 = s2Builder.build();
        /*
        sid: 5
        teacher {
          tid: 3
          tname: "orichimaru"
        }
         */
        System.out.println(s2);
    }
}
