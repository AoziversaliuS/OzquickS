package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import util.XMLData;
import bean.Config;
import logic.EditView;
import logic.MouseRenderer;

public class OzFrame extends JFrame{
	
	public static final String  NAME = "Speed Mini 2";
	public static final String  SHOW = "          A o z I";
	//CENTER  中心组件
	public static OTM otm;
	public static JTable ozTable;
	public static JScrollPane jScrollPane;
	
	public static Config config;
	
	//SOUTH 南部组件
	public static EditView editView;
	

	
    private void init(){
    	this.setSize(config.getSoftWare_Width(), config.getSoftWare_Height());
    	this.setTitle(NAME);
    	this.setResizable(false);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLocation(config.getScreenX(),config.getScreenY());
    }

	public OzFrame(Config config){
		
		OzFrame.config = config;
		this.init();//OzFrame窗口初始化
    	this.tableInit();//表格初始化
    	this.scrollPaneInit();//滑动条加载table
//    	this.setLayout(new BorderLayout()); //设置布局
//    	this.add(jScrollPane, BorderLayout.CENTER);//添加组件
    	
    	//隐藏滚动条(让滚动条在窗口之外显示，所以看不到滚动条)
    	this.setLayout(null); //设置布局
    	//+12是为了让滚动条显示在屏幕外面
    	jScrollPane.setSize(config.getSoftWare_Width()+12, config.getSoftWare_Height());
    	this.add(jScrollPane);//添加组件
    	
//    	editView = new EditView();
//    	
//    	this.add(editView.getjTabbedPane(), BorderLayout.SOUTH);
//    	this.add(new JButton("wawa"), BorderLayout.SOUTH);//添加组件
    	this.setVisible(true);
	}
	public static void setConfig(Config c){
		MouseRenderer.c = c;
		config = c;
		//设置字体
    	ozTable.setFont(config.getFont());
	   	ozTable.setBackground(config.getBg());
    	//设置行高和间距
    	ozTable.setRowHeight(config.getRowHeight());
    	ozTable.setRowMargin(config.getRowMargin());
    	//设置行的动态渲染
    	ozTable.setBackground(config.getBg());
	   	ozTable.updateUI();
	   	ozTable.repaint();

	}
	
	public static void main(String[] args) {
		
		Config c = XMLData.getConfig();
		new OzFrame(c);
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
    	if(otm.edit().size()<= OTM.MAX_ROW_ON_SCREEN){
        	ozTable.setPreferredSize(new Dimension(config.getSoftWare_Width(), config.getSoftWare_Height()-config.getSoftWare_HeightOffset()));
    	}
    }
    private void scrollPaneInit(){
        jScrollPane = new JScrollPane(ozTable);
//        jScrollPane.setVisible(false);
//        jScrollPane.setPreferredSize(new Dimension(1, 1));
        //为保持一致性,让滚动条一直在窗口之外显示(但是由于位于窗口之外，所以看不到)隐藏滚动条的实现在OzFrame的构造方法里
//        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }
	

}
