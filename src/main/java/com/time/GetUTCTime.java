package com.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
/**
 * 通过时区获取的时间都是错误的
 * @author qiqi
 *
 */
public class GetUTCTime {

	// 取得本地时间：
	private Calendar	cal			= Calendar.getInstance();
	// 取得时间偏移量：
	private int			zoneOffset	= cal.get(java.util.Calendar.ZONE_OFFSET);
	// 取得夏令时差：
	private int			dstOffset	= cal.get(java.util.Calendar.DST_OFFSET);

	public static void main(String[] args) throws ParseException {
		GetUTCTime gc = new GetUTCTime();
		long mill = gc.getUTCTimeStr();
		gc.setUTCTime(mill);
		System.out.println("------------------");
		gc.setUTCTime(1540432004580L);
		System.out.println("------------------");
		gc.UtcToLocal(1540432004580L);
		System.out.println("------------------");
		System.out.println("纽约时间：" + gc.getNeworkTime());
		System.out.println("------------------");
		System.out.println("中国时间：" + gc.getChinaTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("中国当前时间转纽约时间:" + DateTool.string2TimezoneDefault(sdf.format(new Date()), "America/New_York"));
		//转换为0时区时间作为参照点
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    	Date   d1=sf.parse("2016-3-14 12:00:00");
    	
        TimeZone york = TimeZone.getTimeZone("America/New_York"); //GMT-5
       	
        System.out.println("目标时区是否使用了夏令时:"+isDaylight(york, gc.getNeworkDate()));


	}

	public long getUTCTimeStr() {

		System.out.println("local millis = " + cal.getTimeInMillis()); // 等效System.currentTimeMillis() , 统一值，不分时区

		// 从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		long mills = cal.getTimeInMillis();
		System.out.println("UTC = " + mills);

		return mills;
	}

	public void setUTCTime(long millis) {

		cal.setTimeInMillis(millis);

		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = foo.format(cal.getTime());
		System.out.println("GMT time= " + time);

		// 从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, (zoneOffset + dstOffset));
		time = foo.format(cal.getTime());
		System.out.println("Local time = " + time);

	}

	public void UtcToLocal(long millis) {
		// 从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, (zoneOffset + dstOffset));
		cal.setTimeInMillis(millis);
		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = foo.format(cal.getTime());
		System.out.println("Local time = " + time);
	}

	public void getGMTTime() {
		//mothed 2
		TimeZone gmtTime = TimeZone.getTimeZone("GMT");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(gmtTime);
		System.out.println("GMT Time: " + format.format(date));

		//method 2
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeZone(gmtTime);
		//System.out.println(calendar1.getTime());    //时区无效
		//System.out.println(calendar1.getTimeInMillis()); //时区无效
		System.out.println("GMT hour = " + calendar1.get(Calendar.HOUR));
	}

	/**
	 * 取北京时间
	 * 
	 * @return
	 */
	public String getBeijingTime() {
		return getFormatedDateString(8);
	}

	/**
	 * 取班加罗尔时间
	 * 
	 * @return
	 */
	public String getBangaloreTime() {
		return getFormatedDateString(5.5f);
	}

	/**
	 * 取纽约时间 error
	 * 
	 * @return
	 */
	public String getNewyorkTime() {
		return getFormatedDateString(-5);
	}

	/**
	 * 获取纽约当前时间
	 * 
	 * @return
	 */
	public String getNeworkTime() {
		TimeZone tz = TimeZone.getTimeZone("America/New_York");
		Calendar cl = Calendar.getInstance(tz, Locale.US);
		DateFormat df = DateFormat.getDateTimeInstance();
		df.setTimeZone(tz);
		return df.format(cl.getTime());
	}
	public Date getNeworkDate(){
		TimeZone tz = TimeZone.getTimeZone("America/New_York");
		Calendar cl = Calendar.getInstance(tz, Locale.US);
		DateFormat df = DateFormat.getDateTimeInstance();
		df.setTimeZone(tz);
		return cl.getTime();
	}
	/**
	 * 此函数非原创，从网上搜索而来，timeZoneOffset原为int类型，为班加罗尔调整成float类型 timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
	 * 
	 * @param timeZoneOffset
	 * @return
	 */
	public String getFormatedDateString(float timeZoneOffset) {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}

		int newTime = (int) (timeZoneOffset * 60 * 60 * 1000);
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(newTime);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(newTime, ids[0]);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(timeZone);
		return sdf.format(new Date());
	}

	/**
	 * 获得东八区时间
	 *
	 * @return
	 */
	public static String getChinaTime() {
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(timeZone);
		return simpleDateFormat.format(new Date());
	}

	//判断是否在夏令时
	private static boolean isDaylight(TimeZone zone, Date date) {
		return zone.useDaylightTime() && zone.inDaylightTime(date);
	}
}