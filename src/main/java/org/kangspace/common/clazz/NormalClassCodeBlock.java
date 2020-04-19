package org.kangspace.common.clazz;

/**
 * 2019/11/21 11:49
 *
 * @author kango2gler@gmail.com
 */
public class NormalClassCodeBlock {
    int i = 0;
    static int si = 0;

    public NormalClassCodeBlock() {
        System.out.println("defalut construct method");
    }

    {
        i++;
        System.out.println(i);

    }

    static {
        System.out.println("si:"+si);
    }

    public static void main(String[] args) {
        System.out.println(NormalClassCodeBlock.si);
//        System.out.println(new NormalClassCodeBlock());
    }
}
