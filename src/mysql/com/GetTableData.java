package mysql.com;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;

import other.com.JudgeString;
import parts.com.MyButtonEditor;

public class GetTableData {
	public static String[] getName(String tableName) throws SQLException {
		String sql = "select * from " + tableName;
		/* 得到结果集 */
		ResultSet result = DBUtil.queryResultSet(sql);
		ResultSetMetaData data = result.getMetaData();
		String[] columnName;
		/* 创建数组的大小 */
		columnName = new String[data.getColumnCount() + 1];
		int i = 0;
		for(; i < data.getColumnCount(); i++){
			/* 获取表的列名 */
			columnName[i] = data.getColumnLabel(i + 1);
		}
		columnName[i] = "操作";
		DBUtil.close(result);
		return columnName;
	}
	
	public static String[][] getData(JTable table, String tableName, String condition, String[] columnName) throws SQLException {
		String sql = "select * from " + tableName + " where 1";
		if (!condition.isEmpty()) {
			sql = sql + condition;
		}
		
		ResultSet result = DBUtil.queryResultSet(sql);
		ResultSetMetaData meta = result.getMetaData();
        result.last();
        int rowCount = result.getRow();
        int columeCount = meta.getColumnCount();
		String[][] data = new String[rowCount][columeCount];
		result = DBUtil.queryResultSet(sql);
		
		int length = columnName.length - 1;
		
		for (int i = 0; i < rowCount; i++) {
			result.next();
			for (int j = 0; j < length; j++) {
				/* 获取表的内容 */
				data[i][j] = result.getString(columnName[j]);
		//		if (columnName[j].equals("ID_number") || columnName[j].equals("Bank_account")) {
		//			data[i][j] = JudgeString.idMask(data[i][j], 4, 4);
		//		}
				
			}
			table.getColumnModel().getColumn(columnName.length - 1).setCellRenderer(new MyButtonEditor(tableName));
			table.getColumnModel().getColumn(columnName.length - 1).setCellEditor(new MyButtonEditor(tableName));
		}
		DBUtil.close(result);
		return data;
	}
}
