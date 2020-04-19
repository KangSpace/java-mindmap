package org.kangspace.common.jdk18;

import java.util.Base64;

/**
 * 2019/12/12 11:27
 *
 * @author kango2gler@gmail.com
 */
public class Base64Test {
    public static void main(String[] args) {
        String str = "Hello";
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(encodeStr);
        System.out.println(new String(Base64.getDecoder().decode(encodeStr)));
        System.out.println("a:"+anInt());
    }

    public static int anInt(){
        try{
            if (true) {
                throw new Exception("aaaa");
            }
            return 1;
        }finally {
            return 2;
        }
    }
}
