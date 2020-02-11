package add.com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;

import mysql.com.DBUtil;
import other.com.DateUtil;
import other.com.JudgeString;
import parts.com.MyTextFieldBorder;

@SuppressWarnings("serial")
public class AddStaff extends JFrame {
	private JTextField textField11;
	private JTextField textField12;
	private JPasswordField textField21;
	private JPasswordField textField22;
	private JComboBox<String> textField31;
	private JTextField textField32;
	private JTextField textField41;
	private JComboBox<String> textField42;
	private JTextField textField51;
	private JTextField textField52;
	private JButton btnNewButton1;
	private JButton btnNewButton2;
	public AddStaff() {
		init_layout();
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addDate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public void init_layout() {
		this.setTitle("添加员工");
		this.setUndecorated(true);
		this.setResizable(false);
		this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		this.setBounds(0, 0, 400, 300);
		
		ImageIcon image = new ImageIcon("image\\staff.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setLocation(0, 0);
		labelimage.setSize(490, 335);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(labelimage, new Integer(Integer.MIN_VALUE));
		
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setOpaque(false);
		panel1.setBounds(0, 20, 400, 30);
		getContentPane().add(panel1);
		
		JLabel lblNewLabel11 = new JLabel("工号");
		lblNewLabel11.setBounds(20, 0, 30, 30);
		panel1.add(lblNewLabel11);
		textField11 = new JTextField();
		textField11.setOpaque(false);
		textField11.setBorder(new MyTextFieldBorder());
		textField11.setBounds(50, 2, 100, 26);
		panel1.add(textField11);
		
		JLabel lblNewLabel12 = new JLabel("姓名");
		lblNewLabel12.setBounds(220, 0, 30, 30);
		panel1.add(lblNewLabel12);
		textField12 = new JTextField();
		textField12.setOpaque(false);
		textField12.setBorder(new MyTextFieldBorder());
		textField12.setBounds(250, 2, 100, 26);
		panel1.add(textField12);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setOpaque(false);
		panel2.setBounds(0, 60, 400, 30);
		getContentPane().add(panel2);
		
		JLabel lblNewLabel21 = new JLabel("密码");
		lblNewLabel21.setBounds(20, 0, 30, 30);
		panel2.add(lblNewLabel21);
		textField21 = new JPasswordField();
		textField21.setOpaque(false);
		textField21.setBorder(new MyTextFieldBorder());
		textField21.setBounds(50, 2, 100, 26);
		panel2.add(textField21);
		
		JLabel lblNewLabel22 = new JLabel("确定密码");
		lblNewLabel22.setBounds(195, 0, 55, 30);
		panel2.add(lblNewLabel22);
		textField22 = new JPasswordField();
		textField22.setOpaque(false);
		textField22.setBorder(new MyTextFieldBorder());
		textField22.setBounds(250, 2, 100, 26);
		panel2.add(textField22);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setOpaque(false);
		panel3.setBounds(0, 100, 400, 30);
		getContentPane().add(panel3);
		
		JLabel lblNewLabel31 = new JLabel("性别");
		lblNewLabel31.setBounds(20, 0, 30, 30);
		panel3.add(lblNewLabel31);
		textField31 = new JComboBox();
		textField31.addItem("男");
		textField31.addItem("女");
		textField31.setOpaque(false);
		UIManager.put("ComboBox.background", new Color(0,0,0,0));
		textField31.setUI(new BasicComboBoxUI() {
            public void installUI(JComponent comboBox) {
                super.installUI(comboBox);
                listBox.setForeground(Color.WHITE);
                listBox.setSelectionBackground(new Color(0,0,0,0));
                listBox.setSelectionForeground(Color.BLACK);
            }
            protected JButton createArrowButton() {
            	JButton b = new JButton();
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setVisible(false);
                return b;
            }
        });
		textField31.setBounds(50, 2, 100, 26);
		panel3.add(textField31);
		
		JLabel lblNewLabel32 = new JLabel("身份证号");
		lblNewLabel32.setBounds(195, 0, 55, 30);
		panel3.add(lblNewLabel32);
		textField32 = new JTextField();
		textField32.setOpaque(false);
		textField32.setBorder(new MyTextFieldBorder());
		textField32.setBounds(250, 2, 100, 26);
		panel3.add(textField32);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setOpaque(false);
		panel4.setBounds(0, 140, 400, 30);
		getContentPane().add(panel4);
		
		JLabel lblNewLabel41 = new JLabel("银行卡号");
		lblNewLabel41.setBounds(0, 0, 55, 30);
		panel4.add(lblNewLabel41);
		textField41 = new JTextField();
		textField41.setOpaque(false);
		textField41.setBorder(new MyTextFieldBorder());
		textField41.setBounds(50, 2, 100, 26);
		panel4.add(textField41);
		
		JLabel lblNewLabel42 = new JLabel("职位");
		lblNewLabel42.setBounds(220, 0, 30, 30);
		panel4.add(lblNewLabel42);
		textField42 = new JComboBox();
		textField42.setUI(new BasicComboBoxUI() {
            public void installUI(JComponent comboBox) {
                super.installUI(comboBox);
                listBox.setSelectionBackground(new Color(0,0,0,0));
            }
            protected JButton createArrowButton() {
            	JButton b = new JButton();
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setVisible(false);
                return b;
            }
        });
		textField42.addItem("管理员");
		textField42.addItem("用户");
		textField42.setBounds(250, 2, 100, 26);
		panel4.add(textField42);
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setOpaque(false);
		panel5.setBounds(0, 180, 400, 30);
		getContentPane().add(panel5);
		
		JLabel lblNewLabel51 = new JLabel("mail");
		lblNewLabel51.setBounds(20, 0, 30, 30);
		panel5.add(lblNewLabel51);
		textField51 = new JTextField();
		textField51.setOpaque(false);
		textField51.setBorder(new MyTextFieldBorder());
		textField51.setBounds(50, 2, 100, 26);
		panel5.add(textField51);
		
		JLabel lblNewLabel52 = new JLabel("部门ID");
		lblNewLabel52.setBounds(195, 0, 55, 30);
		panel5.add(lblNewLabel52);
		textField52 = new JTextField();
		textField52.setOpaque(false);
		textField52.setBorder(new MyTextFieldBorder());
		textField52.setBounds(250, 2, 100, 26);
		panel5.add(textField52);
		
		
		JPanel panel6 = new JPanel();
		panel6.setLayout(null);
		panel6.setOpaque(false);
		panel6.setBounds(0, 220, 400, 30);
		getContentPane().add(panel6);
		
		btnNewButton1 = new JButton("确定");
		btnNewButton1.setContentAreaFilled(false);
		btnNewButton1.setBorderPainted(false);
		btnNewButton1.setBounds(230, 0, 60, 30);
		panel6.add(btnNewButton1);
		
		btnNewButton2 = new JButton("关闭");
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBounds(310, 0, 60, 30);
		panel6.add(btnNewButton2);
	}
	
	public void addDate() throws SQLException {
		String id = textField11.getText();
		String usr = textField12.getText();
		
		String pwd = String.valueOf(textField21.getPassword());
		String pwd1 = String.valueOf(textField22.getPassword());
		String gender = textField31.getSelectedItem().toString();
		String ID_number = textField32.getText();
		String Bank_account = textField41.getText();
		String position = textField42.getSelectedItem().toString();
		String mail = textField51.getText();
		String Department_ID = textField52.getText();
		
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "工号不能为空");
		} else if (usr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "姓名不能为空");
		} else if (pwd.isEmpty()) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
		} else if (pwd1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "确定密码不能为空");
		} else if (!JudgeString.isIDCard(ID_number)) {
			JOptionPane.showMessageDialog(null, "身份证号输入有误");
		} else if (!JudgeString.isBankCard(Bank_account)) {
			JOptionPane.showMessageDialog(null, "银行卡号输入有误");
		} else if (!JudgeString.isEmailAddress(mail)) {
			JOptionPane.showMessageDialog(null, "qq邮箱输入有误");
		}  else {
			
			/* 判断部门是否存在 */
			String sql = "select * from department where Dept_id=?";
			Object[] params = {Department_ID};
			boolean flag = DBUtil.queryExist(sql, params);
			if (!flag) {
				if(JOptionPane.showConfirmDialog(null, "部门不存在，是否创建新的部门？","确认",JOptionPane.OK_CANCEL_OPTION) == 0) {
					AddDept addDept = new AddDept(Department_ID);
					addDept.setLocationRelativeTo(null);
					addDept.setVisible(true);
				}
				return;
			}
			
			String sql1 =  "select * from worker where Worker_ID=?";
			Object[] params1 = {id};
			boolean flag1 = DBUtil.queryExist(sql1, params1);
			if(!flag1){
				if (pwd.equals(pwd1)) {
					String sql2 = "insert into worker(Worker_ID, Worker_name, Sex, Department_ID, ID_number, Identity, Bank_account, Pwd, Mail) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
					Object[] params2 = {id, usr, gender, Department_ID, ID_number, position, Bank_account, pwd, mail};
					int count2 = DBUtil.noQuery(sql2, params2);
					if (count2 > 0) {
						dispose();
						/* 本月的天数 */
						Date dt = new Date();
						int Month_days = 0;
						try {
							Month_days = DateUtil.getCurrentMonthDay(String.format("%tY-%tm-%td", dt, dt, dt));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						int Absent_days = DateUtil.getDay() - 1;
						
						String sql3 = "insert into checks(Worker_ID, Month_days, Absent_days) values(?, ?, ?)";
						Object[] params3 = {id, Month_days, Absent_days};
						DBUtil.noQuery(sql3, params3);
						JOptionPane.showMessageDialog(null, "添加成功");
					} else {
						JOptionPane.showMessageDialog(null, "添加失败");
					}
				} else {
					JOptionPane.showMessageDialog(null, "密码和确定密码不一样");
				}
			} else {
				JOptionPane.showMessageDialog(null, "工号已存在");
			}
		}
	}
}
