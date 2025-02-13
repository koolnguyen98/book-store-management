package com.esdc.bookstore.config.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {
	
	private static DateTimeUtil instance;
	
	public static DateTimeUtil getInstance() {
		if (instance == null) 
			instance = new DateTimeUtil();
		
		return instance;
	}
	
	public LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}
	
	public long getMilliSecondsBetween2Days(Date d1, Date d2) {
		return d2.getTime() - d1.getTime();
	}
	
	public int getSecond(long milliSecond) {
		return (int) milliSecond / 1000;
	}
	
	public double getHour(int second) {
		return (double) second / 3600;
	}
	
	public Date LocalDateTime2Date(LocalDateTime localDateTime) {
		return java.util.Date
		  .from(localDateTime.atZone(ZoneId.systemDefault())
		  .toInstant());
	}
	
	public LocalDate Date2LocalDate(Date date) {
		Instant instant = date.toInstant();
		return instant.atZone(ZoneId.systemDefault())
				  .toLocalDate();
	}
	
	public double getHoursBetween2Days(Date d1, Date d2) {
		long milliseconds = this.getMilliSecondsBetween2Days(d1, d2);
		int seconds = this.getSecond(milliseconds);
		
		return this.getHour(seconds);	
	}
	
	public String getCurrentDayAsString() {
		Date date = new Date();
		
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	 
}
