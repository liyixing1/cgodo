package com.cgodo.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * 时间帮助类
 * 
 * @author 李义星
 * 
 */
public final class UtilDateTime {
	/** 时间格式化器集合，该类在使用过一次格式后，根据格式，保存该格式对应的格式化对象 */
	public static final Map<String, SimpleDateFormat> datetimeFormats = new HashMap<String, SimpleDateFormat>();
	/** 年月日格式 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	/** 年月日 时分秒格式 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/** 时分秒格式 */
	public static final String HH_MM_SS = "HH:mm:ss";
	/** 一天的毫秒数 */
	public static final long DAY_MILLISECOND = 86400000;

	/**
	 * 
	 * 描述:获取当前时间的java.util.Calendar
	 * 
	 * @return
	 * @author liyixing 2012-11-12 下午2:50:58
	 */
	public static final Calendar getNowCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 
	 * 描述:根据指定date或者calendar
	 * 
	 * @return
	 * @author liyixing 2012-11-12 下午2:50:58
	 */
	public static final Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar;
	}

	/**
	 * 描述:获取当前时间的java.util.Date
	 * 
	 * @return
	 * 
	 * @author liyixing 2012-11-12 下午2:50:58
	 */
	public static final Date getNowDate() {
		return new Date();
	}

	/**
	 * 
	 * 描述:获取当前时间的java.sql.Timestamp类型
	 * 
	 * @return
	 * @author liyixing 2012-11-12 下午2:51:47
	 */
	public static final Timestamp getNowTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 
	 * 描述:根据格式获取时间格式器对象
	 * 
	 * @param format
	 * @return
	 * @author liyixing 2012-11-12 下午2:51:56
	 */
	public static final SimpleDateFormat getDateFormat(String format) {
		SimpleDateFormat simpleDateFormat = UtilDateTime.datetimeFormats
				.get(format);

		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat(format);
			UtilDateTime.datetimeFormats.put(format, simpleDateFormat);
		}

		return simpleDateFormat;
	}

	/**
	 * 
	 * 描述:格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author liyixing 2012-11-12 下午2:52:31
	 */
	public static final String format(Date date, String format) {
		return getDateFormat(format).format(date);
	}

	/**
	 * 
	 * 描述:格式化Timestamp类型时间
	 * 
	 * @param timestamp
	 * @param format
	 * @return
	 * @author liyixing 2012-11-12 下午2:52:56
	 */
	public static final String format(Timestamp timestamp, String format) {
		return format(new Date(timestamp.getTime()), format);
	}

	/**
	 * 
	 * 描述:格式化Calendar类型时间
	 * 
	 * @param calendar
	 * @param format
	 * @return
	 * @author liyixing 2012-11-12 下午2:53:10
	 */
	public static final String format(Calendar calendar, String format) {
		return format(calendar.getTime(), format);
	}

	/**
	 * 
	 * 描述:以字符串格式获取当前日期与时间，格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @author liyixing 2012-11-12 下午2:53:31
	 */
	public static final String formatNowDateTime() {
		return format(getNowCalendar(), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 
	 * 描述:以字符串格式获取当前日期，格式yyyy-MM-dd
	 * 
	 * @return
	 * @author liyixing 2012-11-12 下午2:54:06
	 */
	public static final String formatNowDate() {
		return format(getNowCalendar(), YYYY_MM_DD);
	}

	/**
	 * 
	 * 描述:以字符串格式获取当前时间，格式HH:mm:ss
	 * 
	 * @return
	 * @author liyixing 2012-11-12 下午2:54:06
	 */
	public static final String formatNowTime() {
		return format(getNowCalendar(), HH_MM_SS);
	}

	/**
	 * 
	 * 描述:解析字符串，转化成时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 * @author liyixing 2012-11-12 下午2:55:07
	 */
	public static final Date parse(String date, String format)
			throws ParseException {
		return getDateFormat(format).parse(date);
	}

	/**
	 * 
	 * 描述:将Date类型的时间修改为这天最后时间，也就是23点59分59秒
	 * 
	 * @param date
	 * @return
	 * @author liyixing 2012-11-12 下午2:56:25
	 */
	public static final Date setDateToLastTime(Date date) {
		if (date == null) {
			return null;
		}

		Calendar c = getCalendar(date);
		setCalendarToLastTime(c);

		return c.getTime();
	}

	/**
	 * 
	 * 描述:将Calendar类型的时间修改为这天最后时间，也就是23点59分59秒.999毫秒
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:57:22
	 */
	public static final void setCalendarToLastTime(Calendar date) {
		if (date == null) {
			return;
		}

		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
	}

	/**
	 * 
	 * 描述:将date类型的时间修改为这天开始时间，也就是00点00分00秒
	 * 
	 * @param date
	 * @return
	 * @author liyixing 2012-11-12 下午2:58:51
	 */
	public static final Date setDateToFirstTime(Date date) {
		if (date == null) {
			return null;
		}

		Calendar c = getCalendar(date);
		setCalendarToFirstTime(c);

		return c.getTime();
	}

	/**
	 * 
	 * 描述:将Calendar类型的时间修改为这天开始时间，也就是0点0分0秒
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:58:58
	 */
	public static final void setCalendarToFirstTime(Calendar date) {
		if (date == null) {
			return;
		}

		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * 
	 * 描述:计算两个时间相差的秒数
	 * <br>
	 * d1-d2
	 * 
	 * @param d1
	 * @param d2
	 * @author liyixing 2011-7-26
	 */
	public static final long getDifferenceSecond(Date date1, Date date2) {
		return (date1.getTime() - date2.getTime()) / 1000;
	}
	
	/**
	 * 
	 * 描述:计算两个时间相差的分钟数
	 * <br>
	 * d1-d2
	 * 
	 * @param d1
	 * @param d2
	 * @author liyixing 2011-7-26
	 */
	public static final long getDifferenceMinute(Date date1, Date date2) {
		return ((date1.getTime() - date2.getTime()) / 60000);
	}
	
	/**
	 * 
	 * 描述:计算两个时间相差的小时
	 * <br>
	 * d1-d2
	 * 
	 * @param d1
	 * @param d2
	 * @author liyixing 2011-7-26
	 */
	public static final long getDifferenceHour(Date date1, Date date2) {
		return ((date1.getTime() - date2.getTime()) / 3600000);
	}

	/**
	 * 
	 * 描述:计算两个时间相差的天数
	 * 
	 * 把第一个date改成该date天的开始，即0点0分0秒
	 * 
	 * 计算两个date相差毫秒数
	 * 
	 * 用相差毫秒数/一天总毫秒数，即相差天数
	 * 
	 * 返回值，永远是个整数
	 * 
	 * @param d1
	 * @param d2
	 * @author liyixing 2011-7-26
	 */
	public static final long getDifferenceDay(Date date1, Date date2) {
		date1 = setDateToFirstTime(date1);

		long d1t = date1.getTime();
		long d2t = date2.getTime();
		// 计算时间差值（毫秒）
		long div = Math.abs(d1t - d2t);
		long day = div / DAY_MILLISECOND;

		return day;
	}

	/**
	 * 
	 * 描述:将Date类型的时间修改为这月最后时间，也就是一个月最后一天的23点59分59秒.999
	 * 
	 * @param date
	 * @return
	 * @author liyixing 2012-11-12 下午2:56:25
	 */
	public static final Date setDateToLastMonthTime(Date date) {
		Calendar c = getCalendar(date);
		setCalendarToLastMonthTime(c);

		return c.getTime();
	}

	/**
	 * 
	 * 描述:将Calendar类型的时间修改为这月最后时间，也就是一个月最后一天的23点59分59秒
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:57:22
	 */
	public static final void setCalendarToLastMonthTime(Calendar date) {
		// 设为下月第一天
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.add(Calendar.MONTH, 1);
		// 往前推算一天
		date.add(Calendar.DAY_OF_MONTH, -1);
		setCalendarToLastTime(date);
	}
	
	/**
	 * 
	 * 描述:将Date类型的时间修改为这年最后时间，也就是一年最后一天的23点59分59秒
	 * 
	 * @param date
	 * @return
	 * @author liyixing 2012-11-12 下午2:56:25
	 */
	public static final Date setDateToLastYearTime(Date date) {
		Calendar c = getCalendar(date);
		setCalendarToLastYearTime(c);

		return c.getTime();
	}

	/**
	 * 
	 * 描述:将Calendar类型的时间修改为这年最后时间，也就是一年最后一天的23点59分59秒
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:57:22
	 */
	public static final void setCalendarToLastYearTime(Calendar date) {
		// 设为下年第一天
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.MONTH, 1);
		date.add(Calendar.YEAR, 1);
		// 往前推算一天
		date.add(Calendar.DAY_OF_YEAR, -1);
		setCalendarToLastTime(date);
	}

	/**
	 * 
	 * 描述:将date类型的时间修改为这月开始时间，也就是1号00点00分00秒
	 * 
	 * @param date
	 * @return
	 * @author liyixing 2012-11-12 下午2:58:51
	 */
	public static final Date setDateToFirstMonthTime(Date date) {
		Calendar c = getCalendar(date);
		setCalendarToFirstMonthTime(c);

		return c.getTime();
	}

	/**
	 * 
	 * 描述:将Calendar类型的时间修改为这月开始时间，也就是1号00点00分00秒
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:58:58
	 */
	public static final void setCalendarToFirstMonthTime(Calendar date) {
		date.set(Calendar.DAY_OF_MONTH, 1);
		setCalendarToFirstTime(date);
	}
	
	/**
	 * 
	 * 描述:将date类型的时间修改为这年开始时间，也就是1月1号00点00分00秒
	 * 
	 * @param date
	 * @return
	 * @author liyixing 2012-11-12 下午2:58:51
	 */
	public static final Date setDateToFirstYearTime(Date date) {
		Calendar c = getCalendar(date);
		setCalendarToFirstYearTime(c);

		return c.getTime();
	}

	/**
	 * 
	 * 描述:将Calendar类型的时间修改为这年开始时间，也就是1号00点00分00秒
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:58:58
	 */
	public static final void setCalendarToFirstYearTime(Calendar date) {
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.MONTH, 1);
		setCalendarToFirstTime(date);
	}

	/**
	 * 
	 * 描述:是否同一天
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:58:58
	 */
	public static final boolean isEqualDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		return isEqualDay(getCalendar(date1), getCalendar(date2));
	}

	/**
	 * 
	 * 描述:是否同一天
	 * 
	 * @param date
	 * @author liyixing 2012-11-12 下午2:58:58
	 */
	public static final boolean isEqualDay(Calendar date1, Calendar date2) {
		return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)
				&& date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
				&& date1.get(Calendar.DAY_OF_MONTH) == date2
						.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * 描述:增加N周
	 * 
	 * @param date
	 * @param weeks
	 *            多少周，如果要减少，则负数
	 * @author liyixing 2015年10月15日 下午2:49:11
	 */
	public static final void addWeek(Calendar date, int weeks) {
		date.add(Calendar.DATE, weeks * 7);
	}

	/**
	 * 
	 * 描述:增加N周
	 * 
	 * @param date
	 * @param weeks
	 *            多少周，如果要减少，则负数
	 * @author liyixing 2015年10月15日 下午2:49:11
	 */
	public static final void addWeek(Date date, int weeks) {
		addWeek(getCalendar(date), weeks);
	}

	/**
	 * 
	 * 描述:上周一
	 * 
	 * @param date
	 * @param weeks
	 *            多少周，如果要减少，则负数
	 * @author liyixing 2015年10月15日 下午2:49:11
	 * @return
	 */
	public static final Date getPreMondy(Date date) {
		Calendar calendar = getCalendar(date);
		addWeek(calendar, -1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return calendar.getTime();
	}

	/**
	 * 
	 * 描述:N天之后
	 * 
	 * @param date
	 * @param day
	 *            天，如果要减少，则负数
	 * @author liyixing 2015年10月15日 下午2:49:11
	 * @return
	 */
	public static final Date addDay(Date date, int day) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_YEAR, day);

		return calendar.getTime();
	}

	/**
	 * 
	 * 描述:上周日
	 * 
	 * @param date
	 * @param weeks
	 *            多少周，如果要减少，则负数
	 * @author liyixing 2015年10月15日 下午2:49:11
	 * @return
	 */
	public static final Date getPreSundy(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		return calendar.getTime();
	}

	public static void main(String args[]) {
		byte y = 127;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(1447793852000l);

		System.out.println(UtilDateTime.format(c.getTime(),
				UtilDateTime.YYYY_MM_DD_HH_MM_SS));

		c.setTimeInMillis(1447189052000l);

		System.out.println(UtilDateTime.format(c.getTime(),
				UtilDateTime.YYYY_MM_DD_HH_MM_SS));
		System.out.println(new Date().getTime());
		System.out.println((char) y);
		System.out.println(UtilDateTime.format(
				getPreMondy(UtilDateTime.getNowDate()),
				UtilDateTime.YYYY_MM_DD_HH_MM_SS));
		System.out.println(UtilDateTime.format(
				getPreSundy(UtilDateTime.getNowDate()),
				UtilDateTime.YYYY_MM_DD_HH_MM_SS));
		System.out.println(BigDecimal.valueOf(4.1).setScale(0,
				BigDecimal.ROUND_UP));
		System.out.println(BigDecimal.valueOf(33)
				.divide(BigDecimal.valueOf(7), 0, BigDecimal.ROUND_UP)
				.intValue());
		System.out.println(BigDecimal.valueOf(33).divide(BigDecimal.valueOf(7),
				0, BigDecimal.ROUND_UP));
	}

	/**
	 * 与当前时间差值 <br>
	 * 多少秒前<br>
	 * <br>
	 * 多少分前 <br>
	 * 多少小时前 <br>
	 * 多少天前 <br>
	 * 超过一年完整时间
	 * @param date
	 */
	public static final String getDiffyNowByString(Date date) {
		Date now = new Date();
		long d = getDifferenceSecond(now, date);
		
		if(d < 60) {
			return d + "秒钟前";
		}
		
		d = getDifferenceMinute(now, date);
		
		if(d < 60) {
			return d + "分钟前";
		}
		
		d = getDifferenceHour(now, date);
		
		if(d < 24) {
			return d + "小时前";
		}

		d = getDifferenceDay(now, date);
		
		if(d < 365) {
			return d + "天前";
		}


		return UtilDateTime.format(date, YYYY_MM_DD_HH_MM_SS);
	}
}
