package com.utilsdemo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * array中的索引值
 * 
 * @author yalongz
 *
 */
public class ArraySearchDemo {
	public static void main(String[] args) {

		// initializing unsorted short array
		Short shortArr[] = new Short[] { 5, 2, 15, 52, 10 };

		// use comparator as null, sorting as natural ordering
		Comparator<Short> comp = null;

		// sorting array
		Arrays.sort(shortArr, comp);

		// let us print all the elements available in list
		System.out.println("The sorted short array is:");
		for (short number : shortArr) {
			System.out.println("Number = " + number);
		}

		// entering the value to be searched
		short searchVal = 15;

		int retVal = Arrays.binarySearch(shortArr, searchVal, comp);
		System.out.println("The index of element 15 is : " + retVal);

	}
}
