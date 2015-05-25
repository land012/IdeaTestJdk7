package com.umbrella.grammar.serialize;


import com.umbrella.grammar.serialize.composite.Country;
import com.umbrella.grammar.serialize.composite.Person;
import com.umbrella.grammar.serialize.extend.Tiger;
import com.umbrella.vo.Student;
import org.junit.Test;

import java.io.*;

/**
 * Created by 大洲 on 14-12-29.
 */
public class SerializeDemo {
    // 如果不指定全路径，只指定文件名，则默认将文件生成到工程目录下
    private static final String FILE_NAME = "D:\\_java\\file1.txt";

    private static <T> void writeToFile(T t) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(t);
        oos.flush();
        oos.close();
        fos.close();
    }

    private static <T> T readFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        T t = (T)ois.readObject();
        ois.close();
        return t;
    }

    /**
     * 简单序列化
     */
    @Test
    public void testWriteToFile() {
        try {
            Student s1 = new Student();
            s1.setId(1111);
            s1.setName("Leah Dizon");
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单序列化
     */
    @Test
    public void testReadFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            Student s1 = (Student)ois.readObject();
            System.out.println(s1.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 引用没有实现 Serializable 接口
     */
    @Test
    public void testWriteToFile2() throws IOException {
        Country c1 = new Country();
        c1.setId(111);
        c1.setName("China");
        c1.setNationalFlag("FiveStar");
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("Leah Dizon");
        p1.setCountry(c1);
        writeToFile(p1);
    }

    @Test
    public void testReadFromFile2() throws IOException, ClassNotFoundException {
        Person p1 = readFromFile();
        System.out.println("Person.id = " + p1.getId());
        System.out.println("Person.Country.name = " + p1.getCountry().getName());
        System.out.println("Person.Country.id = " + p1.getCountry().getId());
    }

    /**
     * 父类没有实现 Serializable，父类的属性不会被序列化
     */
    @Test
    public void testWriteToFile3() throws IOException {
        Tiger t1 = new Tiger("Alphonse", "yellow");
//        t1.setName("Alphonse");
//        t1.setColor("yellow");
        writeToFile(t1);
    }

    @Test
    public void testReadFromFile3() throws IOException, ClassNotFoundException {
        Tiger t1 = readFromFile();
        System.out.println(t1.getName());  // null
        System.out.println(t1.getColor()); // yellow
    }
}
