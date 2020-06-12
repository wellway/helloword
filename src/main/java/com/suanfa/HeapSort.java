package com.suanfa;

import java.util.Arrays;

/**
 * 堆一般指二叉堆。 复杂度：O(nlogn) - O(nlgn) - O(nlgn) - O(1)[平均 - 最好 - 最坏 - 空间复杂度] 大顶堆实现从小到大的升序排列，小顶堆一般用于构造优先队列
 * 
 * @ClassName: HeapSort
 * @Description:
 * @author yalonz
 * @date 2020年5月13日
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		int a[] = { 5, 9, 7, 4, 5, 7, 6, 1, 9, 9, 7, 4 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(a));
		new HeapSort().heapSort(a);
		System.out.println("排序后");
		System.out.println(Arrays.toString(a));

	}

	public void heapSort(int[] a) {
		if (null == a || a.length < 2) {
			return;
		}

		buildMaxHeap(a);

		for (int i = a.length - 1; i >= 0; i--) {
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;

			adjustHeap(a, i, 0);
		}
	}

	// 建堆
	private void buildMaxHeap(int[] a) {
		int mid = a.length / 2;
		for (int i = mid; i >= 0; i--) {
			adjustHeap(a, a.length, i);
		}
	}

	// 递归调整堆
	private void adjustHeap(int[] a, int size, int parent) {
		int left = 2 * parent + 1;
		int right = 2 * parent + 2;

		int largest = parent;
		if (left < size && a[left] > a[parent]) {
			largest = left;
		}

		if (right < size && a[right] > a[largest]) {
			largest = right;
		}

		if (parent != largest) {
			int temp = a[parent];
			a[parent] = a[largest];
			a[largest] = temp;
			adjustHeap(a, size, largest);
		}
	}
}
