package com.suanfa;

import java.util.Arrays;

/**
 * @ClassName: GracefulSubarray
 * 
 *             给你一个整数数组 nums 和一个整数 k。
 * 
 *             如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 
 *             请返回这个数组中「优美子数组」的数目。
 * 
 *              
 * 
 *             示例 1：
 * 
 *             输入：nums = [1,1,2,1,1], k = 3 输出：2 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。 示例 2：
 * 
 *             输入：nums = [2,4,6], k = 1 输出：0 解释：数列中不包含任何奇数，所以不存在优美子数组。 示例 3：
 * 
 *             输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2 输出：16
 * 
 *             来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @Description:
 * @author yalonz
 * @date 2020年5月27日
 *
 */
public class GracefulSubarray {
	public static void main(String[] args) {
		int[] arr = { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
		int k = 2;
		System.out.println(subarray(arr, k));
	}

	private static int subarray(int[] arr, int k) {
		int len = arr.length;
		if (arr == null || len == 0 || len < k)
			return 0;
		int[] nums = new int[len + 2];
		int odds = 0;
		for (int i = 0; i < len; i++) {
			if ((arr[i] & 1) == 1)
				nums[++odds] = i;
		}
		System.out.println(odds);
		//左边界
		nums[0] = -1;
		//右边界
		nums[odds+1] = len;
		int total = 0;
		// arr[i]是窗口左边界
		// arr[i+k-1] 是窗口右边界
		// arr[i-1]是左边的上一个奇数，在此之后到arr[i]都可选
		// arr[i+k]是右边的下一个奇数，在此之前都arr[i+k-1]都可选
		//前面可选部分长度为arr[i]-arr[i-1]
		//后面可选部分长度为arr[i+k]-arr[i+k-1]
		//总的可能数等于前后可选的组合
		System.out.println(Arrays.toString(nums)+";count:"+odds);
		for (int i = 1; i + k < odds + 2; i++) {
			total += (nums[i] - nums[i - 1]) * (nums[i + k] - nums[i + k - 1]);
		}
		return total;
	}
}
