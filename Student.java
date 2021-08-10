package Battulga_HW1;

import java.io.Serializable;
import java.util.Scanner;

public interface Student extends Serializable{
	
	public abstract void viewopen(Data data);
	
	public abstract void register(Data data, Scanner s, StudentClass st);
	
	public abstract void withdraw(Data data, Scanner s, StudentClass st);
	
	public abstract void mycourses(Data data, StudentClass st);
	
	public abstract void exit();
	
}
