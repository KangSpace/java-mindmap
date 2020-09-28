/**
 * <pre>
 * 动态规划问题:
 * 动态规划(Dynamic Programming,DP)是运筹学的一个分支,是求解决策过程最优化的过程。
 * 动态规划算法通常用于求解具有某种最优性质的问题。
 * 动态规划概念:
 *      多阶段决策问题中,各个阶段采取的决策,一般来说是与时间有关的,决策依赖于当前的状态,又随即引起状态的转移,
 *   一个决策序列就是在变化的状态中产生出来的,固有"动态"的含义,称这种解决多阶段决策的最优化问题的方法称为动态规划方法。
 *   适用条件:
 *   1. 无后效性
 *      将各阶段按一定次序排列好后,对于某个给定的阶段状态,它以前各个阶段的状态无法直接影响它未来的决策,而只能通过当前的这个状态.
 *      换句话说,每个状态都是的过去历史的一个完整总结.这就是无后向性,又称无后效性.
 *   2. 最优化原理(最优子结构)
 *      一个最优化策略具有这样的性质,不论过去状态和决策如何,对前面的决策所形成的状态而言,余下的诸决策必须构成最优策略.
 *      简而言之,一个最优化策略的子策略总是最优的.一个问题满足最优化原理又称其具有最优化子结构性质.
 *   3. 子问题重叠性
 *       动态规划算法的关键在于解决冗余,这是动态规划算法的根本目的.
 *       动态规划实质上是一种以空间换时间的技术,它在实现的过程中,不得不存储产生过程中的各种状态，所以它的空间复杂度要大于其他的算法.
 *       选择动态规划算法是因为动态规划算法在空间上可以承受,而搜索算法在时间上却无法承受,所以我们舍空间而取时间.
 *
 *  动态规划解决问题步骤:
 *  (符合动态规划解决的问题:复杂问题可以被拆分成相似子问题,,
 *  且满足无后效性(已知当前阶段的状态,且之前各阶段的状态不会直接影响之后发展阶段的状态),最优子结构)
 *  1. 定义状态
 *  2. 定义状态转移方程
 *  3. 构建算法或编码(边界值初始化)
 *
 * 动态规划一般有2种解决问题思路
 * 1. 第一种思路是一个一维的 dp 数组:
 *     int n = array.length;
 *     int[] dp = new int[n];
 *     for (int i = 1; i < n; i++) {
 *        for (int j = 0; j < i; j++) {
 *          dp[i] = 最值(dp[i], dp[j] + ...)
 *        }
 *     }
 *    这种思路运用相对更多一些，尤其是涉及两个字符串/数组的子序列.
 *    如: 在子数组 array[0..i] 中，我们要求的子序列（最长递增子序列）的长度是 dp[i]。
 * 2. 第二种思路是一个二维 dp 数组
 *   int n = arr.length;
 *   int[][] dp = new dp[n][n];
 *   for (int i = 0; i < n; i++) {
 *     for (int j = 0; j < n; j++) {
 *         if (arr[i] == arr[j])
 *             dp[i][j] = dp[i][j] + ...
 *         else
 *             dp[i][j] = 最值(...)
 *       }
 *    }
 *   本思路中 dp 数组含义又分为「只涉及一个字符串」和「涉及两个字符串」两种情况。
 *   2.1 涉及两个字符串/数组时（比如最长公共子序列），dp 数组的含义如下：
 *        在子数组 arr1[0..i] 和子数组 arr2[0..j] 中，我们要求的子序列（最长公共子序列）长度为 dp[i][j]。
 *   2.2 只涉及一个字符串/数组时（比如最长回文子序列），dp 数组的含义如下：
 *       在子数组 array[i..j] 中，我们要求的子序列（最长回文子序列）的长度为 dp[i][j]。
 * @see <a href="https://labuladong.gitbook.io/algo/">LeetCode刷题套路</a>
 * </pre>
 */
package org.kangspace.common.algorithms.dynamicprogramming;