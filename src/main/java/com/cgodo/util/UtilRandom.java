package com.cgodo.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 
 * 
 * 描述:随机
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月16日 下午7:52:07
 */
public final class UtilRandom {
	private static final Random RANDOM = new Random();

	/**
	 * 
	 * 描述:随机整数<br>
	 * 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
	 * 
	 * @param bound
	 * @return
	 * @author liyixing 2017年5月16日 下午7:53:55
	 */
	public static final int nextInt(int bound) {
		return RANDOM.nextInt(bound);
	}
	
	/**
	 * 
	 * 描述:随机整数<br>指定位数
	 * 
	 * @param 位数
	 * @return
	 * @author liyixing 2017年5月16日 下午7:53:55
	 */
	public static final long nextDigitLong(int digit) {
		double d = RANDOM.nextDouble();
		double n = Math.pow(10,digit);
		
		BigDecimal result = BigDecimal.valueOf(d*n);
		long r = result.longValue();
		
		while(r < (n/10)) {
			//补位
			r = r * 10;
		}
		
		return r;
	}
	
	/**
	 * 
	 * 描述:随机整数<br>指定位数
	 * 
	 * @param 位数
	 * @return
	 * @author liyixing 2017年5月16日 下午7:53:55
	 */
	public static final int nextDigitInt(int digit) {
		return (int) nextDigitLong(digit);
	}
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			System.out.println(nextDigitLong(5));
			Thread.sleep(100);
		}
	}
}
