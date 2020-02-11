package add.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import mysql.com.DBUtil;
import parts.com.MyTextFieldBorder;

@SuppressWarnings("serial")
public class AddDept extends JFrame {
	private JTextField textField11;
	private JTextField textField12;
	private JButton btnNewButton1;
	private JButton btnNewButton2;
	private String id;
	public AddDept(String id) {
		this.id = id;
		init_layout();
		/* 搜索点击事件 */
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addDate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		/* 关闭点击事件 */
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void init_layout() {
		this.setTitle("添加部门");
		this.setUndecorated(true);
		this.setResizable(false);
		this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		this.setBounds(0, 0, 400, 270);
		
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
		
		JLabel lblNewLabel11 = new JLabel("部门号");
		lblNewLabel11.setBounds(20, 0, 60, 30);
		panel1.add(lblNewLabel11);
		textField11 = new JTextField();
		textField11.setOpaque(false);
		textField11.setBorder(new MyTextFieldBorder());
		textField11.setBounds(80, 2, 100, 26);
		panel1.add(textField11);
		
		JLabel lblNewLabel12 = new JLabel("名称");
		lblNewLabel12.setBounds(220, 0, 30, 30);
		panel1.add(lblNewLabel12);
		textField12 = new JTextField();
		textField12.setBounds(260, 3, 100, 26);
		panel1.add(textField12);
		textField12.setOpaque(false);
		textField12.setBorder(new MyTextFieldBorder());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setOpaque(false);
		panel2.setBounds(0, 200, 400, 30);
		getContentPane().add(panel2);
		
		btnNewButton1 = new JButton("确定");
		btnNewButton1.setContentAreaFilled(false);
		btnNewButton1.setBorderPainted(false);
		btnNewButton1.setBounds(230, 0, 60, 30);
		panel2.add(btnNewButton1);
		
		btnNewButton2 = new JButton("关闭");
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBounds(310, 0, 60, 30);
		panel2.add(btnNewButton2);
		
		textField11.setText(id);
	}
	
	public void addDate() throws SQLException {
		/* 获取输入的数据 */
		String depID = textField11.getText();
		String depName = textField12.getText();
		
		if (depID.isEmpty()) {
			JOptionPane.showMessageDialog(null, "部门号不能为空");
		} else if (depName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "名称不能为空");
		} else {
			/* 判断部门号是否存在 */
			String sql =  "select * from department where Dept_id=?";
			Object[] params = {depID};
			boolean flag = DBUtil.queryExist(sql, params);
			if(!flag){
				String sql1 = "insert into department(Dept_id, Dept_name) values(?, ?)";
				Object[] params1 = {depID, depName};
				int count1 = DBUtil.noQuery(sql1, params1);
				if (count1 > 0) {
					dispose();
					JOptionPane.showMessageDialog(null, "添加成功");
				} else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
			} else {
				JOptionPane.showMessageDialog(null, "部门号已存在");
			}
		}
	}
}
