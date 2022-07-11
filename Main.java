package 双指针.子序列和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/*
* 给你一个整数数组 nums 和一个目标值 goal 。
你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal) 。
返回 abs(sum - goal) 可能的 最小值 。
注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
来源：力扣（LeetCode）
*/
public class Main {
    public static void main(String[] args) {
        int[] nums = {7,-9,15,-2};
        Solution solution = new Solution();
        int a = solution.minAbsDifference(nums,-5);
        System.out.println(a);
    }
}

/*
* 思路：
* 将原数组分成两个数组，并分别切除每个数组的所有子集的数组和，分别保存，然后用双指针将两边数组的和相加与目标值对比
* */

class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int half = nums.length>>1;
        int[] sums1 = sumArr(nums, 0, half);
        int[] sums2 = sumArr(nums, half + 1, nums.length - 1);

        int minAbs=Integer.MAX_VALUE, i = 0, j = sums2.length - 1;
        while(i < sums1.length && j >= 0) {
            int sum = sums1[i] + sums2[j];
            minAbs = Math.min(minAbs, Math.abs(sum - goal));
            if (minAbs == 0) return 0;
            if (sum > goal) j --;
            else i ++;
        }
        return minAbs;
    }

    public int[] sumArr(int[] nums, int start, int end) {
        int[] arrays = new int[1<<(end-start+1)];
        for (int i = start; i <= end; i++) {
            int offset = 1 << (i-start);
            for (int j = 0; j < offset; j++) {
                arrays[j + offset] = arrays[j] + nums[i];
            }
        }
        Arrays.sort(arrays);
        return arrays;
    }
}