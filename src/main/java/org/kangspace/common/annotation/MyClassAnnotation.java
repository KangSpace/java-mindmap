package org.kangspace.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解-我的类注解
 * @Author kangxuefeng@etiantian.com
 * @Date 2017/6/23 15:52
 */
//RetentionPolicy.SOURCE  Annotation只保留在源代码中，编译器编译时，直接丢弃这种Annotation。
//RetentionPolicy.CLASS 编译器把Annotation记录在class文件中。当运行Java程序时，JVM中不再保留该Annotation。
//RetentionPolicy.RUNTIME 编译器把Annotation记录在class文件中。当运行Java程序时，JVM会保留该Annotation，程序可以通过反射获取该Annotation的信息。
@Retention(value = RetentionPolicy.RUNTIME)
///Target指定Annotation用于修饰哪些程序元素。
// @Target也包含一个名为”value“的成员变量，该value成员变量类型为ElementType[ ]，ElementType为枚举类型，值有如下几个：
//        ElementType.TYPE：能修饰类、接口或枚举类型
//        ElementType.FIELD：能修饰成员变量
//        ElementType.METHOD：能修饰方法
//        ElementType.PARAMETER：能修饰参数
//        ElementType.CONSTRUCTOR：能修饰构造器
//        ElementType.LOCAL_VARIABLE：能修饰局部变量
//        ElementType.ANNOTATION_TYPE：能修饰注解
//        ElementType.PACKAGE：能修饰包
@Target(value = ElementType.TYPE)
//jdk1.8+
@Repeatable(MyClassAnnotation.MyClassAnnotations.class)
@Documented
public @interface MyClassAnnotation{

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface MyClassAnnotations{
        MyClassAnnotation[] value();
    }

    /**
     * 如果Annotation里有一个名为“value“的成员变量，使用该Annotation时，可以直接使用XXX(val)形式为value成员变量赋值，无须使用name=val形式。
     * 关于注解支持的元素数据类型除了上述的String，还支持如下数据类型
         所有基本类型（int,float,boolean,byte,double,char,long,short）
         String
         Class
         enum
         Annotation
         上述类型的数组
     */
    String[] value();
    MyAnnotationTypeEnum type();
    Class clazz();
}
