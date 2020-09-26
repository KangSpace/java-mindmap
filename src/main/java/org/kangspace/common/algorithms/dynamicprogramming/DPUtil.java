package org.kangspace.common.algorithms.dynamicprogramming;

/**
 * <pre>
 *
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/13 15:49
 */
public class DPUtil {
    public static void printArray(int[][] mn) {
        for (int[]m : mn) {
            printArray(m);
            System.out.println();
        }
    }
    public static void printArray(int[] m) {
        for (int n : m) {
            System.out.print(n+"\t");
        }
        System.out.println();
    }
}
