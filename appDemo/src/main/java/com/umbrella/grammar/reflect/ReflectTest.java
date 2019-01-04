package com.umbrella.grammar.reflect;

import com.umbrella.vo.Passenger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-4-23.
 */
public class ReflectTest {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(ReflectTest.class);

    class Cat {
        private long id;
        private Long id2;
        private String name;
        private boolean gender;
        private Boolean gender2;
    }

    class Person {
        private long id;
        public String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class User extends Person {
        private Date birth;
        public String address;
        private Long id1;
        private long distance;
        private int age;
        private boolean gender;

        public Date getBirth() {
            return birth;
        }

        public void setBirth(Date birth) {
            this.birth = birth;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Long getId1() {
            return id1;
        }

        public void setId1(Long id1) {
            this.id1 = id1;
        }

        public long getDistance() {
            return distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isGender() {
            return gender;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }
    }

    @Test
    public void test1() {
        try {
            Field tickNo = Passenger.class.getDeclaredField("ticketNo");
            tickNo.setAccessible(true);
            Class clazz1 = tickNo.getType();
            System.out.println(clazz1.getName());

            System.out.println(clazz1.isAssignableFrom(long.class)); // true
            System.out.println(clazz1.isAssignableFrom(Long.class)); // false

            Type type1 = tickNo.getGenericType();
            System.out.println(type1.toString());

            // 可以获取父类的 public 属性
            Field birthDay = Passenger.class.getField("birthDay");
            tickNo.setAccessible(true);
            Class clazz2 = birthDay.getType();
            System.out.println(clazz2.getName()); // java.util.Date

            System.out.println(clazz2.isAssignableFrom(Date.class));

            Type type2 = birthDay.getGenericType();
            System.out.println(type2.toString()); // class java.util.Date

            /**
             * java.lang.NoSuchFieldException: BirthDay
             * 区分大小写
              */
            Field birthDay2 = Passenger.class.getField("BirthDay");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
		log.info(ReflectTest.class.getName());

		// Modified 修饰符
		System.out.println("Modifier.ABSTRACT:" + Modifier.ABSTRACT);
		System.out.println("Modifier.PUBLIC:" + Modifier.PUBLIC);
		System.out.println("Modifier.PRIVATE:" + Modifier.PRIVATE);
		System.out.println("Modifier.PROTECTED:" + Modifier.PROTECTED);
		System.out.println("Modifier.FINAL:" + Modifier.FINAL);
		System.out.println("Modifier.STATIC:" + Modifier.STATIC);

		System.out.println(Modifier.isFinal(16));
		System.out.println(Modifier.isFinal(17)); // 包含 final 就会返回 true
		System.out.println(Modifier.isFinal(40));

    }

    @Test
    public void test3() {
        //         Fields
        /**
         * 显示当前类声明的所有字段(不区分是 public 还是 prirvate)
         * 不包括父类字段
         */
        System.out.println("getDeclaredFields:");
        Field[] fs1 = User.class.getDeclaredFields();
        System.out.println(fs1.length);
        /*
         *     birth    2
         *     address    1
         *     this$0    4112 内部类会返回这个标签
         */
        for(Field f : fs1) {
            System.out.println("    " + f.getName() + "    " + f.getModifiers() + " " + f.getType());
        }

    }

    @Test
    public void test3_0() {
        /**
         * 仅显示 当前类声明的  public 类型的字段
         * 包括父类的 public 字段
         */
        System.out.println("getFileds:");
        Field[] fs2 = User.class.getFields();
        System.out.println(fs2.length);
        for(Field f : fs2) {
            System.out.println("    " + f.getName());
        }
    }

    @Test
    public void test3_1() throws Exception {
        User u1 = new User();
        u1.setBirth(new Date());
        u1.setAddress("tengzhou");

        Field f1 = User.class.getDeclaredField("birth");
        f1.setAccessible(true);
        System.out.println(f1.get(u1));
        TimeUnit.SECONDS.sleep(2);
        f1.set(u1, new Date());
        System.out.println(f1.get(u1));

        Field f2 = User.class.getDeclaredField("address");
        System.out.println(f2.get(u1));
    }

    @Test
    public void test4() {
        //         Method
        /**
         * 仅显示当前类中声明的方法(不区分是 public 还是 private)
         * 不包括父类的方法
         */
        System.out.println("getDeclaredMethods:");
        Method[] ms1 = User.class.getDeclaredMethods();
        System.out.println(ms1.length);
        for(Method m : ms1) {
            System.out.println("    " + m.getName());
        }

        /**
         * 获取方法(不获取私有方法)
         * 包括父类的方法(当然也包括 Object的方法)
         */
        System.out.println("getMethods:");
        Method[] ms2 = User.class.getMethods();
        System.out.println(ms2.length);
        for(Method m : ms2) {
            System.out.println("    " + m.getName());
        }
    }

    /**
     * 成员变量类型
     */
    @Test
    public void test22() {
        Field[] fields = Cat.class.getDeclaredFields();
        for(Field f : fields) {
            if("boolean".equals(f.getType().getCanonicalName()) || "java.lang.Boolean".equals(f.getType().getCanonicalName())) {
                System.out.println(f.getType().toString() + " - " + f.getType().getCanonicalName());
            } else {
                System.out.println(f.getType().toString() + " , " + f.getGenericType());
            }

        }
    }
}
