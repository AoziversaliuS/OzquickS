package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.OTM;
import gui.OzFrame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import util.XMLData;
import bean.Config;
import bean.SoftWare;

public class EditView  extends JFrame implements ActionListener {
	
	public static int WIDTH=350,HEIGHT=300;
	
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
	private  JButton     changeButton =  new JButton("移动");
	private  JButton     deleteButton = new JButton("确定");
	private JLabel     l6 = new JLabel("                  信 息 :");
	
	private  JLabel     tips =  new JLabel("           OzquickS");
	
	
	//配置编辑模块
	private  JTextField fbColor   = new JTextField();
	private  JTextField sfbColor   = new JTextField();
	private  JTextField fontSet   = new JTextField();
	private  JTextField swWH   = new JTextField();
	private  JTextField rowSet   = new JTextField();
	private  JTextField locationHeight   = new JTextField();
	private  JTextField gColor   = new JTextField();
	private  JButton    editButton = new JButton("修改");
	
	
	
	private Color bg= new Color(41,36,33);
	private Color fg= new Color(240,255,255);
	
	private Color red = Color.red;
	private Color green = new Color(127,255,0);
	
	public EditView(){
		System.out.println("asdasdasdsa");
		this.init();
		
		
		this.jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		this.softWarePanel = new JPanel();
		this.configPanel = new JPanel();
		
		colorSet();
		
		jTabbedPane.addTab("软件编辑", softWarePanel);
		jTabbedPane.addTab("界面配置", configPanel);
//		jTabbedPane.setVisible(false);
		jTabbedPane.setVisible(true);
		
		swEdit();
		configEdit();
		
		addListener();
		
		this.setLayout(new BorderLayout());
		this.add(jTabbedPane,BorderLayout.CENTER);
		makeConfig();
		
		this.setVisible(true);
		
	}
	private void init(){
		this.setSize(WIDTH, HEIGHT);
		System.out.println("config="+OzFrame.config.getSoftWare_Width());
    	this.setTitle("Speed Edit View");
    	this.setResizable(false);
//    	this.setDefaultCloseOperation(JFrame.);
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  
    	this.setLocation(screen.width/2-WIDTH/2,screen.height/2-HEIGHT/2);
	}

	private void  configEdit(){
		configPanel.setLayout(new GridLayout(8,1));
		configPanel.add(fbColor);
		configPanel.add(sfbColor);
		configPanel.add(fontSet);
		configPanel.add(swWH);
		configPanel.add(rowSet);
		configPanel.add(locationHeight);
		configPanel.add(gColor);
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
		softWarePanel.add(changeButton);
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
		changeButton.setBackground(bg);
		
		jTabbedPane.setForeground(fg);
		softWarePanel.setForeground(fg);
		configPanel.setForeground(fg);
		addButton.setForeground(fg);
		insertButton.setForeground(fg);
		deleteButton.setForeground(fg);
		changeButton.setForeground(fg);

		
		l1.setForeground(fg);
		l2.setForeground(fg);
		l3.setForeground(fg);
		l4.setForeground(fg);

		l6.setForeground(fg);
		tips.setForeground(green);
		
		
		
		editButton.setBackground(bg);
		editButton.setForeground(fg);

	}
	
	private void addListener(){
		addButton.addActionListener(this);
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		editButton.addActionListener(this);
		changeButton.addActionListener(this);
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

	private void makeConfig(){
		Config c = XMLData.getConfig();
		fbColor.setText("     FG=I"+c.getFg_RGB()+"I                  BG=I"+c.getBg_RGB()+"I");
		sfbColor.setText("  SFG=I"+c.getSfg_RGB()+"I         SBG=I"+c.getSbg_RGB()+"I");
		fontSet.setText("                           Font=I"+c.getFontString()+"I");
		swWH.setText("                           软件宽高=I"+c.getSoftWare_Width()+","+c.getSoftWare_Height()+"I");
		rowSet.setText("                           行高,行间距=I"+c.getRowHeight()+","+c.getRowMargin()+"I");
		locationHeight.setText("   屏幕坐标=I"+c.getScreenX()+","+c.getScreenY()+"I"+"            高度偏差值=I"+c.getSoftWare_HeightOffset()+"I");
		gColor.setText("     GF=I"+c.getGf_RGB()+"I                  GB=I"+c.getGb_RGB()+"I");
	}
	private static int[] getColorRGB(String RGB){
		String RGBs[] = RGB.split(",");
		int rgb[] = {Integer.parseInt(RGBs[0]),Integer.parseInt(RGBs[1]),Integer.parseInt(RGBs[2])};
		return rgb;
	}
	private void editReset(){
		swName.setText("");
		swPath.setText("");
		insertNum.setText("");
		deleteNum.setText("");
		tips.setForeground(green);
		tips.setText(OzFrame.SHOW);
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
				SoftWare sw = new SoftWare(swName.getText().trim(),swPath.getText().trim());
				ABsetting(sw);
				XMLData.addSoftWare(sw);
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
						int realIndex = OTM.getSoftWareByRow(index).getIndex();
						SoftWare sw = new SoftWare(realIndex, swName.getText().trim(), swPath.getText().trim());
						ABsetting(sw);
						XMLData.insertSoftWare(sw);
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
					
					int realIndex = OTM.getSoftWareByRow(index).getIndex();
					
					XMLData.deleteSoftWare(realIndex);
					
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
		else if( e.getSource()==editButton ){
			Config c = new Config();
			System.out.println("修改数据");
			String fgString = fbColor.getText().trim().split("I")[1];
			String bgString = fbColor.getText().trim().split("I")[3];
			String sfgString = sfbColor.getText().trim().split("I")[1];
			String sbgString = sfbColor.getText().trim().split("I")[3];
			String fontString = fontSet.getText().trim().split("I")[1];
			String whString = swWH.getText().trim().split("I")[1];
			String rsString = rowSet.getText().trim().split("I")[1];
			String lString = locationHeight.getText().trim().split("I")[1];
			String hoString = locationHeight.getText().trim().split("I")[3];
			String gfString = gColor.getText().trim().split("I")[1];
			String gbString = gColor.getText().trim().split("I")[3];

			int fg[] =  getColorRGB(fgString);
			int bg[] =  getColorRGB(bgString);
			int sfg[] = getColorRGB(sfgString);
			int sbg[] = getColorRGB(sbgString);
			int gf[] = getColorRGB(gfString);
			int gb[] = getColorRGB(gbString);
			
			
			
			
			
			c.setFg(new Color(fg[0],fg[1],fg[2]));
			c.setBg(new Color(bg[0],bg[1],bg[2]));
			c.setSfg(new Color(sfg[0],sfg[1],sfg[2]));
			c.setSbg(new Color(sbg[0],sbg[1],sbg[2]));
			c.setFont(new Font(
					fontString.split(",")[0], 
					Integer.parseInt(fontString.split(",")[1]),
					Integer.parseInt(fontString.split(",")[2]))
			);

			c.setSoftWare_Width(Integer.parseInt(whString.split(",")[0]));
			c.setSoftWare_Height(Integer.parseInt(whString.split(",")[1]));

			c.setRowHeight(Integer.parseInt(rsString.split(",")[0]));
			c.setRowMargin(Integer.parseInt(rsString.split(",")[1]));
			
			c.setScreenX(Integer.parseInt(lString.split(",")[0]));
			c.setScreenY(Integer.parseInt(lString.split(",")[1]));
			
			c.setSoftWare_HeightOffset(Integer.parseInt(hoString));
			
			c.setGfColor(new Color(gf[0],gf[1],gf[2]));
			c.setGbColor(new Color(gb[0],gb[1],gb[2]));
			
			XMLData.setConfig(c);
			OzFrame.setConfig(c);
		}
		else if( e.getSource()==changeButton ){
			String[] buff = deleteNum.getText().trim().split("[+]");
			System.out.println(deleteNum.getText().trim().split("[+]")[0]);
			 int index1 = Integer.parseInt(buff[0]);
			 int index2 = Integer.parseInt(buff[1]);
			 
			if(OTM.edit().size()>index1 && OTM.edit().size()>index2 ){
				SoftWare sw = new SoftWare();
				SoftWare swBuff = OTM.getSoftWareByRow(index1);
				sw.setIndex(OTM.getSoftWareByRow(index2).getIndex());
				sw.setName(swBuff.getName());
				sw.setPath(swBuff.getPath());
				XMLData.deleteSoftWare(swBuff.getIndex());
				XMLData.insertSoftWare(sw);
				OzFrame.otm.updateRowValue();
				OzFrame.ozTable.updateUI();
			}
		}
	}
	
	private void ABsetting(SoftWare sw){
		if( sw.getPath().equals("B") ){
			sw.setName("---------------");
		}
	}
	
	
	
	
	

}
