package org.kangspace.common.algorithms.datastructure.sort.test;

import org.kangspace.common.algorithms.datastructure.sort.Sort;
import org.kangspace.common.algorithms.datastructure.sort.SortUtil;

import java.util.Arrays;

/**
 * 快速排序测试(分治法,递归)
 * 2020/4/15 9:37
 *
 * @author kango2gler@gmail.com
 */
public class QuickSortTest extends Sort {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序:
     * 不稳定排序, 当相同值的数中有1个为基准数时，排序后相对位置可能会变化
     *
     * @param arr   待排序数组
     * @param left  子序列起始索引
     * @param right 子序列结束索引
     */
    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            //获取基准值 pivot 索引,并将小于基准值的数排到左边，大于基准值的数排到右边
            int pivotIdx = partition(arr, left, right);
            //基准值左边子序列排序
            sort(arr,left,pivotIdx-1);
            //基准值右边子序列排序
            sort(arr,pivotIdx+1,right);
        }
    }

    /**
     * 获取基准值索引,并分割为2个子序列(分区)
     *
     * @return pivot index
     */
    public int partition(int arr[], int left, int right) {
        int pivot = arr[left];
        int pivotIdx = left+1;
        //排序,从序列,第二位开始与基准值比较,小于基准值,将最近得到的小数后一位数(即大于基准值的第一个数)与新的小数交换位置
        for (int i = pivotIdx; i <= right; i++) {
                if (arr[i] < pivot) {
                    if (i > pivotIdx) {
                        //做冒泡
                        swap(arr,i,pivotIdx);
                    }
                    pivotIdx++;
                }
//            SortUtil.printStep(arr,i,pivotIdx);
        }
        //
        pivotIdx = pivotIdx -  1;
        // 将基准值放到序列中间: 即将最后得到的小数与基准数所在的0数交换位置
        swap(arr,left,pivotIdx );
        return pivotIdx;
    }

    /**
     * 顺序处理,交换数据位置
     * @param arr
     * @param left
     * @param right
     */
    @Override
    public void swap(int[] arr, int left , int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    static void main() {
        System.out.println("排序算法测试: 快速排序");
        int[] arr = SortUtil.getArray(20);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new QuickSortTest().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        main();
    }


}
