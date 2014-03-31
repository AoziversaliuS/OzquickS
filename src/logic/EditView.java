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
	//SOURTH  南部组件
	private  JTabbedPane jTabbedPane;
	private  JPanel softWarePanel;
	private  JPanel configPanel;
	
	//软件编辑模块
	private  JLabel     l1 =  new JLabel("            软  件  名  :");
	private  JLabel     l2 =  new JLabel("                  路  径  :");
	private  JLabel     l3 =  new JLabel("             嵌  入 点  :");
	private  JTextField insertNum    = new JTextField();
	private  JTextField swName = new JTextField();
	private  JTextField swPath = new JTextField();
	private  JButton     addButton = new JButton("添 加");
	private  JButton     insertButton = new JButton("嵌 入");
	
	private  JLabel     l4 =  new JLabel("       软 件 目 录  :");
	private  JTextField deleteNum   = new JTextField();
	private  JLabel     l5 =  new JLabel("                 删 除  :");
	private  JButton     deleteButton = new JButton("确定");
	private JLabel     l6 = new JLabel("                  信 息 :");
	
	private  JLabel     tips =  new JLabel("           OzquickS");
	
	
	//配置编辑模块
	private  JTextField fbColor   = new JTextField();
	private  JTextField sfbColor   = new JTextField();
	private  JTextField fontSet   = new JTextField();
	private  JTextField swWH   = new JTextField();
	private  JTextField rowSet   = new JTextField();
	private  JTextField location   = new JTextField();
	private  JTextField heightOffset   = new JTextField();
	private  JButton    editButton = new JButton("修改");
	
	
	
	private Color bg= new Color(41,36,33);
	private Color fg= new Color(240,255,255);
	
	private Color red = Color.red;
	private Color green = new Color(127,255,0);
	
	public EditView(){
		
		this.jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		this.softWarePanel = new JPanel();
		this.configPanel = new JPanel();
		
		colorSet();
		
		jTabbedPane.addTab("软件编辑", softWarePanel);
		jTabbedPane.addTab("界面配置", configPanel);
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

		//添加软件
		softWarePanel.add(l1);
		softWarePanel.add(swName);
		softWarePanel.add(l2);
		softWarePanel.add(swPath);
		//插入软件
		softWarePanel.add(l3);
		softWarePanel.add(insertNum);
		softWarePanel.add(addButton);
		softWarePanel.add(insertButton);
		//删除软件
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
		fbColor.setText("字体颜色=["+c.getFg_RGB()+"]      背景颜色=["+c.getBg_RGB()+"]");
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
			System.out.println("添加软件");
			if(!swName.getText().trim().equals("") && !swPath.getText().trim().equals("")){
				XMLData.addSoftWare(new SoftWare(swName.getText().trim(),swPath.getText().trim()) );
				OzFrame.otm.updateRowValue();
				OzFrame.ozTable.updateUI();
				swName.setText("");
				swPath.setText("");
				insertNum.setText("");
				tips.setForeground(green);
				tips.setText("                  完 成");
			}
			else{
				tips.setForeground(red);
				tips.setText("软件名或路径不能为空！");
			}

		}
		else if( e.getSource()==insertButton ){
			System.out.println("插入软件");
			if(!swName.getText().trim().equals("") && !swPath.getText().trim().equals("") && !insertNum.getText().trim().equals("")){
//				
				try{
					int index = Integer.parseInt(insertNum.getText().trim());
					if(index>XMLData.getSoftWares().size() || index<=0 ){
						tips.setForeground(red);
						tips.setText("没有此index！");
					}
					else{
						XMLData.insertSoftWare(new SoftWare(index, swName.getText().trim(), swPath.getText().trim()));
						OzFrame.otm.updateRowValue();
						OzFrame.ozTable.updateUI();
						swName.setText("");
						swPath.setText("");
						insertNum.setText("");
						tips.setForeground(green);
						tips.setText("                  完 成");
					}
				}catch (NumberFormatException e1) {
					tips.setForeground(red);
					tips.setText("请输入数字！");
				}
				

			}else{
				tips.setForeground(red);
				tips.setText("输入信息不全！");
			}
			
			
		}
		else if( e.getSource()==deleteButton ){
			System.out.println("删除软件");
			try{
				int index = Integer.parseInt(deleteNum.getText().trim());
				if(index>XMLData.getSoftWares().size() || index<=0){
					tips.setForeground(red);
					tips.setText("没有此index！");
				}
				else{
					XMLData.deleteSoftWare(index);
					OzFrame.otm.updateRowValue();
					OzFrame.ozTable.updateUI();
					deleteNum.setText("");
					tips.setForeground(green);
					tips.setText("                  完 成");
				}

			}catch (NumberFormatException e2) {
				tips.setForeground(red);
				tips.setText("请输入数字！");
			}
			
			
		}
	}
	
	
	
	
	
	

}
