package logic;

import java.awt.Color;
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
	
	//����༭ģ��
	private  JLabel     l1 =  new JLabel("            ��  ��  ��  :");
	private  JLabel     l2 =  new JLabel("                  ·  ��  :");
	private  JLabel     l3 =  new JLabel("             Ƕ  �� ��  :");
	private  JTextField insertNum    = new JTextField();
	private  JTextField swName = new JTextField();
	private  JTextField swPath = new JTextField();
	private  JButton     addButton = new JButton("�� ��");
	private  JButton     insertButton = new JButton("Ƕ ��");
	
	private  JLabel     l4 =  new JLabel("       �� �� Ŀ ¼  :");
	private  JTextField deleteNum   = new JTextField();
	private  JLabel     l5 =  new JLabel("                 ɾ ��  :");
	private  JButton     deleteButton = new JButton("ȷ��");
	private JLabel     l6 = new JLabel("                  �� Ϣ :");
	
	private  JLabel     tips =  new JLabel("           OzquickS");
	
	
	//���ñ༭ģ��
	private  JTextField fbColor   = new JTextField();
	private  JTextField sfbColor   = new JTextField();
	private  JTextField fontSet   = new JTextField();
	private  JTextField swWH   = new JTextField();
	private  JTextField rowSet   = new JTextField();
	private  JTextField location   = new JTextField();
	private  JTextField heightOffset   = new JTextField();
	private  JButton    editButton = new JButton("�޸�");
	
	
	
	private Color bg= new Color(41,36,33);
	private Color fg= new Color(240,255,255);
	
	private Color red = Color.red;
	private Color green = new Color(127,255,0);
	
	public EditView(){
		
		this.jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		this.softWarePanel = new JPanel();
		this.configPanel = new JPanel();
		
		colorSet();
		
		jTabbedPane.addTab("����༭", softWarePanel);
		jTabbedPane.addTab("��������", configPanel);
		jTabbedPane.setVisible(false);
		
		swEdit();
		configEdit();
		
		addListener();
		

		
		
	}
	private void  configEdit(){
		configPanel.setLayout(new GridLayout(8,1));
		configPanel.add(fbColor);
		configPanel.add(sfbColor);
		configPanel.add(fontSet);
		configPanel.add(swWH);
		configPanel.add(rowSet);
		configPanel.add(location);
		configPanel.add(heightOffset);
		configPanel.add(editButton);
		
	}
	private void  swEdit(){
		softWarePanel.setLayout(new GridLayout(7, 2));

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
		softWarePanel.add(l5);
		softWarePanel.add(deleteButton);
		
		softWarePanel.add(l6);
		softWarePanel.add(tips);
	}
	
	
	private void colorSet(){

		
		jTabbedPane.setBackground(bg);
		softWarePanel.setBackground(bg);
		configPanel.setBackground(bg);
		addButton.setBackground(bg);
		insertButton.setBackground(bg);
		deleteButton.setBackground(bg);
		
		jTabbedPane.setForeground(fg);
		softWarePanel.setForeground(fg);
		configPanel.setForeground(fg);
		addButton.setForeground(fg);
		insertButton.setForeground(fg);
		deleteButton.setForeground(fg);
		
		l1.setForeground(fg);
		l2.setForeground(fg);
		l3.setForeground(fg);
		l4.setForeground(fg);
		l5.setForeground(fg);
		l6.setForeground(fg);
		tips.setForeground(green);
		
		
		
		editButton.setBackground(bg);
		editButton.setForeground(fg);
		
	}
	
	private void addListener(){
		addButton.addActionListener(this);
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setVisible(){
		if(jTabbedPane.isVisible()==false){
			editReset();
			makeConfig();
			jTabbedPane.setVisible(true);
		}
		else{
			jTabbedPane.setVisible(false);
		}
	}
//	private  JTextField fbColor   = new JTextField();
//	private  JTextField sfbColor   = new JTextField();
//	private  JTextField fontSet   = new JTextField();
//	private  JTextField swWH   = new JTextField();
//	private  JTextField rowSet   = new JTextField();
//	private  JTextField location   = new JTextField();
//	private  JTextField heightOffset   = new JTextField();
	public void makeConfig(){
		Config c = XMLData.getConfig();
		fbColor.setText("������ɫ=["+c.getFg_RGB()+"]      ������ɫ=["+c.getBg_RGB()+"]");
	}
	public void editReset(){
		swName.setText("");
		swPath.setText("");
		insertNum.setText("");
		deleteNum.setText("");
		tips.setForeground(green);
		tips.setText("           OzquickS");
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
			if(!swName.getText().trim().equals("") && !swPath.getText().trim().equals("")){
				XMLData.addSoftWare(new SoftWare(swName.getText().trim(),swPath.getText().trim()) );
				OzFrame.otm.updateRowValue();
				OzFrame.ozTable.updateUI();
				swName.setText("");
				swPath.setText("");
				insertNum.setText("");
				tips.setForeground(green);
				tips.setText("                  �� ��");
			}
			else{
				tips.setForeground(red);
				tips.setText("�������·������Ϊ�գ�");
			}

		}
		else if( e.getSource()==insertButton ){
			System.out.println("�������");
			if(!swName.getText().trim().equals("") && !swPath.getText().trim().equals("") && !insertNum.getText().trim().equals("")){
//				
				try{
					int index = Integer.parseInt(insertNum.getText().trim());
					if(index>XMLData.getSoftWares().size() || index<=0 ){
						tips.setForeground(red);
						tips.setText("û�д�index��");
					}
					else{
						XMLData.insertSoftWare(new SoftWare(index, swName.getText().trim(), swPath.getText().trim()));
						OzFrame.otm.updateRowValue();
						OzFrame.ozTable.updateUI();
						swName.setText("");
						swPath.setText("");
						insertNum.setText("");
						tips.setForeground(green);
						tips.setText("                  �� ��");
					}
				}catch (NumberFormatException e1) {
					tips.setForeground(red);
					tips.setText("���������֣�");
				}
				

			}else{
				tips.setForeground(red);
				tips.setText("������Ϣ��ȫ��");
			}
			
			
		}
		else if( e.getSource()==deleteButton ){
			System.out.println("ɾ�����");
			try{
				int index = Integer.parseInt(deleteNum.getText().trim());
				if(index>XMLData.getSoftWares().size() || index<=0){
					tips.setForeground(red);
					tips.setText("û�д�index��");
				}
				else{
					XMLData.deleteSoftWare(index);
					OzFrame.otm.updateRowValue();
					OzFrame.ozTable.updateUI();
					deleteNum.setText("");
					tips.setForeground(green);
					tips.setText("                  �� ��");
				}

			}catch (NumberFormatException e2) {
				tips.setForeground(red);
				tips.setText("���������֣�");
			}
			
			
		}
	}
	
	
	
	
	
	

}
