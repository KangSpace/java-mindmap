package org.kangspace.common.clone;

/**
 * 2020/1/7 11:53
 *
 * @author kango2gler@gmail.com
 */
public class CloneTest implements Cloneable{
    Integer i = 0;
    Object a = new Object();
    static void main(){
        CloneTest obj = new CloneTest();
        try {
            //浅拷贝
            CloneTest objClone = (CloneTest) obj.clone();
            System.out.println("obj:"+obj);
            System.out.println("objClone:"+objClone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        main();
    }
}
