package org.kangspace.common.callstack;

/**
 * 2020/4/27 14:19
 *
 * @author kango2gler@gmail.com
 */
public class PrintCallStack {
    public static void print() {
        Throwable exception = new Throwable();
        exception.printStackTrace();
        new Throwable().printStackTrace();
        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        if (stackTraceElements != null) {
            for (StackTraceElement element : stackTraceElements) {
                System.out.println(element.getClassName() + "/t" +element.getFileName() + "/t" + element.getLineNumber()
                        + "/t" +element.getMethodName());
                System.out.println("-----------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        print();
    }
}
