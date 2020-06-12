package com.suanfa;

import java.util.Arrays;

/**
 * @ClassName: ShellSort
 * @Description:
 * @author yalonz
 * @date 2020年4月8日 希尔排序实质上是一种分组插入方法。它的基本思想是: 对于n个待排序的数列，取一个小于n的整数gap(gap被称为步长)将待排序元素分成若干个组子序列，所有距离为gap的倍数的记录放在同一个组中；然后，对各组内的元素进行直接插入排序。 这一趟排序完成之后，每一个组的元素都是有序的。然后减小gap的值，并重复执行上述的分组和排序。重复这样的操作，当gap=1时，整个数列就是有序的。
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] a = { 20, 40, 30, 10, 60, 50 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(a));
		shellSort(a, a.length);
		System.out.println("排序后");
		System.out.println(Arrays.toString(a));
	}

	/**
	 * @Title: shellSort
	 * @Description: 当增量为1时，希尔排序退化成了直接插入排序，此时的时间复杂度为O(N²)，而Hibbard增量的希尔排序的时间复杂度为O(N3/2)
	 * @param a
	 * @param n
	 *            参数
	 * @return void 返回类型
	 */
	private static void shellSort(int[] a, int n) {
		// gap为步长，每次减为原来的一半。
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = 0; i < n; i++) {
				groupSort(a, n, i, gap);
			}
		}
	}

	public static void groupSort(int[] a, int n, int i, int gap) {
		//控制增量序列
		for (int j = i + gap; j < n; j += gap) {
			// 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
			int temp = a[j];
			int k = j - gap;
			while (k >= 0 && a[k] > temp) {
				a[k + gap] = a[k];
				k -= gap;
			}
			a[k + gap] = temp;
		}
	}
}
