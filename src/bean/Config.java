package bean;

import java.awt.Color;
import java.awt.Font;

public class Config {
	
	private Color fg;
	private Color bg;
	private Color sfg;
	private Color sbg;
	private Font  font;
	private int rowHeight = 50;
	private int rowMargin = 10;
	
	
	
	
	
	public Config(Color fg, Color bg, Color sfg, Color sbg, Font font,
			int rowHeight, int rowMargin) {
		super();
		this.fg = fg;
		this.bg = bg;
		this.sfg = sfg;
		this.sbg = sbg;
		this.font = font;
		this.rowHeight = rowHeight;
		this.rowMargin = rowMargin;
	}



	public int getRowHeight() {
		return rowHeight;
	}



	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}



	public int getRowMargin() {
		return rowMargin;
	}



	public void setRowMargin(int rowMargin) {
		this.rowMargin = rowMargin;
	}



	public Config(Color fg, Color bg, Color sfg, Color sbg, Font font) {
		super();
		this.fg = fg;
		this.bg = bg;
		this.sfg = sfg;
		this.sbg = sbg;
		this.font = font;
	}
	
	
	
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Color getFg() {
		return fg;
	}
	public void setFg(Color fg) {
		this.fg = fg;
	}
	public Color getBg() {
		return bg;
	}
	public void setBg(Color bg) {
		this.bg = bg;
	}
	public Color getSfg() {
		return sfg;
	}
	public void setSfg(Color sfg) {
		this.sfg = sfg;
	}
	public Color getSbg() {
		return sbg;
	}
	public void setSbg(Color sbg) {
		this.sbg = sbg;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public String getFg_RGB(){
		String rgb = ""+this.fg.getRed()+","+this.fg.getGreen()+","+this.fg.getBlue()+"";
		return rgb;
	}
	public String getBg_RGB(){
		String rgb = ""+this.bg.getRed()+","+this.bg.getGreen()+","+this.bg.getBlue()+"";
		return rgb;
	}
	public String getSfg_RGB(){
		String rgb = ""+this.sfg.getRed()+","+this.sfg.getGreen()+","+this.sfg.getBlue()+"";
		return rgb;
	}
	public String getSbg_RGB(){
		String rgb = ""+this.sbg.getRed()+","+this.sbg.getGreen()+","+this.sbg.getBlue()+"";
		return rgb;
	}
	public String getFontString(){
//		new Font(name, style, size)
		String fontString = ""+this.font.getName()+","+this.font.getStyle()+","+this.font.getSize()+"";
		return fontString;
	}
	
	
	
}
