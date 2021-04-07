package core;

import platform.User;


public class Seller extends User{
	
	
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
	
	

	public Seller() {
		// TODO Auto-generated constructor stub
	}


	public boolean getIsBlocked() {
		return isBlocked;
	}
		
	
	/**
	 * Block the seller
	 */
	

	public void setIsBlocked(boolean blocked) {

		
		isBlocked = blocked;
	}

	
}
