package org.kangspace.common.algorithms.dynamicprogramming.simple;

/**
 * <pre>
 * [难度: 简单]
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 *  示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 解题步骤:
 * 1. 定义状态
 *  假设dp[i] 为第n阶时可以爬到楼顶的方法数
 * 2. 定义状态转移方程
 *  因为每次可以爬 1 或 2 个台阶,所以存在dp[i] = dp[i-2] + dp[i-1] (i>2)
 * 3. 初始值或编码
 * dp[0] = 1 , dp[1] = 1 ;
 *  </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/13 16:49
 */
public class ClimbStair {
    public int climbStairs(int n) {
        if (n <= 1) {
            System.out.printf("不同的方法可以爬到%d楼顶数: %d\n" ,n, n);
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2]  + dp[i - 1];
        }
        System.out.printf("不同的方法可以爬到%d楼顶数: %d\n" ,n, dp[n]);
        return dp[n];
    }

    public static void main(String[] args) {
        new ClimbStair().climbStairs(1);
        new ClimbStair().climbStairs(2);
        new ClimbStair().climbStairs(3);
        new ClimbStair().climbStairs(4);
    }
}
