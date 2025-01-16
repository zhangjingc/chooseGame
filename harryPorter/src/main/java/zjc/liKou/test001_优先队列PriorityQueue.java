package zjc.liKou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class test001_优先队列PriorityQueue {

    /**
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     * <p>
     * 一次操作中，你将执行：
     * <p>
     * 选择 nums 中最小的两个整数 x 和 y 。
     * 将 x 和 y 从 nums 中删除。
     * 将 min(x, y) * 2 + max(x, y) 添加到数组中的任意位置。
     * 注意，只有当 nums 至少包含两个元素时，你才可以执行以上操作。
     * <p>
     * 你需要使数组中的所有元素都大于或等于 k ，请你返回需要的 最少 操作次数。
     */


    /**
     * 知识点 —————— 优先队列 PriorityQueue
     * 优先队列（PriorityQueue）是一种用于按照优先级管理元素的数据结构，在Java中，它通过堆的方式实现(二叉堆和斐波那契堆)
     * Java中的PriorityQueue:
     *      PriorityQueue是无界的，但具有内部容量,初始容量为11，用于控制用于存储队列中元素的数组的大小。它会自动增加容量以适应添加的元素。
     *      这个类提供了时间复杂度为O(log(n))的入队和出队操作，如offer、poll、remove()和add。
     *      具有线性时间复杂度的remove(Object)和contains(Object)方法。
     *      具有恒定时间复杂度的检索方法，如peek、element和size。
     *      注意，PriorityQueue不是线程安全的，如果多个线程同时访问它，需要使用线程安全的PriorityBlockingQueue类。
     *
     * 添加元素：add(E e)、offer(E e)，两者是等效的
     * 获取头部元素：peek() 、 poll()。peek() 用于查看队列头部元素而不删除它，而 poll() 用于获取并删除队列头部元素。
     *
     */




    public static void main(String[] args) {
        // 文件路径
        int[] nums = {999999999, 999999999, 999999999};
        var i = minOperations(nums, 1000000000);
        var j = minOperations2(nums, 1000000000);
        System.out.println(i+" 第二结果："+j);
    }

    /**
     * 暴力硬解 ———— 大数据量时 超时
     * @param nums
     * @param k
     * @return
     */
    public static int minOperations(int[] nums, int k) {

        int bb =  0;
        // 将int数组转换为Integer数组
        Integer[] integerArray = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        // 将Integer数组转换为List
        List<Integer> list = new ArrayList<>(Arrays.asList(integerArray));
        while (true){
            int aa =  0;
            for (int d = 0; d < list.size(); d++){
                if(list.get(d)>= k){
                    aa ++ ;
                }
            }
            if(aa == list.size()){
                break;
            }
            if(aa < list.size()){
                bb ++;
                test001(list,k);
            }
        }
        return bb;
    }

    private static void test001(List<Integer> list, int k) {
        int a = findMin(list);
        list.remove(Integer.valueOf(a));
        int b = findMin(list);
        list.remove(Integer.valueOf(b));

        Long l = Long.valueOf(a) * 2 + Long.valueOf(b);
        if(l.compareTo(Long.valueOf(k))>0){
            list.add(k);
        }else{
            list.add(a*2+b);
        }
    }

    private static int findMin(List<Integer> list) {
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }
        return min;
    }





    /**
     * 优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    public static int minOperations2(int[] nums, int k) {
        int bb = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
        }
        while (true) {
            var peek = minHeap.peek();
            if (peek < k) {
                bb++;
                var poll = minHeap.poll();
                var poll2 = minHeap.poll();
                long result = (long) poll * 2 + poll2;
                if (result > k) {
                    minHeap.add(k);
                } else {
                    minHeap.add((int) result);
                }
            } else {
                break;
            }
        }
        return bb;
    }
}
