package org.kangspace.common.algorithms.datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort extends Sort{
    @Override
    public void sort(int[] arr) {
        int temp;
        int moveCnt = 0;
        boolean isFinished = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
//                    System.out.println(Arrays.toString(arr));
                    moveCnt++;
                    isFinished = true;
                }
            }
            if (!isFinished) {
                break;
            }
            isFinished = false;
        }
        System.out.println("移动次数:" + moveCnt);
    }


    static void main() {
        int arr[] = SortUtil.getArray();
        long startTime = System.currentTimeMillis();
        System.out.println("排序前:" + Arrays.toString(arr) + "," + startTime);
        new BubbleSort().sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("排序后:" + Arrays.toString(arr) + "," + endTime);
        System.out.println("cost:" + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        main();
    }


}
