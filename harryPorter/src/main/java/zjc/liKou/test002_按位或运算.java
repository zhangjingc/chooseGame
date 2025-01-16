package zjc.liKou;

public class test002_按位或运算 {

    /**
     * 给你一个 非负 整数数组 nums 和一个整数 k 。
     * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
     * 请你返回 nums 中 最短特别非空
     * 子数组
     * 的长度，如果特别子数组不存在，那么返回 -1 。
     */


    /**
     * 知识点 —————— 按位或运算
     * 按位或运算的运算符 |
     * 运算规则：参与运算的数字，低位对齐，高位不足补零。如果两个操作数对应的二进制位只要有一个为1，那么结果就为1.对应二进制位都为0时，结果才为0.
     *
     * 扩展
     * 按位与运算 &
     *   运算规则：参与运算的数字，低位对齐，高位不足补零。如果两个操作数对应的二进制位都为1，那么结果才为1.对否则为0.
     * 按位异或运算 ^
     *   运算规则：参与运算的数字，低位对齐，高位不足补零。如果两个操作数对应的二进制位相同（同时为1或0），那么结果才为0.不相同则为1.
     * 按位取反运算 ~
     *   运算规则：用于对操作数的每一位执行取反操作。具体来说，对于每一位，如果该位是 1，则取反后变为 0；如果该位是 0，则取反后变为 1。
     */

    public static void main(String[] args) {
        int[] a = {1,2,3};
        System.out.println(minimumSubarrayLength(a,2));
    }

    public static int minimumSubarrayLength(int[] nums, int k) {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            var a = nums[i];
            for (int j = i; j < nums.length; j++) {
                a = a | nums[j];
                if (a >= k) {
                    ret = ret > (j - i + 1) ? (j - i + 1) : ret;
                    break;
                }
            }
        }
        return ret != Integer.MAX_VALUE ? ret : -1;
    }
}
