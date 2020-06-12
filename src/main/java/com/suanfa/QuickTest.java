package com.suanfa;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @author yalongz
 * 
 *         它的基本思想是: 选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；\n\r其中一部分的所有数据都比另外一部分的所有数据都要小。然后，再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 */
public class QuickTest {
	static boolean	first	= false;

	public static void main(String[] args) {
		if (first) {
			int[] arr = { 5, 9, 7, 4, 5, 7, 6, 1, 9, 9, 7, 4 };
			System.out.println(Arrays.toString(arr));
			sort(arr);
			System.out.println(Arrays.toString(arr));
		} else {
			int i;
			int a[] = { 5, 9, 7, 4, 5, 7, 6, 1, 9, 9, 7, 4 };

			System.out.println("before sort:");
			System.out.println(Arrays.toString(a));

			quickSort(a, 0, a.length - 1);

			System.out.println("after  sort:");
			System.out.println(Arrays.toString(a));
		}

	}
	
	
	public static void sort(int[] a) {
		if (a.length > 0) {
			sort(a, 0, a.length - 1);
		}

	}

	public static void sort(int[] a, int low, int height) {
		System.out.println("low:"+low+"height:"+height);
		int i = low;
		int j = height;
		if (i > j) {//放在k之前，防止下标越界
			return;
		}
		System.out.println(low + "-------->" + height);
		System.out.println(Arrays.toString(a));
		int k = a[i];

		while (i < j) {
			while (i < j && a[j] > k) {// 从右向左找第一个小于x的数
				j--;
			}
			while (i < j && a[i] <= k) { //从左向右找第一个大于x的数
				i++;
			}
			if (i < j) {//交换
				int swap = a[i];
				a[i] = a[j];
				a[j] = swap;
			}

		}
		//交换K
		k = a[i];
		a[i] = a[low];
		a[low] = k;

		//对左边进行排序,递归算法
		sort(a, low, i - 1);
		//对右边进行排序
		sort(a, i + 1, height);
	}


	/*
	 * 快速排序
	 * 
	 * 参数说明: a -- 待排序的数组 l -- 数组的左边界(例如，从起始位置开始排序，则l=0) r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
	 */
	public static void quickSort(int[] a, int l, int r) {
		System.out.println("low:"+l+"->height:"+r);
		if (l < r) {
			int i, j, x;

			i = l;
			j = r;
			x = a[i];
			while (i < j) {
				while (i < j && a[j] > x)
					j--; // 从右向左找第一个小于x的数
				if (i < j)
					a[i++] = a[j];
				while (i < j && a[i] < x)
					i++; // 从左向右找第一个大于x的数
				if (i < j)
					a[j--] = a[i];
				System.out.println(Arrays.toString(a));
			}
			a[i] = x;
			System.out.println(Arrays.toString(a)+"=="+i);
			//quickSort(a, l, i - 1); /* 递归调用 */
			//quickSort(a, i + 1, r); /* 递归调用 */
		}
	}

}