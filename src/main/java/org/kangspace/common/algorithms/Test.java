package org.kangspace.common.algorithms;

import org.apache.commons.lang.ArrayUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018/10/27 10:02
 *
 * @author kangxuefeng@etiantian.com
 */
public class Test {
    public static String findTargetIndex() {
        Integer[] intArr = new Integer[]{2, 7, 11, 15};
        Integer target = 9;
        for (Integer i = 0; i < intArr.length; i++) {
            Integer i2 = target - intArr[i];
            int i2Idx = ArrayUtils.indexOf(intArr, i2);
            if (i2Idx > -1) {
                return "[" + i + "," + i2Idx + "]";
            }
        }
        return null;
    }

    /**
     * 算法1: two sum: 给定一个整数数组，返回两个数字的索引，使它们相加到特定目标。您可以假设每个输入只有一个解决方案，并且您可能不会两次使用相同的元素。举例： Given nums = [2, 7, 11, 15], target = 9,
     *
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     * @return
     */
    public static String twoSum() {
        Integer[] intArr = new Integer[]{2, 7, 11, 15};
        Integer target = 9;
        for (Integer i = 0; i < intArr.length; i++) {
            for (Integer j = i+1; j < intArr.length; j++) {
                if ((target - intArr[i]) == intArr[j]) {
                    return "[" + i + "," + j + "]";
                }
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
//        String newString = new String(new byte[]{13,10});
//        System.out.println(newString);
//        System.out.println(twoSum());
//        fileCopy();
        mapTest();
    }

    public static void mapTest(){
        Map<String, String> map = new HashMap();
        map.put("SignatureVersion","1.0");
        map.put("DomainName","example.com");
        map.put("Timestamp","2016-03-24T16%3A41%3A54Z");
        map.put("Format","XML");
        map.put("AccessKeyId","testid");
        System.out.println(map);

        System.out.println("SignatureVersion".hashCode());
        System.out.println("DomainName".hashCode());
        System.out.println("Timestamp".hashCode());
        System.out.println("Format".hashCode());
        System.out.println("AccessKeyId".hashCode());
        System.out.println("a".hashCode());

        byte[] b = new byte[1024];
        Byte[] b1 = new Byte[1024];
        byte[][] b2 = new byte[1][1];
        byte b22[][] = new byte[2][2];
        byte[] b3 = { (byte)1,(byte)2,3 };
        byte[] b32 = new byte[]{ (byte)1,(byte)2,3 };

    }

    public static void fileCopy() throws IOException {
        String filePaht = "D:\\11\\11";
        java.io.File file = new java.io.File(filePaht);
        FileInputStream res = null;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            res = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (res != null)
                res.close();
            if (out != null)
                out.close();
        }


    }
}
