package core;

import java.util.ArrayList;

import platform.User;


public class Seller extends User{
	
	private ArrayList<Item> items; 
	private boolean isBlocked = false;
	
	/**
	 * seller Constructor
	 * @see user
	 * @param username
	 * @param password
	 */
	
	public Seller(String username, String password) {
		super(username, password);
	}
	
	/**
	 * Returns true if the seller is blocked otherwise returns false
	 * @return
	 */
	
	
	public boolean getIsBlocked() {
		return isBlocked;
	}
	
	
	
	/**
	 * Block the seller
	 */
	public void setIsBlocked() {
		
	}

	
}
