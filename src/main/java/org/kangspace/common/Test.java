package org.kangspace.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 */
public class Test {
    static void main(){
        try {
            System.out.println(URLEncoder.encode("大连测试002","GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        main();
    }
}
