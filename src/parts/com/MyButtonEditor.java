package parts.com;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import interfacefile.com.Constant;
import other.com.Change;

@SuppressWarnings("serial")
public class MyButtonEditor extends AbstractCellEditor implements TableCellRenderer,ActionListener, TableCellEditor {
    private JPanel jPanel = new JPanel();
    private JButton modifyButton;
    private JButton deleteButton;
	JTable table;
	int row;
	DefaultTableModel model;

    public MyButtonEditor(final String name) {
        jPanel.setLayout(null);
        jPanel.setOpaque(false);
        
		modifyButton = new JButton("修改");
        modifyButton.setContentAreaFilled(false);
        modifyButton.setBorderPainted(false);
        modifyButton.setBorder(BorderFactory.createEtchedBorder());
        modifyButton.setBounds(0,0,30,20);
        
        deleteButton = new JButton("删除");
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setBorder(BorderFactory.createEtchedBorder());
        deleteButton.setBounds(35,0,30,20);
        
        jPanel.add(modifyButton);
        jPanel.add(deleteButton);
        
        final Change change = new Change();
        
        modifyButton.addActionListener(new ActionListener() {
           // @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == modifyButton){
            		String string = "是否确定修改？";
            		int i = JOptionPane.showConfirmDialog(null, string, "确认", JOptionPane.OK_CANCEL_OPTION);
            		if (i == 0) {
            			if (name.equals(Constant.AWARD)) {
            				
						} else if (name.equals(Constant.BANKINFO)) {
							
						} else if (name.equals(Constant.CHECKS)) {
							change.changeCheck(model, row);
						} else if (name.equals(Constant.DEPARTMENT)) {
							change.changeDept(model, row);
						} else if (name.equals(Constant.payroll_award_view)) {
							change.changeWages(model, row);
						} else if (name.equals(Constant.WORKER)) {
							change.changeWorker(model, row);
						}
            		}
            	}
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
           // @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == deleteButton){
            		fireEditingStopped();
            		int i = JOptionPane.showConfirmDialog(null, "是否确定删除？", "确认", JOptionPane.OK_CANCEL_OPTION);
            		if (i == 0) {
            			if (name.equals(Constant.AWARD)) {
            				
						} else if (name.equals(Constant.BANKINFO)) {
							
						} else if (name.equals(Constant.CHECKS)) {
							change.deleteCheck(model, row);
						} else if (name.equals(Constant.DEPARTMENT)) {
							change.deleteDept(model, row);
						} else if (name.equals(Constant.payroll_award_view)) {
							change.deleteWages(model, row);
						} else if (name.equals(Constant.WORKER)) {
							change.deleteWorker(model, row);
						}
            		}
            	}
            }
        });
    }
    
    //@Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	this.table = table;
    	this.model = (DefaultTableModel) table.getModel();
    	this.row = row;
        return jPanel;
    }
    
   // @Override
    public Object getCellEditorValue() {
        return null;
    }
    
   // @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return jPanel;
    }
    
	//@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
