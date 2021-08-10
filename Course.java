package Battulga_HW1;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Course implements java.io.Serializable{
	
	private String name;
	private String id;
	private int max;
	private int current;
	private ArrayList<StudentClass> stulist;
	private String instruc;
	private int section;
	private String loc;
	
	public Course() {
		
	}
	
	public Course(String name, String id, int max, int current, ArrayList<StudentClass> stulist, String instruc, int section,
			String loc) {
		super();
		this.name = name;
		this.id = id;
		this.max = max;
		this.current = current;
		this.stulist = stulist;
		this.instruc = instruc;
		this.section = section;
		this.loc = loc;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public ArrayList<StudentClass> getStulist() {
		return stulist;
	}
	public void setStulist(ArrayList<StudentClass> stulist) {
		this.stulist = stulist;
	}
	public String getInstruc() {
		return instruc;
	}
	public void setInstruc(String instruc) {
		this.instruc = instruc;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
