package org.kangspace.common.reflex;

import java.lang.reflect.Constructor;

/**
 * 2019/12/19 10:45
 *
 * @author kangxuefeng@etiantian.com
 */
public class TestEnumObjReflex {
    public static void main(String[] args) {
        try {
            Class enumObjClass  =EnumObj.class;
            System.out.println(enumObjClass.getEnumConstants());
            Constructor c = enumObjClass.getDeclaredConstructors()[0];
            c.setAccessible(true);
            System.out.println(c.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
