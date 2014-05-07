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
	
	
	//���
	private static void creatSoftWares(){
		File softWaresFile = new File(softWaresXML);
		if(softWaresFile.exists()==false){
			Document doc = DocumentHelper.createDocument();
			doc.addElement("softWareList");
			flush(doc,softWaresXML);
			System.out.println("����浵�ѽ�����");
		}
		
	}
	//��ȡȫ��
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
			System.out.println("��ȡ������ϳɹ���");
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("��ȡ�������ʧ�ܣ�");
		}
		return softWares;
	}
	//����
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
		System.out.println("��������ɹ���");
		
		
	}
	//���
	public static void addSoftWare(SoftWare sw){
		creatSoftWares();
		softWareAddWithIndex(sw,false);
		System.out.println("��������");
		
	}
	//ɾ��
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
		System.out.println("ɾ������ɹ���");
	}
	//��ȡ����
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
			System.out.println("������ʧ�ܣ�");
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
	
	
	
	
	
	
	
	//����
	public static Config getConfig(){
		SAXReader reader = new SAXReader();
		Config c = null;
		File configFile = new File(configXML);
		if(configFile.exists()==false){
			System.out.println("�����ļ������ڣ�");
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
			System.out.println("��ȡ�ĵ�ʧ�ܣ�");
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
		//������ɫ
		root.addElement("foreGround").setText(c.getFg_RGB());
		//������ɫ
		root.addElement("backGround").setText(c.getBg_RGB());
		//ѡ�е�������ɫ
		root.addElement("sForeGround").setText(c.getSfg_RGB());
		//ѡ�п����ɫ
		root.addElement("sBackGround").setText(c.getSbg_RGB());
		//���������ʽ
		root.addElement("font").setText(c.getFontString());
		//�и�
		root.addElement("rowHeight").setText(c.getRowHeight()+"");
		//�м��
		root.addElement("rowMargin").setText(c.getRowMargin()+"");
		//�����
		root.addElement("softWare_Width").setText(c.getSoftWare_Width()+"");
		//�����
		root.addElement("softWare_Height").setText(c.getSoftWare_Height()+"");
		//����ƫ��ֵ
		root.addElement("softWare_HeightOffset").setText(c.getSoftWare_HeightOffset()+"");
		//���X����
		root.addElement("screenX").setText(c.getScreenX()+"");
		//���Y����
		root.addElement("screenY").setText(c.getScreenY()+"");
		
		root.addElement("gfColor").setText(c.getGf_RGB()+"");
		
		root.addElement("gbColor").setText(c.getGb_RGB()+"");
		
		flush(doc,configXML);
		System.out.println("���������ļ��ɹ���");
	}
	private static void creatConfig(){
		
		Config c = new Config(
				new Color(41,36,33),
				new Color(250,250,250), 
				new Color(250,250,250),
				new Color(50,205,50), 
				new Font("����", Font.BOLD, 17)
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
//			System.out.println("��д��XML");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("XML�ļ�������ʧ�ܣ�");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("XML��������ʧ��!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("XMLд��ʧ��!");
		}
		
		if(writer!=null){
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("XML������ر�ʧ��!");
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
		SoftWare qq = new SoftWare("��ѶQQ", "E:/test/��ѶQQ.lnk");
		SoftWare zip = new SoftWare("Zip", "E:/test/����С��-��Ŀ����.zip");
		SoftWare video = new SoftWare("video", "E:/test/video.mp4");
		SoftWare music = new SoftWare("music", "E:/test/mu sic.mp3");
		XMLData.addSoftWare(qq);
		XMLData.addSoftWare(zip);
		XMLData.addSoftWare(video);
		XMLData.addSoftWare(music);
//		XMLData.addSoftWare(sw);
//		XMLData.deleteSoftWare(1);
//		SoftWare sw = XMLData.getSoftWare(4);
//		System.out.println("�����:"+sw.getName());
	}

}
