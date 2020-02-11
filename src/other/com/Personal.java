package other.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mysql.com.DBUtil;

@SuppressWarnings("serial")
public class Personal extends JInternalFrame {
	JLabel txtId;
	JLabel txtName;
	JLabel txtSex;
	JLabel txtDepartment;
	JLabel txtIDNumber;
	JLabel txtIdentity;
	JLabel txtBank_account;
	JLabel txtMail;
	
	JButton btnNewButton1;
	JButton btnNewButton2;
	
	public Personal(final String usr_id) {
		init_layout();
		txtId.setText(usr_id);
		
		String sql =  "select Worker_name from worker where Worker_ID=?";
		Object[] params = {usr_id};
		String usr_name = DBUtil.queryObject(sql, params).toString();
		txtName.setText(usr_name);
		
		String sql2 =  "select Sex from worker where Worker_ID=?";
		Object[] params2 = {usr_id};
		txtSex.setText(DBUtil.queryObject(sql2, params2).toString());
		
		String sql31 =  "select Department_ID from worker where Worker_ID=?";
		Object[] params31 = {usr_id};
		String Department_ID = DBUtil.queryObject(sql31, params31).toString();
		
		String sql32 =  "select Dept_name from department where Dept_id=?";
		Object[] params32 = {Department_ID};
		txtDepartment.setText(DBUtil.queryObject(sql32, params32).toString());
		
		String sql4 =  "select ID_number from worker where Worker_ID=?";
		Object[] params4 = {usr_id};
		txtIDNumber.setText(JudgeString.idMask(DBUtil.queryObject(sql4, params4).toString(), 4, 4));
		
		String sql5 =  "select Identity from worker where Worker_ID=?";
		Object[] params5 = {usr_id};
		txtIdentity.setText(DBUtil.queryObject(sql5, params5).toString());
		
		String sql6 =  "select Bank_account from worker where Worker_ID=?";
		Object[] params6 = {usr_id};
		txtBank_account.setText(JudgeString.idMask(DBUtil.queryObject(sql6, params6).toString(), 4, 4));
		
		String sql7 =  "select Mail from worker where Worker_ID=?";
		Object[] params7 = {usr_id};
		txtMail.setText(DBUtil.queryObject(sql7, params7).toString());
		
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] str={"男","女"};
				String gender = (String) JOptionPane.showInputDialog(getContentPane(), null, "性别", 1, null, str, str[0]);
				if(gender == null){
				} else {
					gender = gender.trim();
				    if("".equals(gender)){
				    } else {
				    	String sql =  "update worker set Sex=? where Worker_ID=?";
						Object[] params = {gender, usr_id};
						int count = DBUtil.noQuery(sql, params);
						if (count > 0) {
							txtSex.setText(gender);
							JOptionPane.showMessageDialog(null, "修改成功");
						} else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
				    }
				}
			}
		});
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = (String) JOptionPane.showInputDialog(getContentPane(), null, "邮箱", JOptionPane.PLAIN_MESSAGE, null, null, "输入你的新qq邮箱");
				if(mail == null){
				} else {
					mail = mail.trim();
				    if("".equals(mail)){
				    } else if (JudgeString.isEmailAddress(mail)) {
				    	String sql =  "update worker set Mail=? where Worker_ID=?";
						Object[] params = {mail, usr_id};
						int count = DBUtil.noQuery(sql, params);
						if (count > 0) {
							txtMail.setText(mail);
							JOptionPane.showMessageDialog(null, "修改成功");
						} else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
				    } else {
				    	JOptionPane.showMessageDialog(null, "输入有误");
					}
				}
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void init_layout() {
		setTitle("我的信息");
		setClosable(true);
		getContentPane().setLayout(null);
		
		ImageIcon image = new ImageIcon("image\\personal.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setLocation(0, 0);
		labelimage.setOpaque(false);
		labelimage.setSize(490, 335);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(labelimage, new Integer(Integer.MIN_VALUE));
		
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setOpaque(false);
		
		JPanel contentPane1 = new JPanel();
		contentPane1.setBounds(0, 10, 478, 30);
		contentPane1.setLayout(null);
		contentPane1.setOpaque(false);
		JLabel lableId = new JLabel("ID");
		lableId.setOpaque(false);
		lableId.setBounds(20, 0, 60, 30);
		txtId = new JLabel();
		txtId.setBounds(90, 0, 150, 30);
		contentPane1.add(lableId);
		contentPane1.add(txtId);
		contentPane.add(contentPane1);
		
		JPanel contentPane2 = new JPanel();
		contentPane2.setBounds(0, 45, 478, 30);
		contentPane2.setLayout(null);
		contentPane2.setOpaque(false);
		JLabel lableName = new JLabel("姓名");
		lableName.setOpaque(false);
		lableName.setBounds(20, 0, 60, 30);
		txtName = new JLabel();
		txtName.setBounds(90, 0, 150, 30);
		contentPane2.add(lableName);
		contentPane2.add(txtName);
		contentPane.add(contentPane2);
		
		JPanel contentPane3 = new JPanel();
		contentPane3.setBounds(0, 80, 478, 30);
		contentPane3.setLayout(null);
		contentPane3.setOpaque(false);
		JLabel lableGender = new JLabel("性别");
		lableGender.setOpaque(false);
		lableGender.setBounds(20, 0, 60, 30);
		txtSex = new JLabel();
		txtSex.setBounds(90, 0, 150, 30);
		contentPane3.add(lableGender);
		contentPane3.add(txtSex);
		contentPane.add(contentPane3);
		btnNewButton1 = new JButton("修改");
		btnNewButton1.setContentAreaFilled(false);
		btnNewButton1.setBorderPainted(false);
		btnNewButton1.setBounds(230, 0, 80, 30);
		contentPane3.add(btnNewButton1);
		
		JPanel contentPane4 = new JPanel();
		contentPane4.setBounds(0, 115, 478, 30);
		contentPane4.setLayout(null);
		contentPane4.setOpaque(false);
		JLabel lableDepartment = new JLabel("部门");
		lableDepartment.setOpaque(false);
		lableDepartment.setBounds(20, 0, 60, 30);
		txtDepartment = new JLabel();
		txtDepartment.setBounds(90, 0, 150, 30);
		contentPane4.add(lableDepartment);
		contentPane4.add(txtDepartment);
		contentPane.add(contentPane4);
		
		JPanel contentPane5 = new JPanel();
		contentPane5.setBounds(0, 150, 478, 30);
		contentPane5.setLayout(null);
		contentPane5.setOpaque(false);
		JLabel lableIDNumber = new JLabel("身份证号");
		lableIDNumber.setOpaque(false);
		lableIDNumber.setBounds(20, 0, 60, 30);
		txtIDNumber = new JLabel();
		txtIDNumber.setBounds(90, 0, 150, 30);
		contentPane5.add(lableIDNumber);
		contentPane5.add(txtIDNumber);
		contentPane.add(contentPane5);
		
		JPanel contentPane6 = new JPanel();
		contentPane6.setBounds(0, 185, 478, 30);
		contentPane6.setLayout(null);
		contentPane6.setOpaque(false);
		JLabel lableIdentity = new JLabel("ְ职位");
		lableIdentity.setOpaque(false);
		lableIdentity.setBounds(20, 0, 60, 30);
		txtIdentity = new JLabel();
		txtIdentity.setBounds(90, 0, 150, 30);
		contentPane6.add(lableIdentity);
		contentPane6.add(txtIdentity);
		contentPane.add(contentPane6);
		
		JPanel contentPane7 = new JPanel();
		contentPane7.setBounds(0, 220, 478, 30);
		contentPane7.setLayout(null);
		contentPane7.setOpaque(false);
		JLabel lableBank_account = new JLabel("ְ银行账号");
		lableBank_account.setOpaque(false);
		lableBank_account.setBounds(20, 0, 60, 30);
		txtBank_account = new JLabel();
		txtBank_account.setBounds(90, 0, 150, 30);
		contentPane7.add(lableBank_account);
		contentPane7.add(txtBank_account);
		contentPane.add(contentPane7);
		
		JPanel contentPane8 = new JPanel();
		contentPane8.setBounds(0, 255, 478, 30);
		contentPane8.setLayout(null);
		contentPane8.setOpaque(false);
		JLabel lableMail = new JLabel("ְQQ邮箱");
		lableMail.setOpaque(false);
		lableMail.setBounds(20, 0, 60, 30);
		txtMail = new JLabel();
		txtMail.setBounds(90, 0, 150, 30);
		contentPane8.add(lableMail);
		contentPane8.add(txtMail);
		contentPane.add(contentPane8);
		btnNewButton2 = new JButton("修改");
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBounds(230, 0, 80, 30);
		contentPane8.add(btnNewButton2);
	}
}
