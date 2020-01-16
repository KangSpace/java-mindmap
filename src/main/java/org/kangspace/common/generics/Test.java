package org.kangspace.common.generics;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author kango2gler@gmail.com
 * @desc
 * @date 2019/12/1 21:54
 */
public class Test {
    public static void main(String[] args) {
        GenericsClass gc = new GenericsClass< GenericsSuperClass, Number > ();
        GenericsClass gc2 = new GenericsClass< GenericsClass, Number > ();
        gc.m1(gc);
        try {
            Method gcm1 = gc.getClass().getDeclaredMethod("m2",List.class);
            //获取泛型参数列表(实际为ParameterizedType类型)
            Type[] gcm1t  = gcm1.getGenericParameterTypes();
            System.out.println(gcm1t[0]);
            System.out.println(((ParameterizedType) gcm1t[0]).getActualTypeArguments()[0]);
            //获取Class[]的参数列表
            Class[] cs = gcm1.getParameterTypes();
            System.out.println(cs[0]);
            //获取定义的泛型
            TypeVariable[] tvs = gcm1.getTypeParameters();
            System.out.println(tvs[0]);

            System.out.println();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
