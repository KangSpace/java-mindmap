package org.kangspace.common.functionalinterface;

import java.util.List;

/**
 * 2019/12/9 18:47
 * 匿名类/局部类调用所在类的成员变量和局部变量时,需要将传入的变量转换为final,防止成员变量和局部变量在匿名类中被修改。
 * @author kangxuefeng@etiantian.com
 */
public class LambdaTest {
    public LambdaTest() {
    }
    interface SubInterface{
        void run(List list);
        void run2(List list);
    }

    public void test(Consumer<Integer> c, Integer i){
        c.accept(i);
        System.out.println();
    }


    public static void main(String[] args) {
        int a = 0;
        //将a作为SubInterface匿名类的成员变量val$a;
       /* Class sc = new SubInterface() {
            @Override
            public void run(List list) {
//                a = 1;
                list.add(a);
            }

            @Override
            public void run2(List list) {

            }
        }.getClass();
        System.out.println(sc.getDeclaredFields());*/
//        new LambdaTest(){}.test((i-> System.out.println(a)),1);
        new LambdaTest().test(i -> System.out.println(i+a),1);
        new LambdaTest().test(i -> {
//            a = 1;
            System.out.println();
            System.out.println(i+a);
        },1);
        System.out.println(a);
    }
}
