package org.kangspace.common.algorithms.datastructure.sort;

/**
 * 2020/4/7 17:20
 *
 * @author kangxuefeng@etiantian.com
 */
public class SortUtil {
    public static int[] getArray() {
        int size = 20;
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }

    public static void timeCost(TimeCostWrapper wrapper) {
        long initTime = System.currentTimeMillis();
        if (wrapper != null) {
            wrapper.run();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cost:" + (endTime - initTime) + " ms");
    }

    interface TimeCostWrapper{
        void run();
    }
}
