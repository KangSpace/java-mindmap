package org.kangspace.common.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kango2gler@gmail.com
 * @desc 泛型类型
 * @date 2019/12/1 21:38
 */
//
public class GenericsClass<T extends GenericsSuperClass,S extends Number> extends GenericsSuperClass<T>{
    //作用于方法参数
    public <T> void m1(GenericsClass<? super GenericsClass,?> p1){
    }
    //作用于方法参数
    public <T> void m2(List<Integer> p1){
    }
    public  static  void playFruitBase(List < ? super  Number> list){

        Collection<String> cs = new ArrayList<String>(){
            @Override
            public String toString() {
                return "$classname{}";
            }
        };
        cs.add("asd");

        //普通通配符不能存，只能用object接收读取
        Collection<?> c = new ArrayList<String>();
        Object  a = c.iterator().next();
        //c.add(new Object());

        //下界通配符super可以存，但取只能用Object接
        Collection<? super Integer> css = new ArrayList<Integer>();
        css.add(1);
        css.add(2);
        Object aa = css.iterator().next();
        //上届界通配符extend 可以取，但不能存
        Collection<? extends Number> cssn = new ArrayList<Integer>();
        //cssn.add(new Integer(1));
        Number aaa = cssn.iterator().next();

    }
    //作用于方法参数
    public <T extends Number,S extends String> void m2(List<T> p1, List<S> p2,List<?> p3){

    }

    class MemberClass1<T extends GenericsClass>{}
}
