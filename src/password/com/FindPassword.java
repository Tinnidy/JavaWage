package password.com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import mail.com.SendMail;
import mysql.com.DBUtil;
import parts.com.MyTextFieldBorder;

@SuppressWarnings("serial")
public class FindPassword extends JFrame {
	JTextField id;
	JTextField qq;
	JButton save;
	private static String ID = null;
	private static String TOUSER = null;
	private static String PWD = null;
	public FindPassword() {
		init_layout();
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID = id.getText();
				TOUSER = qq.getText();
				if (ID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "ID不能为空");
				} else if (TOUSER.isEmpty()) {
					JOptionPane.showMessageDialog(null, "你的QQ邮箱不能为空");
				} else {
					String sql = "select Mail from worker where Worker_ID=?";
					Object[] params = {ID};
					String QQ = DBUtil.queryObject(sql, params).toString();
					if (!QQ.equals(TOUSER)) {
						JOptionPane.showMessageDialog(null, "你的QQ邮箱不正确");
					} else {
						String sql1 = "select Pwd from worker where Worker_ID=?";
						Object[] params1 = {ID};
						PWD = DBUtil.queryObject(sql1, params1).toString();
						if (!PWD.isEmpty()) {
							SendMail mail = new SendMail();
							mail.setTargetAddress(TOUSER);
							mail.setSubject("您的密码：");
							mail.setContent(PWD);
							mail.start();
							JOptionPane.showMessageDialog(null, "邮件已经发送，请注意查收");
							dispose();
						}
					}
				}
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void init_layout() {
		setTitle("找回密码");
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setOpaque(false);
		contentPane.setLayout(null);
		this.setSize(400, 250);
		this.setUndecorated(true);
		this.setResizable(false);
		this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ImageIcon image = new ImageIcon("image\\changepassword.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setSize(490, 335);
		getLayeredPane().add(labelimage, new Integer(Integer.MIN_VALUE));
		
		JLabel usrJLabel = new JLabel("你的ID");
		usrJLabel.setOpaque(false);
		usrJLabel.setForeground(Color.BLACK);
		usrJLabel.setLocation(10, 0);
		usrJLabel.setSize(70, 30);
		id = new JTextField();
		id.setBorder(new MyTextFieldBorder());
		id.setOpaque(false);
		id.setForeground(Color.BLACK);
		id.setSize(180, 30);
		id.setLocation(80, 0);
		JPanel contentPane1 = new JPanel();
		contentPane1.setOpaque(false);
		contentPane1.setLayout(null);
		contentPane1.add(usrJLabel);
		contentPane1.add(id);
		contentPane1.setBounds(65, 30, 270, 30);
		contentPane.add(contentPane1);
		
		JLabel usrJLabel1 = new JLabel("你的qq邮箱");
		usrJLabel1.setOpaque(false);
		usrJLabel1.setForeground(Color.BLACK);
		usrJLabel1.setLocation(10, 0);
		usrJLabel1.setSize(70, 30);
		qq = new JTextField();
		qq.setBorder(new MyTextFieldBorder());
		qq.setOpaque(false);
		qq.setForeground(Color.BLACK);
		qq.setSize(180, 30);
		qq.setLocation(80, 0);
		JPanel contentPane2 = new JPanel();
		contentPane2.setOpaque(false);
		contentPane2.setLayout(null);
		contentPane2.add(usrJLabel1);
		contentPane2.add(qq);
		contentPane2.setBounds(65, 90, 270, 30);
		contentPane.add(contentPane2);
		
		save = new JButton("确定");
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		save.setForeground(Color.BLACK);
		save.setLocation(85, 0);
		save.setSize(95, 30);
		JPanel contentPane4 = new JPanel();
		contentPane4.setOpaque(false);
		contentPane4.setLayout(null);
		contentPane4.setOpaque(false);
		contentPane4.setBounds(65, 150, 270, 30);
		contentPane4.add(save);
		contentPane.add(contentPane4);
	}
}
