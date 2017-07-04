package cn.xsintech.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

	public static final ThreadLocal<DateFormat> YYYYMMDD = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}

	};

	public static final ThreadLocal<DateFormat> YYYYMM = new ThreadLocal<DateFormat>() {
		
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMM");
		}
		
	};

	public static final ThreadLocal<DateFormat> YYYYMMDDHHMMSS = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}

	};

	private static final ThreadLocal<DateFormat> LOCALIZE_LONG_FORMAT = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}

	};

	private static final ThreadLocal<DateFormat> LOCALIZE_SHORT_FORMAT = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd");
		}

	};

	public static String formatToLongPattern(Date d) {
		return LOCALIZE_LONG_FORMAT.get().format(d);
	}

	public static String formatToShortPattern(Date d) {
		return LOCALIZE_SHORT_FORMAT.get().format(d);
	}
	
	/**
	 * dateDiffMonth
	 * 
	 * @param Date date1
	 * @param Date date2
	 * @return int
	 */
	public static final int dateDiffMonth(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int time = calendar.get(Calendar.YEAR) * 12;
		calendar.setTime(date2);
		time -= calendar.get(Calendar.YEAR) * 12;
		calendar.setTime(date1);
		time += calendar.get(Calendar.MONTH);
		calendar.setTime(date2);
		return time - calendar.get(Calendar.MONTH);
	}

	public static final int[] getPastMonthsCompriseThisMonth(int n) {
		if (n < 1) {
			return new int[0];
		}

		int[] months = new int[n];
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < n; i ++ ) {
			int month = calendar.get(Calendar.MONTH) + 1 - i;
			if (month < 1) {
				month = month + 12;
			}
			months[i] = month;
		}
		return months;
	}
}
