package org.kangspace.common.algorithms.datastructure.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 概述: 通过构建有序序列，对于未排序数据，在已排序序列中向后往前扫描，找到相应的位置并插入。
 * 步骤: 将待排序序列的第一个元素看做一个有序序列，把第二个元素到最后一个元素看做未排序队列,
 *       从头到尾依次扫描待排序序列，将扫描到的每个元素插入有序序列的适当位置(当待插入的元素与有序序列的某个值相等时，则将待插入元素插入到相等元素后边)。
 * 时间复杂度:O(n^(1~2)) , 最小时间复杂度:O(n),最大时间复杂度O(n^2)
 * 优化: 折半插入: 扫描已排序序列时，使用折半法查找插入位置。
 * 稳定排序
 *
 * @author kango2gler@gmail.com
 */
public class InsertingSort extends Sort{

    /**
     * 插入排序,升序
     * 若需要降序, 将 &gt; key 改为 &lt; key
     * @param arr
     * @author kango2gler@gmail.com
     * @date 2018/6/8 17:09
     * @return
     */
    @Override
    public void sort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int nextVal = arr[i];
            int j = i-1;
            //目标值从已排序队列尾依次扫描,处理已排序队列值位置
            for (; j >=0 && nextVal < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            //将目标值插入到合适的位置
            arr[j + 1] = nextVal;
        }
    }

    /**
     * 折半插入排序
     * @param arr
     */
    public void binaryInsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int nextVal = arr[i];
            //折半查找扫描位置
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (high + low) / 2;
                if (arr[mid] > nextVal) {
                    high = mid - 1;
                }else{
                    low = mid +1 ;
                }
            }
            for (int j = i; j > high+1; j--) {
                arr[j] = arr[j - 1];
                SortUtil.printStep(arr,j,j-1);
            }
            arr[high + 1] = nextVal;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("排序算法: 插入排序" );
        int [] arr = SortUtil.getArray();
        System.out.println("排序前:" + Arrays.toString(arr) );
        SortUtil.timeCost(()->{
//            new InsertingSort().sort(arr);
            //折半插入
            new InsertingSort().binaryInsertSort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
