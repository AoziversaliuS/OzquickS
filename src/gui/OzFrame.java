package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import util.XMLData;

import bean.Config;

import logic.MouseRenderer;

public class OzFrame extends JFrame{
	
	//CENTER  中心组件
	public static OTM otm;
	public static JTable ozTable;
	public static JScrollPane jScrollPane;
	
	public static Config config;
	
	//SOURTH  南部组件
	public static JTabbedPane jTabbedPane;
	public static JPanel editSoftWarePanel;
	public static JPanel configPanel;
	

	
    private void init(){
    	this.setSize(config.getSoftWare_Width(), config.getSoftWare_Height());
    	this.setTitle("OzquickS");
    	this.setResizable(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLocation(config.getScreenX(),config.getScreenY());
    }

	public OzFrame(Config config){
		
		OzFrame.config = config;
		this.init();//OzFrame窗口初始化
    	this.tableInit();//表格初始化
    	this.scrollPaneInit();//滑动条加载table
    	this.tabbedPaneInit();
    	
    	this.setLayout(new BorderLayout()); //设置布局
    	
    	this.add(jScrollPane, BorderLayout.CENTER);//添加组件
    	this.add(jTabbedPane, BorderLayout.SOUTH);
    	jTabbedPane.setVisible(false);
//    	this.add(new JButton("wawa"), BorderLayout.SOUTH);//添加组件
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
	
	
	private void tabbedPaneInit(){
		this.jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.editSoftWarePanel = new JPanel();
		this.configPanel = new JPanel();
		editSoftWarePanel.add(new JButton("软件"));
		configPanel.add(new JButton("config"));
		
		jTabbedPane.addTab("软件编辑", editSoftWarePanel);
		jTabbedPane.addTab("界面配置", configPanel);
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
        	ozTable.setPreferredSize(new Dimension(config.getSoftWare_Width(), config.getSoftWare_Height()-config.getSoftWare_HeightOffset()));
    	}
    }
    private void scrollPaneInit(){
        jScrollPane = new JScrollPane(ozTable);
    }
	

}
