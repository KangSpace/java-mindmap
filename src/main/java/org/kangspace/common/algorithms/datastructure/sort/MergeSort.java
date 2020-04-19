package org.kangspace.common.algorithms.datastructure.sort;

import java.util.Arrays;

/**
 * 归并排序(分治法)
 * 概述: 建立在归并操作上的有效算法，该算法是采用分治法的典型应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合成一个有序表，成为二路归并。
 * <p>
 * 原理:
 * 1. 将序列每相邻的两个数字进行归并操作(merge),形成floor(n/2+n%2)个序列， 排序后每个序列包含2个元素，
 * 2. 将上诉的序列再次归并，形成floor(n/4)个序列，每个序列包含4个元素
 * 重复2步骤，直到所有排序完毕。
 *
 * 原理2:
 * 1. 将序列分为左右2序列， 分别对左右2序列做归并排序(递归)操作，然后合并左右2序列为完整有序序列
 *
 * 稳定性: 稳定算法
 * 时间复杂度: O(n log n)
 * 速度仅次于快速排序
 * <p>
 * 实现方法:
 * 1. 自上而下的递归
 * 2. 自下而上的迭代
 *
 * Arrays.sort() 为优化版的归并排序
 */
public class MergeSort extends Sort {
    @Override
    public void sort(int[] arr) {
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左边归并排序，使得左子序列有序
            sort(arr, left, mid, temp);
            //右边归并排序，使得右子序列有序
            sort(arr, mid + 1, right, temp);
            //将两个有序子数组合并操作
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        //临时数组指针
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        //将右序列剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    static void main() {
        System.out.println("排序算法: 归并排序");
        int[] arr = SortUtil.getArray(7);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new MergeSort().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        main();
    }


}
