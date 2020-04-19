package org.kangspace.common.algorithms.datastructure.sort;

/**
 * 2020/4/7 17:20
 *
 * @author kango2gler@gmail.com
 */
public class SortUtil {
    public static int[] getArray() {
        int size = 20;
        return getArray(size);
    }
    public static int[] getArray(int len) {
        int size = len;
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }

    public static void timeCost(TimeCostWrapper wrapper) {
        long initTime = System.nanoTime();
        if (wrapper != null) {
            wrapper.run();
        }
        long endTime = System.nanoTime();
        System.out.println("cost:" + (endTime - initTime) + " 1/1000000 ms");
    }

    /**
     * Print the sigle step , mark switch index : idx1, idx2
     * @param arr
     * @param idx1
     * @param idx2
     */
    public static void printStep(int[] arr, int idx1,int idx2) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            boolean needMark = i == idx1 || i == idx2;
            if (needMark) {
                sb.append("{");
            }
            sb.append(arr[i]);
            if (needMark) {
                sb.append("}");
            }
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
    public interface TimeCostWrapper{
        void run();
    }
}
