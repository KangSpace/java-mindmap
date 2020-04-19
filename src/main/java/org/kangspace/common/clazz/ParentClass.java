package org.kangspace.common.clazz;

/**
 * 父类
 * 2019/11/22 13:19
 *
 * @author kango2gler@gmail.com
 */
public class ParentClass {
    final static String ps = "ParnetClass final String ps";
    static int pi = 0;
    ParentClass(){
        System.out.println("ParentClass Non-ArgsConstruct run"+" Thread:"+Thread.currentThread().getName());
    }
    ParentClass(int i){
        System.out.println("ParentClass(int i) run"+" Thread:"+Thread.currentThread().getName());
    }

    static {
        System.out.println("ParentClass static block run: ps:"+ps+" , pi:"+pi+" Thread:"+Thread.currentThread().getName());
    }

    public void syncThisFn(){
        synchronized (this){
            System.out.println("syncThisFn:"+this);
        }
    }

    public static void main(String[] args) {
        new ParentClass(1);
    }
}
