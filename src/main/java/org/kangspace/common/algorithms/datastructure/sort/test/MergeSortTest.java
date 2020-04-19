package org.kangspace.common.algorithms.datastructure.sort.test;

import org.kangspace.common.algorithms.datastructure.sort.Sort;
import org.kangspace.common.algorithms.datastructure.sort.SortUtil;

import java.util.Arrays;

/**
 * 归并排序测试(分治法,递归):
 * 利用分支法将待排序数组分割成若干个子序列，然后合并各子序列为有序序列
 * 1. 将待排序数组分割成2个无序序列
 * 2. 将2个无序序列合并并排序为有序序列
 * 3. 利用1,2 步骤递归将1，中的2个队列排序
 * 稳定排序算法
 */
public class MergeSortTest extends Sort {
    @Override
    public void sort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    /**
     * 将待排序队列arr分割为2个序列，并排序，最后合并子序列
     *
     * @param arr
     * @param left
     * @param right
     * @param temp  提前申请与待排序队列长度相同的数组，避免排序过程中的平凡申请内存空间
     */
    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左子序列排序
            mergeSort(arr, left, mid, temp);
            //右子序列排序
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并无需子序列为有序序列
     *<a href="https://www.cnblogs.com/chengxiao/p/6194356.html">https://www.cnblogs.com/chengxiao/p/6194356.html</a>
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左序指针
        int i = left,
        //右序指针
            j = mid + 1,
        // 临时数组指针
            t = 0;
        //1. 将右侧子序列与左侧子序列比较,并将排序结果放到temp中
        for (; i <= mid &&j<= right;) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }

        // 2. 将左子序列剩余数据填入temp
        while (i <= mid) {
            temp[t++] = arr[i++] ;
        }
        // 3. 将右子序列剩余数据填入temp
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        //4. 将temp 数据填充到arr中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    static void main() {
        System.out.println("排序算法测试: 归并排序");
        int[] arr = SortUtil.getArray(5);
        System.out.println("排序前:" + Arrays.toString(arr));
        SortUtil.timeCost(() -> {
            new MergeSortTest().sort(arr);
        });
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        main();
    }


}
