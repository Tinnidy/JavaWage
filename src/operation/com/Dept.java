package operation.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import add.com.AddDept;
import interfacefile.com.Constant;
import mysql.com.DBUtil;
import mysql.com.GetTableData;
import parts.com.MyTextFieldBorder;
import parts.com.SetTableSize;

@SuppressWarnings("serial")
public class Dept extends JInternalFrame {
	private JTable table;
	private JTextField textField1;
	private JTextField textField2;
	private JButton btnNewButton;
	private JButton update;
	private DefaultTableModel tableModel;
	String[] columnName;
	private JFrame frame;
	String tableName = "department";
	String usr_position;
	public Dept(String usr_id) throws SQLException {
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
				if (usr_position.equals(Constant.POSITION)) {
					if (frame != null)
						frame.dispose();
					AddDept addDept = new AddDept(null);
					addDept.setLocationRelativeTo(null);
					addDept.setVisible(true);
					frame = addDept;
				} else {
					JOptionPane.showMessageDialog(null, "你没有权限操作");
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
		this.setTitle("部门管理");
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
				if(column == columnName.length - 1 || column == columnName.length - 2)
					return true;
				else
					return false;
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
		
		JLabel lblNewLabel1 = new JLabel("部门号");
		lblNewLabel1.setBounds(10, 0, 40, 30);
		panel.add(lblNewLabel1);
		
		textField1 = new JTextField();
		textField1.setOpaque(false);
		textField1.setBorder(new MyTextFieldBorder());
		textField1.setBounds(50, 2, 80, 26);
		panel.add(textField1);
		
		JLabel lblNewLabel2 = new JLabel("名称");
		lblNewLabel2.setBounds(160, 0, 30, 30);
		panel.add(lblNewLabel2);
		
		textField2 = new JTextField();
		textField2.setOpaque(false);
		textField2.setBorder(new MyTextFieldBorder());
		textField2.setBounds(190, 3, 80, 26);
		panel.add(textField2);
		
		btnNewButton = new JButton("搜索");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(290, 0, 30, 30);
		btnNewButton.setBorder(BorderFactory.createEtchedBorder());
		panel.add(btnNewButton);
		
		update = new JButton("添加");
		update.setContentAreaFilled(false);
		update.setBorderPainted(false);
		update.setBounds(330, 0, 30, 30);
		update.setBorder(BorderFactory.createEtchedBorder());
		panel.add(update);
		
		tableModel = (DefaultTableModel)table.getModel();
	}
	
	public void getdate() throws SQLException {
		tableModel.setRowCount(0);
		
		String depID = textField1.getText();
		String depName = textField2.getText();
		String condition = "";
		if (!depID.isEmpty()) {
			condition += " and Dept_id='" + depID + "'";
		}
		if (!depName.isEmpty()) {
			condition += " and Dept_name='" + depName + "'";
		}
		/* 往表格添加内容 */
		String[][] data = GetTableData.getData(table, tableName, condition, columnName);
		for (int i = 0; i < data.length; i++) {
			tableModel.addRow(data[i]);
		}
	}
}