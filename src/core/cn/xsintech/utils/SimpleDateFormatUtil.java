package cn.xsintech.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimpleDateFormatUtil {

	/** Lock the Object */
	private static final Object lockObj = new Object();

	/** a Map to save different sdf */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * return a ThreadLocal sdf, each thread new a sdf once only
	 * 
	 * @param pattern
	 * @return SimpleDateFormat
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		// to prevent duplicated pattern in the map
		if (tl == null) {
			synchronized (lockObj) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// put the sdf to the map if it doesn't exist
					System.out.println("put new sdf of pattern " + pattern + " to map");
					// use ThreadLocal<SimpleDateFormat> instead of new
					// SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {
						@Override
						protected SimpleDateFormat initialValue() {
							System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}
		return tl.get();
	}

	/**
	 * use ThreadLocal <SimpleDateFormat>to get SimpleDateFormat, so each thread will have only one SimpleDateFormat object
	 * 
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSdf(pattern).parse(dateStr);
//		System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
//		return new SimpleDateFormat(pattern).parse(dateStr);
	}

	public static Date getLastOneMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
}
