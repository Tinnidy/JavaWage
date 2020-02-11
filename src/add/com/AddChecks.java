package add.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import mysql.com.DBUtil;
import other.com.DateUtil;

public class AddChecks {
	
	public static void insertChecks() throws SQLException, ParseException {
		/* 获取所有的员工ID */
		String id;
		String sql = "select * from worker";
		Date dt = new Date();
		String dString =String.format("%tY-%tm", dt, dt);
		
		ResultSet rs = DBUtil.queryResultSet(sql);
		while(rs != null && rs.next()) {
			boolean flag = true;
			id = rs.getString("Worker_ID");
			/* 本月的天数 */
			String dateString = String.format("%tY-%tm-%td", dt, dt, dt);
			int Month_days = DateUtil.getCurrentMonthDay(dateString);
			/* 从第几天开始 */
			int Absent_days = DateUtil.getDay() - 1;
			
			/* 判断数据库是否存在信息了 */
			String sql1 = "select * from checks where Worker_ID='" + id + "'";
			ResultSet rs1 = DBUtil.queryResultSet(sql1);
			while(rs1 != null && rs1.next()) {
				String m = (rs1.getString("Time")).substring(0,7);
				if (m.equals(dString)) {
					flag = false;
				}
			}
			/* 没有就插入数据 */
			if (flag) {
				String sql2 = "insert into checks(Worker_ID, Month_days, Absent_days) values(?, ?, ?)";
				Object[] params2 = {id, Month_days, Absent_days};
				DBUtil.noQuery(sql2, params2);
			}
		}
	}
}
