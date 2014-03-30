package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.XMLData;

import bean.SoftWare;

public class OTM extends AbstractTableModel{

	public static final int MODE_SOFTWARE_VIEW = 1,MODE_SOFTWARE_SET = 2;
	
	private static ArrayList<String> rowValue;
	private static int contentMode = 1;
	
	public static final int COLUMN_COUNT = 1;      //总共有多少行
	public static final int MAX_ROW_ON_SCREEN = 11;//屏幕上能显示的最大行数
	
	public OTM(){
		rowValue = new ArrayList<String>();
		ArrayList<SoftWare> softWares = XMLData.getSoftWares();
		
		rowValue.add(" ["+0+"]       设置");
		for(int i=1; i<=softWares.size(); i++){
			for(SoftWare sw:softWares){
				if( sw.getIndex()==i ){
					rowValue.add(" ["+i+"]       "+sw.getName());
					
				}
			}
		}
	}
	
	
	@Override
	public int getRowCount() {
//		System.out.println("获取当前行数"+quickPaths.size());
		return rowValue.size();
	}

	@Override
	public int getColumnCount() {
//		System.out.println("获取当前列数");
		return COLUMN_COUNT;
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
//		System.out.println("取值中"+rowIndex);
		return rowValue.get(rowIndex);
		
	}


	public ArrayList<String> getRowValue() {
		return rowValue;
	}


	public void setRowValue(ArrayList<String> rowValue) {
		this.rowValue = rowValue;
	}
	public static void setModel(int MODE){
		contentMode = MODE;
		if(contentMode==OTM.MODE_SOFTWARE_SET){
			rowValue = new ArrayList<String>();
			rowValue.add("返回");
			rowValue.add("添加");
			rowValue.add("在此输入值");
			rowValue.add("插入");
			rowValue.add("在此输入值");
			rowValue.add("删除");
			rowValue.add("在此输入值");
			rowValue.add("配置。。。");
		}
		OzFrame.ozTable.updateUI();
		OzFrame.ozTable.repaint();
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(contentMode==OTM.MODE_SOFTWARE_SET){
			if(rowIndex==2 || rowIndex==4 || rowIndex==6 ){
				return true;
			}
		}
		return false;
	}

    
	public static int getContentModel(){
		return contentMode;
	}
	

}
