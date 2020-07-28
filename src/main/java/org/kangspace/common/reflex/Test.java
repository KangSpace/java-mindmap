package org.kangspace.common.reflex;


import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * 2019/11/29 12:05
 *
 * @author kango2gler@gmail.com
 */
public class Test {
    public static void main(String[] args) {
        Class<ReflexClass> claz = ReflexClass.class;
        try {
            //无参构造生成实例对象
            ReflexClass rc = claz.newInstance();
            System.out.println(claz.getName());

            //有参构造生成实例对象
            Constructor<ReflexClass>[] cs = (Constructor<ReflexClass>[]) claz.getConstructors();
            for (Constructor c:cs) {
                if (c.getParameterCount() > 0) {
                    rc = (ReflexClass) c.newInstance("");
                }
            }

            Package pkg = claz.getPackage();
            Annotation[] as = pkg.getAnnotations();
            Annotation[] as2 = pkg.getDeclaredAnnotations();
            Annotation a = pkg.getAnnotation(Annotation.class);
//            Annotation[] at = pkg.getAnnotationsByType(Annotation.class);

            //获取所有描述符
            int mi = claz.getModifiers();
            Modifier.classModifiers();

            //该类是在那个类中定义的， 比如直接定义的内部类或匿名内部类
            Class<?> ec = claz.getEnclosingClass();

            Constructor<?>[] cs1 = claz.getConstructors();
            Constructor<?>[] dcs = claz.getDeclaredConstructors();
            Constructor<?> ics = claz.getConstructor(String.class);
            //该类是在哪个构造函数中定义的，比如构造方法中定义的匿名内部类
            Constructor<?> encs = claz.getEnclosingConstructor();


            System.out.println();
            //该类是在哪个方法中定义的，比如方法中定义的匿名内部类
            Method eem = claz.getEnclosingMethod();
            Method em = claz.getDeclaredMethod("m1",String[].class);
            em.setAccessible(true);

            System.out.println("em.getGenericParameterTypes():");
            Type[] mts = em.getGenericParameterTypes();
            for (Type c : mts) {
                System.out.println(c.getTypeName());
            }
            System.out.println("em.getGenericParameterTypes():");
            Class[] mtcs = em.getParameterTypes();
            for (Class c : mtcs) {
                System.out.println(c.getTypeName()+","+c.getTypeName());
            }
            System.out.println("em.getAnnotatedParameterTypes():");
            AnnotatedType[] amtcs = em.getAnnotatedParameterTypes();
            for (AnnotatedType c : amtcs) {
                System.out.println(c.getType());
            }
            System.out.println("em.getParameterAnnotations():");
            Annotation[][] amtcss = em.getParameterAnnotations();
            for (Annotation[] c : amtcss) {
                System.out.println(c);
            }
            Parameter[] ps = em.getParameters();
//            ps[0].gett
            System.out.println();
            Type[] empt= em.getGenericParameterTypes();
            empt[0].getTypeName();
            if(true) {
                return;
            }

            Class<?>[] clazzs= claz.getClasses();
            System.out.println("claz.getClasses():");
            for (Class c : clazzs) {
                System.out.println(c.getName());
            }
            System.out.println("claz.getDeclaredClasses():");
            Class<?>[] clazzds = claz.getDeclaredClasses();
            for (Class c : clazzds) {
                System.out.println(c.getName());
            }
            ReflexClass rcp = new ReflexClass();
            ReflexClassParent rcc = claz.cast(rcp);
            System.out.println("claz.cast(rcp)");
            System.out.println(rcc);
            Class<? extends ReflexClassParent> rcpc  = claz.asSubclass(ReflexClassParent.class);
            System.out.println("claz.asSubclass(ReflexClassParent.class)");
            System.out.println(rcc);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
