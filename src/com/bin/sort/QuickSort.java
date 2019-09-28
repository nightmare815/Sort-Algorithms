package com.bin.sort;

import java.util.Random;

public class QuickSort {

	//测试的随机数组的长度
	static final int length = 50000;
	
	public static void main(String[] args) {
		
		testPrint();
	}
	
	//测试quickSort对数组的10000个元素进行排序的时间耗费
	public static void testPrint() {
		Random random = new Random();
		int [] arr1 = new int[length];
		System.out.println("------------利用quickSort对随机生成长度为"+ length +"的整数数组进行排序--------------");
		for(int i = 0;i < length; i++) {
			arr1[i] = random.nextInt(100);
		}
		
		System.out.println("排序前:\t");
		for (int i : arr1) {
			System.out.print(i + ", ");
		}
		
		long start = System.currentTimeMillis();
		quickSort(arr1,0,length - 1);
		long end = System.currentTimeMillis();
		
		System.out.println("\n排序后:\t");
		for (int i : arr1) {
			System.out.print(i + ", ");
		}
		System.out.println("\n---------------------------");
		System.out.println("\n算法执行时间为:" + (end - start) + "毫秒..");
	}
	
	
	/**
	 * 
	 * @param A	待排序的数组
	 * @param i	待排序的起始下标
	 * @param j	待排序的结束下标
	 */
	public static void quickSort(int[] A, int i, int j) {
		
		if(i < j) {
			
			//一次partition可以确定一个元素在数组中的最终位置
			int pivot = partition(A, i, j);
			quickSort(A, i, pivot - 1);
			quickSort(A, pivot + 1, j);
		}
	}

	public static int partition(int[] A, int i, int j) {
		
		int x = A[j];
		int p = i - 1;	//p记录的是最后一个比待排元素小的元素下标
		int r = i;
		for(r = i;r < j;r++) {
			
			if(A[r] <= x) {
				//遇到比x小的就更新p,并把元素放到放到下标为p的位置
				++p;
				int temp = A[r];
				A[r] = A[p];
				A[p] = temp;
			}else {
				//如果比x大,就放到p+1位置
				int temp = A[r];
				A[r] = A[p + 1];
				A[p + 1] = temp;
			}
		}
		//最后把待排序元素A[r]放在下标为p的元素后面即p+1位置,则有下标为i~p的元素均比x小,p+2~r的元素均比x大
		int temp = A[j];
		A[j] = A[p + 1];
		A[p + 1] = temp;
		
		//返回此次partition已确定位置元素的下标
		return p + 1;
	}
}
