package org.kangspace.common.generics;

import org.kangspace.common.clazz.Interface;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 2019/12/6 18:25
 *
 * @author kangxuefeng@etiantian.com
 */
public class ClassGenerics<T> {
    public static void main(String[] args) {
        ClassGenerics<Integer> cg = new ClassGenerics<Integer>(){};
//        Type tv =  cg.getClass().getTypeParameters()[0];
        Type type = cg.getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        System.out.println(types[0]);
//        System.out.println(cg.getClass().toGenericString());
        System.out.println(cg.getClass().getGenericSuperclass().getTypeName());

        Interface inta = new Interface(){
            @Override
            public void run() {

            }

            @Override
            public void run2() {

            }

            @Override
            public void run1() {

            }
        };
        Class inac = inta.getClass();
        System.out.println("interface 匿名类==================");
        System.out.println("className="+inac.getName());
        System.out.println("class="+inac);
        System.out.println("superClass="+inac.getSuperclass());
        System.out.println("superInterface="+inac.getInterfaces()[0]);
        System.out.println("class 匿名类==================");
        Class cgc = new ClassGenerics(){}.getClass();
        System.out.println("className="+cgc.getName());
        System.out.println("class="+cgc);
        System.out.println("superClass="+cgc.getSuperclass());
        System.out.println("superInterface="+cgc.getInterfaces());

    }
    interface A{}
}

