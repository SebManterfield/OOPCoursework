package platform;

import java.io.IOException;

import core.Auction;
import core.Seller;

public class Admin {
	
	public static void adminLogin() throws IOException
	{
		String adminUsername = "seb";
		String adminPassword = "sebpass";
		
		System.out.println("Please enter admin username: ");
		String username = System_Menu.keyboard.nextLine();
		
		System.out.println("Please enter admin password: ");
		String password = System_Menu.keyboard.nextLine();
		
		if(!adminPassword.equals(password)|| !adminUsername.equals(username))
		{
			
			System.out.println("Username or password incorrect, please try again");
			adminLogin();
		}
		
		System_Menu.setloggedIn(true);
		adminMenu();
		
		
		
	}
	
	public static void adminMenu() throws IOException 
	{
	
		System.out.println("Logged in: ADMIN: Seb\n1. Change User Status\n2. Change Auction Status\n3. Log Out");
	
		String choice = System_Menu.keyboard.nextLine(); 
		switch (choice) 
		{
		case "1":
			changeUserStatus();
			break;
		case "2":
			changeAuctionStatus();
			break;
		case "3":
			System_Menu.signOut();
			break;
				
		}
		
	}
	
	public static void changeUserStatus() throws IOException 
	{	Seller tempSeller = new Seller();
		System.out.println("Select a user to change the status of (Blocked users will be unblocked and vice versa)):");
		
		for (User tempUser:System_Menu.Accounts) 
		{	
			int userNum = 1;
			System.out.println("User number: "+userNum+"\t Username: " + tempUser.getUsername());
			tempSeller = (Seller) tempUser;
			if (tempSeller.isBlocked())
			{
				System.out.print("\tStatus: Blocked");
			}
			
			else if (!tempSeller.isBlocked())
			{
				System.out.print("\tStatus: Active");
			}
			userNum++;
				
		}
		
		System.out.println("Type the user number of the user to change the status of: ");
		
		int userNumToBlock = 0;
		boolean flag = false;
		while (flag == false) {
			
			if( System_Menu.keyboard.hasNextInt())
			{
				flag = true;
				userNumToBlock = System_Menu.keyboard.nextInt();
			}
			else 
			{
				System.out.println("Please enter a valid user number");
			}
			
			}
		
		if (userNumToBlock > System_Menu.Accounts.size()+1 || userNumToBlock < 0)
		{
			System.out.println("Inavlid Number input, please enter a valid value");
			changeUserStatus();
		}
				
		
		
		tempSeller = (Seller) System_Menu.Accounts.get(userNumToBlock-1);
		
		if(!tempSeller.isBlocked()) {
		tempSeller.setIsBlocked(true);
		}
		else if (tempSeller.isBlocked())
		{
			tempSeller.setIsBlocked(false);
		}
		
		
		adminMenu();
		
	}
	
	
	
	public static void changeAuctionStatus() throws IOException 
	{
		int auctionNum = 1;
		System.out.println("Select an auction to change the status of (Blocked users will be unblocked and vice versa)):");
	
		for (Auction auction: System_Menu.Auctions)
		{
			System.out.println("Auction Number: "+ auctionNum + "\tItem on sale: " + auction.getItemToSell().getName()+"\\tAuction Status: ");
			if (auction.isBlocked())
			{
				System.out.print("Blocked");
			}
			else if (!auction.isBlocked())
			{
				System.out.print("Active");
			}
			
			auctionNum++;
		}
		
		System.out.println("Type the auction number of the user to change the status of: ");
		
		Auction tempAuction = new Auction();
		int auctionNumToBlock = 0;
		boolean flag = false;
		while (flag == false) {
			
			if( System_Menu.keyboard.hasNextInt())
			{
				flag = true;
				auctionNumToBlock = System_Menu.keyboard.nextInt();
			}
			else 
			{
				System.out.println("Please enter a valid auction number");
			}
			
			}
		
		if (auctionNumToBlock > System_Menu.Accounts.size()+1 || auctionNumToBlock < 0)
		{
			System.out.println("Inavlid Number input, please enter a valid value");
			changeAuctionStatus();
		}
		
		

		tempAuction = System_Menu.Auctions.get(auctionNumToBlock-1);
		
		if(!tempAuction.isBlocked()) {
			tempAuction.setBlocked();
		}
		else if (tempAuction.isBlocked())
		{
			tempAuction.setUnblocked();
		}
		
		
		adminMenu();
		
		
		
	}
	
	
	
	
	

}
