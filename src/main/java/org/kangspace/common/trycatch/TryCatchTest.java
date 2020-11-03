package org.kangspace.common.trycatch;

/**
 * <pre>
 * TryFinally异常测试
 * finally中存在return,会异常淹没(因为tryFinally会存在返回值栈,最终取finally中返回结果),反之不会
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/10/26 17:57
 */
public class TryCatchTest{
    static void main(){
        System.out.println(tryFinallyReturnTest());
        System.out.println(tryFinallyExceptionReturnTest());
        tryFinallyExceptionTest();
    }

    /**
     * 返回值栈测试(异常淹没)
     * @return
     */
    static Object tryFinallyReturnTest() {
        try {
            throw new RuntimeException("tryFinallyReturnTest Exception,1");
//            return new Object();
        } catch (Exception e) {
            throw e;
        }finally {
            return new Integer(1);
        }
//        return new Integer(2);
    }
    /**
     * 异常未淹没
     * @return
     */
    static void tryFinallyExceptionTest() {
        try{
            throw new RuntimeException("tryFinallyExceptionTest Exception");
        }finally {
            System.out.println("tryFinallyExceptionTest finally");
        }
    }
    /**
     * 异常淹没测试
     * @return
     */
    static Object tryFinallyExceptionReturnTest() {
        try{
            throw new RuntimeException("tryFinallyExceptionTest Exception");
        }finally {
            System.out.println("tryFinallyExceptionTest finally3");
            return new Integer(3);
        }
    }

    public static void main(String[] args) {
        main();
    }
}
