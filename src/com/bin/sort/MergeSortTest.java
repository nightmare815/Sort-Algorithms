package com.bin.sort;

import java.util.Random;


/**
 * 基于分治策略的二路归并算法
 * @author tianbin
 *
 */
public class MergeSortTest {
	
	//测试的随机数组的长度
	static final int length = 50000;
	
	public static void main(String[] args) {
		testPrint();
	}

	//测试mergeSort对数组的10000个元素进行排序的时间耗费
	public static void testPrint() {
		Random random = new Random();
		int [] arr1 = new int[length];
		System.out.println("------------利用mergeSort对随机生成长度为"+ length +"的整数数组进行排序--------------");
		for(int i = 0;i < length; i++) {
			arr1[i] = random.nextInt(100);
		}
		
		System.out.println("排序前:\t");
		for (int i : arr1) {
			System.out.print(i + ", ");
		}
		
		long start = System.currentTimeMillis();
		mergeSort(arr1,1,length);
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
	 * @param A 整型数组
	 * @param i	待排序数组的开始位置(从1开始)
	 * @param j	待排序数组的结束位置(从1开始)
	 */
	public static void mergeSort(int[] A, int i, int j) {
		if(i < j) {
			int k = (i + j) / 2;
			mergeSort(A, i, k);
			mergeSort(A, k + 1, j);
			merge(A, i, k, j);
			//merge2(A, i, k, j);
		}
	}

	//不使用哨兵, 以数组的长度作为结束标志
	public static void merge(int[] A, int i, int k, int j) {
		int len1 = k - i + 1;
		int len2 = j - k;
		int m = 0,n = 0;
		int [] L1 = new int[len1];
		int [] L2 = new int[len2];
		
		//将待排序子数组复制到L1和L2中
		while(m < len1) {
			L1[m] = A[i + m - 1];
			m++;
		}
		while(n < len2) {
			L2[n] = A[k + n];
			n++;
		}
		
		m = 0;
		n = 0;
		
		//当两个数组都仍有元素未排序时循环
		while(m < len1 && n < len2) {
			//等号保证稳定性
			if(L1[m] <= L2[n]) {
				A[i - 1] = L1[m];
				m++;
				i++;
			}else {
				A[i - 1] = L2[n];
				n++;
				i++;
			}
		}
		
		//若其中一个数组的元素先排完,将另一个未排完的数组中的元素直接复制到原数组中
		if(m >= len1) {
			while(n < len2) {
				A[i - 1] = L2[n];
				n++;
				i++;
			}
		}
		if(n >= len2) {
			while(m < len1) {
				A[i - 1] = L1[m];
				m++;
				i++;
			}
		}
	}
	
	//使用Integer.MAX_VALUE作为哨兵的merge算法
	public static void merge2(int[] A, int i, int k, int j) {
		int len1 = k - i + 1;
		int len2 = j - k;
		int m = 0,n = 0;
		int [] L1 = new int[len1 + 1];
		int [] L2 = new int[len2 + 1];
		
		while(m < len1) {
			L1[m] = A[i + m - 1];
			m++;
		}
		while(n < len2) {
			L2[n] = A[k + n];
			n++;
		}
		L1[m] = Integer.MAX_VALUE;
		L2[n] = Integer.MAX_VALUE;
		
		m = 0;
		n = 0;
		
		while(i <= j) {
			if(L1[m] <= L2[n]) {
				A[i - 1] = L1[m];
				m++;
			}else {
				A[i - 1] = L2[n];
				n++;
			}
			
			i++;
		}
	}
}
