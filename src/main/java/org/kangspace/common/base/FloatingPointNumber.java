package org.kangspace.common.base;

import java.math.BigDecimal;

/**
 * <pre>
 * 浮点数
 * </pre>
 *
 * @author kangxuefeng@etiantian.com
 * @date 2020/12/9 17:42
 */
public class FloatingPointNumber {
    static void main(){
        System.out.println("Float.MIN:"+Float.MIN_VALUE);
        System.out.println("Float.MAX:"+Float.MAX_VALUE);
        System.out.println("Float.MAX to Long:"+ Float.valueOf(Float.MAX_VALUE).toString());
        System.out.println("Long.MAX:"+(Long.MAX_VALUE));
        float longFloat = (Long.MAX_VALUE) * 0.1f;
        System.out.println("Long.MAX*0.1:"+ longFloat);
        System.out.println("float(Long.MAX*0.1) to Long :"+ Float.valueOf(longFloat).longValue());
        Float f = new Float(9223372036854776000f);
        System.out.println(f);
        float longMaxf = Long.valueOf(0x7ffffffffffffffL).floatValue();
        System.out.println(longMaxf);
        System.out.println("FLoat.value 9.223372E18:"+new Float("9.223372E20"));
        System.out.println(Float.valueOf(longMaxf).longValue());

        System.out.println("0.0000000001:"+new Float("0.0000000001234"));

        BigDecimal longBig = new BigDecimal((Long.MAX_VALUE +Long.MAX_VALUE)+ "");
        System.out.println("longBig:"+longBig);
        float c = 123.123456789123f;
        float d = 1234567.1234567f;
        System.out.println("c:123.123456789123f:"+c);
        System.out.println("d:1234567.1234567f:"+d);
    }
    public static void main(String[] args) {
        main();
    }
}
