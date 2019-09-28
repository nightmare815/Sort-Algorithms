package com.bin.sort;

import java.util.Random;

/**
 * 基于分治策略的堆排序算法
 * @author tianbin
 *
 */
public class HeapSort {
	
	static int heap_size = 0;
	public static void main(String[] args) {
		testPrint();
	}
	
	public static void testPrint() {
		Random random = new Random();
		int [] arr1 = new int[10];
		
		for(int i = 0;i < 10; i++) {
			arr1[i] = random.nextInt(100);
		}
		//int[] arr1 = {10,9,8,7,6,5,4,3,2,1};
		System.out.println("排序前:");
		for (int i : arr1) {
			System.out.print(i + ", ");
		}
		System.out.println("\n");
		HEAP_SORT(arr1);
		System.out.println("---------------------------");
		System.out.println("排序后:\t");
		for (int i : arr1) {
			System.out.print(i + ", ");
		}
	}
	
	//把大顶堆数组转换成升序排列的数组
	public static void HEAP_SORT(int[] A) {
		
		BUILD_MAX_HEAP(A);
		System.out.println("这是建完大顶堆后的数组:");
		for (int i : A) {
			System.out.print(i + ",");
		}
		System.out.println("\n");
		
		HeapSort.heap_size = A.length;
		
		//当堆中的节点数大于1时,就进行交换操作, 只剩一个元素时就不必交换了
		while(HeapSort.heap_size - 1 > 0) {		
			//将堆顶元素A[0]与堆得最后一个元素A[heap_size - 1]交换位置
			int tem;
			tem = A[0];
			A[0] = A[HeapSort.heap_size - 1];
			A[HeapSort.heap_size - 1] = tem;
			HeapSort.heap_size--;
			MAX_HEAPIFY(A,0);
		}
	}
	
	
	//把所有的父节点及其子树构造成大顶堆
	public static void BUILD_MAX_HEAP(int[] A) {
		HeapSort.heap_size = A.length;
		int i = (HeapSort.heap_size - 2) / 2;
		while(i >= 0) {
			MAX_HEAPIFY(A, i);
			i--;
		}
	}
	
	
	//把一个有父节点序号为i的子树构造成大顶堆
	public static void MAX_HEAPIFY(int[] A, int i) {
		
		int largest = -1;
		int l = i * 2 + 1;
		int r = i * 2 + 2;
		
		if(l <= (HeapSort.heap_size - 1) && A[l] > A[i]) {
			largest = l;
		}else {
			largest = i;
		}
		
		if(r <= (HeapSort.heap_size - 1) && A[r] > A[largest]) {
			largest = r;
		}
		
		if(largest != i) {		//A[i]和A[largest]交换位置
			int tem;
			tem = A[i];
			A[i] = A[largest];
			A[largest] = tem;
			MAX_HEAPIFY(A, largest);
		}
	}
}
