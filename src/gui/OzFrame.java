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
	
	public static final String  NAME = "SpeedSearch 2.0";
	public static final String  SHOW = "                   A o z I";
	//CENTER  �������
	public static OTM otm;
	public static JTable ozTable;
	public static JScrollPane jScrollPane;
	
	public static Config config;
	
	//SOUTH �ϲ����
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
		this.init();//OzFrame���ڳ�ʼ��
    	this.tableInit();//����ʼ��
    	this.scrollPaneInit();//����������table
    	this.setLayout(new BorderLayout()); //���ò���
    	this.add(jScrollPane, BorderLayout.CENTER);//������
    	
    	editView = new EditView();
    	
    	this.add(editView.getjTabbedPane(), BorderLayout.SOUTH);
//    	this.add(new JButton("wawa"), BorderLayout.SOUTH);//������
    	this.setVisible(true);
	}
	public static void setConfig(Config c){
		MouseRenderer.c = c;
		config = c;
		//��������
    	ozTable.setFont(config.getFont());
	   	ozTable.setBackground(config.getBg());
    	//�����иߺͼ��
    	ozTable.setRowHeight(config.getRowHeight());
    	ozTable.setRowMargin(config.getRowMargin());
    	//�����еĶ�̬��Ⱦ
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
    	if(otm.edit().size()<= OTM.MAX_ROW_ON_SCREEN){
        	ozTable.setPreferredSize(new Dimension(config.getSoftWare_Width(), config.getSoftWare_Height()-config.getSoftWare_HeightOffset()));
    	}
    }
    private void scrollPaneInit(){
        jScrollPane = new JScrollPane(ozTable);
    }
	

}
