package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.XMLData;

import bean.SoftWare;

public class OTM extends AbstractTableModel{

	private static ArrayList<SoftWare>  softWares;
	
	public static final int COLUMN_COUNT = 1;      //总共有多少行
	public static final int MAX_ROW_ON_SCREEN = 13;//屏幕上能显示的最大行数
	
	private static SoftWare setting;
	
	public OTM(){
		setting = new SoftWare("设置", "setting");
		softWares = XMLData.getSoftWares();
		updateRowValue();
	}
	
	
	@Override
	public int getRowCount() {
//		System.out.println("获取当前行数"+quickPaths.size());

		
		
		return edit().size();
	}

	@Override
	public int getColumnCount() {
//		System.out.println("获取当前列数");
		return COLUMN_COUNT;
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return "["+rowIndex+"]    "+edit().get(rowIndex).getName();
	}




	
	
	public static ArrayList<SoftWare> getSoftWares() {
		return softWares;
	}




	public void updateRowValue(){
		softWares = XMLData.getSoftWares();
	}
	
	
	public static SoftWare getSoftWareByRow(int focusRow){
		
		return edit().get(focusRow);
	}
	public static boolean isAB(int row){
		SoftWare sw = getSoftWareByRow(row);
		if( sw.getPath().equals("A") && sw.isOpen()==true ){
			return true;
		}
		else if( sw.getPath().equals("B") ){
			return true;
		}
		return false;
	}
	
	public  static ArrayList<SoftWare> edit(){
		ArrayList<SoftWare> editeds = new ArrayList<SoftWare>();
		
		boolean jumping = false;
		editeds.add(setting);
		
		boolean useAB = false;
		int aNum = 0;
		int bNum = 0;
		for(SoftWare sw:softWares){
			if( sw.getPath().equals("A") ){
				aNum++;
			}
			else if( sw.getPath().equals("B") ){
				bNum++;
			}
		}
		if( aNum==bNum ){
			useAB = true;
		}
		

		for(int index=1;index<=softWares.size();index++){
			SoftWare sw = null;
			for(SoftWare swBuff:softWares){
				if( swBuff.getIndex()==index ){
					sw = swBuff;
				}
			}
			
			if( sw.getPath().equals("A")){
				editeds.add(sw);
				//用于验证有无结尾，有结尾则跳跃，无则不跳
				if(  sw.isOpen()==false && useAB){
					for(SoftWare sw2:softWares){
						if( sw2.getIndex()>sw.getIndex() && sw2.getPath().equals("B") ){
							jumping = true;
						}
					}
				}
			}
			else if(  sw.getPath().equals("B") ){
				if(jumping){
					jumping = false;
				}
				else{
					editeds.add(sw);
				}
			}
			else if( !jumping ){
				editeds.add(sw);
			}
		}
		
		return editeds;
	}



    
	

}
