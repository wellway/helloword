package com.suanfa;

import java.util.Arrays;

/**
 * @ClassName: MaopaoSort
 * @Description:
 * @author yalonz
 * @date 2020年4月8日 它是一种较简单的排序算法。它会遍历若干次要排序的数列，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；如果前者比后者大，则交换它们的位置。这样，一次遍历之后，最大的元素就在数列的末尾！ 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。重复此操作，直到整个数列都有序为止！
 */
public class MaopaoSort {
	public static void main(String[] args) {
		int a[] = { 5, 9, 7, 4, 5, 7, 6, 1, 9, 9, 7, 4 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(a));
		MaopaoSort(a, a.length);
		System.out.println("排序后");
		System.out.println(Arrays.toString(a));
	}

	private static void MaopaoSort(int[] a, int n) {
		for (int i = n - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
}
