package Battulga_HW1;

import java.util.Scanner;

@SuppressWarnings("serial")
public class User implements java.io.Serializable{
	private String username;
	private String password;
	private String first;
	private String last;
	
	public User() {
		
	}

	public User(String username, String password, String first, String last) {
		this.username = username;
		this.password = password;
		this.first = first;
		this.last = last;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
	
	public void viewcourses(Data data) {
		
	}
	
	public void stucourses(Data data, Scanner s) {
		
	}
	
}
