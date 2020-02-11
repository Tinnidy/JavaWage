package add.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import mysql.com.DBUtil;

public class AddWages {
	/* 计算每个人的工资 */
	
	public static void insertWages() throws SQLException, ParseException {
		/* 奖扣表的生成 */
		/* 获取所有的员工出勤情况 */
		/* 本月 */
		Date dt = new Date();
		String dString =String.format("%tY-%tm", dt, dt);
		/* 本月的出勤情况 */
		String sql = "select * from checks where Time like '" + dString + "%'";
		ResultSet rs = DBUtil.queryResultSet(sql);
		
		while(rs != null && rs.next()) {
			/* 获取ID */
			String Worker_ID = rs.getString("Worker_ID");
			/* 获取出勤情况 */
			int Absent_days = Integer.parseInt(rs.getString("Absent_days"));
			int Leave_days = Integer.parseInt(rs.getString("Leave_days"));
			int Late_days = Integer.parseInt(rs.getString("Late_days"));
			int Leave_early_days = Integer.parseInt(rs.getString("Leave_early_days"));
			
			/* 计算扣款金额 */
			int deduction = Absent_days * 200 + Leave_days * 100 + Late_days * 20 + Leave_early_days * 20;
			
			/* 奖金 : 全勤奖金*/
			int award = 0;
			if (deduction == 0) {
				award = 100;
			}
			
			String textString = "奖金 : 全勤奖金  扣款：deduction = Absent_days * 200 + Leave_days * 100 + Late_days * 20 + Leave_early_days * 20";
			
			String sql1 = "select * from payroll_award_view where Worker_ID ='" + Worker_ID + "' and Time like '" + dString + "%'";
			ResultSet rs1 = DBUtil.queryResultSet(sql1);
			if (rs1.next()) {
				System.out.println(123);
				continue;
			}
			
			/* 插入数据到奖扣表 */
			String sql2 = "insert into award(Worker_ID, Award_numm, Deduction_num, Award_info) values(?, ?, ?, ?)";
			Object[] params2 = {Worker_ID, award, deduction, textString};
			DBUtil.noQuery(sql2, params2);
			
			/* 获取本人的奖扣表的ID */
			String sql3 = "select max(Award_ID) from award where Worker_ID=?";
			Object[] params3 = {Worker_ID};
			Object Award_ID = DBUtil.queryObject(sql3, params3);
			
			/* 计算工资表 */
			/* 基本工资 */
			int Base_pay = 6000;
			
			int Income_tax = 0;
			
			int Equip_pay = Base_pay + award - deduction - Income_tax;
			
			/* 插入数据到payroll */
			String sql4 = "insert into payroll(Worker_ID, Base_pay, Award_ID, Income_tax, Equip_pay) values(?, ?, ?, ?, ?)";
			Object[] params4 = {Worker_ID, Base_pay, Award_ID, Income_tax, Equip_pay};
			DBUtil.noQuery(sql4, params4);
		}
	}
}
