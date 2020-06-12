package com.suanfa;

import java.util.Arrays;

/**
 * @ClassName: InsertSort
 * @Description:
 * @author yalonz
 * @date 2020年4月8日 直接插入排序的时间复杂度是O(N2)，直接插入排序是稳定的算法，它满足稳定算法的定义。
 */
public class InsertSort {
	public static void main(String[] args) {
		//	int a[] = { 5, 9, 7, 4, 5, 7, 6, 1, 9, 9, 7, 4 };
		int[] a = { 90, 20, 40, 30, 10, 60, 50 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(a));
		insertSort(a, a.length);
		System.out.println("排序后");
		System.out.println(Arrays.toString(a));
	}

	private static void insertSort(int[] a, int n) {
		if (null == a || a.length < 2)
			return;

		for (int i = 1; i < n; i++) {
			int j = i - 1;
			int temp = a[i];
			while (j >= 0 && a[j] > temp) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = temp;
		}
	}
}
