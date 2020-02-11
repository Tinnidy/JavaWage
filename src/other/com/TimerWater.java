package other.com;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import add.com.AddChecks;
import add.com.AddWages;

public class TimerWater implements ServletContextListener {
	//@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/* 指定的任务，从指定的延迟后，开始进行重复执行 */
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		/* 定制每天的8:00:00执行 */
		calendar.set(year, month, day, 8, 0, 0);
		Date sendDate = calendar.getTime();
		
		/* 每月任务，启动服务器后，若此时时间已经超过8点，会立刻执行一次，等到下个月再次执行一次，周而复始 */
		Timer mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Calendar c = Calendar.getInstance();
				int day = c.get(Calendar.DAY_OF_MONTH);
				if (day == 1) {              //每月一号生成
					/* 本月的考勤记录 */
					try {
						AddChecks.insertChecks();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}       
				}
				Date dt = new Date();
				try {
					if (day == DateUtil.getCurrentMonthDay(String.format("%tY-%tm-%td", dt, dt, dt))) {
						/* 本月工资处理 */
						AddWages.insertWages();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}, sendDate, 24 * 60 * 60 * 1000);/* 每天执行一次检查 */
	}

	//@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}