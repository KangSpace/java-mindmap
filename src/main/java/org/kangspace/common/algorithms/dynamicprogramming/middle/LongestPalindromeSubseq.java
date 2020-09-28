package org.kangspace.common.algorithms.dynamicprogramming.middle;

/**
 * <pre>
 * 最长回文子序列
 * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * 示例 1:
 *
 * 示例 1:
 * 输入:
 * "bbbab"
 * 输出:
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 * 示例 2:
 * 输入:
 * "cbbd"
 * 输出:
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 *
 * 解题步骤 *
 * 1. 定义状态
 *  假设dp[i][j]表示为s[i,j]的最长回文子序列长度
 * 2. 定义状态转移方程
 * 若已知dp[i+1][j-1](s[i+1,j-1]的最长回文子序列长度)的值,求dp[i][j]的值有2种情况:
 * 1. 当s[i]==s[j]时s[i,j]一定存在最长回文子序列,且长度+2,即
 *   dp[i][j] = dp[i+1][j-1]+2
 * 2. 当s[i]!=s[j]时,则需要分别s[i],s[j]加入到s[i+1,j-1]序列中,去最大的回文子序列长度,即
 *   dp[i][j] = max{dp[i][j-1],dp[i+1][j]}
 * 3. 初始值(编写算法或代码)
 *    当数组只有1位时 i==j时为回文子序列,长度为1
 *    i<j,所以子序列长度为0
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/21 21:55
 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int dp[][] = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        //从下往上,从左往右计算最终dp[0][len-1]
//        for (int i = len-1; i >=0; i--) {
//            for (int j = i+1; j < len; j++) {
//                if (chars[i] == chars[j]) {
//                    dp[i][j] = dp[i + 1][j - 1] + 2;
//                } else {
//                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
//                }
//            }
//        }
        //从左往右,从下往上计算最终dp[0][len-1]
        for (int step = 1; step < len; step++) {
            for (int i = 0; i + step < len; i++) {
                int j = i + step;
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }


    public static void main(String[] args) {
        String s = "bbbab";
        LongestPalindromeSubseq longestPalindromeSubseq = new LongestPalindromeSubseq();
        System.out.println(s + ":" + longestPalindromeSubseq.longestPalindromeSubseq(s));
        String s1 = "cbbd";
        System.out.println(s1 + ":" + longestPalindromeSubseq.longestPalindromeSubseq(s1));
    }
}
