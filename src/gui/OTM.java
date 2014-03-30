package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.XMLData;

import bean.SoftWare;

public class OTM extends AbstractTableModel{

	
	private static ArrayList<String> rowValue;
	
	public static final int COLUMN_COUNT = 1;      //�ܹ��ж�����
	public static final int MAX_ROW_ON_SCREEN = 11;//��Ļ������ʾ���������
	
	public OTM(){
		rowValue = new ArrayList<String>();
		ArrayList<SoftWare> softWares = XMLData.getSoftWares();
		
		rowValue.add(" ["+0+"]       ����");
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
//		System.out.println("��ȡ��ǰ����"+quickPaths.size());
		return rowValue.size();
	}

	@Override
	public int getColumnCount() {
//		System.out.println("��ȡ��ǰ����");
		return COLUMN_COUNT;
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
//		System.out.println("ȡֵ��"+rowIndex);
		return rowValue.get(rowIndex);
		
	}


	public ArrayList<String> getRowValue() {
		return rowValue;
	}


	public void setRowValue(ArrayList<String> rowValue) {
		OTM.rowValue = rowValue;
	}



    
	

}
