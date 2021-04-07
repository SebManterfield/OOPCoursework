package core;

import java.util.ArrayList;

import platform.User;

public class Seller extends User{
	
	private ArrayList<Item> items; 
	private boolean isBlocked = false;
	
	public Seller(String username, String password) {
		super(username, password);
	}
	
	public boolean getIsBlocked() {
		return isBlocked;
	}
	
	public void setIsBlocked() {
		
	}

	
}
