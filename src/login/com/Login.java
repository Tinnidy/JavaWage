package login.com;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import interfacefile.com.Constant;
import main.com.MainWindow;
import mysql.com.DBUtil;
import parts.com.MyTextFieldBorder;
import password.com.FindPassword;

@SuppressWarnings("serial")
public class Login extends JFrame implements MouseListener{
	private JTextField usrJTextField;
	private JPasswordField password;
	private JButton logButton;
	private JButton exitButton;
	String loginname;
	String txtpassword;
	
	JRadioButton rdbtnNewRadioButton;
	JRadioButton rdbtnNewRadioButton1;
	
	private JLabel label1;
	
	String identity = "管理员";
	
	FindPassword findPassword;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public <HttpServletResponse> Login() {
		init_layout();
		
		logButton.addMouseListener(this);
		exitButton.addMouseListener(this);
		label1.addMouseListener(this);
		rdbtnNewRadioButton.addMouseListener(this);
		rdbtnNewRadioButton1.addMouseListener(this);
	}
	
	@SuppressWarnings("deprecation")
	public <BackgroundPanel> void init_layout() {
		this.setTitle("学校工资管理登陆界面");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setUndecorated(true);
		this.setResizable(false);
		this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon image = new ImageIcon("image\\login.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setSize(Constant.WIDTH, Constant.HEIGHT);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(labelimage, new Integer(Integer.MIN_VALUE));
		
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setOpaque(false);
		
		JLabel label = new JLabel("学校工资管理系统");
		label.setLocation(0, 0);
		label.setSize(Constant.WIDTH, 150);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("华文行楷", Font.PLAIN, 50));
		JPanel contentPane1 = new JPanel();
		contentPane1.setOpaque(false);
		contentPane1.add(label);
		contentPane1.setSize(Constant.WIDTH, 150);
		contentPane1.setLayout(null);
		contentPane.add(contentPane1);
		
		JLabel usrJLabel = new JLabel();
		usrJLabel.setOpaque(false);
		usrJLabel.setIcon(new ImageIcon("image\\ic_edittextuname.png"));
		usrJLabel.setLocation(10, 0);
		usrJLabel.setSize(40, 30);
		usrJTextField = new JTextField();
		usrJTextField.setOpaque(false);
		usrJTextField.setForeground(Color.black);
		usrJTextField.setBorder(new MyTextFieldBorder());
		usrJTextField.setSize(150, 30);
		usrJTextField.setLocation(50, 0);
		JPanel contentPane2 = new JPanel();
		contentPane2.setLayout(null);
		contentPane2.setOpaque(false);
		contentPane2.add(usrJLabel);
		contentPane2.add(usrJTextField);
		contentPane2.setBounds(195, 170, 210, 30);
		getContentPane().add(contentPane2);
		
		JLabel pwdJLabel = new JLabel();
		pwdJLabel.setOpaque(false);
		pwdJLabel.setIcon(new ImageIcon("image\\ic_edittextupwd.png"));
		pwdJLabel.setBounds(10,0,40,30);
		password = new JPasswordField();
		password.setBorder(new MyTextFieldBorder());
		password.setOpaque(false);
		password.setForeground(Color.black);
		password.setBounds(50,0,150,30);
		JPanel contentPane3 = new JPanel();
		contentPane3.setLayout(null);
		contentPane3.setOpaque(false);
		contentPane3.add(pwdJLabel);
		contentPane3.add(password);
		contentPane3.setBounds(195, 210, 210, 30);
		getContentPane().add(contentPane3);
		JPanel contentPane4 = new JPanel();
		contentPane4.setLayout(null);
		contentPane4.setOpaque(false);
		contentPane4.setBounds(195, 250, 210, 30);
		getContentPane().add(contentPane4);
		
		rdbtnNewRadioButton = new JRadioButton("管理员", true);
		rdbtnNewRadioButton.setFont(new Font("华文行楷", Font.PLAIN, 14));
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.setBounds(50, 0, 70, 30);
		contentPane4.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton1 = new JRadioButton("用户");
		rdbtnNewRadioButton1.setFont(new Font("华文行楷", Font.PLAIN, 14));
		rdbtnNewRadioButton1.setBounds(130, 0, 65, 30);
		rdbtnNewRadioButton1.setOpaque(false);
		contentPane4.add(rdbtnNewRadioButton1);
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton1);
		
		logButton = new JButton("登陆");
		logButton.setContentAreaFilled(false);
		logButton.setBorderPainted(false);
		logButton.setForeground(Color.black);
		logButton.setLocation(30, 0);
		logButton.setSize(80, 35);
		logButton.setFont(new Font("华文行楷", Font.PLAIN, 20));
		exitButton = new JButton("退出");
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setForeground(Color.black);
		exitButton.setLocation(130, 0);
		exitButton.setSize(80, 35);
		exitButton.setFont(new Font("华文行楷", Font.PLAIN, 20));
		JPanel contentPane5 = new JPanel();
		contentPane5.setLayout(null);
		contentPane5.setOpaque(false);
		contentPane5.setBounds(195, 290, 210, 35);
		contentPane5.add(logButton);
		contentPane5.add(exitButton);
		getContentPane().add(contentPane5);
		
		label1 = new JLabel("<html><u>忘记密码？</u></html>");
		label1.setOpaque(false);
		label1.setFont(new Font("宋体", Font.PLAIN, 12));
		label1.setBounds(476, 336, 60, 23);
		getContentPane().add(label1);
	}
	
	//@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == logButton) {
			/* 点击登陆按钮事件 */
			loginname = usrJTextField.getText();
			txtpassword = String.valueOf(password.getPassword());
			 
			if (loginname.isEmpty()) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
			} else if (txtpassword.isEmpty()) {
				JOptionPane.showMessageDialog(null, "密码不能为空");
			} else if (identity.isEmpty()) {
				JOptionPane.showMessageDialog(null, "选择您的身份");
			} else {
				/* 判断账号密码是否正确 */
				String sql = "select * from worker where Worker_name=? and Pwd=? and Identity=?";
				Object[] params = {loginname, txtpassword, identity};
				boolean flag = DBUtil.queryExist(sql, params);
				if (flag) {
					dispose();
					/* 查询用户的id */
					String sql1 = "select Worker_ID from worker where Worker_name=? and Pwd=? and Identity=?";
					Object[] params1 = {loginname, txtpassword, identity};
					Object usr_id = DBUtil.queryObject(sql1, params1);
					/* 登陆成功，显示主界面，把id传过去，用于判断权限等功能 */
					MainWindow frame = new MainWindow(usr_id.toString());
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码不正确");
				}
			}
		} else if (e.getSource() == exitButton) {
			/* 点击退出按钮事件 */
			System.exit(ABORT);
		} else if (e.getSource() == label1) {
			/* 点击忘记密码事件 */
			if (findPassword != null) {
				findPassword.dispose();
			}
			findPassword = new FindPassword();
			findPassword.setLocationRelativeTo(null);
			findPassword.setVisible(true);
		} else if (e.getSource() == rdbtnNewRadioButton) {
	        identity = "管理员";
	    } else if (e.getSource() == rdbtnNewRadioButton1) {
	    	identity = "用户";
	    }
	}
	
	//@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == label1) {
			label1.setForeground(Color.RED);
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	//@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == label1) {
			label1.setForeground(Color.BLACK);
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	//@Override
	public void mousePressed(MouseEvent e) {
	} 

	//@Override
	public void mouseReleased(MouseEvent e) {
	}
}
