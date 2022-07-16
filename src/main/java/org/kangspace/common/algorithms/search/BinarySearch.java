package org.kangspace.common.algorithms.search;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 折半查找
 * @author kango2gler@gmail.com
 * @since 2022/7/13
 */
@Slf4j
public class BinarySearch {

    public static void binarySearch(int[] arr, int target) {
        log.info("src:{}, target:{}", Arrays.toString(arr), target);
        int len = arr.length;
        int min = 0;
        int max = len - 1;
        int mid;
        int targetIdx = -1;
        while (min <= max) {
            mid = (min + max)/2 ;
            if (arr[mid] > target) {
                max = mid - 1;
            } else if (arr[mid] < target) {
                min = mid + 1;
            }else{
                targetIdx = mid;
                break;
            }
        }
        log.info("end: target index:{}", targetIdx);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int target = 5;
        binarySearch(arr,target);
        target = 1;
        binarySearch(arr,target);
        target = 111;
        binarySearch(arr,target);
    }
}
