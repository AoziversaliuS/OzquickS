package logic;

import gui.OzFrame;
import gui.OTM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import util.XMLData;

import bean.Config;
import bean.SoftWare;

public class MouseRenderer extends DefaultTableCellRenderer implements MouseListener , MouseMotionListener{
	
	private static int focusRow = -1;
	private boolean isPressing = false;
	public static Config c;
	
	public MouseRenderer(Config config){
		c = config;
	}
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.isPressing = true;
		
			this.ignoreSelect();
			
			if(focusRow==0){
				System.out.println("进入  isVisible="+OzFrame.jTabbedPane.isVisible());
				if(OzFrame.jTabbedPane.isVisible()==false){
					OzFrame.jTabbedPane.setVisible(true);
				}
				else{
					OzFrame.jTabbedPane.setVisible(false);
				}
			}
			else{
				SoftWare sw = XMLData.getSoftWare(focusRow);
				openFile(sw);
			}
			OzFrame.ozTable.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
//		this.ignoreSelect();
		if(OzFrame.otm.getRowValue().size()>OTM.MAX_ROW_ON_SCREEN){
			OzFrame.ozTable.setPreferredSize(null);
		}
		
		
		focusRow = OzFrame.ozTable.rowAtPoint(e.getPoint());
			this.ignoreSelect();
		OzFrame.ozTable.repaint();//重画table
	}
	@Override
	public void mouseDragged(MouseEvent e) {
			this.ignoreSelect();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.ignoreSelect();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.isPressing = false;
		this.ignoreSelect();
		OzFrame.ozTable.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.isPressing = false;
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
			if(isPressing){
				cell.setForeground(c.getFg());
			}
			else{
				cell.setForeground(c.getSfg());
			}
			cell.setBackground(c.getSbg());
		}
		else{
			cell.setForeground(c.getFg());
			cell.setBackground(c.getBg());
		}
		
		

		
		return cell;
	}


	
	private void ignoreSelect(){
//		System.out.println("刷新了");
		OzFrame.ozTable.clearSelection();//取消选中
		OzFrame.ozTable.updateUI();
		OzFrame.ozTable.repaint();//重画table
	}
	private void openFile(SoftWare sw){
		
		File file = new File(sw.getPath());
			try {
				if(file.exists()){
					Desktop.getDesktop().open(file);
				}
				else{
					System.out.println("文件不存在！");
//					JOptionPane.showMessageDialog(labelFor, "文件不存在！");
				}
			} catch (IOException e) {
				System.out.println("打开文件失败！");
			}
		}
		

}
