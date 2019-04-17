package application.helper.session;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {
	
	
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String d = sdf.format(date);
		return d;
	}
	
	
//	public String absencePreview(Date dateFrom, boolean absenceFromHalf, Date dateTo, boolean absenceToHalf) {
//		String string = "";
//		double days = 0;
//
//		long time = dateTo.getTime() - dateFrom.getTime();  // Difference in milli-seconds
//		days = Math.round((double) time / (24. * 60. * 60. * 1000.) + 1); // Difference in days
//		days = getRealAbsenceDays(dateFrom, days);
//		if (absenceFromHalf) {
//			days -= 0.5;
//		}
//		if (absenceToHalf) {
//			days -= 0.5;
//		}
//
//		return string;
//	}
	
	
//	public static double getRealAbsenceDays(Date dateFrom, Double dbl) {
//		int n = dbl.intValue();
//		int weekDay;
//		int absenceDays = 0;
//
//		Calendar calendarFrom = Calendar.getInstance();
//		calendarFrom.setTime(dateFrom);
//		weekDay = calendarFrom.get(Calendar.DAY_OF_WEEK);
//
//		for (int a = 1; a <= n; a++) {
//			if (weekDay == 7) {  // Check Saturday
//			} else if (weekDay == 1) {  // Check Sunday
//			} else {
//				absenceDays += 1;  // Urlaubstage addieren
//			}
//			calendarFrom.add(Calendar.DATE, 1);
//			weekDay = calendarFrom.get(Calendar.DAY_OF_WEEK);
//		}
//		return absenceDays;
//	}
	
	
}
