package com.umbrella.grammar.annotation;

import java.lang.reflect.Method;

/**
 * Created by 大洲 on 14-12-28.
 */
@MyAnnotation(color="red")
public class AnnotationTest {

    public static void main(String[] args) {
        if(AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation);
            System.out.println(annotation.color());
        }

        Method[] methods = AnnotationTest.class.getMethods();
        for(Method m : methods) {
            MyAnnotation anno = m.getAnnotation(MyAnnotation.class);
            if(anno!=null) {
                System.out.println(anno);
                System.out.println(anno.color());
            }
        }
    }

    private String name;

    @MyAnnotation(value = "I am Leah Dizon", color = "blue", array = {2, 3, 4})
    public void setName(String name) {
        this.name = name;
    }
}
