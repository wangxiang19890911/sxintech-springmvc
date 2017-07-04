package cn.xsintech.utils;

import java.util.Date;

public class Dates {

	private static final ThreadLocal<Date> threadDate = new ThreadLocal<Date>() {

		@Override
		protected Date initialValue() {
			return new Date();
		}

	};

	public static Date getThreadDate() {
		return threadDate.get();
	}

	public static void resetThreadDate() {
		threadDate.remove();
	}

}
