package Battulga_HW1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings({ "unused", "serial" })
public class StudentClass extends User implements Student, java.io.Serializable{
	
	private String id; 
	
	public StudentClass() {
	}

	public StudentClass(String username, String password, String first, String last, String id) {
		super(username, password, first, last);
		this.id = id;
	}

	
	@Override
	public void viewcourses(Data data) {
		ArrayList<Course> C = new ArrayList<Course>();
		
		System.out.println("The courses are: ");
		C = data.getCourses();
		for (Course c : C) {
			System.out.println(c.getName());
		}	
	}

	@Override
	public void viewopen(Data data) {
		ArrayList<Course> C = new ArrayList<Course>();
		
		System.out.println("The courses that are available right now: ");
		C = data.getCourses();
		for (Course c : C) {
			if (c.getMax() != c.getCurrent()) {
				System.out.println(c.getName());
			}
		}	
	}

	@Override
	public void register(Data data, Scanner s, StudentClass stu) {
		System.out.println("What is the name of the course you want to register for? ");
		String cn = s.nextLine();
		System.out.println("What is the section number for this course? ");
		int sec = Integer.parseInt(s.nextLine());
		System.out.println("What is your first name? ");
		String f = s.nextLine();
		System.out.println("What is your last name? ");
		String l = s.nextLine();
		
		
		ArrayList<Course> ar = new ArrayList<Course>();
		//fill with og data
		ar = data.getCourses();
		//iterate through each course id
		for (Course courses : ar) {
			String name = courses.getName();
			int section = courses.getSection();
			int max = courses.getMax();
			int cur = courses.getCurrent();
			
			if(max == cur) {
				System.out.println("Sorry this course is full!");
			}
			
			if (name.equals(cn) && (section == sec) && (max != cur)) {
				ArrayList<StudentClass> stud = new ArrayList<StudentClass>();
				stud = courses.getStulist();
				stud.add(stu);
				courses.setStulist(stud);
				cur += 1;
				courses.setCurrent(cur);
			}
		}
		data.setCourses(ar);
	}

	@Override
	public void withdraw(Data data, Scanner s, StudentClass stu) {
		System.out.println("What is the name of the course you want withdraw from? ");
		String cn = s.nextLine();
		System.out.println("What is your name? ");
		String f = s.nextLine();
		ArrayList<Course> ar = new ArrayList<Course>();
		//fill with og data
		ar = data.getCourses();
		
		//iterate through each course id
		for (Course courses : ar) {
			String name = courses.getName();
			int cur = courses.getCurrent();
			if (name.equals(cn)) {
				ArrayList<StudentClass> stud = new ArrayList<StudentClass>();
				stud = courses.getStulist();
				for (StudentClass sc : stud) {
					if (stu.getFirst().equals(sc.getFirst()) && stu.getLast().equals(sc.getLast())) {
						stud.remove(stu);
						courses.setStulist(stud);
						cur -= 1;
						courses.setCurrent(cur);
					}
				}
			}
		}
		data.setCourses(ar);
	}

	@Override
	public void mycourses(Data data, StudentClass stu) {
		ArrayList<Course> ar = new ArrayList<Course>();
		//fill with og data
		ar = data.getCourses();
		
		System.out.println("You are currently registered in: ");
		
		//iterate through each course id
		for (Course courses : ar) {
			ArrayList<StudentClass> stud = new ArrayList<StudentClass>();
			stud = courses.getStulist();
			for (StudentClass sc : stud) {
				if (stu.getId().equals(sc.getId())) {
					System.out.println(courses.getName());
					}
				}
			}
		}
		

	@Override
	public void exit() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
