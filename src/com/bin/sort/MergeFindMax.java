package com.bin.sort;

import java.util.Random;

/**
 * 		需求: 	
 * 			设计一个分治算法在给定的无序整数数组，设计实现一个分治算法，
 * 			寻找输入数据中的最大值，实现该分治算法，分析算法的时间复杂度。
 * 
 * 		
 * 		时间复杂度分析:
 * 			T(n) = 2T(n/2) + n (n为每次比较的次数)
 * 			由主定理可知算法的时间复杂度为:	nlgn
 * 	
 * @author tianbin
 *
 */
public class MergeFindMax {
	
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		testPrint();
	}


	public static void testPrint() {
		int[] A = new int[10];
		Random rd = new Random();
		//随机生成10个元素的数组
		for(int i = 0;i < 10;i ++) {
			int nextInt = rd.nextInt(100);
			A[i] = nextInt;
		}
		
		System.out.println("随机生成的数组元素为:");
		for (int i : A) {
			System.out.print(i + ",");
		}
		
		System.out.println("\n");
		max = findMax(A, 0, 9);
		
		System.out.println("数组中的最大元素为:" + max);
	}
	
	/**
	 * 
	 * @param A	待排序的数组
	 * @param i	待排序的起始下标
	 * @param j	待排序的结束下标
	 * @return
	 */
	public static int findMax(int[] A, int i, int j) {
		//子数组元素个数大于一时循环
		if(i < j) {
			int k = (i + j) / 2;
			int key1 = findMax(A, i, k);
			int key2 = findMax(A, k + 1, j);
			//返回两路中的最大值
			return key1 >= key2 ? key1 : key2;
			
		}else {
			//如果子数组只有一个元素,自身就是最大.返回自身即可
			return A[i] >= A[j] ? A[i] : A[j];
		}
	}


}
