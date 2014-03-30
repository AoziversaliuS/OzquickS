package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.XMLData;

import bean.SoftWare;

public class OTM extends AbstractTableModel{

	public static final int MODE_SOFTWARE_VIEW = 1,MODE_SOFTWARE_SET = 2;
	
	private static ArrayList<String> rowValue;
	private static int contentMode = 1;
	
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
		this.rowValue = rowValue;
	}
	public static void setModel(int MODE){
		contentMode = MODE;
		if(contentMode==OTM.MODE_SOFTWARE_SET){
			rowValue = new ArrayList<String>();
			rowValue.add("����");
			rowValue.add("���");
			rowValue.add("�ڴ�����ֵ");
			rowValue.add("����");
			rowValue.add("�ڴ�����ֵ");
			rowValue.add("ɾ��");
			rowValue.add("�ڴ�����ֵ");
			rowValue.add("���á�����");
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
