package com.bin.subarray;

import java.util.Random;

/**
 * 需求:实现最大子数组的分治算法，将其实际运行效率与改进后的蛮力算法进行对比分析。
 * 
 * @author tianbin
 *
 */
public class FindMaximumSubarrayTest {
	//测试数组长度
	public static int length = 10000;
	
	public static void main(String[] args) {
		test();
	}

	public static void test() {
		int[] A = new int[length];
		Random random = new Random();
		for(int i = 0;i < length;i++) {
			//随机生成位于[-50,50]内的整数
			A[i] = random.nextInt(101) - 50;
		}
		
		System.out.println("\n数组A中的元素为 :");
		for (int i : A) {
			System.out.print(i + ", ");
		}
		System.out.println("\n-------------利用分治法求最大子数组--------------");
		long begin1 = System.currentTimeMillis();
		SubArray subarray1 = FindMaximumSubarray(A, 0, length - 1);
		long end1 = System.currentTimeMillis();
		System.out.println("\n最大子数组中的元素为: ");
		for(int i = subarray1.low;i <= subarray1.high;i++) {
			System.out.print(A[i] + ", ");
		}
		subarray1.print();
		System.out.println("FindMaximumSubarray算法运行时间: " + (end1 - begin1) + "毫秒");
		
		System.out.println("-------------利用朴素法求最大子数组--------------");
		long begin2 = System.currentTimeMillis();
		SubArray subarray2 = naiveFindMaximumSubarray(A, 0, length - 1);
		long end2 = System.currentTimeMillis();
		
		System.out.println("\n最大子数组中的元素为: ");
		for(int i = subarray2.low;i <= subarray2.high;i++) {
			System.out.print(A[i] + ", ");
		}
		subarray2.print();
		System.out.println("naiveFindMaximumSubarray算法运行时间: " + (end2 - begin2) + "毫秒");
	}
	
	/**
	 * 用来求最大子数组的主要实现方法
	 * @param A		数组A
	 * @param low	数组A的起始下标
	 * @param high	数组A的结束下标
	 * @return		最大的子数组
	 */
	public static SubArray FindMaximumSubarray(int[] A, int low, int high) {
		if(low == high) {
			 SubArray subArray = new SubArray(low, high, getSum(A,low,high));
			 return subArray;
		}else {
			int mid = (low + high) / 2;
			SubArray leftSubArray = FindMaximumSubarray(A, low, mid);
			SubArray rightSubArray = FindMaximumSubarray(A, mid + 1, high);
			SubArray crossSubArray = FindMaximumCrossingSubarray(A, low, mid, high);
			
			if(leftSubArray.sum >= rightSubArray.sum) {
				return leftSubArray.sum >= crossSubArray.sum ? leftSubArray : crossSubArray;
			}else {
				return rightSubArray.sum >= crossSubArray.sum ? rightSubArray : crossSubArray;
			}
		}
	}
	
	/**
	 * 用来求最大子数组的CrossingSubarray
	 * @param 		数组A
	 * @param low	数组A的起始下标
	 * @param mid	数组A的中位数下标
	 * @param high	数组A的结束下标
	 * @return		CrossingSubarray
	 */
	public static SubArray FindMaximumCrossingSubarray(int[] A, int low, int mid, int high) {
		int left_sum = Integer.MIN_VALUE;
		int right_sum = Integer.MIN_VALUE;
		int left_low = -1;
		int right_high = -1;
		int sum = 0;
		
		for(int i = mid; i >= low; i--) {
			sum += A[i];
			if(sum >= left_sum) {
				left_sum = sum;
				left_low = i;
			}
		}
		
		sum = 0;
		for(int i = mid + 1; i <= high; i++) {
			sum += A[i];
			if(sum >= right_sum) {
				right_sum = sum;
				right_high = i;
			}
		}
		
		return new SubArray(left_low, right_high, left_sum + right_sum);
	}
	
	/**
	 * 求最大子数组的朴素方法
	 * @param A
	 * @param low
	 * @param high
	 * @return
	 */
	public static SubArray naiveFindMaximumSubarray(int[] A, int low, int high) {
		int max_sum = -1;
		int max_low = -1;
		int max_high = -1;
		for(int i = low;i <= high;i++) {
			int sum = 0;
			for(int j = i;j <=high; j++) {
				sum += A[j];
				if(sum >= max_sum) {
					max_sum = sum;
					max_low = i;
					max_high = j;
				}
			}
		}
		return new SubArray(max_low, max_high, max_sum);
	}

	/**
	 * 
	 * @param 			A数组A
	 * @param low		数组A的起始下标
	 * @param high		数组A的结束下标
	 * @return			数组的所有元素和
	 */
	public static int getSum(int[] A, int low, int high) {
		int sum = 0;
		for(int i = low; i <= high; i++) {
			sum += A[i];
		}
		return sum;
	}
}
