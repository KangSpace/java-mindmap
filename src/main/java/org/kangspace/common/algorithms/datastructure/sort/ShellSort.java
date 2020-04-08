package org.kangspace.common.algorithms.datastructure.sort;

import java.util.Arrays;

/**
 * 希尔排序(Shell's Sort)(递减增量排序:Diminishing Increment Sort):
 *  是插入排序的一种更高效的改进版本。
 *
 *  希尔排序是基于插入排序的2点性质改进的方法:
 *  1. 插入排序在对几乎已经排好序的数据操作时，效率高，即可达到线性排序的效率；
 *  2. 但插入排序一般来说是低效的，应为插入排序每次只能将数据移动一位
 *  希尔排序的基本思想:
 *     先将整个待排序序列分割成若干个子序列分别进行直接插入排序，待整个序列基本有序时，再对全体记录进行一次直接插入排序
 *
 *  希尔增量: 每次增量为原来的一半 即 gap = gap / 2
 *  稳定性: 不稳定
 *
 * 时间复杂度O(n^2)
 */
public class ShellSort extends Sort {

    @Override
    public void sort(int[] arr) {
        //希尔增量,最坏时间复杂度O(n^2)
        int gap = arr.length / 2;
        while (gap > 0) {
            //内部为插入排序
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            //逐步减小增量,直到1
            gap = gap / 2;
        }
    }

    static void main(){
        System.out.println("排序算法: 希尔排序" );
        int [] arr = SortUtil.getArray();
        System.out.println("排序前:" + Arrays.toString(arr) );
        SortUtil.timeCost(()->{
            new ShellSort().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        main();
    }
}
