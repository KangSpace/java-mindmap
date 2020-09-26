package org.kangspace.common.algorithms.dynamicprogramming.middle;

/**
 * <pre>
 * 最长回文子序列
 * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * 示例 1:
 *
 * 输入:
 * "bbab"
 * 输出:
 * 4
 * 一个可能的最长回文子序列为 "bab"。
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
 * 解题步骤
 * 分析:
 * 假设s(i...j)为最长回文子序列,则存在:
 * a. s(i)==s(j)
 * b. s(i+1..j-1) 存在 s(i+1) == s(j-1) j>=i+2
 *
 * 1. 定义状态
 *  假设p[i][j]表示为s[i..j]是否是回文子序列,若p[i][j]为回文子序列,则p[i][j] == p[i+1][j-1] 且s[i] == s[j]
 * 2. 定义状态转移方程
 *    若p[i][j] == p[i+1][j-1] 且s[i] == s[j],则为回文子串,记录开始下标及子序列长度
 * 3. 初始值(编写算法或代码)
 *    当数组只有1位时s[i]==s[j] = true为回文子序列
 *    当数组有2位时s[i]==s[j]为回文子序列
 *    循环序列:
 *     1. 先计算单个字符的子序列都为回文子序列(i,j 间隔为0)
 *     2. 计算相邻的字符组成的子序列是否是回文子序列(i,j 间隔为1)
 *     3. 计算i,j间隔为2的子序列是否为回文子序列,其中会用到1,2中的结果
 *     4. 计算i,j间隔为i的子序列是否为回文子序列，直至s[0],s[j]最长回文子序列
 *
 * </pre>
 * @author kango2gler@gmail.com
 * @date 2020/9/21 21:55
 */
public class LongestPalindromeSubseq {
    public String longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 0 || len > 1000) {
            return "";
        }
        char[] chars = s.toCharArray();
        int max = 0;
        boolean p[][] = new boolean[len][len];
        String result = "";
        for (int l = 0; l < len; l++) {
            for (int i = 0; i+l < len; i++) {
                int j = i + l;
                //若p[i][j] == p[i+1][j-1] 且s[i] == s[j],则为回文子串,记录开始下标及子序列长度
                if (i == j) {
                    p[i][j] = true;
                }else if(i+1 == j &&chars[i] == chars[j]){
                    p[i][j] = true;
                }else if(i+1 == j &&chars[i] != chars[j]){
                    p[i][j] = false;
                }else if (chars[i] == chars[j] && p[i+1][j-1]) {
                    p[i][j] = true;
                }else{
                    p[i][j] = false;
                }
                if (p[i][j]) {
                    max = Math.max(max,j - i + 1);
                    result = s.substring(i, j+1);
                }
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        String s = "bbbab";
        LongestPalindromeSubseq longestPalindromeSubseq= new LongestPalindromeSubseq();
        System.out.println(longestPalindromeSubseq.longestPalindromeSubseq(s));
        String s1 = "cbbd";
        System.out.println(longestPalindromeSubseq.longestPalindromeSubseq(s1));
    }
}
