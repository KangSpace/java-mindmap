package org.kangspace.common.algorithms;

import org.apache.commons.lang.ArrayUtils;

/**
 * 插入排序
 * 2018/6/8 17:07
 *
 * @author kangxuefeng@etiantian.com
 */
public class InsertingSort {

    /**
     * 插入排序,升序
     * 若需要降序, 将 &gt; key 改为 &lt; key
     * @param arr
     * @author kangxuefeng@etiantian.com
     * @date 2018/6/8 17:09
     * @return
     */
    public static void sort(int[] arr){
        for(int j = 1;j < arr.length; j++){
            int key = arr[j];
            int i = j - 1;
            while (i>=0 && arr[i] > key){
                arr[i+1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,1,3,6,2,4};
        sort(arr);
        System.out.println(ArrayUtils.toString(arr));
    }
}
