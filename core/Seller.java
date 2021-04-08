package core;

import platform.Status;
import platform.User;


public class Seller extends User{
	
	

	
	/**
	 * seller Constructor
	 * @see user
	 * @param username
	 * @param password
	 */
	
	private Status status;
	
	public Seller(String username, String password) {
		super(username, password,"Seller");
		this.status = Status.ACTIVE;
	}
	

	/**
	 * Returns true if the seller is blocked otherwise returns false
	 * @return
	 */
	
	

	public Seller() {
		// TODO Auto-generated constructor stub
	}


	
	public boolean isBlocked()
	{
		if(this.status.equals(Status.BLOCKED)) {
			return true;
		}
		else {
		
		return false;
		}
	}
		
	
	/**
	 * Block the seller
	 */
	
	
	public void setIsBlocked(boolean blocked) {

		
		this.status=Status.BLOCKED;
	}
	
	public void setUnblocked() {
		this.status = Status.ACTIVE;
	}
	

	
}
