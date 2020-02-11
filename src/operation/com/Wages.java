package operation.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import interfacefile.com.Constant;
import mysql.com.DBUtil;
import mysql.com.GetTableData;
import other.com.IfRange;
import parts.com.MyTextFieldBorder;
import parts.com.SetTableSize;

@SuppressWarnings("serial")
public class Wages extends JInternalFrame {
	private JTable table;
	private JTextField textField11;
	private JTextField textField121;
	private JTextField textField122;
	private JTextField textField211;
	private JTextField textField212;
	private JTextField textField221;
	private JTextField textField222;
	private JTextField textField311;
	private JTextField textField312;
	private JTextField textField321;
	private JTextField textField322;
	private JButton btnNewButton;
	private JButton senior;
	private DefaultTableModel tableModel;
	String[] columnName;
	JScrollPane scrollPane;
	boolean seniorFlag = false;
	JPanel panel2;
	JPanel panel3;
	String tableName = "payroll_award_view";
	String usr_position;
	public Wages(String usr_id) throws SQLException {
		init_layout();
		
		String sql = "select Identity from worker where Worker_ID=?";
		Object[] params = {usr_id};
		usr_position = DBUtil.queryObject(sql, params).toString();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getdate();
					SetTableSize.fitTableColumns(table);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		senior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (seniorFlag == false) {
					seniorFlag = true;
					panel2.setVisible(true);
					panel3.setVisible(true);
					scrollPane.setLocation(0, 90);
					scrollPane.setSize(488, 210);
				} else {
					panel2.setVisible(false);
					panel3.setVisible(false);
					seniorFlag = false;
					scrollPane.setLocation(0, 30);
					scrollPane.setSize(488, 270);
				}
				
			}
		});
		table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
            	int col = table.columnAtPoint(event.getPoint());
            	if (!usr_position.equals(Constant.POSITION) && col == columnName.length - 1) {
            		JOptionPane.showMessageDialog(null, "你没有权限操作");
				}
            }
        });
	}
	
	@SuppressWarnings("deprecation")
	public void init_layout() throws SQLException {
		this.setTitle("工资管理");
		this.setClosable(true);
		
		ImageIcon image = new ImageIcon("image\\staff.jpg");
		JLabel labelimage = new JLabel(image);
		labelimage.setLocation(0, 0);
		labelimage.setSize(490, 335);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(labelimage, new Integer(Integer.MIN_VALUE));
		
		JPanel contentPane = ((JPanel)this.getContentPane());
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		
		columnName = GetTableData.getName(tableName);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(null, columnName) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (!usr_position.equals(Constant.POSITION)) {
					return false;
				}
				if(column == 4 || column == columnName.length - 2 || column == columnName.length - 1)
					return true;
				else
					return false;
			}
		});
		SetTableSize.fitTableColumns(table);
		scrollPane = new JScrollPane();
		scrollPane.setLocation(0, 30);
		scrollPane.setSize(488, 270);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false);
		table.setDefaultRenderer(Object.class, render);
		
		JTableHeader header = table.getTableHeader();
		header.setOpaque(false);
		header.setDefaultRenderer(render);
		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		if (headerRenderer instanceof JLabel) {
			((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);
			((JLabel) headerRenderer).setOpaque(false);
		}
		scrollPane.setColumnHeaderView(table.getTableHeader());
		scrollPane.getColumnHeader().setOpaque(false);
		contentPane.add(scrollPane);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setOpaque(false);
		panel1.setBounds(0, 0, 488, 30);
		getContentPane().add(panel1);
		
		JLabel lblNewLabel11 = new JLabel("姓名");
		lblNewLabel11.setBounds(10, 0, 40, 30);
		panel1.add(lblNewLabel11);
		
		textField11 = new JTextField();
		textField11.setOpaque(false);
		textField11.setBorder(new MyTextFieldBorder());
		textField11.setBounds(50, 2, 80, 26);
		panel1.add(textField11);
		
		JLabel lblNewLabel121 = new JLabel("时间");
		lblNewLabel121.setBounds(140, 0, 30, 30);
		panel1.add(lblNewLabel121);
		textField121 = new JTextField();
		textField121.setOpaque(false);
		textField121.setBorder(new MyTextFieldBorder());
		textField121.setBounds(170, 2, 80, 26);
		panel1.add(textField121);
		JLabel lblNewLabel12 = new JLabel("--");
		lblNewLabel12.setBounds(250, 0, 10, 30);
		panel1.add(lblNewLabel12);
		textField122 = new JTextField();
		textField122.setOpaque(false);
		textField122.setBorder(new MyTextFieldBorder());
		textField122.setBounds(260, 2, 80, 26);
		panel1.add(textField122);
		
		btnNewButton = new JButton("搜索");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(350, 0, 30, 30);
		btnNewButton.setBorder(BorderFactory.createEtchedBorder());
		panel1.add(btnNewButton);
		
		senior = new JButton("高级搜索");
		senior.setContentAreaFilled(false);
		senior.setBorderPainted(false);
		senior.setBounds(380, 0, 60, 30);
		senior.setBorder(BorderFactory.createEtchedBorder());
		panel1.add(senior);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setOpaque(false);
		panel2.setBounds(0, 30, 488, 30);
		getContentPane().add(panel2);
		
		JLabel lblNewLabel21 = new JLabel("奖金金额");
		lblNewLabel21.setBounds(0, 0, 55, 30);
		panel2.add(lblNewLabel21);
		
		textField211 = new JTextField();
		textField211.setOpaque(false);
		textField211.setBorder(new MyTextFieldBorder());
		textField211.setBounds(50, 2, 80, 26);
		panel2.add(textField211);
		
		JLabel lblNewLabel210 = new JLabel("--");
		lblNewLabel210.setBounds(130, 0, 10, 30);
		panel2.add(lblNewLabel210);
		
		textField212 = new JTextField();
		textField212.setOpaque(false);
		textField212.setBorder(new MyTextFieldBorder());
		textField212.setBounds(140, 2, 80, 26);
		panel2.add(textField212);
		
		JLabel lblNewLabel22 = new JLabel("扣款金额");
		lblNewLabel22.setBounds(230, 0, 55, 30);
		panel2.add(lblNewLabel22);
		
		textField221 = new JTextField();
		textField221.setOpaque(false);
		textField221.setBorder(new MyTextFieldBorder());
		textField221.setBounds(280, 2, 80, 26);
		panel2.add(textField221);
		panel2.setVisible(false);
		
		JLabel lblNewLabel220 = new JLabel("--");
		lblNewLabel220.setBounds(360, 0, 10, 30);
		panel2.add(lblNewLabel220);
		
		textField222 = new JTextField();
		textField222.setOpaque(false);
		textField222.setBorder(new MyTextFieldBorder());
		textField222.setBounds(370, 2, 80, 26);
		panel2.add(textField222);
		
		
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setOpaque(false);
		panel3.setBounds(0, 60, 488, 30);
		getContentPane().add(panel3);
		
		JLabel lblNewLabel31 = new JLabel("所得税");
		lblNewLabel31.setBounds(10, 0, 50, 30);
		panel3.add(lblNewLabel31);
		
		textField311 = new JTextField();
		textField311.setOpaque(false);
		textField311.setBorder(new MyTextFieldBorder());
		textField311.setBounds(50, 2, 80, 26);
		panel3.add(textField311);
		
		JLabel lblNewLabel310 = new JLabel("--");
		lblNewLabel310.setBounds(130, 0, 10, 30);
		panel3.add(lblNewLabel310);
		
		textField312 = new JTextField();
		textField312.setOpaque(false);
		textField312.setBorder(new MyTextFieldBorder());
		textField312.setBounds(140, 2, 80, 26);
		panel3.add(textField312);
		
		JLabel lblNewLabel321 = new JLabel("实际工资");
		lblNewLabel321.setBounds(230, 0, 55, 30);
		panel3.add(lblNewLabel321);
		
		textField321 = new JTextField();
		textField321.setOpaque(false);
		textField321.setBorder(new MyTextFieldBorder());
		textField321.setBounds(280, 2, 80, 26);
		panel3.add(textField321);
		panel3.setVisible(false);
		
		JLabel lblNewLabel320 = new JLabel("--");
		lblNewLabel320.setBounds(360, 0, 10, 30);
		panel3.add(lblNewLabel320);
		
		textField322 = new JTextField();
		textField322.setOpaque(false);
		textField322.setBorder(new MyTextFieldBorder());
		textField322.setBounds(370, 2, 80, 26);
		panel3.add(textField322);
		
		tableModel = (DefaultTableModel)table.getModel();
	}
	
	public void getdate() throws SQLException {
		tableModel.setRowCount(0);
		
		String Worker_name = textField11.getText();
		
		String minTime = textField121.getText();
		String maxTime = textField122.getText();
		
		String minAward_numm = textField211.getText();
		String maxAward_numm = textField212.getText();
		
		String minDeduction_num = textField221.getText();
		String maxDeduction_num = textField222.getText();
		
		String minIncome_tax = textField311.getText();
		String maxIncome_tax = textField312.getText();
		
		String minEquip_pay = textField321.getText();
		String maxEquip_pay = textField322.getText();
		
		String condition = "";
		if (!Worker_name.isEmpty()) {
			condition += " and Worker_name='" + Worker_name + "'";
		}
		
		int i = IfRange.ifData(minTime, maxTime);
		String temp = IfRange.addCondition(i, "Time", minTime, maxTime);
		if (temp == null) {
			JOptionPane.showMessageDialog(null, "条件输入有误");
			return;
		} else {
			condition += temp;
		}
		
		if (seniorFlag) {
			i = IfRange.ifRange(minAward_numm, maxAward_numm);
			temp = IfRange.addCondition(i, "Award_numm", minAward_numm, maxAward_numm);
			if (temp == null) {
				JOptionPane.showMessageDialog(null, "条件输入有误");
				return;
			} else if (!temp.equals("")) {
				condition += temp;
			}
			
			i = IfRange.ifRange(minDeduction_num, maxDeduction_num);
			temp = IfRange.addCondition(i, "Deduction_num", minDeduction_num, maxDeduction_num);
			if (temp == null) {
				JOptionPane.showMessageDialog(null, "条件输入有误");
				return;
			} else if (!temp.equals("")) {
				condition += temp;
			}
			
			i = IfRange.ifRange(minIncome_tax, maxIncome_tax);
			temp = IfRange.addCondition(i, "Income_tax", minIncome_tax, maxIncome_tax);
			if (temp == null) {
				JOptionPane.showMessageDialog(null, "条件输入有误");
				return;
			} else if (!temp.equals("")) {
				condition += temp;
			}
			
			i = IfRange.ifRange(minEquip_pay, maxEquip_pay);
			temp = IfRange.addCondition(i, "Equip_pay", minEquip_pay, maxEquip_pay);
			if (temp == null) {
				JOptionPane.showMessageDialog(null, "条件输入有误");
				return;
			} else if (!temp.equals("")) {
				condition += temp;
			}
		}
		String[][] data = GetTableData.getData(table, tableName, condition, columnName);
		for (int j = 0; j < data.length; j++) {
			tableModel.addRow(data[j]);
		}
	}
}