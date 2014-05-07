package util;


import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import bean.Config;
import bean.SoftWare;

public class XMLData {
	public static final String configXML="./config.xml";
	public static final String softWaresXML="./softWares.xml";
	
	
	//软件
	private static void creatSoftWares(){
		File softWaresFile = new File(softWaresXML);
		if(softWaresFile.exists()==false){
			Document doc = DocumentHelper.createDocument();
			doc.addElement("softWareList");
			flush(doc,softWaresXML);
			System.out.println("软件存档已建立！");
		}
		
	}
	//获取全部
	public static ArrayList<SoftWare> getSoftWares(){
		creatSoftWares();
		SAXReader reader = new SAXReader();
		File softWaresFile = new File(softWaresXML);
		Document doc;
		
		ArrayList<SoftWare> softWares = new ArrayList<SoftWare>();
		try {
			doc = reader.read(softWaresFile);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element>  softWareElements = root.elements("softWare");
			
			if(softWareElements!=null){
				for(Element e:softWareElements){
					softWares.add(new SoftWare(
							Integer.parseInt(e.element("index").getTextTrim()),
							e.element("name").getTextTrim(),
							e.element("path").getTextTrim()
							));
				}
			}
			System.out.println("获取软件集合成功！");
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("获取软件集合失败！");
		}
		return softWares;
	}
	//插入
	public static void insertSoftWare(SoftWare sw){
		creatSoftWares();
		
		ArrayList<SoftWare> softWares = getSoftWares();
		
		for(SoftWare softWare:softWares){
			if( softWare.getIndex()>=sw.getIndex() ){
				softWare.setIndex( softWare.getIndex()+1 );
			}
		}
		softWares.add(sw);
		setAllSoftWare(softWares);
		System.out.println("插入软件成功！");
		
		
	}
	//添加
	public static void addSoftWare(SoftWare sw){
		creatSoftWares();
		softWareAddWithIndex(sw,false);
		System.out.println("添加软件！");
		
	}
	//删除
	public static void deleteSoftWare(int index){
		creatSoftWares();
		ArrayList<SoftWare> softWares = getSoftWares();
		int i;
		for(i=0; i<softWares.size(); i++){
			if( softWares.get(i).getIndex()==index ){
				break;
			}
		}
		for(SoftWare sw:softWares){
			if(sw.getIndex()>index){
				sw.setIndex( sw.getIndex()-1 );
			}
		}
		softWares.remove(i);
		setAllSoftWare(softWares);
		System.out.println("删除软件成功！");
	}
	//获取单个
	public static SoftWare getSoftWare(int index){
		creatSoftWares();
		ArrayList<SoftWare> softWares = getSoftWares();
		int i;
		for(i=0; i<softWares.size(); i++){
			if( softWares.get(i).getIndex()==index ){
				return softWares.get(i);
			}
		}
		return null;
	}
	private static void softWareAddWithIndex(SoftWare sw,boolean withIndex){
		SAXReader reader = new SAXReader();
		File softWaresFile = new File(softWaresXML);
		Document doc;
		try {
			doc = reader.read(softWaresFile);
			Element root = doc.getRootElement();
			Element softWare = root.addElement("softWare");
			
			if(withIndex){
				softWare.addElement("index").setText(sw.getIndex()+"");
			}
			else{
				softWare.addElement("index").setText((getSoftWares().size()+1)+"");
			}
			
			softWare.addElement("name").setText(sw.getName());
			softWare.addElement("path").setText(sw.getPath());
			
			
			flush(doc, softWaresXML);
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("添加软件失败！");
		}
	}
	private static void deleteAllSoftWare(){
		Document doc = DocumentHelper.createDocument();
		doc.addElement("softWareList");
		flush(doc, softWaresXML);
	}
	private static void setAllSoftWare(ArrayList<SoftWare> softWares){
		deleteAllSoftWare();
		for(SoftWare sw:softWares){
			softWareAddWithIndex(sw, true);
		}
	}
	
	
	
	
	
	
	
	//配置
	public static Config getConfig(){
		SAXReader reader = new SAXReader();
		Config c = null;
		File configFile = new File(configXML);
		if(configFile.exists()==false){
			System.out.println("配置文件不存在！");
			creatConfig();
		}
		try {
			Document doc = reader.read(configFile);
			Element root = doc.getRootElement();
			int fg[]  = getColorRGB(root.element("foreGround").getTextTrim());
			int bg[]  = getColorRGB(root.element("backGround").getTextTrim());
			int sfg[] = getColorRGB(root.element("sForeGround").getTextTrim());
			int sbg[] = getColorRGB(root.element("sBackGround").getTextTrim());
			int gf[]  = getColorRGB(root.element("gfColor").getTextTrim());
			int gb[]  = getColorRGB(root.element("gbColor").getTextTrim());
			String f = root.element("font").getTextTrim();
			c = new Config(
					mC(fg),
					mC(bg),
					mC(sfg),
					mC(sbg),
					new Font(f.split(",")[0], Integer.parseInt(f.split(",")[1]),  Integer.parseInt(f.split(",")[2])),
					mI(root.element("rowHeight").getText()),
					mI(root.element("rowMargin").getText()),
					mI(root.element("softWare_Width").getText()),
					mI(root.element("softWare_Height").getText()),
					mI(root.element("softWare_HeightOffset").getText()),
					mI(root.element("screenX").getText()),
					mI(root.element("screenY").getText()),
					mC(gf),
					mC(gb)
					);
			
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("获取文档失败！");
		}
		
		return c;
	}
	private static int mI(String integer){
		return Integer.parseInt(integer.trim());
	}
	private static Color mC(int c[]){
		return new Color(c[0],c[1],c[2]);
	}
	public static void setConfig(Config c){
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("config");
		//字体颜色
		root.addElement("foreGround").setText(c.getFg_RGB());
		//背景颜色
		root.addElement("backGround").setText(c.getBg_RGB());
		//选中的字体颜色
		root.addElement("sForeGround").setText(c.getSfg_RGB());
		//选中框的颜色
		root.addElement("sBackGround").setText(c.getSbg_RGB());
		//设置字体格式
		root.addElement("font").setText(c.getFontString());
		//行高
		root.addElement("rowHeight").setText(c.getRowHeight()+"");
		//行间距
		root.addElement("rowMargin").setText(c.getRowMargin()+"");
		//界面宽
		root.addElement("softWare_Width").setText(c.getSoftWare_Width()+"");
		//界面高
		root.addElement("softWare_Height").setText(c.getSoftWare_Height()+"");
		//界面偏移值
		root.addElement("softWare_HeightOffset").setText(c.getSoftWare_HeightOffset()+"");
		//软件X坐标
		root.addElement("screenX").setText(c.getScreenX()+"");
		//软件Y坐标
		root.addElement("screenY").setText(c.getScreenY()+"");
		
		root.addElement("gfColor").setText(c.getGf_RGB()+"");
		
		root.addElement("gbColor").setText(c.getGb_RGB()+"");
		
		flush(doc,configXML);
		System.out.println("设置配置文件成功！");
	}
	private static void creatConfig(){
		
		Config c = new Config(
				new Color(41,36,33),
				new Color(250,250,250), 
				new Color(250,250,250),
				new Color(50,205,50), 
				new Font("楷体", Font.BOLD, 17)
				);
		c.setGfColor(Color.WHITE);
		c.setGbColor(Color.BLACK);
		setConfig(c);
	}
	
	
	private static void flush(Document doc,String filePath){
		FileOutputStream fileOutPutStream = null;
		XMLWriter writer = null;
		
		try {
			fileOutPutStream = new FileOutputStream(filePath);
			writer = new XMLWriter(fileOutPutStream);
			writer.write(doc);
			writer.flush();
//			System.out.println("已写入XML");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("XML文件流创建失败！");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("XML输出环境搭建失败!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("XML写入失败!");
		}
		
		if(writer!=null){
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("XML输出流关闭失败!");
			}
		}
	}
	
	private static int[] getColorRGB(String RGB){
		String RGBs[] = RGB.split(",");
		int rgb[] = {Integer.parseInt(RGBs[0]),Integer.parseInt(RGBs[1]),Integer.parseInt(RGBs[2])};
		return rgb;
	}
	
	public static void main(String[] args) {

//		XMLData.deleteAllSoftWare();
		SoftWare qq = new SoftWare("腾讯QQ", "E:/test/腾讯QQ.lnk");
		SoftWare zip = new SoftWare("Zip", "E:/test/奥兹小队-项目资料.zip");
		SoftWare video = new SoftWare("video", "E:/test/video.mp4");
		SoftWare music = new SoftWare("music", "E:/test/mu sic.mp3");
		XMLData.addSoftWare(qq);
		XMLData.addSoftWare(zip);
		XMLData.addSoftWare(video);
		XMLData.addSoftWare(music);
//		XMLData.addSoftWare(sw);
//		XMLData.deleteSoftWare(1);
//		SoftWare sw = XMLData.getSoftWare(4);
//		System.out.println("软件名:"+sw.getName());
	}

}
