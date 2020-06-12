package com.suanfa;

import java.util.Arrays;
import java.util.HashMap;
/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

 

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* @ClassName: NumsEqTarget
* @Description:
* @author yalonz
* @date 2020年5月21日
*
 */
public class NumsEqTarget {
	public static void main(String[] args) {
		int[] nums ={2,5,5,11};
		int target =10;
		Long start =System.currentTimeMillis();
		int[] tar =new Solution().twoSum(nums, target);
		long mid = System.currentTimeMillis();
		int[] tar1 =new Solution1().twoSum(nums, target);
		long end = System.currentTimeMillis();
		System.out.println(Arrays.toString(tar));
		System.out.println(Arrays.toString(tar1));
		
		System.out.println("tar: "+(mid-start));
		System.out.println("tar1: "+(end-mid));
	}

	static class Solution1 {
		public int[] twoSum(int[] nums, int target) {
			int len = nums.length;
			int[] god = new int[2];
			for (int i = 0; i < len; i++) {
				for (int j = i + 1; j < len; j++) {
					if (nums[i] + nums[j] == target) {
						god[0] = i;
						god[1] = j;
						return god;
					}
				}

			}
			return god;
		}
	}
	static class Solution {
	public int[] twoSum(int[] nums, int target) {
	        int[] indexs = new int[2];	        
	        // 建立k-v ，一一对应的哈希表
	        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
	        for(int i = 0; i < nums.length; i++){
	            if(hash.containsKey(nums[i])){
	                indexs[0] = i;
	                indexs[1] = hash.get(nums[i]);
	                return indexs;
	            }
	            // 将数据存入 key为补数 ，value为下标
	            hash.put(target-nums[i],i);
	        }
	        // // 双重循环 循环极限为(n^2-n)/2 
	        // for(int i = 0; i < nums.length; i++){
	        //     for(int j = nums.length - 1; j > i; j --){
	        //         if(nums[i]+nums[j] == target){
	        //            indexs[0] = i;
	        //            indexs[1] = j; 
	        //            return indexs;
	        //         }
	        //     }
	        // }
	        return indexs;
	    }
	}
}
