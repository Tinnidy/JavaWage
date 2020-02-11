package password.com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import mysql.com.DBUtil;
import other.com.JudgeString;
import parts.com.MyTextFieldBorder;

@SuppressWarnings("serial")
public class ChangePassword extends JInternalFrame {
	JPasswordField npwd1;
	JPasswordField npwd2;
	JPasswordField npwd3;
	JButton save;
	
	public ChangePassword(final String usr_id) {
		init_layout();
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string1 = String.valueOf(npwd1.getPassword());
				String string2 = String.valueOf(npwd2.getPassword());
				String string3 = String.valueOf(npwd3.getPassword());
				
				if (string1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "当前密码不能为空");
				} else if (string2.isEmpty()) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
				} else if (string3.isEmpty()) {
					JOptionPane.showMessageDialog(null, "确定密码不能为空");
				}else {
					if (!JudgeString.isChinese(string2) && !JudgeString.isChinese(string3)) {
						String sql =  "select Pwd from worker where Worker_ID=?";
						Object[] params = {usr_id};
						Object pwdString= DBUtil.queryObject(sql, params);
						if (!(pwdString.equals(string1))) {
							JOptionPane.showMessageDialog(null, "当前密码不正确");
						} else if (!(string2.equals(string3))) {
							JOptionPane.showMessageDialog(null, "密码和确定密码不一样");
						} else {
							String sql1 =  "update worker set Pwd=? where Worker_ID=?";
							Object[] params1 = {string2, usr_id};
							int count = DBUtil.noQuery(sql1, params1);
							if (count > 0) {
								JOptionPane.showMessageDialog(null, "修改成功");
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "修改失败");
							}
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
		setTitle("修改密码");
		setClosable(true);
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setOpaque(false);
		contentPane.setLayout(null);
		
		ImageIcon image = new ImageIcon("image\\changepassword.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setSize(490, 335);
		getLayeredPane().add(labelimage, new Integer(Integer.MIN_VALUE));
		
		JLabel usrJLabel = new JLabel("当前密码");
		usrJLabel.setOpaque(false);
		usrJLabel.setForeground(Color.BLACK);
		usrJLabel.setLocation(10, 0);
		usrJLabel.setSize(70, 30);
		npwd1 = new JPasswordField();
		npwd1.setBorder(new MyTextFieldBorder());
		npwd1.setOpaque(false);
		npwd1.setForeground(Color.BLACK);
		npwd1.setSize(180, 30);
		npwd1.setLocation(80, 0);
		JPanel contentPane1 = new JPanel();
		contentPane1.setOpaque(false);
		contentPane1.setLayout(null);
		contentPane1.add(usrJLabel);
		contentPane1.add(npwd1);
		contentPane1.setBounds(100, 30, 270, 30);
		contentPane.add(contentPane1);
		
		JLabel usrJLabel1 = new JLabel("密码");
		usrJLabel1.setOpaque(false);
		usrJLabel1.setForeground(Color.BLACK);
		usrJLabel1.setLocation(10, 0);
		usrJLabel1.setSize(70, 30);
		npwd2 = new JPasswordField();
		npwd2.setBorder(new MyTextFieldBorder());
		npwd2.setOpaque(false);
		npwd2.setForeground(Color.BLACK);
		npwd2.setSize(180, 30);
		npwd2.setLocation(80, 0);
		JPanel contentPane2 = new JPanel();
		contentPane2.setOpaque(false);
		contentPane2.setLayout(null);
		contentPane2.add(usrJLabel1);
		contentPane2.add(npwd2);
		contentPane2.setBounds(100, 90, 270, 30);
		contentPane.add(contentPane2);
		
		JLabel usrJLabel2 = new JLabel("确定密码");
		usrJLabel2.setOpaque(false);
		usrJLabel2.setForeground(Color.BLACK);
		usrJLabel2.setLocation(10, 0);
		usrJLabel2.setSize(70, 30);
		npwd3 = new JPasswordField();
		npwd3.setBorder(new MyTextFieldBorder());
		npwd3.setOpaque(false);
		npwd3.setForeground(Color.BLACK);
		npwd3.setSize(180, 30);
		npwd3.setLocation(80, 0);
		JPanel contentPane3 = new JPanel();
		contentPane3.setOpaque(false);
		contentPane3.setLayout(null);
		contentPane3.add(usrJLabel2);
		contentPane3.add(npwd3);
		contentPane3.setBounds(100, 150, 270, 30);
		contentPane.add(contentPane3);
		
		save = new JButton("修改");
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		save.setForeground(Color.BLACK);
		save.setLocation(85, 0);
		save.setSize(95, 30);
		JPanel contentPane4 = new JPanel();
		contentPane4.setOpaque(false);
		contentPane4.setLayout(null);
		contentPane4.setOpaque(false);
		contentPane4.setBounds(100, 220, 270, 30);
		contentPane4.add(save);
		contentPane.add(contentPane4);
	}
}
