package core;

import platform.User;

public class Buyer extends User{


	
	public Buyer(String username, String password) {
		
		super(username,password,"Buyer");
	}
	
	public void victory(Auction auction, Bid bid) 
	{
		System.out.println("The winner of the Auction for the item: " + auction.getItemToSell().getName() + "is "+this.getUsername() + " for the amount £"+bid.getAmount());
	}
	
}
