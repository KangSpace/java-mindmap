package org.kangspace.common.clazz;

/**
 * 子类加载测试
 * 2019/11/22 13:19
 *
 * @author kangxuefeng@etiantian.com
 */
public class ChildClassLoadTest {
    public static void main(String[] args) {
        // new 对象时，会加载并初始化父类及子类对象,执行父类static代码块和父类无参构造方法，及子类static代码块和子类构造方法
        ChildClass childClass = new ChildClass(1);
        System.out.println("childClass:"+childClass);

        String className = "org.kangspace.common.clazz.ChildClass";
        try {
            //ClassLoader加载类,只是将字节码加载到内存，并不会初始化类(即调用类的static代码块)
            Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ClassLoader load childClass end ");
        //调用类中常量时不会初始化类
        System.out.println("ChildClass.ps:"+ChildClass.cs);
        System.out.println("ChildClass.ps: end ");
        //调用类中静态属性时，会初始化父类及子类，及执行父类static代码块及子类static代码块
        System.out.println("ChildClass.pi:"+ChildClass.ci);
        System.out.println("ChildClass.pi: end ");

        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ChildClass childClass1 = new ChildClass();
        System.out.println("childClass:"+childClass1);
    }
}
