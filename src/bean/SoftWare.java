package bean;

public class SoftWare {
	
	private int index;
	private String name;
	private String path;
	private boolean open = false;
	
	
	
	
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public SoftWare(int index, String name, String path) {
		super();
		this.index = index;
		this.name = name;
		this.path = path;
	}
	
	public SoftWare(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}
	public SoftWare() {
		super();
		this.name = "未指定";
		this.path = "未指定";
		this.index = 100;
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
