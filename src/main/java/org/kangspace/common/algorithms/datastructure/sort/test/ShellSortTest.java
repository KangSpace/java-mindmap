package org.kangspace.common.algorithms.datastructure.sort.test;

import org.kangspace.common.algorithms.datastructure.sort.Sort;
import org.kangspace.common.algorithms.datastructure.sort.SortUtil;

import java.util.Arrays;

/**
 * 希尔排序测试:
 * 插入排序的高效版本,基于增量递减分组,做插入排序
 * 不稳定排序算法
 */
public class ShellSortTest extends Sort {
    @Override
    public void sort(int[] arr) {
        //获取希尔增量
        int gap = arr.length / 2;
        //做增量递减
        while (gap>0) {
            //使用增量,将待排序队列分成若干子序列,做插入排序
            for (int i = gap; i < arr.length; i++) {
                //当前值
                int val = arr[i];
                //已排序队列尾,
                int j = i - gap;
                //每组有两个元素, 做插入排序
                while (j >= 0 && arr[j] > val ) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j+gap] = val ;
            }
            gap = gap / 2;
        }
    }

    static void main(){
        System.out.println("排序算法测试: 希尔排序");
        int[] arr = SortUtil.getArray(5);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new ShellSortTest().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }
    public static void main(String[] args) {
        main();
    }


}
