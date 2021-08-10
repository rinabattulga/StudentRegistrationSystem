package Battulga_HW1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("serial")
public class AdminClass extends User implements Admin, java.io.Serializable{
	private String username = "Admin";
	private String password = "Admin001";
	
	public AdminClass() {
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public void createcourse(Data data, Scanner s) {
		System.out.println("Please enter a course name: ");
		String name = s.nextLine();
		System.out.println("Please enter a course id: ");
		String id = s.nextLine();
		System.out.println("Please enter max number of students: ");
		int max = Integer.parseInt(s.nextLine());
		System.out.println("Please enter current number of students in class: ");
		int cur = Integer.parseInt(s.nextLine());
		System.out.println("Please enter the instructor name: ");
		String ins = s.nextLine();
		System.out.println("Please enter section number: ");
		int sec = Integer.parseInt(s.nextLine());
		System.out.println("Please enter class location: ");
		String loc = s.nextLine();
		//create new course object with specified fields
		ArrayList<StudentClass> stu = new ArrayList<StudentClass>();
		Course nc = new Course(name,id,max,cur,stu,ins,sec,loc);
		
		//ADD COURSE TO FILE!
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		//add new course
		arr.add(nc); 
		//set arraylist of courses to updated version
		data.setCourses(arr);
	}

	@Override
	public void deletecourse(Data data, Scanner s) {
		System.out.println("Please enter course id:");
		String Id = s.nextLine();
		System.out.println("Please enter course name: ");
		String Na = s.nextLine();
		System.out.println("Please enter course instructor name: ");
		String IN = s.nextLine();
		
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		
		//iterate through each course id
		for (Course courses : arr) {
			if (Id.equals(courses.getId()) && Na.equals(courses.getName()) && IN.equals(courses.getInstruc())) {
				arr.remove(courses);
				break;
			}
		}
		data.setCourses(arr);
	}

	@Override
	public void editcourse(Data data, Scanner s) {
		System.out.println("Please enter course id:");
		String Id = s.nextLine();
		System.out.println("Please enter course name: ");
		String Na = s.nextLine();
		System.out.println("Please enter course instructor name: ");
		String IN = s.nextLine();
		
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		//to edit course, create new course object inside method 
		Course c = new Course();
		//iterate through each course id
		for (Course courses : arr) {
			//when the id is found, deletes the course that has that id
			if (Id.equals(courses.getId()) && Na.equals(courses.getName()) && IN.equals(courses.getInstruc())) {
				c = courses;
				break;
			}
		}
		
		boolean v = true;
		while(v) {
			System.out.println("What do you want to edit in this course? (enter corresponding letter)");
			System.out.println("a) Maximum number of students");
			System.out.println("b) Registered student names list");
			System.out.println("c) Course instructor");
			System.out.println("d) Course section number");
			System.out.println("e) Course location");
			System.out.println("f) Exit");
			String ans = s.nextLine();
		
			if (ans.equalsIgnoreCase("a")) {
				System.out.println("What do you want the new maximum number of students to be?");
				int max = Integer.parseInt(s.nextLine());
				c.setMax(max);
			}
			if (ans.equalsIgnoreCase("b")) {
				System.out.println("What do you want to edit in the student names list? ");
				System.out.println("a) Edit name of registered student");
				System.out.println("b) Delete registered student's name from list");
				String ch = s.nextLine();
				
				ArrayList<StudentClass> hold = new ArrayList<StudentClass>();
				hold = c.getStulist();
				
				if (ch.equalsIgnoreCase("a")) {
					System.out.println("What is the student username? ");
					String uname = s.nextLine();
					
					for (StudentClass stu : hold) {
						if (stu.getUsername().equals(uname)) {
							System.out.println("Please enter new first name: ");
							String first = s.nextLine();
							stu.setFirst(first);
							System.out.println("Please enter new last name: ");
							String last = s.nextLine();
							stu.setLast(last);
							break;
						}
					}
					c.setStulist(hold);
				}
				if (ch.equalsIgnoreCase("b")) {
					System.out.println("What is the student username? ");
					String uname = s.nextLine();
					
					for (StudentClass stu : hold) {
						if (stu.getUsername().equalsIgnoreCase(uname)) {
							hold.remove(stu);
							break;
						}
					}
					c.setStulist(hold);
				}
			}
			if (ans.equalsIgnoreCase("c")) {
				System.out.println("Please enter the new course instructor's name: ");
				String iname = s.nextLine();
				c.setInstruc(iname);
			}
			if (ans.equalsIgnoreCase("d")) {
				System.out.println("What do you want the new section number to be? ");
				int sec = Integer.parseInt(s.nextLine());
				c.setSection(sec);
			}
			if (ans.equalsIgnoreCase("e")) {
				System.out.println("What do you want the new course location to be? ");
				String loc = s.nextLine();
				c.setLoc(loc);
			}
			if (ans.equalsIgnoreCase("f")) {
				for (Course courses : arr) {
					if (Id.equals(courses.getId()) && Na.equals(courses.getName()) && IN.equals(courses.getInstruc())) {
						courses.setLoc(c.getLoc());
						courses.setMax(c.getMax());
						courses.setStulist(c.getStulist());
						courses.setInstruc(c.getInstruc());
						courses.setSection(c.getSection());
						courses.setLoc(c.getLoc());
						break;
					}
				}
				data.setCourses(arr);
				break;
			}
		}
	}

	@Override
	public void displaycinfo(Data data, Scanner s) {
		System.out.println("Please enter course id: ");
		String info = s.nextLine();
		System.out.println("Please enter course name: ");
		String n = s.nextLine();
		System.out.println("Please enter course instructor name: ");
		String in = s.nextLine();
		
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		//iterate through each course id
		
		for (Course courses : arr) {
			
			if (info.equals(courses.getId()) && n.equals(courses.getName()) && in.equals(courses.getInstruc())) {
				
				String name = courses.getName();
				int max = courses.getMax();
				int cur = courses.getCurrent();
				
				ArrayList<StudentClass> st = new ArrayList<StudentClass>();
				st = courses.getStulist();
				
				String inst = courses.getInstruc();
				int sec = courses.getSection();
				String loc = courses.getLoc();
				
				System.out.println("The course name: " + name);
				System.out.println("The max number of students in class: " + max);
				System.out.println("The current number of students in class: " + cur);
				System.out.println("The students in the class: ");
				
				for (StudentClass student : st) {
					System.out.println(student.getFirst() + " " + student.getLast());
				}
				System.out.println("The course instructor: " + inst);
				System.out.println("The course section:  " + sec);
				System.out.println("The course location: " + loc);
			}
		}
	}

	@Override
	public void registerst(Data data, Scanner s) {
		System.out.println("What is the first name of the student you want to register? ");
		String f = s.nextLine();
		System.out.println("What is the last name of the student you want to register? ");
		String l = s.nextLine();
		System.out.println("What is the username of the student you want to register? ");
		String us = s.nextLine();
		System.out.println("What is the password of the student you want to register? ");
		String pa = s.nextLine();
		System.out.println("What is the student id? ");
		String id = s.nextLine();

		StudentClass stu = new StudentClass(us, pa, f,l, id);
		//ADD STUDENT TO STUDENT LIST
		ArrayList<StudentClass> arr = new ArrayList<StudentClass>();
		//fill with og data
		arr = data.getStudents();
		//add students to portal 
		arr.add(stu);
		
		data.setStudents(arr);
		
		System.out.println("Do you want to register this student to a course? (enter yes or no)");
		String ans = s.nextLine();
		if (ans.equals("yes")) {
			
			System.out.println("What is the name of the course? ");
			String cn = s.nextLine();
			System.out.println("What is the course id? ");
			String z = s.nextLine();
			System.out.println("Please enter course instructor name: ");
			String in = s.nextLine();
			
			ArrayList<Course> ar = new ArrayList<Course>();
			ar = data.getCourses();
			
			//iterate through each course id
			for (Course courses : ar) {
				String name = courses.getName();
				String ID = courses.getId();
				String str = courses.getInstruc();
				int max = courses.getMax();
				int cur = courses.getCurrent();
				
				if ((name.equals(cn)) && (ID.equals(z)) && (max != cur) && (in.equals(str))) {
					ArrayList<StudentClass> stud = new ArrayList<StudentClass>();
					stud = courses.getStulist();
					
					stud.add(stu);
					courses.setStulist(stud);
					cur += 1;
					courses.setCurrent(cur);
					break;
				}
			}
			data.setCourses(ar);
		}
	}

	@Override
	public void viewcourses(Data data) {
		ArrayList<Course> C = new ArrayList<Course>();
		ArrayList<StudentClass> sc = new ArrayList<StudentClass>();
		
		C = data.getCourses();
		
		for (Course c : C) {
			System.out.println("Class " + c.getName() + ":");
			sc = c.getStulist();
			for(StudentClass st : sc) {
				System.out.println("List of enrolled students names: ");
				System.out.println(st.getFirst() + " " + st.getLast());
				System.out.println("List of enrolled students ids: ");
				System.out.println(st.getId());
			}
			System.out.println("The number of students registered: " + c.getCurrent());
			System.out.println("The max number of students: " + c.getMax());
		}
	}

	@Override
	public void viewfullcourse(Data data) {
		ArrayList<Course> C = new ArrayList<Course>();
		C = data.getCourses();
		System.out.println("Full courses are: ");
		for (Course c : C) {
			if (c.getCurrent() == c.getMax()) {
				System.out.println(c.getName());
			}
		}
	}

	@Override
	public void writetofile(Data data) {
		String fileName = "max.txt";
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			ArrayList<Course> C = new ArrayList<Course>();
			C = data.getCourses();
			System.out.println("Full courses are: ");
			for (Course c : C) {
				if (c.getCurrent() == c.getMax()) {
					String text = c.getName();
					bufferedWriter.write(text);
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		}
		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
		
	}

	@Override
	public void courseroster(Data data, Scanner s) {
		System.out.println("Please enter course id: ");
		String info = s.nextLine();
		System.out.println("Please enter course name: ");
		String Na = s.nextLine();
		System.out.println("Please enter course instructor name: ");
		String IN = s.nextLine();
		
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		//iterate through each course id
		
		for (Course courses : arr) {
			if (courses.getId().equals(info) && courses.getName().equals(Na) && courses.getInstruc().equals(IN)) {
				ArrayList<StudentClass> sc = new ArrayList<StudentClass>();
				sc = courses.getStulist();
				for(StudentClass st : sc) {
					System.out.println("List of enrolled students names: ");
					System.out.println(st.getFirst() + " " + st.getLast());
				}
			}
		}
	}

	@Override
	public void stucourses(Data data, Scanner s) {
		System.out.println("What is the first name of the student you are looking for? ");
		String fi = s.nextLine();
		System.out.println("What is the last name of the student you are looking for? ");
		String la = s.nextLine();
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		System.out.println("This student is currently registered in: ");
		for (Course courses : arr) {
			ArrayList<StudentClass> sc = new ArrayList<StudentClass>();
			sc = courses.getStulist();
			for(StudentClass st : sc) {
				if (st.getFirst().equals(fi) && st.getLast().equals(la)) {
					System.out.println(courses.getName());
				}
			}
		}
	}

	@Override
	public void sortcourses(Data data) {
		ArrayList<Course> arr = new ArrayList<Course>();
		//fill with og data
		arr = data.getCourses();
		System.out.println("The sorted courses:");
		Collections.sort(arr, new sorting());
        for (Course co : arr) {
            System.out.println(co.getName()); 
        } 
	}
	
	public class sorting implements Comparator<Course> 
	{ 
		@Override
		public int compare(Course o1, Course o2) {
			// TODO Auto-generated method stub
			return o1.getCurrent() - o2.getCurrent();
		} 
	} 
}
