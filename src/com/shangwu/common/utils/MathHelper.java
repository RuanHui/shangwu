package com.shangwu.common.utils;

import java.math.BigDecimal;

public class MathHelper {
	private static final int DEF_DIV_SCALE = 10;

	private MathHelper() {

	}

	public static double add(Object v1, Object v2) {
		if(v1 == null || v2 == null){
			throw new IllegalArgumentException("the params is not null");
		}
		BigDecimal b1 = null;
		BigDecimal b2 = null;
		if(v1 instanceof Double){
			Double v1Double = (Double) v1; 
			b1 = new BigDecimal(Double.toString(v1Double));
		}else{
			b1 = (BigDecimal) v1;
		}
		
		if(v2 instanceof Double){
			Double v2Double = (Double) v2; 
			b2 = new BigDecimal(Double.toString(v2Double));
		}else{
			b2 = (BigDecimal) v2;
		}
		return b1.add(b2).doubleValue();
	}
	
	public static double sub(Object v1, Object v2) {
		if(v1 == null || v2 == null){
			throw new IllegalArgumentException("the params is not null");
		}
		BigDecimal b1 = null;
		BigDecimal b2 = null;
		if(v1 instanceof Double){
			Double v1Double = (Double) v1; 
			b1 = new BigDecimal(Double.toString(v1Double));
		}else{
			b1 = (BigDecimal) v1;
		}
		
		if(v2 instanceof Double){
			Double v2Double = (Double) v2; 
			b2 = new BigDecimal(Double.toString(v2Double));
		}else{
			b2 = (BigDecimal) v2;
		}
		return b1.subtract(b2).doubleValue();
	}
	
	public static double mul(Object v1, Object v2) {
		if(v1 == null || v2 == null){
			throw new IllegalArgumentException("the params is not null");
		}
		BigDecimal b1 = null;
		BigDecimal b2 = null;
		if(v1 instanceof Double){
			Double v1Double = (Double) v1; 
			b1 = new BigDecimal(Double.toString(v1Double));
		}else if(v1 instanceof BigDecimal){
			b1 = (BigDecimal) v1;
		}else {
			Object b1Object = String.valueOf(v1);
			b1 = new BigDecimal(b1Object.toString());
		}
		
		if(v2 instanceof Double){
			Double v2Double = (Double) v2; 
			b2 = new BigDecimal(Double.toString(v2Double));
		}else if(v2 instanceof BigDecimal){
			b2 = (BigDecimal) v2;
		}else{
			Object b2Object = String.valueOf(v2);
			b2 = new BigDecimal(b2Object.toString());
		}
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double div(BigDecimal v1, BigDecimal v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		
		return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}