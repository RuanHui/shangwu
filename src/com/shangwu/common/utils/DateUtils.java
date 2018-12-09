package com.shangwu.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * @author lionger
 * 
 */
public class DateUtils {

	public static final String FORMAT_TIME = "HH:mm:ss";	
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_DATE3 = "yyyy-MM";
	public static final String FORMAT_DATE4 = "yyyyMM";
	public static final String FORMAT_DATE2 = "dd/MM/yyyy";
	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE_TIME2 = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMAT_DATE_TIME3 = "HH:mm:ss dd/MM/yyyy";
	public static final String FORMAT_TIME_MILLISECOND = "HH:mm:ss SSS";
	public static final String FORMAT_DATE_TIME_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSS";
	public static final String FORMAT_ONLY_MD = "MM/dd";
	public static final String FORMAT_ONLY_HM = "HH:mm";
	

    public static SimpleDateFormat df = null;

	/**
	 * 构造函数
	 */
	private DateUtils() {
	}
	
	/**
	 * 返回时间：格式“08：13：36”
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static String getTime( int hour, int minute, int second ) {
		String result = "";
		if ( hour>9 )
			result += hour + ":";
		else
			result += "0" + hour + ":";
		
		if ( minute>9 )
			result += minute + ":";
		else
			result += "0" + minute + ":";
		
		if ( second>9 )
			result += second;
		else
			result += "0" + second;
		
		return result;		
	}
	
	public static String getDateTime( int year, int month, int day, int hour, int minute, int second ) {
		String result = year + "-";
		
		if ( month>9 )
			result += month + "-";
		else
			result += "0" + month + "-";
		
		if ( day>9 )
			result += day + " ";
		else
			result += "0" + day + " ";
		
		if ( hour>9 )
			result += hour + ":";
		else
			result += "0" + hour + ":";
		
		if ( minute>9 )
			result += minute + ":";
		else
			result += "0" + minute + ":";
		
		if ( second>9 )
			result += second;
		else
			result += "0" + second;
		
		return result;
	}
	
	public static String getDate( int year, int month, int day) {
		String result = year + "-";
		
		if ( month>9 )
			result += month + "-";
		else
			result += "0" + month + "-";
		
		if ( day>9 )
			result += day;
		else
			result += "0" + day;
		
		return result;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return 当前时间 格式“08：13：36”
	 */
	public static String getCurrentTime() {
		return formatDate(new Date(), FORMAT_TIME);
	}
	/**
	 * 跟据传入的秒数,得到传入秒数往前推算的日期时间
	 * @mark add by Jason
	 * @param seconds
	 * @return
	 */
	public static String getDateTime(Integer seconds){
		Date date = new Date();
		date.setTime(date.getTime()-seconds);
		return formatTime(date);
	}
	
	/**
	 * 获取当前系统日期和时间
	 * 
	 * @return 当前系统日期和时间时间格式"2004－08－23 08：34：35"
	 */
	public static String getCurrentDateTime() {
		return formatDate(new Date(), FORMAT_DATE_TIME);
	}

	/**
	 * 返回当前日期
	 * 
	 * @return String ： 格式：2004－03－23
	 */
	static public String getCurrentDate() {
		return formatDate(new Date(), FORMAT_DATE);
	}

	/**
	 * 格式化时间的显示
	 * 
	 * @param objCal
	 *            Calendar Object
	 * @param strFormat
	 *            日期格式化的标准 e.g. "yyyy/MM/dd
	 *            HH:mm:ss"（务必按标准写，可参考java.text.simpleDateFormat.java)
	 * @return String 格式化的时间
	 */
	public static String formatCalendar(Calendar objCal, String strFormat) {
		if (objCal == null) {
			return "";
		} else {
			return formatDate( objCal.getTime() );
		}
	}


	/**
	 * 格式化date
	 * 
	 * @param dt
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDate(Date dt, String format) {
		if (dt == null) {
			return "";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			
			return formatter.format(dt);
		}
	}

	public static String formatDateTime(Date dt) {
		return formatDate(dt, FORMAT_DATE_TIME);
	}

	public static String formatDate(Date dt) {
		return formatDate(dt, FORMAT_DATE);
	}
	
	public static String formatTime(Date dt) {
		return formatDate(dt, FORMAT_TIME);
	}

	public static String formatTime(Date dt,String format) {
		return formatDate(dt, format);
	}
	
	static public int[] timeSplit(Date dt) {
		if (dt == null)
			return null;
		
		int[] split = new int[6];
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		
		split[0] = calendar.get(Calendar.YEAR);
		split[1] = calendar.get(Calendar.MONTH) + 1;
		split[2] = calendar.get(Calendar.DAY_OF_MONTH);
		split[3] = calendar.get(Calendar.HOUR_OF_DAY);
		split[4] = calendar.get(Calendar.MINUTE);
		split[5] = calendar.get(Calendar.SECOND);
		
		if (split[0] == 1970) {
			split[0] = 0;
			split[1] = 0;
			split[2] = 0;
		}
		
		return split;
	}

	/**
	 * 返回特定时间的前几个月，如果特定时间为“2005-5-23”，则months=3，则返回2005-2-23
	 * @param dt
	 * @param months
	 * @return
	 */
	static public Date getBeforeMonths(Date dt, int months, boolean from0day, boolean from0time ) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.add(Calendar.MONTH, -months);
		
		if ( from0day ) {
			Calendar c2 = Calendar.getInstance();
			c2.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), 0, cl.get(Calendar.HOUR), cl.get(Calendar.MINUTE), cl.get(Calendar.SECOND));
			cl = c2;
		}
		
		if ( from0time ) {
			Calendar c2 = Calendar.getInstance();
			c2.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl
					.get(Calendar.DATE), 0, 0, 0);
			cl = c2;
		}
		
		return cl.getTime();
	}

	/**
	 * 返回特定时间的前几天 如果特定时间为“2005-5-23 23:22:32",则days＝7， 如果from0=false,则为2005-5-16
	 * 23:22:32, 如果from0＝true，则为2005-5-16 00:00:00
	 * 
	 * @param dt
	 *            Date
	 * @param days
	 *            int
	 * @param from0
	 *            boolean 是否从零点开始
	 * @return Date
	 */
	static public Date getBeforeDays(Date dt, int days, boolean from0) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);

		if (from0) {
			Calendar c2 = Calendar.getInstance();
			c2.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl
					.get(Calendar.DATE), 0, 0, 0);
			cl = c2;
		}

		cl.add(Calendar.DATE, -days);
		return cl.getTime();
	}
	
	static public Date getBeforeHours(Date dt, int hours) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.add(Calendar.HOUR, -hours);
		return cl.getTime();
	}
	
	
	/**
	 * 返回特定时间的前几天列表 如果特定时间为“2005-5-23 23:22:32",则days＝7， 如果from0=false,则为2005-5-16
	 * 23:22:32, 如果from0＝true，则为2005-5-16 00:00:00
	 * 
	 * @param dt
	 *            Date
	 * @param days
	 *            int
	 * @param from0
	 *            boolean 是否从零点开始
	 * @return Date
	 */
	public static List<Date> getWeekDays(Date dt, int days, boolean from0) {
		List<Date> dateList = new ArrayList<Date>();
		for(int i = days ; i > 0; i -- ){
			dateList.add(getBeforeDays(dt, i, from0));
		}
		
		return dateList;
	}
	
	public static boolean isInDateList(List<Date> dateList, Date date){
		for(Date birthday: dateList){
			if(isBirthday(birthday, date)){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isBirthday(Date birthday, Date date) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(birthday);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date);
		
		return c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH) && c1.get(Calendar.DATE)==c2.get(Calendar.DATE);
	}
	
	public static boolean isBirthday(String birthday, Date date) {
		Date dt = parseDate(birthday);
		if (dt == null)
			return false;
		
		return isBirthday(dt, date);
	}

	public static Calendar parseCalendar(String sdate, String formate) {
		Date date = parseDate( sdate, formate );
		if ( date==null)
			return null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl;
	}
	
	public static Date parseDate(String sdate, String formate) {
		if (StringUtils.isEmpty(sdate)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(formate);
		try {
			return df.parse(sdate);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(String sdate) {
		return parseDate( sdate, FORMAT_DATE );
	}

	public static Date parseTime(String sdate) {
		return parseDate( sdate, FORMAT_TIME );
	}

	public static Date parseDateTime(String sdate) {
		return parseDate( sdate, FORMAT_DATE_TIME );
	}

	/**
	 * 返回一年的天数，用于区别闰年
	 * @param year
	 * @return
	 */
	public static int getLastDayOfYear(int year) {
		return (isLeapYear(year)) ? 366 : 365;
	}

	/**
	 * 获取每个月的天数
	 * 
	 * @param monthNum
	 *            1－12
	 * @param year
	 * @return
	 */
	public static int getLastDayOfMonth(int monthNum, int year) {

		switch (monthNum) {
		case 1:
			return 31;
		case 2:
			return (isLeapYear(year)) ? 29 : 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			throw new IllegalArgumentException("Illegal month number: "
					+ monthNum);
		}
	}

	/**
	 * 是否是闰年，是闰年2月份为29天
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
			return true;
		else
			return false;
	}
	
	public static java.sql.Date toSqlDate(Date date){
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date toSqlDate(Date date, java.sql.Date defaultSqlDate){
		if (date == null)
			return defaultSqlDate;
		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Time toSqlTime(Date date){
		if (date == null)
			return null;
		return new java.sql.Time(date.getTime());
	}
	
	public static java.sql.Time toSqlTime(Date date, java.sql.Time defaultSqlTime){
		if (date == null)
			return defaultSqlTime;
		return new java.sql.Time(date.getTime());
	}
	
	public static java.sql.Timestamp toSqlTimestamp(Date date){
		if (date == null)
			return null;
		return new java.sql.Timestamp(date.getTime());
	}
	
	public static java.sql.Timestamp toSqlTimestamp(Date date, java.sql.Timestamp defaultSqlTimestamp){
		if (date == null)
			return defaultSqlTimestamp;
		return new java.sql.Timestamp(date.getTime());
	}
	
	/**
	 * @param begin 开始日期
	 * @param end 结束日期
	 * @return 天数之差
	 */
	public static int countDays(Date begin, Date end) {
		int days = 0;
		if(begin==null){
			begin= new Date();
		}
		if(end==null){
			end= new Date();
		}
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();

		c_b.setTime(begin);
		c_e.setTime(end);
		while (c_b.before(c_e)) {
			days++;
			c_b.add(Calendar.DAY_OF_YEAR, 1);
		}

		return days;
	}
	/**
	 * 计算两个日期相差的月份数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthSpace(Date date1, Date date2) throws ParseException {

		int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);

		result = 12*(c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))+c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result == 0 ? 0 : Math.abs(result);
	}
	
	/**
	 * 根据联通舆情项目需要封装以下方法
	 * 用于方便将时间格式转化为时间戳并且自定小时数修正补充时差
	 * @param dateTime 待转字符串 YYYY-MM-DD HH:MI:SS
	 * @param hours 时间浮动 以小时为单位可以前推或后推n小时
	 * add by wangchong 
	 */
	public static String datetimeTotimestamp(String dateTime, int hours) {
		if(parseDateTime(dateTime)==null){
			return null;
		}
		String timeStamp = parseDateTime(dateTime).getTime()+hours*60*60*1000+"";
		timeStamp = timeStamp.substring(0,timeStamp.length()-3)+"e";//时间戳除以1000,字符串内容故截取后三位
		return timeStamp;
	}
	
	/**
	 * 根据联通舆情项目需要封装以下方法
	 * 用于方便将时间格式转化为时间戳并且自定小时数修正补充时差
	 * @param dateTime 待转字符串 YYYY-MM-DD HH:MI:SS
	 * @param hours 时间浮动 以小时为单位可以前推或后推n小时
	 * add by wangxudong
	 */
	public static int datetimeToIntValue(String dateTime, int hours) {
		if(parseDateTime(dateTime)==null){
			return 0;
		}
		String timeStamp = parseDateTime(dateTime).getTime()+hours*60*60*1000+"";
		return Integer.valueOf(timeStamp);
	}
	
	/**
	 * 跟据传入的小时，计划出日期 
	 * @param dateTime
	 * @param hours
	 * @return
	 */
	public static String getDatetime(Date dateTime, int hours) {
		Long timeStamp =dateTime.getTime()+hours*60*60*1000;
		Date date = new Date(timeStamp);
		return DateUtils.formatDateTime(date);
	}
	/**
	 * 根据联通舆情项目需要封装以下方法
	 * 用于方便将时间格式转化为时间戳
	 * @param dateTime 待转字符串 YYYY-MM-DD HH:MI:SS
	 * add by wangchong 
	 */
	public static String datetimeTotimestamp(String dateTime) {
		return datetimeTotimestamp(dateTime,0);
	}
	
	/**
	 * 在指定时间的指定字段（年、月、日、时、分、秒）上加上指定值
	 * @param date 指定时间
	 * @param field 时间字段 参考 Calendar.YEAR 、Calendar.MONTH 等
	 * @param amount  指定要加的数值
	 * @return
	 */
	public static Date add(Date date,int field,int amount ){
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	/**
	 * 在当前时间的指定字段（年、月、日、时、分、秒）上加上指定值
	 * @param field 时间字段 参考  Calendar.YEAR 、Calendar.MONTH 等
	 * @param amount  指定要加的数值
	 * @return
	 */
	public static Date add(int field,int amount ){
		return add(new Date(),field,amount);
	}
	
	/**
	 * 获取指定格式时间字符串的unix时间戳
	 * time 时间字符串
	 * @return
	 */
	public static int getUnixTimestamp(String time) {
		DateFormat sdf = new SimpleDateFormat ("E MMM dd HH:mm:ss z yyyy",Locale.US);
		try {
			Date date = sdf.parse(time);
			return (int) (date.getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Date getDateByWeekOfYear(int year, int week){
		Calendar calendar = Calendar.getInstance(); 
		calendar.clear();
		calendar.set(year, 0,-1);
		calendar.add(Calendar.WEEK_OF_YEAR, week);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 获取本月的第一天
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 当前日所在周是第几天，1表示礼拜一，7表示礼拜天
	 * @param date
	 * @return int
	 * @author jiazhihui 
	 * @Create 2015-6-10 下午4:51:17
	 */
	public static int getWeekOfDay(Date date) {
		Calendar rili = Calendar.getInstance();
		// 将日历翻到year年month月1日、0表示1月
		rili.setTime(date);
		// 获取今天 是星期几（get方法返回的值是0表示星期日，返回的值是6表示星期六）
		int day = rili.get(Calendar.DAY_OF_WEEK) - 1;
		day = day == 0?day=7:day;
		return day;
	}
	//获取上月 年月份
	public static String getMonthData(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String monthData =sdf.format(date);
		return monthData;
		
	}
	//获取昨天 年月份
		public static String getMonthNowData(){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Date date = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String dateStr =sdf.format(date);
			return dateStr;
			
		}
	/**  获取某一天的日期   */
	public Date getLastBeforeDay(Date date, int dayNum){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNum);
		date = calendar.getTime();
		return date;
	}
	/**
	 * 获取当前日期  yyyymmdd
	 */
	public static String getDay(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr =sdf.format(date);
		return dateStr;
	}

	/**
	 * 传入参数：yyyy-mm 或者yyyy-mm-dd
	 * @param 返回格式为：yyyy
	 * @return
	 */
	public static String getYear(String year) {//参数年的格式是yyyy-MM-dd
		year = year.split("-")[0];//以下标区分获取年
		return year;
	}

	/**
	 * 传入参数：yyyy-mm
	 * @param 返回格式为：yyyymm
	 * @return
	 */
	public static String getMonth(String year) {
		//year = year.replace("-", "");//如果包含有—就用空替换掉
		String month = year.replace("-","");//以下标区分获取月
		return month;
	}
	
	//获取上月 年月份
	public static String getFormatMonth(String format){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -1);
			Date date = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String monthData =sdf.format(date);
			return monthData;
	}
	//获取昨天日期
	public static String getFormatDay(String format){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr =sdf.format(date);
		return dateStr;
	}
	
	//经纬度转为小数  分位数除以60，秒位数除以3600，比如，37°15′54″=37+15/60+54/3600=37.265
	public Map<String,Object> conversionFloat(String str){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(!StringUtils.isEmpty(str)){
				String strs[]=str.split("°");
				if(strs.length<2){
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @author fanlei
	 * @time 20160907
	 * @description 日期转换
	 * @throws ParseException 
	 */
	public static String getStrDate(String strDate,String dateFormate){
		String format = "";
		df = new SimpleDateFormat(dateFormate);
		try {
			format = df.format(df.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format;
	} 
	
	/**
	 * @author fanlei
	 * @time 20160907
	 * @description 日期转换
	 * @throws ParseException 
	 */
	public static String getStrDate(Date date,String dateFormate){
		df = new SimpleDateFormat(dateFormate);
		return df.format(date);
	} 
	/**
	 * 根据日期获取月份第一天
	 * @author xiangyong
	 * @date 2017-3-14
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFirstDate(String date,String format){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日"); 
		SimpleDateFormat df = new SimpleDateFormat(format); 
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.valueOf(year));
		cal.set(Calendar.MONTH, Integer.valueOf(month)-1);//不减1，会获取出入月份的后一个月
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为该月第一天
		String firstdate = df.format(cal.getTime());
		System.out.println("===firstdate==="+firstdate);
		return firstdate;
	}
	/**
	 * 根据日期获取月份最后一天
	 * @author xiangyong
	 * @date 2017-3-14
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getLastDate(String date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format); 
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.valueOf(year));
		cal.set(Calendar.MONTH, Integer.valueOf(month));
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为该月第一天
		cal.add(Calendar.DAY_OF_MONTH, -1);//再减一天即为月份最后一天 
		String lastdate = df.format(cal.getTime());
		System.out.println("===lastdate==="+lastdate);
		return lastdate;
	}
	
	/**
	 * 获得两个日期之间的所有月份
	 * @author xiangyong
	 * @date 2017-3-14
	 * @param minDate
	 * @param maxDate
	 * @param flag -- 是否过滤掉传入的最小和最大月份
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate,boolean flag) throws ParseException {
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		if(flag){
			min.setTime(sdf.parse(minDate));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH)+1, 1);//
			
			max.setTime(sdf.parse(maxDate));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH)-1, 2);
			
		}else{
			min.setTime(sdf.parse(minDate));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);//
			
			max.setTime(sdf.parse(maxDate));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		}

		Calendar curr = min;
		while (curr.before(max)) {
		 result.add(sdf.format(curr.getTime()));
		 curr.add(Calendar.MONTH, 1);
		}

		return result;
	}
	
	/**
	 * 日期格式转换
	 * @author xiangyong
	 * @date 2017-3-14
	 * @param date
	 * @param fromat
	 * @return
	 * @throws ParseException
	 */
	public static String getFormatDate(String date,String fromat) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(fromat);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sd.parse(date));
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 获取三天前日期
	 */
	public static String getBeforeYesterday() {
		Calendar calendar1 = Calendar.getInstance();
		 SimpleDateFormat sdf1 = new SimpleDateFormat(FORMAT_DATE);
		  calendar1.add(Calendar.DATE, -3);
		  String two_days_ago = sdf1.format(calendar1.getTime());
		return two_days_ago;
	}

	public static void main(String[] args) throws Exception {
	    //String str="37°15′54″";  
        //用经度分割返回的网页代码   
        //String s="°"+"\""+"lat"+"\""+":";  
       //String strs[]=str.split("°");  
       //String a[]=strs[1].split("′");  
      //System.out.println(strs[0]+"======"+a[0]);
		System.out.println(getBeforeYesterday());
//		System.out.println(DateUtils.getStrDate(new Date(), "yyyyMMdd"));
//		String minstring = "2017-03-12";
//		String maxstring = "2017-07-15";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//	    Date date1 = sdf.parse(minstring); 
//	    Date date2 = sdf.parse(maxstring); 
//		System.out.println("====="+DateUtils.getMonthSpace(date1, date2));
//		String year = minstring.substring(0,4);
//		String month = minstring.substring(5,7);
//		System.out.println(year);
//		System.out.println(month);
//		getFirstDate("2017-11","M月d日");
//		getLastDate("2017-03","M月d日");
//		System.out.println(getMonthBetween("2017-03-12", "2017-07-15",false));
//		System.out.println(getFormatDate("2017-03-12","M月d日"));
//		
//		String result = "";
//		List<String> list = null;
//		if(minstring != null && !"".equals(minstring) && maxstring != null && !"".equals(maxstring)){
//			try {
//				list = cmcc.gz.channel2.common.utils.DateUtils.getMonthBetween(minstring, maxstring, true);
//				if(list!=null && list.size()>0){
//					for (int i = 0; i < list.size(); i++) {
//						result += ","+getFirstDate(list.get(i),"M月d日")+"至"+getLastDate(list.get(i),"M月d日");;
//					}
//					result = getFormatDate(minstring,"M月d日")+"至"+getLastDate(minstring,"M月d日")+result+","+getFirstDate(maxstring,"M月d日")+"至"+getFormatDate(maxstring,"M月d日");
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(result);
	}
	
	
}
