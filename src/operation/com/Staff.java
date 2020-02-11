package operation.com;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import add.com.AddStaff;
import interfacefile.com.Constant;
import mysql.com.DBUtil;
import mysql.com.GetTableData;
import other.com.JudgeString;
import parts.com.MyButtonEditor;
import parts.com.MyTextFieldBorder;
import parts.com.SetTableSize;

@SuppressWarnings("serial")
public class Staff extends JInternalFrame {
	private static JTable table;
	private static JTextField textField1;
	private static JTextField textField2;
	private static JTextField textField3;
	private JButton btnNewButton;
	private JButton update;
	private static DefaultTableModel tableModel;
	static String[] columnName;
	private JFrame frame = null;
	static String usr_position;
	public Staff(String usr_id) throws SQLException {
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
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frame != null)
					frame.dispose();
				if (usr_position.equals(Constant.POSITION)) {
					AddStaff addStaff = new AddStaff();
					addStaff.setLocationRelativeTo(null);
					addStaff.setVisible(true);
					frame = addStaff;
				} else {
					JOptionPane.showMessageDialog(null, "你没有权限添加");
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
		this.setTitle("员工管理");
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
		
		String[] column = GetTableData.getName("worker");
		columnName = new String[column.length - 1];
		int j = 0;
		for (int i = 0; i < column.length; i++) {
			if (!column[i].equals("Pwd")) {
				columnName[j++] = column[i];
			}
		}
		
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 15));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
				if(column == 0 || column == 1)
					return false;
				else
					return true;
			}
			
		});
		SetTableSize.fitTableColumns(table);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(0, 30);
		scrollPane.setSize(488, 271);
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBounds(0, 0, 488, 30);
		getContentPane().add(panel);
		
		JLabel lblNewLabel1 = new JLabel("工号");
		lblNewLabel1.setBounds(10, 0, 30, 30);
		panel.add(lblNewLabel1);
		
		textField1 = new JTextField();
		textField1.setOpaque(false);
		textField1.setBorder(new MyTextFieldBorder());
		textField1.setBounds(40, 2, 80, 26);
		panel.add(textField1);
		
		JLabel lblNewLabel2 = new JLabel("姓名");
		lblNewLabel2.setBounds(130, 0, 30, 30);
		panel.add(lblNewLabel2);
		
		textField2 = new JTextField();
		textField2.setOpaque(false);
		textField2.setBorder(new MyTextFieldBorder());
		textField2.setBounds(160, 2, 80, 26);
		panel.add(textField2);
		
		JLabel lblNewLabel3 = new JLabel("职位");
		lblNewLabel3.setBounds(250, 0, 30, 30);
		panel.add(lblNewLabel3);
		
		textField3 = new JTextField();
		textField3.setOpaque(false);
		textField3.setBorder(new MyTextFieldBorder());
		textField3.setBounds(280, 2, 80, 26);
		panel.add(textField3);
		
		btnNewButton = new JButton("搜索");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(360, 0, 30, 30);
		btnNewButton.setBorder(BorderFactory.createEtchedBorder());
		panel.add(btnNewButton);
		
		update = new JButton("添加");
		update.setContentAreaFilled(false);
		update.setBorderPainted(false);
		update.setBounds(390, 0, 30, 30);
		update.setBorder(BorderFactory.createEtchedBorder());
		panel.add(update);
	}
	
	public static void getdate() throws SQLException {
		tableModel = (DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("男");
		comboBox.addItem("女");
		
		JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.addItem("管理员");
		comboBox1.addItem("用户");
		
		String idString = textField1.getText();
		String naneString= textField2.getText();
		String positionString = textField3.getText();
		
		String sql =  "select * from worker where 1";
		if (!idString.isEmpty()) {
			sql += " and Worker_ID = '" + idString + "'";
		}
		if (!naneString.isEmpty()) {
			sql += " and Worker_name = '" + naneString + "'";
		}
		if (!positionString.isEmpty()) {
			sql += " and Identity = '" + positionString + "'";
		}
		Object[] playerInfo = new Object[columnName.length];
		ResultSet result = DBUtil.queryResultSet(sql);
		while (result.next()) {
			for (int i = 0; i < playerInfo.length - 1; i++) {
	//			if (columnName[i].equals("ID_number") || columnName[i].equals("Bank_account")) {
	//				playerInfo[i] = JudgeString.idMask(result.getString(columnName[i]), 4, 4);
	//			} else {
					playerInfo[i] = result.getString(columnName[i]);
	//			}
			}
			table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));
			table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBox1));
			table.getColumnModel().getColumn(columnName.length - 1).setCellRenderer(new MyButtonEditor(Constant.WORKER));
			table.getColumnModel().getColumn(columnName.length - 1).setCellEditor(new MyButtonEditor(Constant.WORKER));
			tableModel.addRow(playerInfo);
		}
		DBUtil.close(result);
	}
}
