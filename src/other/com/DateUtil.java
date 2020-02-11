package other.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static int getCurrentMonthDay(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	public static int getDay() {
		Date date = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("dd");
		return Integer.parseInt(ft.format(date));
	}
}
