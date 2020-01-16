package org.kangspace.common.clazz;

/**
 * 2019/11/29 9:41
 *
 * @author kangxuefeng@etiantian.com
 */
public class ImplInterface implements Interface {
    @Override
    public void run1() {
        System.out.println("ImplInterface run1");
    }

    public static void main(String[] args) {
        new ImplInterface().run();
    }
}
