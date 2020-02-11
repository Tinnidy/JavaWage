package main.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import interfacefile.com.Constant;
import login.com.Login;
import mysql.com.DBUtil;
import operation.com.BankInfo;
import operation.com.Checks;
import operation.com.Dept;
import operation.com.Staff;
import operation.com.Wages;
import other.com.Personal;
import other.com.TimerWater;
import password.com.ChangePassword;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JMenuItem personal_menu;
	private JMenuItem modify_menu;
	private JMenuItem account_menu;
	private JMenuItem exit_menu;
	private JTree tree;
	JInternalFrame inFrame;
	JDesktopPane desktopPane;
	private JLabel lblNewLabel1;
	private JMenu menu;
	String usr_id;
	public MainWindow(final String usr_id) {
		this.usr_id = usr_id;
		
		TimerWater timerWater = new TimerWater();
		timerWater.contextInitialized(null);
		
		init_layout();
		
		String sql1 = "select Worker_name from worker where Worker_ID=?";
		Object[] params1 = {usr_id};
		Object usr_name = DBUtil.queryObject(sql1, params1);
		lblNewLabel1.setText(usr_name.toString());
		
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		desktopPane.setLocation(100, 30);
		desktopPane.setSize(500, 335);
		ImageIcon image = new ImageIcon("image\\main.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setLocation(0, 0);
		labelimage.setBackground(Color.BLACK);
		labelimage.setSize(500, 370);
		desktopPane.add(labelimage);
		getContentPane().add(desktopPane);
		
		/* 菜单中个人信息的点击事件 */
		personal_menu.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				if(inFrame != null)
					inFrame.dispose();	
				Personal personal = new Personal(usr_id);
				
				personal.setBounds(0,0,500,335);
// 				try {
//					personal.setMaximum(true);
//				} catch (PropertyVetoException e1) {
//					e1.printStackTrace();
//				}
				personal.setVisible(true);
				desktopPane.add(personal);
				inFrame = personal;
			}
		});
		
		/* 菜单中修改密码的点击事件 */
		modify_menu.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				if(inFrame != null)
					inFrame.dispose();
				ChangePassword changePassword = new ChangePassword(usr_id);
				
				changePassword.setBounds(0,0,500,335);
				
//				try {
//					changePassword.setMaximum(true);
//				} catch (PropertyVetoException e1) {
//					e1.printStackTrace();
//				}
				changePassword.setVisible(true);
				desktopPane.add(changePassword);
				inFrame = changePassword;
			}
		});
		
		/* 菜单中退出账号的点击事件 */
		account_menu.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				int valuex = JOptionPane.showConfirmDialog(null, "您是否确定退出当前账号", null, JOptionPane.YES_NO_OPTION);
				if (valuex == JOptionPane.YES_OPTION) {
					dispose();
					Login login = new Login();
					login.setLocationRelativeTo(null);
					login.setVisible(true);
				}
			}
		});
		
		/* 菜单中退出系统的点击事件 */
		exit_menu.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				int valuex = JOptionPane.showConfirmDialog(null, "您是否确定退出当前程序", null, JOptionPane.YES_NO_OPTION);
				if (valuex == JOptionPane.YES_OPTION) {
					System.exit(ABORT); 
				}
			}
		});
		
		/* 为 tree 添加鼠标点击事件 */
		tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == tree && e.getClickCount() == 1) {
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                    if (selPath != null)
                    {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                        try {
							clickEvents(node.toString());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
                    }
                }
            }
        });
	}
	
	/* tree 点击事件 */
	public void clickEvents(String event) throws SQLException {
		if (event.equals("员工管理")) {
			if(inFrame != null)
				inFrame.dispose();	
			Staff staff = new Staff(usr_id);
			
			staff.setBounds(0,0,500,335);
//			try {
//				staff.setMaximum(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
			staff.setVisible(true);
			desktopPane.add(staff);
			inFrame = staff;
		} else if (event.equals("部门管理")) {
			if(inFrame != null)
				inFrame.dispose();	
			Dept dept = new Dept(usr_id);
			dept.setBounds(0,0,500,335);
//			try {
//				dept.setMaximum(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
			dept.setVisible(true);
			desktopPane.add(dept);
			inFrame = dept;
		} else if (event.equals("出勤管理")) {
			if(inFrame != null)
				inFrame.dispose();	
			Checks checks = new Checks(usr_id);
			
			checks.setBounds(0,0,500,335);
//			try {
//				checks.setMaximum(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
			checks.setVisible(true);
			desktopPane.add(checks);
			inFrame = checks;
		} else if (event.equals("工资管理")) {
			if(inFrame != null)
				inFrame.dispose();	
			Wages wages = new Wages(usr_id);
			
			wages.setBounds(0,0,500,335);
//			try {
//				wages.setMaximum(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
			wages.setVisible(true);
			desktopPane.add(wages);
			inFrame = wages;
		} else if (event.equals("银行账单")) {
			if(inFrame != null)
				inFrame.dispose();	
			BankInfo wages = new BankInfo(usr_id);
			
			wages.setBounds(0,0,500,335);
//			try {
//				wages.setMaximum(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
			wages.setVisible(true);
			desktopPane.add(wages);
			inFrame = wages;
		}
	}
	
	/* 图形界面 */
	public void init_layout() {
		this.setTitle("学校工资管理界面");
		this.setUndecorated(true);
		this.setResizable(false);
		this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		this.setLocationRelativeTo(null);
		this.setBounds(450, 200, Constant.WIDTH, Constant.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(false);
		menuBar.setBounds(0, 0, 590, 31);
		contentPane.add(menuBar);
		
		JPanel contentPane1 = new JPanel();
		contentPane1.setOpaque(false);
		menuBar.add(contentPane1);
		contentPane1.setLayout(null);
		contentPane1.setBounds(0, 0, 490, 30);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 0, 60, 30);
		contentPane1.add(lblNewLabel);
		
		lblNewLabel1 = new JLabel("");
		lblNewLabel1.setFont(new Font("华文行楷", Font.PLAIN, 20));
		lblNewLabel1.setBounds(65, 0, 473, 30);
		contentPane1.add(lblNewLabel1);
		
		menu = new JMenu("个人中心");
		menuBar.add(menu);
		
		personal_menu = new JMenuItem("我的资料");
		menu.add(personal_menu);
		modify_menu = new JMenuItem("修改密码");
		menu.add(modify_menu);
		account_menu = new JMenuItem("切换账号");
		menu.add(account_menu);
		exit_menu = new JMenuItem("退出系统");
		menu.add(exit_menu);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		root.setParent(null);
		DefaultMutableTreeNode nodeFirstA = new DefaultMutableTreeNode("系统设置");
		nodeFirstA.add(new DefaultMutableTreeNode("员工管理"));
		nodeFirstA.add(new DefaultMutableTreeNode("部门管理"));
		root.add(nodeFirstA);
		
		DefaultMutableTreeNode nodeFirstB = new DefaultMutableTreeNode("基本资料");
		nodeFirstB.add(new DefaultMutableTreeNode("出勤管理"));
		root.add(nodeFirstB);
		
		DefaultMutableTreeNode nodeFirstC = new DefaultMutableTreeNode("业务操作");
		nodeFirstC.add(new DefaultMutableTreeNode("工资管理"));
		nodeFirstB.add(new DefaultMutableTreeNode("银行账单"));
		root.add(nodeFirstC);
		
		tree = new JTree(root);
		tree.setFont(new Font("黑体", Font.PLAIN, 15));
		tree.setOpaque(false);
		tree.setRootVisible(false);
		tree.setSize(100, 370);
		tree.setLocation(0, 30);
		tree.setRowHeight(20);
		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		DefaultTreeCellRenderer treeCellRenderer;
		treeCellRenderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeCellRenderer.setOpaque(false);
		treeCellRenderer.setLeafIcon(null);
		treeCellRenderer.setClosedIcon(null);
		treeCellRenderer.setOpenIcon(null);
		treeCellRenderer.setBackground(null);
		treeCellRenderer.setTextNonSelectionColor(Color.BLACK);
		treeCellRenderer.setTextSelectionColor(Color.BLUE);
		
		treeCellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
		treeCellRenderer.setBackgroundSelectionColor(new Color(0, 0, 0, 0));
        tree.setCellRenderer(treeCellRenderer);
        
		Enumeration<?> enumeration;
		enumeration = root.preorderEnumeration();
		while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) enumeration.nextElement();
			if (!node.isLeaf()) {
				TreePath path = new TreePath(node.getPath());
				tree.expandPath(path);
			}
		}
		contentPane.add(tree);
	}
}
