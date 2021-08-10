package Battulga_HW1;

import java.util.Scanner;

public interface Admin {
	
	public abstract void createcourse(Data data, Scanner s);
		
	public abstract void deletecourse(Data data, Scanner s);
		
	public abstract void editcourse(Data data, Scanner s); //everything except courseID and name
	
	public abstract void displaycinfo(Data data, Scanner s);
			
	public abstract void registerst(Data data, Scanner s);
		
	public abstract void viewfullcourse(Data data);
	
	public abstract void writetofile(Data data);
	
	public abstract void courseroster(Data data, Scanner s);
	
	public abstract void sortcourses(Data data);

	

}
