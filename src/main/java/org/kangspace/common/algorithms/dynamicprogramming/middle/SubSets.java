package org.kangspace.common.algorithms.dynamicprogramming.middle;

import java.util.*;

/**
 * <pre>
 *  子集问题
 *  给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 解题步骤:
 * 1. 定义状态
 *  假设dp[x]为第x位时,存在的不重复数组
 * 2. 定义状态转移方程
 *  dp[0] = []
 *  dp[1] = [],[1] = dp[0]+dp[0,1]
 *  dp[2] = [],[1].[2],[1,2] = dp[1]+ dp[0..1+2]
 *  dp[3] = [],[1].[2],[1,2],[3],[1,3].[2,3],[1,2,3] = dp[2] + dp[0..2+3]
 *  得出dp[x] = dp[x-1] + dp[0..x-1+x] x>0&&x=0 = dp[0] = [] ,0..x-1+x 表示为,0到x-1的数组都与x组成新数组
 * 3. 初始值(编写算法或代码)
 *  dp[0] = []
 *
 *  可将每次的结果都保存在一个数组里,最终只操作一个数组
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/20 18:27
 */
public class SubSets {
    public List<List<Integer>> subSets(int[] sets) {
        int size = sets.length;
        if (size < 1) {
            return new ArrayList<>();
        }
        List<List<Integer>> dp = new ArrayList<>(size);
        dp.add(0,new ArrayList<>());
        for (int i = 0; i < size; i++) {
            int seti = sets[i];
            //dp[x] = dp[x-1] + dp[0..x-1+x] x>0&&x=0 = dp[0] = [] ,0..x-1+x 表示为,0到x-1的数组都与x组成新数组
            List<List<Integer>> temp = new ArrayList<>();
            for (int j = 0; j < dp.size(); j++) {
                List<Integer> t2 = new ArrayList<>(dp.get(j).size()+1);
                t2.addAll(dp.get(j));
                t2.add(seti);
                temp.add(j, t2);
            }
            dp.addAll(temp);
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] sets = new int[]{1,2,3};
        SubSets subSets = new SubSets();
        System.out.println("subSets:"+subSets.subSets(sets));
        sets = new int[]{1,2,3,4};
        System.out.println("subSets:"+subSets.subSets(sets));
    }
}
