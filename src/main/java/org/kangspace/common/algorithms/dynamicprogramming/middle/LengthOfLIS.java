package org.kangspace.common.algorithms.dynamicprogramming.middle;

import org.kangspace.common.algorithms.dynamicprogramming.DPUtil;

/**
 * <pre>
 * 最长上升子序列问题
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 *  来源：力扣（LeetCode）
 *  链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 *
 * 解答步骤:
 * 1. 定义状态
 *  假设dp[x] 为以x结尾时,LIS的长度,则结果为max(dp[x])
 * 2. 定义状态转移方程
 *  假设比x小的每一个p,存在 x>p,则dp[x] = dp[p]+1 , 因为x大于p,就能构造一个以x结尾的上升子序列,
 *  则方程为 dp[x] = max{dp[p]} + 1 (list(p)<list(x))
 * 3. 初始值(编写算法或代码)
 *  dp[0..x] = 1
 *
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/23 23:05
 */
public class LengthOfLIS {
    /**
     * 时间复杂度O(n²)
     * @param list
     * @return
     */
    public int lengthOfLIS(int[] list) {
        int len = list.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len+1];
        for (int i = 0; i <len ; i++) {
            dp[i] = 1;
        }
        int maxLen = 1;
        for (int x = 0; x < len; x++) {
            for (int p = 0; p < x; p++) {
                //list[p] 为每个小余 list[x]的数
                if (list[p] < list[x]) {
                    dp[x] = Math.max(dp[x], dp[p] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[x]);
        }
        return maxLen;
    }

    /**
     * <pre>
     * 时间复杂度O(nlogn)
     * 解决方案:
     * 1. 将序列按值分成若干子序列,子序列的新值总是小于子序列最后一位,  若循环时,值小于所有子序列的最后一位(从左往右搜索,此处所有子序列的最后一位值是从小到大排列),则开始新序列
     *    最后的子序列数就是最长上升子序列长度,最终的最长上升子序列可能有多组.
     *    该问题类似扑克牌分牌,把牌分堆,每次选的牌都依次放入最近小余最后一张牌的牌堆里
     *  2.其中查找当前值放到哪个子序列可以使用二分法查找存放位置,使得时间复杂度可以为logn
     * </pre>
     * @param list
     * @return
     */
    public int lengthOfLIS2(int[] list) {
        int len = list.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        int lisLen = 0;
        for (int i = 0; i < len; i++) {
            int curr = list[i];
            //二分法查找值保存位置
            int start = 0, end = lisLen;
            while (start < end) {
                int mid = (end + start) / 2;
                if (dp[mid]>= curr ) {
                    end = mid;
                } if(dp[mid] < curr) {
                    start = mid + 1;
                }
            }
            if (start == lisLen) {
                lisLen++;
            }
            dp[start] = curr;
        }
        return lisLen;
    }


    public static void main(String[] args) {
        int[] list = {10, 9, 2, 5, 3, 7, 101, 18};
        DPUtil.printArray(list);
        LengthOfLIS lis = new LengthOfLIS();
        System.out.println(lis.lengthOfLIS(list));
        System.out.println("method2:"+lis.lengthOfLIS2(list));
        list = new int[]{0};
        System.out.println(lis.lengthOfLIS(list));
        System.out.println("method2:"+lis.lengthOfLIS2(list));
        list = new int[]{-2,-1};
        System.out.println(lis.lengthOfLIS(list));
        System.out.println("method2:"+lis.lengthOfLIS2(list));
    }
}
