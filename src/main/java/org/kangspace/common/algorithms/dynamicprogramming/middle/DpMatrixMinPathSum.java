package org.kangspace.common.algorithms.dynamicprogramming.middle;

import org.kangspace.common.algorithms.dynamicprogramming.DPUtil;

/**
 * <pre>
 * [难度:中等]
 * 利用动态规划解决最短路径和问题:
 *     动态规划算法通常用于求解具有某种最优性质的问题
 *  动态规划概念:
 *     多阶段决策问题中,各个阶段采取的决策,一般来说是与时间有关的,决策依赖于当前的状态,又随即引起状态的转移,
 *  一个决策序列就是在变化的状态中产生出来的,固有"动态"的含义,称这种解决多阶段决策的最优化问题的方法称为动态规划方法。
 *  适用条件:
 *  1. 无后效性
 *     将各阶段按一定次序排列好后,对于某个给定的阶段状态,它以前各个阶段的状态无法直接影响它未来的决策,而只能通过当前的这个状态.
 *     换句话说,每个状态都是的过去历史的一个完整总结.这就是无后向性,又称无后效性.
 *  2. 最优化原理(最优子结构)
 *     一个最优化策略具有这样的性质,不论过去状态和决策如何,对前面的决策所形成的状态而言,余下的诸决策必须构成最优策略.
 *     简而言之,一个最优化策略的子策略总是最优的.一个问题满足最优化原理又称其具有最优化子结构性质.
 *  3. 子问题重叠性
 *      动态规划算法的关键在于解决冗余,这是动态规划算法的根本目的.
 *      动态规划实质上是一种以空间换时间的技术,它在实现的过程中,不得不存储产生过程中的各种状态，所以它的空间复杂度要大于其他的算法.
 *      选择动态规划算法是因为动态规划算法在空间上可以承受,而搜索算法在时间上却无法承受,所以我们舍空间而取时间.
 *
 * 动态规划解决问题步骤:
 * (符合动态规划解决的问题:复杂问题可以被拆分成相似子问题,,
 * 且满足无后效性(已知当前阶段的状态,且之前各阶段的状态不会直接影响之后发展阶段的状态),最优子结构)
 *
 * 1. 定义状态
 * 2. 定义状态转移方程
 * 3. 构建算法或编码(边界值初始化)
 * 问题:
 *     给定一个矩阵(m,n), 从左上角开始每次只能向右或者向下走,最后到达右下角的位置,路径上所有的数子累加起来就是路径和,返回所有的路径中最小的路径和.
 * 给定（m,n）如下：
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 * 解法1:
 * 使用一个二维的DP矩阵来求解:
 * 对于DP,第一行和第一列只有一种走法,就是从左到右或从上到下的累加,可以先初始化,然后其他元素可以用转移方程填充,直到矩阵填充完成;
 * 状态转移方程: dp[i][j] = min{dp[i-1][j],dp[i][j-1]} + mn[i][j] (i>1,j>1)
 *
 * 解法2(优化解法):
 * 如果使用二维数组,对于m行n列的的数组,空间复杂度是O(m*n).
 * 动态规划中常用的优化方法之一就是仅使用一个一纬数组在进行迭代,但空间压缩也有局限性,但不能记录最后结果的路径.
 * 如果需要完整路径还需要二维动态规划表
 *
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/9/9 13:39
 */
public class DpMatrixMinPathSum {
    /**
     * 记录操作步骤及步骤值
     */
    static class Step {
        public Step(int val, String desc) {
            this.desc = desc;
            this.val = val;
        }

        public Step(String desc) {
            this.desc = desc;
        }

        private String desc;
        private int val;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    /**
     * <pre>
     * 二维数组实现
     * 1. 定义状态
     * 定义dp[i][j]为最终最短路径和
     * 2. 定义状态转移方程
     * 由于只能向右或向下走, 则存在d[i][j]的和可能为:
     * a. d[i-1][j]+mn[i][j] 向右到终点
     * b. d[i][j-1]+mn[i][j] 向下到终点
     * 由于求最短路径,所以d[i][j] = min{d[i-1][j],d[i][j-1]}+mn[i][j]
     * 3. 初始值
     * 由于第0行和第0列不满足d[i-1],d[j-1]情况,所以,需要先初始化d[0][0..j],d[0..i][j]的值
     * 最终方程为: d[i][j] = min{d[i-1][j],d[i][j-1]}+mn[i][j] (i>1,j>1)
     *
     * </pre>
     *
     * @param mn
     */
    public static void matrixMinPathSum2Dimension(int[][] mn) {
        DPUtil.printArray(mn);
        if (mn.length < 1 && mn[0].length < 1) {
            return;
        }
        System.out.println("二维数组动态规划方法解:");
        int m = mn.length;
        int n = mn[0].length;
        Step[][] dp = new Step[m][n];
        //初始化边界值
        //起点
        dp[0][0] = new Step(mn[0][0], "mn[0][0]:" + String.valueOf(mn[0][0]));
        for (int i = 1; i < m; i++) {
//            dp[i][0] = dp[i - 1][0] + mn[i][0];
            int val = dp[i - 1][0].getVal() + mn[i][0];
            dp[i][0] = new Step(val, dp[i - 1][0].getDesc() + " mn[" + i + "][0]:" + String.valueOf(mn[i][0]));
        }
        for (int j = 1; j < n; j++) {
//            dp[0][j] = dp[0][j - 1] + mn[0][j];
            int val = dp[0][j - 1].getVal() + mn[0][j];
            dp[0][j] = new Step(val, dp[0][j - 1].getDesc() + " mn[0][" + j + "]:" + String.valueOf(mn[0][j]));
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
//                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + mn[i][j];
                Step minStep = dp[i - 1][j].getVal() - dp[i][j - 1].getVal() > 0 ? dp[i][j - 1] : dp[i - 1][j];
                int val = minStep.getVal() + mn[i][j];
                dp[i][j] = new Step(val, minStep.getDesc() + " mn[" + i + "][" + j + "]:" + String.valueOf(mn[i][j]));
            }
        }
        System.out.println("最小路径和为：" + dp[m - 1][n - 1].getVal() + ",路径为: " + dp[m - 1][n - 1].getDesc());
    }

    /**
     * <pre>
     * 一维数组实现
     * 优化版,降低空间复杂度,但无法获取结果路径,只能获取到最终和
     * 分析:
     * 二维数组实现时,每次只需要用到dp[i-1][j]和dp[j-1][i]其他不使用
     * 如果用二维数组,对于m行n列的数组,空间复杂度就是O(m*n).动态规划中常用的优化方法之一就是仅使用一个一维数组在进行这个迭代过程
     * 解法1中使用dp数组的空间大小为M*N，可以对dp数组的空间压缩至N，
     * 定义大小为N的dp数组，
     * 对于第一行，dp[i]=dp[i-1]+m[0][i],在求第二行中的 dp[i] 时可以覆盖第一行 dp[i] ,
     * 第二行dp[i]=Math.min（dp[i],dp[i-1]）+m[i][j]。
     * 逐行计算,最终得出dp[m-1]值
     * </pre>
     *
     * @param mn
     */
    public static void matrixMinPathSum1Dimension(int[][] mn) {
        DPUtil.printArray(mn);
        if (mn.length < 1 && mn[0].length < 1) {
            return;
        }
        System.out.println("一维数组动态规划方法解:");
        int m = mn.length;
        int n = mn[0].length;
        int[] dp = new int[n];
        dp[0] = mn[0][0];
        //初始化第一行
        for (int i = 1; i <n ; i++) {
            dp[i] = dp[i - 1] + mn[0][i];
        }
        for (int i = 1; i <m ; i++) {
            dp[0] = dp[0] + mn[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + mn[i][j];
            }
        }
        System.out.println("最小路径和为：" + dp[n - 1]);
    }

    public static void main(String[] args) {
        int[][] mn = new int[][]{
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0},
        };
        matrixMinPathSum2Dimension(mn);
        matrixMinPathSum1Dimension(mn);
        mn = new int[][]{{1, 2, 5}, {3, 2, 1}};
        matrixMinPathSum2Dimension(mn);
        matrixMinPathSum1Dimension(mn);
    }

}
