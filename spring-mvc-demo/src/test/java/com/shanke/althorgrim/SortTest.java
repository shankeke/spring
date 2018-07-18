package com.shanke.althorgrim;

/**
 * 描述 :常用的排序方法. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2017-2-8 下午4:46:35
 * @version 1.0
 */
public class SortTest {

	/**
	 * 1）冒泡排序： 依次比较相邻的两个元素，通过一次比较把未排序序列中最大（或最小）的元素放置在未排序序列的末尾。
	 */
	public static void bubbleSort(int data[]) {
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 2）选择排序： 每一次从待排序的数据元素中选出最小（或最大）的一个元素，顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完。
	 */
	public static void selectionSort(int data[]) {
		int minVal;
		int minIndex;
		for (int i = 0; i < data.length - 1; i++) {
			minVal = data[i];
			minIndex = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < minVal) {
					minVal = data[j];
					minIndex = j;
				}
			}
			if (minVal != data[i] && minIndex != i) {
				data[minIndex] = data[i];
				data[i] = minVal;
			}
		}

	}

	/**
	 * 3）插入排序： 将数列分为有序和无序两个部分，每次处理就是将无序数列的第一个元素与有序数列的元素从后往前逐个进行比较，找出插入位置，
	 * 将该元素插入到有序数列的合适位置中。
	 */
	public static void insertionSort(int data[]) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0; j--) {
				if (data[j] < data[j - 1]) {
					int temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				}
			}
		}
	}

	/**
	 * 4）归并排序：
	 * 将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
	 * 排序过程如下： （1）申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
	 * （2）设定两个指针，最初位置分别为两个已经排序序列的起始位置 （3）比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
	 * （4）重复步骤3直到某一指针达到序列尾 （5）将另一序列剩下的所有元素直接复制到合并序列尾
	 */
	public static void mergeSort(int data[], int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(data, start, mid);
			mergeSort(data, mid + 1, end);
			merge(data, start, mid, end);
		}
	}

	public static void merge(int data[], int start, int mid, int end) {
		int temp[] = new int[end - start + 1];
		int i = start;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= end) {
			if (data[i] < data[j]) {
				temp[k++] = data[i++];
			} else {
				temp[k++] = data[j++];
			}
		}

		while (i <= mid) {
			temp[k++] = data[i++];
		}
		while (j <= end) {
			temp[k++] = data[j++];
		}

		for (k = 0, i = start; k < temp.length; k++, i++) {
			data[i] = temp[k];
		}
	}

	/**
	 * 5）快速排序：
	 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都小，然后再按此方法对这两部分数据分别进行快速排序
	 * ，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
	 */
	public static void quickSort(int data[], int start, int end) {
		if (end - start <= 0) {
			return;
		}
		int last = start;
		for (int i = start + 1; i <= end; i++) {
			if (data[i] < data[start]) {
				int temp = data[++last];
				data[last] = data[i];
				data[i] = temp;
			}
		}
		int temp = data[last];
		data[last] = data[start];
		data[start] = temp;
		quickSort(data, start, last - 1);
		quickSort(data, last + 1, end);
	}
}
