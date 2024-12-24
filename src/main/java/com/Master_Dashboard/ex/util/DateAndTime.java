package com.Master_Dashboard.ex.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateAndTime {

	/*
	 * This function is using for storing Current Date and Time in database.
	 */
	public static Timestamp currentDateAndTime() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	/*
	 * This Function is using for API Response Current Date & Time.
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return f.format(new Date());
	}

	public static String getCurrentTimeInIST() throws ParseException {
		DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		utcFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		Date timestamp = new Date();
		return utcFormat.format(timestamp);
	}

	public static String formatTimestampInIST(Timestamp timestamp) {
		DateFormat istFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.S");
		istFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		return istFormat.format(timestamp);
	}

	public static String getCurrentTimeInISTS() {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		return formatTimestampInIST(currentTimestamp);
	}
	
	/*
	 * this method return currentdate
	 */
	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}
	
	/**
	 * convert locatedate to string
	 * @param date
	 * @return
	 */
	public static String convertLocalDateToString(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static String getBeforeDate(LocalDate currentDate, int day) {
		LocalDate date = currentDate.minusDays(day);
		 return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
