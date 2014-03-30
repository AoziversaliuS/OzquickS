package logic;

import gui.OzFrame;
import gui.OTM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import util.XMLData;

import bean.Config;
import bean.SoftWare;

public class MouseRenderer extends DefaultTableCellRenderer implements MouseListener , MouseMotionListener{
	
	private static int focusRow = -1;
	public static Config c;
	
	public MouseRenderer(Config config){
		c = config;
	}
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		//软件浏览模式
		if(OTM.getContentModel()==OTM.MODE_SOFTWARE_VIEW){
			this.ignoreSelect();
			
			if(focusRow==0){
				OTM.setModel(OTM.MODE_SOFTWARE_SET);
			}
			else{
				SoftWare sw = XMLData.getSoftWare(focusRow);
				openFile(sw);
			}
		}
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
//		this.ignoreSelect();
		if(OzFrame.otm.getRowValue().size()>OTM.MAX_ROW_ON_SCREEN){
			OzFrame.ozTable.setPreferredSize(null);
		}
		
		
		focusRow = OzFrame.ozTable.rowAtPoint(e.getPoint());
		//软件浏览模式
		if(OTM.getContentModel()==OTM.MODE_SOFTWARE_VIEW){
			this.ignoreSelect();
		}
		//软件设置模式
		if(OTM.getContentModel()==OTM.MODE_SOFTWARE_SET){

			int selectedRow = OzFrame.ozTable.getSelectedRow();
			
			if( selectedRow!=-1 ){
				
				if(OzFrame.ozTable.getCellEditor()!=null){
					OzFrame.ozTable.getCellEditor().stopCellEditing();
				}
				System.out.println("");
				String s = (String)OzFrame.ozTable.getValueAt(OzFrame.ozTable.getSelectedRow(), 0);
				System.out.println(s);
			}

		}
		OzFrame.ozTable.repaint();//重画table
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(OTM.getContentModel()==OTM.MODE_SOFTWARE_VIEW){
			this.ignoreSelect();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		this.ignoreSelect();
		if(OTM.getContentModel()==OTM.MODE_SOFTWARE_VIEW){
			this.ignoreSelect();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		this.ignoreSelect();
		if(OTM.getContentModel()==OTM.MODE_SOFTWARE_VIEW){
			this.ignoreSelect();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		focusRow = -1;
		OzFrame.ozTable.repaint();//重画table
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
//		System.out.println("focusRow="+focusRow+" "+"row="+row);
		if(row == focusRow){
//			cell.setBackground(new Color(034, 139, 034));
			cell.setForeground(c.getSfg());
			cell.setBackground(c.getSbg());
		}
		else{
			cell.setForeground(c.getFg());
			cell.setBackground(c.getBg());
		}
		
		

		
		return cell;
	}


	
	private void ignoreSelect(){
		System.out.println("刷新了");
		OzFrame.ozTable.clearSelection();//取消选中
		OzFrame.ozTable.updateUI();
		OzFrame.ozTable.repaint();//重画table
	}
	private void openFile(SoftWare sw){
		if(sw!=null){
			try {
				Desktop.getDesktop().open(new File(sw.getPath()));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("打开文件失败！");
			}
		}
		else{
			System.out.println("文件不存在！");
		}
		
	}

}
