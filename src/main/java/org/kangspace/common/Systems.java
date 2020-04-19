package org.kangspace.common;

import javax.naming.Context;

/**
 * @author kango2gler@gmail.com
 * @desc Good Class
 * @date 2017/7/3 9:37
 */
public class Systems {
    static Context sHostContext;

    public static Context getContext(){
        return sHostContext;
    }
}
