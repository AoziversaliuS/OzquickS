package logic;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.OzFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import util.XMLData;

import bean.Config;
import bean.SoftWare;

public class EditView implements ActionListener{
	//SOURTH  �ϲ����
	private  JTabbedPane jTabbedPane;
	private  JPanel softWarePanel;
	private  JPanel configPanel;
	
	
	private  JLabel     l1 =  new JLabel("            ��  ��  ��  :");
	private  JLabel     l2 =  new JLabel("                  ·  ��  :");
	private  JLabel     l3 =  new JLabel("                  Ƕ �� ��  :");
	private  JTextField insertNum    = new JTextField();
	private  JTextField swName = new JTextField();
	private  JTextField swPath = new JTextField();
	private  JButton     addButton = new JButton("���");
	private  JButton     insertButton = new JButton("Ƕ��");
	
	private  JLabel     l4 =  new JLabel("                  ���Ŀ¼  :");
	private  JTextField deleteNum   = new JTextField();
	private  JButton     deleteButton = new JButton("ɾ��");
	
	private  JLabel     tips =  new JLabel();
	public EditView(){
		
		this.jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		this.softWarePanel = new JPanel();
		this.configPanel = new JPanel();
		
		colorSet();
		
		jTabbedPane.addTab("����༭", softWarePanel);
		jTabbedPane.addTab("��������", configPanel);
		jTabbedPane.setVisible(false);
		
		softWarePanel.setLayout(new GridLayout(7, 2));

		
		
		
		softWarePanel.add(new JLabel());
		softWarePanel.add(tips);
		//������
		softWarePanel.add(l1);
		softWarePanel.add(swName);
		softWarePanel.add(l2);
		softWarePanel.add(swPath);
		//�������
		softWarePanel.add(l3);
		softWarePanel.add(insertNum);
		softWarePanel.add(addButton);
		softWarePanel.add(insertButton);
		//ɾ�����
		softWarePanel.add(l4);
		softWarePanel.add(deleteNum);
		softWarePanel.add(new JLabel());
		softWarePanel.add(deleteButton);
		

		
		addListener();
		
		
	}
	
	
	private void colorSet(){
		jTabbedPane.setBackground(OzFrame.config.getBg());
		softWarePanel.setBackground(OzFrame.config.getBg());
		configPanel.setBackground(OzFrame.config.getBg());
		addButton.setBackground(OzFrame.config.getBg());
		insertButton.setBackground(OzFrame.config.getBg());
		deleteButton.setBackground(OzFrame.config.getBg());
	}
	
	private void addListener(){
		addButton.addActionListener(this);
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setVisible(){
		if(jTabbedPane.isVisible()==false){
			jTabbedPane.setVisible(true);
		}
		else{
			jTabbedPane.setVisible(false);
		}
	}
	
	
	
	
	
	public JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}
	public JPanel getEditSoftWarePanel() {
		return softWarePanel;
	}
	public JPanel getConfigPanel() {
		return configPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource()==addButton ){
			System.out.println("������");
			XMLData.addSoftWare(new SoftWare(swName.getText().trim(),swPath.getText().trim()) );
			OzFrame.otm.updateRowValue();
			OzFrame.ozTable.updateUI();
		}
		else if( e.getSource()==insertButton ){
			System.out.println("�������");
			int index = Integer.parseInt(insertNum.getText().trim());
			XMLData.insertSoftWare(new SoftWare(index, swName.getText().trim(), swPath.getText().trim()));
			OzFrame.otm.updateRowValue();
			OzFrame.ozTable.updateUI();
		}
		else if( e.getSource()==deleteButton ){
			System.out.println("ɾ�����");
			int index = Integer.parseInt(deleteNum.getText().trim());
			XMLData.deleteSoftWare(index);
			OzFrame.otm.updateRowValue();
			OzFrame.ozTable.updateUI();
		}
	}
	
	
	
	
	
	

}
