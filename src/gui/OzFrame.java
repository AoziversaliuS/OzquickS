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
	
	//CENTER  �������
	public static OTM otm;
	public static JTable ozTable;
	public static JScrollPane jScrollPane;
	
	public static Config config;
	
	//SOURTH  �ϲ����
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
		this.init();//OzFrame���ڳ�ʼ��
    	this.tableInit();//����ʼ��
    	this.scrollPaneInit();//����������table
    	this.tabbedPaneInit();
    	
    	this.setLayout(new BorderLayout()); //���ò���
    	
    	this.add(jScrollPane, BorderLayout.CENTER);//������
    	this.add(jTabbedPane, BorderLayout.SOUTH);
    	jTabbedPane.setVisible(false);
//    	this.add(new JButton("wawa"), BorderLayout.SOUTH);//������
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
		
//		Config c = new Config(Color.black,Color.ORANGE,new Color(50,205,50), Color.white, new Font("����", Font.BOLD, 17));
		Config c = XMLData.getConfig();
		OzFrame z= new OzFrame(c);
//		c.setBg(Color.black);
//		z.setColor(c);
//		try {
//			Desktop.getDesktop().open(new File("E:/��ѶQQ.lnk"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	private void tabbedPaneInit(){
		this.jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.editSoftWarePanel = new JPanel();
		this.configPanel = new JPanel();
		editSoftWarePanel.add(new JButton("���"));
		configPanel.add(new JButton("config"));
		
		jTabbedPane.addTab("����༭", editSoftWarePanel);
		jTabbedPane.addTab("��������", configPanel);
	}
	
	
    private void tableInit(){
    	
    	otm = new OTM();
    	ozTable = new JTable(otm);
    	//���ñ�����ɫ
    	//����table�߿�
    	ozTable.setShowGrid(false);
    	ozTable.setShowHorizontalLines(false);
    	ozTable.setShowVerticalLines(false);
    	//���ر�����
    	ozTable.getTableHeader().setVisible(false);
    	DefaultTableCellRenderer headRenderer = new DefaultTableCellRenderer();
    	headRenderer.setPreferredSize(new Dimension(0, 0));
    	ozTable.getTableHeader().setDefaultRenderer(headRenderer);
    	//�����еĶ�̬��Ⱦ
    	ozTable.setBackground(config.getBg());
//    	System.out.println(config.getFg());
//    	System.out.println(config.getBg());
//    	System.out.println(config.getSfg());
//    	System.out.println(config.getSbg());
    	MouseRenderer oml = new MouseRenderer(config);
    	ozTable.setDefaultRenderer(Object.class, oml);
    	//�����иߺͼ��
    	ozTable.setRowHeight(config.getRowHeight());
    	ozTable.setRowMargin(config.getRowMargin());
    	//����������

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
