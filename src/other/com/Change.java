package other.com;

import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mysql.com.DBUtil;

@SuppressWarnings("serial")
public class Change extends AbstractCellEditor {
	public void changeWorker(DefaultTableModel model, int row) {
		String Worker_ID = model.getValueAt(row, 0).toString();
		
		String Sex = model.getValueAt(row, 2).toString();
		String Department_ID = model.getValueAt(row, 3).toString();
		String ID_number = model.getValueAt(row, 4).toString();
		String Identity = model.getValueAt(row, 5).toString();
		String Bank_account = model.getValueAt(row, 6).toString();
		String Mail = model.getValueAt(row, 7).toString();
		
		if (Department_ID == null || ID_number == null || Bank_account == null || Mail == null) {
			return;
		} else if (Department_ID.isEmpty() || ID_number.isEmpty() || Bank_account.isEmpty() || Mail.isEmpty() ||
				!JudgeString.isIDCard(ID_number) || !JudgeString.isBankCard(Bank_account) || !JudgeString.isEmailAddress(Mail)) {
			JOptionPane.showMessageDialog(null, "输入有误");
			return;
		}
		
		/* 判断部门是否存在 */
		String sql = "select * from department where Dept_id=?";
		Object[] params = {Department_ID};
		boolean flag = DBUtil.queryExist(sql, params);
		if (!flag) {
			JOptionPane.showMessageDialog(null, "部门不存在");
			return;
		}
		
		String sql2 = "update worker set Sex=?, Department_ID=?, ID_number=?, Identity=?, Bank_account=?, Mail=? where Worker_ID=?";
		Object[] params2 = {Sex, Department_ID, ID_number, Identity, Bank_account, Mail, Worker_ID};
		int count = DBUtil.noQuery(sql2, params2);
		if (count > 0) {
			JOptionPane.showMessageDialog(null, "修改成功");
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}
	
	public void deleteWorker(DefaultTableModel model, int row) {
		String sql = "delete from worker where Worker_ID=?";
		Object id = model.getValueAt(row, 0);
		Object[] params = {id};
		int count = DBUtil.noQuery(sql, params);
		if (count > 0) {
			fireEditingStopped();
			model.removeRow(row);
		} else {
			JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	
	public void changeDept(DefaultTableModel model, int row) {
		String depID = model.getValueAt(row, 0).toString();
		String depName = model.getValueAt(row, 1).toString();
		if (depName == null) {
			return;
		} else if (depName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "名称不能为空");
			return;
		}
		
		String sql = "update department set Dept_name=? where Dept_id=?";
		Object[] params = {depName, depID};
		int count = DBUtil.noQuery(sql, params);
		if (count > 0) {
			JOptionPane.showMessageDialog(null, "修改成功");
			
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}
	
	public void deleteDept(DefaultTableModel model, int row) {
		String sql = "delete from department where Dept_id=?";
		Object id = model.getValueAt(row, 0);
		Object[] params = {id};
		int count = DBUtil.noQuery(sql, params);
		if (count > 0) {
			fireEditingStopped();
			model.removeRow(row);
		} else {
			JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	
	public void changeCheck(DefaultTableModel model, int row) {
		String ID = model.getValueAt(row, 0).toString();
		
		String Month_days = model.getValueAt(row, 3).toString();
		
		String Work_days = model.getValueAt(row, 4).toString();
		String Absent_days = model.getValueAt(row, 5).toString();
		String Leave_days = model.getValueAt(row, 6).toString();
		String Late_days = model.getValueAt(row, 7).toString();
		String Leave_early_days = model.getValueAt(row, 8).toString();
		
		if (Work_days == null || Absent_days == null || Leave_days == null || Late_days == null || Leave_early_days == null) {
			System.out.println("gfhgfhfg");
			return;
		} else if (Work_days.isEmpty() || Absent_days.isEmpty() || Leave_days.isEmpty() || Late_days.isEmpty() || Leave_early_days.isEmpty() ||
				!JudgeString.isComparativeSize_integer(Month_days, Work_days) || !JudgeString.isComparativeSize_integer(Month_days, Absent_days) ||
				!JudgeString.isComparativeSize_integer(Month_days, Leave_days) || !JudgeString.isComparativeSize_integer(Month_days, Late_days) ||
				!JudgeString.isComparativeSize_integer(Month_days, Leave_early_days)) {
			JOptionPane.showMessageDialog(null, "输入有误");
			return;
		}
		
		String sql = "update checks set Work_days=?, Absent_days=?, Leave_days=?, Late_days=?, Leave_early_days=? where ID=?";
		Object[] params = {Work_days, Absent_days, Leave_days, Late_days, Leave_early_days, ID};
		int count = DBUtil.noQuery(sql, params);
		if (count > 0) {
			JOptionPane.showMessageDialog(null, "修改成功");
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}
	
	public void deleteCheck(DefaultTableModel model, int row) {
		String sql = "delete from checks where ID=?";
		Object id = model.getValueAt(row, 0);
		Object[] params = {id};
		int count = DBUtil.noQuery(sql, params);
		if (count > 0) {
			fireEditingStopped();
			model.removeRow(row);
		} else {
			JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	
	public void changeWages(DefaultTableModel model, int row) {
		String ID = model.getValueAt(row, 0).toString();
		String Base_pay = model.getValueAt(row, 4).toString();
		
		if (Base_pay == null) {
			return;
		} else if (Base_pay.isEmpty() || !JudgeString.isNumeric(Base_pay)) {
			JOptionPane.showMessageDialog(null, "输入有误");
			return;
		}
		
		String sql = "select Base_pay from payroll where ID=?";
		Object[] params = {ID};
		String Base_pay_old = DBUtil.queryObject(sql, params).toString();
		
		String sql1 = "select Equip_pay from payroll where ID=?";
		Object[] params1 = {ID};
		String Equip_pay_old = DBUtil.queryObject(sql1, params1).toString();
		
		
		
		float Equip_pay_new = (Float.parseFloat(Base_pay)) - Float.parseFloat(Base_pay_old) + Float.parseFloat(Equip_pay_old);
		
		String Equip_pay = String.valueOf(Equip_pay_new);
		
		String sql2 = "update payroll set Base_pay=?, Equip_pay=? where ID=?";
		Object[] params2 = {Base_pay, Equip_pay, ID};
		int count = DBUtil.noQuery(sql2, params2);
		if (count > 0) {
			JOptionPane.showMessageDialog(null, "修改成功");
		} else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}
	
	public void deleteWages(DefaultTableModel model, int row) {
		String sql = "delete from Base_pay where ID=?";
		Object id = model.getValueAt(row, 0);
		Object[] params = {id};
		int count = DBUtil.noQuery(sql, params);
		if (count > 0) {
			fireEditingStopped();
			model.removeRow(row);
		} else {
			JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	
	//@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
