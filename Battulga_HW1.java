package Battulga_HW1;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Battulga_HW1 {
	
	private static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		//CHECK IF ITS USER'S FIRST TIME ENTERING	
		System.out.println("Are you an Admin or Student? (enter admin or student)");
		String user = s.nextLine().toLowerCase();
	
		if (user.equalsIgnoreCase("admin")) {
			System.out.println("Is this your first time entering the system (enter yes or no)");
			String wat = s.nextLine();
			ArrayList<StudentClass> students = new ArrayList<StudentClass>();
			ArrayList<Course> courses= new ArrayList<Course>();
			//empty data class with empty students and courses
			Data data = new Data(students, courses);
			
			if (wat.equals("yes")) {
				//populate data with csv file
				data = reading(data, courses);
			}
			if (wat.equals("no")){
				//populate data with ser file
				data = reading(data, students, courses);
			}
			AdminClass a = new AdminClass();
			a = askuser(user, data);
			
			//ADMIN SYSTEM
			while(true) {
				System.out.println("You have entered the Admin system. Please choose from the menu. (enter 1 or 2)");
				System.out.println("1) Course Management");
				System.out.println("2) Reports");
				System.out.println("3) Exit");
				
				String input = s.nextLine();
					
				//ADMIN COURSE MANAGEMENT
				if (input.equalsIgnoreCase("1")) {
					while (true) {
						System.out.println("You have entered Course Management. Please choose from the menu. (enter corresponding letter)");
						System.out.println("a) Create new course");
						System.out.println("b) Delete a course");
						System.out.println("c) Edit a course");
						System.out.println("d) Display information for given course");
						System.out.println("e) Register a student");
						System.out.println("f) Exit ");
						String ans = s.nextLine();
						
						if (ans.equalsIgnoreCase("a")) {
							a.createcourse(data, s);
							continue;
						}
						if (ans.equalsIgnoreCase("b")) {
							a.deletecourse(data, s);
							continue;
						}
						if (ans.equalsIgnoreCase("c")) {
							a.editcourse(data, s);
							continue;
						}
						if (ans.equalsIgnoreCase("d")) {
							a.displaycinfo(data, s);
							continue;
						}
						if (ans.equalsIgnoreCase("e")) {
							a.registerst(data, s);
							continue;
						}
						if (ans.equalsIgnoreCase("f")) {
							
							try {
								FileOutputStream fos = new FileOutputStream("Students.ser");
									
								//ObjectOutputStream writes objects to a stream (A sequence of data)
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								
								ArrayList<StudentClass> sc = new ArrayList<StudentClass>();
								sc = data.getStudents();
									
								//Writes the specific object to the OOS
								oos.writeObject(sc);
								//Close both streams
								oos.close();
								fos.close();
								System.out.println("1 Serialization complete");
							} 
							catch (IOException ioe) {
								ioe.printStackTrace();
							}
							
							try {
								FileOutputStream fos = new FileOutputStream("Courses.ser");
									
								//ObjectOutputStream writes objects to a stream (A sequence of data)
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								
								ArrayList<Course> crs = new ArrayList<Course>();
								crs = data.getCourses();
								//Writes the specific object to the OOS
								oos.writeObject(crs);
							
								//Close both streams
								oos.close();
								fos.close();
								System.out.println("2 Serialization complete");
							} 
							catch (IOException ioe) {
								ioe.printStackTrace();
							}
							break;
						}
						else {
							continue;
						}
					}
				}
				
				//ADMIN REPORTS
				if(input.equalsIgnoreCase("2")) {
					while(true) {
						System.out.println("You have entered Reports. Please choose from the menu. (enter corresponding letter)");
						System.out.println("a) View all courses");
						System.out.println("b) View all courses that are full");
						System.out.println("c) Write to a file the list of course that are full");
						System.out.println("d) View the names of students that are registered in a specific course");
						System.out.println("e) View list of courses a given student is registered in");
						System.out.println("f) Sort");
						System.out.println("g) Exit");
						String ans = s.nextLine();
						
						if (ans.equalsIgnoreCase("a")) {
							a.viewcourses(data);
							continue;
						}
						if (ans.equalsIgnoreCase("b")) {
							a.viewfullcourse(data);
							continue;
						}
						if (ans.equalsIgnoreCase("c")) {
							a.writetofile(data);
							continue;
							
						}
						if (ans.equalsIgnoreCase("d")) {
							a.courseroster(data, s);
							continue;
							
						}
						if (ans.equalsIgnoreCase("e")) {
							a.stucourses(data, s);
							continue;
							
						}
						if (ans.equalsIgnoreCase("f")) {
							a.sortcourses(data);
							continue;
							
						}
						if (ans.equalsIgnoreCase("g")) {	
							break;
						}
						else {
							continue;
						}
					}
				}
				if (input.equals("3")) {
					break;
				}
			}
		}
		
		if (user.equalsIgnoreCase("student")) {
			ArrayList<StudentClass> students = new ArrayList<StudentClass>();
			ArrayList<Course> courses= new ArrayList<Course>();
			Data data = new Data(students, courses);
			
			data = reading(data, students, courses);
			
			StudentClass stud = new StudentClass();
			stud = askuser2(stud, data);
			
			boolean v =true; 
			while(v) {
					System.out.println("You have entered the Student system. Please choose from the Course Management menu. (enter corresponding letter)");
					System.out.println("a) View all courses");
					System.out.println("b) View all courses that are not full");
					System.out.println("c) Register in a course");
					System.out.println("d) Withdraw from a course");
					System.out.println("e) View all courses that a student is registered in");
					System.out.println("f) Exit");
					String input = s.nextLine();
					if (input.equalsIgnoreCase("a")) {
						stud.viewcourses(data);
						continue;
					}
					if (input.equalsIgnoreCase("b")) {
						stud.viewopen(data);
						continue;
					}
					if (input.equalsIgnoreCase("c")) {
						stud.register(data, s, stud);
						continue;
					}
					if (input.equalsIgnoreCase("d")) {
						stud.withdraw(data, s, stud);
						continue;
					}
					if (input.equalsIgnoreCase("e")) {
						stud.mycourses(data, stud);
						continue;
					}
					if (input.equalsIgnoreCase("f")) {
						try {
							FileOutputStream fos = new FileOutputStream("Students.ser");
								
							//ObjectOutputStream writes objects to a stream (A sequence of data)
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							
							ArrayList<StudentClass> sc = new ArrayList<StudentClass>();
							sc = data.getStudents();
								
							//Writes the specific object to the OOS
							oos.writeObject(sc);
							//Close both streams
							oos.close();
							fos.close();
							//System.out.println("Serialization complete");
						} 
						catch (IOException ioe) {
							ioe.printStackTrace();
						}
						try {
							FileOutputStream fos = new FileOutputStream("Courses.ser");
								
							//ObjectOutputStream writes objects to a stream (A sequence of data)
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							
							ArrayList<Course> crs = new ArrayList<Course>();
							crs = data.getCourses();
							//Writes the specific object to the OOS
							oos.writeObject(crs);
						
							//Close both streams
							oos.close();
							fos.close();
							//System.out.println("Serialization complete");
						} 
						catch (IOException ioe) {
							ioe.printStackTrace();
						}
						v = false;
						break;
					}
					else {
							System.out.println("Please enter again.");
					}
				}
			}
		}
	
	//IF ADmin's FIRST TIME ENTERING 
	public static AdminClass askuser(String user, Data data) {
		//MAKE SURE ADMIN INPUTS CORRECT USERNAME AND PASSWORD
		AdminClass a = new AdminClass();
		boolean t = true; 
		while (t) {
			System.out.println("Please enter your username: ");
			String u = s.nextLine();
			System.out.println("Please enter your password: ");
			String p = s.nextLine();
			if (u.equals(a.getUsername()) && p.equals(a.getPassword())) {
				break;
			}
			else {
				System.out.println("Either your username or password is wrong. Please try again.");
				continue;
			}     
		}
		return a;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Data reading(Data data, ArrayList<StudentClass> students, ArrayList<Course> courses) {
		
		try{
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream("Students.ser");
	      
			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
	      
			//Cast as Employee. readObject will take the object from ObjectInputStream
			students = (ArrayList) ois.readObject();
			
			data.setStudents(students);
	      
			ois.close();
			fis.close();
			
			
	    	}
	    	catch(IOException ioe) {
	    		ioe.printStackTrace();
	    	}
			catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
		/*for (StudentClass employee : students) {
			System.out.println("lets do this");
			System.out.println(employee.getId());
        }*/
		
		try{
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream("Courses.ser");
	      
			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
	      
			//Cast as Employee. readObject will take the object from ObjectInputStream
			
			courses = (ArrayList)ois.readObject();
	      
			data.setCourses(courses);
			ois.close();
			fis.close();
	    	}
	    	catch(IOException ioe) {
	    		ioe.printStackTrace();
	    		
	    	}
			catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				
			}
		/*
		for (Course employe : courses) {
            System.out.println(employe.getName());
        }*/
		return data;
	}
		
	public static Data reading(Data data, ArrayList<Course> courses) {
		String file = "MyUniversityCourses.csv";
		Path pa = Paths.get(file);
		
		try{	
			BufferedReader br = Files.newBufferedReader(pa);
			@SuppressWarnings("unused")
			String k = br.readLine();
			String row = br.readLine();
			ArrayList<StudentClass> stu = new ArrayList<StudentClass>();
	                
			// loop until all lines are read
		    while (row != null) {
		    	String[] elem = row.split(",");
		            	
		    	Course course = new Course();
		    	course.setName(elem[0]);
		    	course.setId(elem[1]);
		    	course.setStulist(stu);
		    	course.setMax((int)Integer.parseInt(elem[2]));
		    	course.setCurrent((int)Integer.parseInt(elem[3]));
		    	course.setInstruc(elem[5]);
		    	course.setSection((int)Integer.parseInt(elem[6]));
		    	course.setLoc(elem[7]);

		    	courses.add(course);
		    	row = br.readLine();
		    }
		    
		    //create new Data object to store course information in 
		    data.setCourses(courses);
		    
		}catch(FileNotFoundException ex){
	    	System.out.println( "Unable to open file ");	
		    ex.printStackTrace();
	    }
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return data;
	}
	
	//IF ENTERING FOR 1+ TIMES
	public static StudentClass askuser2(StudentClass stud, Data data) {
		ArrayList<StudentClass> arr = new ArrayList<StudentClass>();
		//fill with og data
		arr = data.getStudents();
			
		while (true) {
			System.out.println("Please enter your username: ");
			String u = s.nextLine();
			System.out.println("Please enter your password: ");
			String p = s.nextLine();
			for (StudentClass st : arr) {
				if (u.equals(st.getUsername()) && p.equals(st.getPassword())) {
					return st;
				}
				else {
					System.out.println("Either your username or password is wrong. Please try again.");
					continue;
				}     
			}
		}
	}
}

	





	
