package testproject;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MemberController extends AbstractTableModel{
	ArrayList list;
	String[] column= {"ID","Pass","Email"};
			
	public MemberController() {
		list = new ArrayList();
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return column[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		String[] member = (String[])list.get(row);
		return member[col];
	}
	
	
}
