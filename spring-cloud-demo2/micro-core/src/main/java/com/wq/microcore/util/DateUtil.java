package com.wq.microcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String format(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String format(Date date,String formatStr){
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		return df.format(date);
	}
	
	public static Date parse(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(date);
		} catch (ParseException e) {}
		return null;
	}
	
	public static Date parse(String date,String formatStr){
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		try {
			return df.parse(date);
		} catch (ParseException e) {}
		return null;
	}
	
	public static Date addDate(Date date,int datanum){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, datanum);
		return cal.getTime();
	}
	public static Date addHour(Date date,int hournum){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hournum);
		return cal.getTime();
	}
	
	public static int checkWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	//获取某个月的天数
	public static int getDayCountInMonth(int year,int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH,month-1);
		return cal.getActualMaximum(Calendar.DATE);
	}
	//solr上的时间比本地少8小时
	public static String formatSolrSearchDate(Date date){
		Date earlyDate = addHour(date,-8);
		String str = format(earlyDate,"yyyy-MM-dd'T'HH:mm:ss'Z'");
		return str;
	}
	public static String formatSolrSearchDate(String dateStr){
		Date date = parse(dateStr);
		Date earlyDate = addHour(date,-8);
		String str = format(earlyDate,"yyyy-MM-dd'T'HH:mm:ss'Z'");
		return str;
	}
	
	public static String getNowTime(){
		return format(new Date());
	}
	
	public static void main(String[] args){
		String s = "2015-08-01 23:36:01";
		Date d =parse(s,"yyyy-MM-dd HH:mm:ss");
		Date d2 = addHour(d,8);
		System.out.println(formatSolrSearchDate(d));
	}
	
}
