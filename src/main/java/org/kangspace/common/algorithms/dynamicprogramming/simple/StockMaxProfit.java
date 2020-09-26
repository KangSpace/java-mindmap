package org.kangspace.common.algorithms.dynamicprogramming.simple;

import org.kangspace.common.algorithms.dynamicprogramming.DPUtil;

/**
 * <pre>
 * [难度: 简单]
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 解题步骤:
 * 1. 定义状态
 *  假设dp[i]为第i天最大利润
 * 2. 定义状态转移方程
 *  由于只完成一次买入,卖出,且不能再买入前卖出,定义buy为买入金额,buy=n[0]为初始值 dp[i] = max{n[i]-budy,dp[i-1]},若n[i]<buy时,将buy赋值为n[i]
 * 3. 初始值或编码
 *  dp[0] = dp[1] = 0;
 *
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/13 18:03
 */
public class StockMaxProfit {
    public int maxProfit(int[] n) {
        DPUtil.printArray(n);
        if(n.length<2){
            return 0;
        }
        int[] dp = new int[n.length];
        //初始化,第一天没有利润
        dp[0] = dp[1] = 0;
        int buy = n[0];
        for (int i = 1; i < n.length; i++) {
            dp[i] = Math.max(n[i]-buy,dp[i-1]);
            //切换更小买入项
            buy = Math.min(buy ,n[i]);
        }
        int max = dp[n.length - 1];
        System.out.printf("所能获取的最大利润为: %d\n",max);
        return max;
    }

    public static void main(String[] args) {
        int[] n =new int[]{7,1,5,3,6,4};
        int[] n2 =new int[]{7,6,4,3,1};
        //5
        new StockMaxProfit().maxProfit(n);
        //0
        new StockMaxProfit().maxProfit(n2);
    }
}
