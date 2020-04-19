package org.kangspace.common.algorithms.datastructure.sort;

import java.util.Arrays;

/**
 * 快速排序(分治法)
 * 概述: 快速排序又是一种分治法的典型应用。本质上来看，快速排序应该算在冒泡排序的基础上的递归分治法
 *
 * 原理: 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 *
 * 算法步骤:
 * 1. 从数列中挑出一个元素，称为 "基准"（pivot）;
 * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 *
 * 稳定性: 不稳定算法,当相同数中有一个为基准数时，排序后相同数的相对位置会发生变化
 * 时间复杂度: O(n log n)
 * 最坏时间复杂度: O(n^2),
 * 速度最快
 * <p>
 * 实现方法:
 *
 *
 *
 */
public class QuickSort extends Sort {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    @Override
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void main() {
        System.out.println("排序算法: 快速排序");
        int[] arr = SortUtil.getArray(20);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new QuickSort().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        main();
    }


}
