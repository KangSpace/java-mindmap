package org.kangspace.common.algorithms.datastructure.sort;

import java.util.Arrays;

/**
 * 选择排序:
 *
 * 原理: 第一次从待排序的数据元素中选出最小(或最大)的一个元素,存放到队列的起始位置，
 * 然后再从剩余的未排序的元素中寻找最小(或最大)的元素,然后放到已排序的末尾。以次类推，直到全部待排序的数据元素个数为0。
 *
 * 选择排序是不稳定的排序算法
 *
 * 时间复杂度O(n^2)
 */
public class SelectionSort extends Sort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }
    static void main(){
        System.out.println("排序算法: 选择排序" );
        int [] arr = SortUtil.getArray();
        System.out.println("排序前:" + Arrays.toString(arr) );
        SortUtil.timeCost(()->{
            new SelectionSort().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        main();
    }
}
