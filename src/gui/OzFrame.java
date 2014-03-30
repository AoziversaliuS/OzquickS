package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import util.XMLData;

import bean.Config;

import logic.MouseRenderer;

public class OzFrame extends JFrame{
	
	public static OTM otm;
	public static JTable ozTable;
	public static JScrollPane jsp;
	public static Config config;
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 700;
	public static final int OFFSET_HEIGHT = 35;
	
	public static final int screenX = 500;
	public static final int screenY = 100;
	
    private void init(){
    	this.setSize(WIDTH, HEIGHT);
    	this.setTitle("OzquickS");
    	this.setResizable(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLocation(screenX,screenY);
    }

	public OzFrame(Config config){
		
		OzFrame.config = config;
		this.init();//窗口初始化
    	this.tableInit();//表格初始化
    	this.scrollPaneInit();//滑动条加载table
    	this.add(jsp);//添加组件
    	this.setResizable(false);
    	this.setVisible(true);
	}
	public static void setColor(Config c){
		MouseRenderer.c = c;
	   	ozTable.setBackground(config.getBg());
	   	ozTable.updateUI();
	   	ozTable.repaint();
	}
	
	public static void main(String[] args) {
		
//		Config c = new Config(Color.black,Color.ORANGE,new Color(50,205,50), Color.white, new Font("楷体", Font.BOLD, 17));
		Config c = XMLData.getConfig();
		OzFrame z= new OzFrame(c);
//		c.setBg(Color.black);
//		z.setColor(c);
//		try {
//			Desktop.getDesktop().open(new File("E:/腾讯QQ.lnk"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
    private void tableInit(){
    	
    	otm = new OTM();
    	ozTable = new JTable(otm);
    	//设置背景颜色
    	//隐藏table边框
    	ozTable.setShowGrid(false);
    	ozTable.setShowHorizontalLines(false);
    	ozTable.setShowVerticalLines(false);
    	//隐藏标题行
    	ozTable.getTableHeader().setVisible(false);
    	DefaultTableCellRenderer headRenderer = new DefaultTableCellRenderer();
    	headRenderer.setPreferredSize(new Dimension(0, 0));
    	ozTable.getTableHeader().setDefaultRenderer(headRenderer);
    	//设置行的动态渲染
    	ozTable.setBackground(config.getBg());
//    	System.out.println(config.getFg());
//    	System.out.println(config.getBg());
//    	System.out.println(config.getSfg());
//    	System.out.println(config.getSbg());
    	MouseRenderer oml = new MouseRenderer(config);
    	ozTable.setDefaultRenderer(Object.class, oml);
    	//设置行高和间距
    	ozTable.setRowHeight(config.getRowHeight());
    	ozTable.setRowMargin(config.getRowMargin());
    	//设置鼠标监听

    	ozTable.addMouseMotionListener(oml);
    	ozTable.addMouseListener(oml);
    	
    	ozTable.setFont(config.getFont());
    	
//    	ozTable.setPreferredScrollableViewportSize(new Dimension(WIDTH, HEIGHT));
    	if(otm.getRowValue().size()<= OTM.MAX_ROW_ON_SCREEN){
        	ozTable.setPreferredSize(new Dimension(WIDTH, HEIGHT-OFFSET_HEIGHT));
    	}
    }
    private void scrollPaneInit(){
        jsp = new JScrollPane(ozTable);
    }
	

}
