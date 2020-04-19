package org.kangspace.common.algorithms.datastructure.sort.test;

import org.kangspace.common.algorithms.datastructure.sort.Sort;
import org.kangspace.common.algorithms.datastructure.sort.SortUtil;

import java.util.Arrays;

/**
 * 选择排序测试:
 * 取待排序队列中最小值，放到已排序队列的队尾。
 *
 * 不稳定排序算法
 *
 *
 */
public class SelectionSortTest extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            //最小值下标
            int min = i;
            //取待排序队列: 中的最小值
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j ;
                }
            }
            if(i != min) {
                swap(arr, i, min);
            }
            SortUtil.printStep(arr,i,min);
        }
    }


    static void main(){
        System.out.println("排序算法测试: 选择排序");
        int[] arr = SortUtil.getArray(5);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new SelectionSortTest().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }
    public static void main(String[] args) {
        main();
    }


}
