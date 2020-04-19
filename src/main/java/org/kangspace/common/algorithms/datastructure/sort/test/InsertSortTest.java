package org.kangspace.common.algorithms.datastructure.sort.test;

import org.kangspace.common.algorithms.datastructure.sort.Sort;
import org.kangspace.common.algorithms.datastructure.sort.SortUtil;

import java.util.Arrays;

/**
 * 插入排序测试:
 * 将待排序队列,依次从已排序队列尾扫描，直到合适的位置
 * 稳定排序算法
 */
public class InsertSortTest extends Sort {
    @Override
    public void sort(int[] arr) {
        //遍历未排序数组,默认0位置元素为已排序数组
        for (int i = 1; i < arr.length; i++) {
            //已排序队尾索引
            int j = i-1;
            int nextVal = arr[i];
            //已排序队列: 依次从已排序队列尾扫描
            for (; j >=0 && nextVal < arr[j]; j--) {
                //大于当前待排序值的数据向后移动一位
                arr[j+1] = arr[j];
            }
            arr[j+1]  = nextVal;
        }
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

    static void main(){
        System.out.println("排序算法测试: 插入排序");
        int[] arr = SortUtil.getArray(20);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new InsertSortTest().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }
    public static void main(String[] args) {
        main();
    }


}
