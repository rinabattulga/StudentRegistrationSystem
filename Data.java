package Battulga_HW1;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings({ "serial", "unused" })
public class Data implements java.io.Serializable{
	
	private ArrayList<StudentClass> Students;
	private ArrayList<Course> Courses;
	
	public Data() {
	}
	
	public Data(ArrayList<StudentClass> students, ArrayList<Course> courses) {
		Students = students;
		Courses = courses;
	}

	public ArrayList<StudentClass> getStudents() {
		return Students;
	}
	public void setStudents(ArrayList<StudentClass> students) {
		Students = students;
	}
	public ArrayList<Course> getCourses() {
		return Courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		Courses = courses;
	}
	
	
}
