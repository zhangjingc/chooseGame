package zjc.liKou;

import java.util.Arrays;

public class test003_贪心 {

    /**
     * 有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
     *
     * 每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
     * Alice 将会取走硬币数量最多的那一堆。
     * 你将会取走硬币数量第二多的那一堆。
     * Bob 将会取走最后一堆。
     * 重复这个过程，直到没有更多硬币。
     * 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
     *
     * 返回你可以获得的最大硬币数目。
     */


    /**
     * 贪心算法 ：是寻找 最优解问题 的常用方法。
     *
     * 基本步骤
     * 步骤1：从某个初始解出发；
     * 步骤2：把求解的问题分成若干个子问题；
     * 步骤3：对每一子问题求解，得到子问题的局部最优解；
     * 步骤4：把子问题的局部最优解合成原来问题的一个解。
     *
     * 优点：简单、高效，省去为了寻找最优解可能需要穷举的操作，通常作为其他算法的辅助算法来使用；
     * 缺点：不从整体上考虑其它可能情况，每次选取局部最优解，不再进行回溯处理，所以 并非一定能得到整体最优解。
     *
     * 获取最优解：动态规划
     */

    public static void main(String[] args) {
        int[] a = {9,8,7,6,5,1,2,3,4};
        System.out.println(maxCoins(a));
    }

    public static int maxCoins(int[] piles) {
        // 将int数组转换为Integer数组
        Arrays.sort(piles);
        var aa = 0;
        var n = piles.length/3; // 3
        for (int i = 0; i < n; i++) {
            aa += piles[piles.length-1-(i*2+1)];
        }
        return aa;
    }
}
