package com.bin.subarray;

/**
 * 最大子数组属性的包装类
 * @author tianbin
 *
 */
public class SubArray {
	
	public int low;
	public int high;
	public int sum = 0;
	
	public SubArray() {
		super();
		
	}

	public SubArray(int low, int high, int sum) {
		super();
		this.low = low;
		this.high = high;
		this.sum = sum;
	}
	
	public void print() {
		System.out.println();
		System.out.println("最大子数组中的最小下标: " + low + ",  最大下标: " + high + ",  子数组中元素总和为: " + sum);
	}
	
}
