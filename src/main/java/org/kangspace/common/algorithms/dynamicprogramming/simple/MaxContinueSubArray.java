package org.kangspace.common.algorithms.dynamicprogramming.simple;

import org.kangspace.common.algorithms.dynamicprogramming.DPUtil;

/**
 * <pre>
 * [难度: 简单]
 * 最大连续子数组
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 *
 * 解题步骤:
 * 使用动态规划解题(因为题目满足最优解,最优子结构和无后效性)
 * 1.定义状态
 *  定义dp[i]为到第i位时,最大连续子数组的和
 * 2.定义状态转移方程
 *  由dp[i]定义得出dp[i]=max(dp[i-1]+nums[i],nums[i]) 其中 i>0 ,且d[i-1] 和 d[i]取其中最大值,且需与最大值max比较,并保存max
 * 3.初始值及编码
 *  dp[0] = mn[0]
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/13 15:37
 */
public class MaxContinueSubArray {

    public void maxSubArry(int[] nums){
        DPUtil.printArray(nums);
        int len = nums.length;
        int[] dp = new int[len];
        //初始值
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1]+nums[i], nums[i]) ;
            max = Math.max(dp[i], max);
        }
        System.out.println("子序列最大值为: "+ max);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        new MaxContinueSubArray().maxSubArry(nums);
    }
}
