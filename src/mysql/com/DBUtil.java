package mysql.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	/* 获取连接 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wagesystem?characterEncoding=UTF8&useOldAliasMetadataBehavior=true", "root", "tingting");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/* 关闭连接 */
	public static void close(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* 得到单个结果 */
	public static Object queryObject(String sql, Object[] params) {
		Object obj = null;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				obj = rs.getObject(1);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	/* 得到结果集 */
	public static ResultSet queryResultSet(String sql) {
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/* 增删改操作 */
	public static int noQuery(String sql, Object[] params) {
		int n = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(params!=null)
				for(int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			n = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	/* 查询是否存在 */
	public static boolean queryExist(String sql,Object[] params){
		boolean find = false;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(params!=null)
				for(int i=0; i<params.length; i++){
					pstmt.setObject(i+1, params[i]);
				}
			ResultSet rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				 find = true;
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return find;
	}
}
