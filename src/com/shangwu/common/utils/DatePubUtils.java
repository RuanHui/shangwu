package com.shangwu.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatePubUtils {

	/**
	 * 获得两个日期之间的所有日期
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public  static List<String> getDayBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//格式化为年月

	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();

	    min.setTime(sdf.parse(minDate));//格式化后给min设置时间
	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH),min.get(Calendar.DAY_OF_MONTH));// 设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。

	    max.setTime(sdf.parse(maxDate));
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH),max.get(Calendar.DAY_OF_MONTH)+1);

	    Calendar curr = min;
	    while (curr.before(max)) { //循环到最大日期值
	     result.add(sdf.format(curr.getTime()));//获取当前日期
	     curr.add(Calendar.DAY_OF_MONTH, 1);//根据日历的规则，为给定的日历字段添加或减去指定的时间量。
	    }

	    return result;
	  }
	
	/**
	 * 获得两个日期之间的所有月份
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public  static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
	    ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();

	    min.setTime(sdf.parse(minDate));//格式化后给min设置时间
	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);// 设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。

	    max.setTime(sdf.parse(maxDate));
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

	    Calendar curr = min;
	    while (curr.before(max)) { //循环到最大日期值
	     result.add(sdf.format(curr.getTime()));//获取当前日期
	     curr.add(Calendar.MONTH, 1);//根据日历的规则，为给定的日历字段添加或减去指定的时间量。
	    }

	    return result;
	  }
	
	/**
	 * 获取当前日期向前或向后推N天后的所有月份
	 * @param begDate
	 * @param mount  
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getMonthReduce(String begDate, int mount) throws ParseException {
	    ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

	    Calendar beg = Calendar.getInstance(); 

	    beg.setTime(sdf.parse(begDate));
	    beg.set(beg.get(Calendar.YEAR), beg.get(Calendar.MONTH), 1);
	    
	    Calendar curr = beg;
	    for (int i = 0; i < mount; i++) {
	    	result.add(sdf.format(curr.getTime()));
	    	curr.add(Calendar.MONTH, -1); //-1向后减一个月  1往前加一个月
		}
	      
	    return result;
	  }
	
	/**
	 * 获取当前日期向前或向后推N天后的所有日期
	 * @param begDate
	 * @param mount
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDayReduce(String begDate, int mount) throws ParseException {
	    ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//格式化为年月

	    Calendar beg = Calendar.getInstance(); 

	    beg.setTime(sdf.parse(begDate));
	    beg.set(beg.get(Calendar.YEAR), beg.get(Calendar.MONTH), beg.get(Calendar.DAY_OF_MONTH));
	    
	    Calendar curr = beg;
	    for (int i = 0; i < mount; i++) {
	    	result.add(sdf.format(curr.getTime()));
	    	curr.add(Calendar.DAY_OF_MONTH, -1);
		}  
	    return result;
	  }
	
	public static String intToCapital(Integer value){
		String rtstr = "";
		if (1==value){
			rtstr = "一";
		}else if (2==value){
			rtstr = "二";
		}else if (3==value){
			rtstr = "三";
		}else if (4==value){
			rtstr = "四";
		}else if (5==value){
			rtstr = "五";
		}else if (6==value){
			rtstr = "六";
		}else if (7==value){
			rtstr = "七";
		}else if (8==value){
			rtstr = "八";
		}else if (9==value){
			rtstr = "九";
		}else if (0==value){
			rtstr = "";
		}
		return rtstr;
		
	}
	
	/**
	 * 格式化日期
	 * @author fanlei 2016/06/23 改动 返回格式化后日期
	 * @param date
	 *            要格式化的日期
	 * @param format
	 *            格式字符串
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf=null;
		try {
			if (StringUtils.isBlank(format)) {
				format = "yyyy-MM-dd";
			}
		   sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			e.toString();
		}
		return sdf.format(date);
	}

}
