package org.kangspace.common.algorithms.datastructure.sort;

/**
 * 2020/4/8 11:33
 *
 * @author kango2gler@gmail.com
 */
public abstract class Sort {
    /**
     * 排序实现接口
     * @param arr
     */
    public abstract void sort(int[] arr);
    /**
     * 顺序处理,交换数据位置
     * @param arr
     * @param left
     * @param right
     */
    public void swap(int[] arr, int left , int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
