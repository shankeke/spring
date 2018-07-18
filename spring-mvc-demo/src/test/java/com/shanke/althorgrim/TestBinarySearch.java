package com.shanke.althorgrim;

/**
 * 1、必须采用顺序存储结果 2、关键字必须有序
 * 
 * @author hanrk-2734
 */
public class TestBinarySearch {

	public static int binarySearch(int a[], int goal) {
		int high = a.length - 1;
		int low = 0;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (a[middle] == goal) {
				return middle;
			} else if (a[middle] > goal) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] src = new int[] { 1, 5, 7, 8, 9, 3 };
		System.out.println(binarySearch(src, 3));
	}
}
