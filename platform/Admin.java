package platform;

import java.io.IOException;

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
	
		System.out.println("Logged in: ADMIN: Seb\n1. Block User\n2. Block Auction\n3. Log Out");
	
		String choice = System_Menu.keyboard.nextLine(); 
		switch (choice) 
		{
		case "1":
			blockUser();
			break;
		case "2":
			blockAuction();
			break;
		case "3":
			System_Menu.signOut();
			break;
				
		}
		
	}
	
	public static void blockUser() throws IOException 
	{
		System.out.println("Select a user to block:");
		
		for (User tempUser:System_Menu.Accounts) 
		{	int userNum = 0;
			System.out.println("Usernum: "+userNum+" :" + tempUser.getUsername());
			userNum++;
				
		}
		
		System.out.println("Type the user number of the user to block: ");
		
		int userNum = 1;
		boolean flag = false;
		while (flag == false) {
			
			if( System_Menu.keyboard.hasNextInt())
			{
				userNum =  System_Menu.keyboard.nextInt();
				flag = true;
			}
			else 
			{
				System.out.println("Please enter a valid user number");
			}
			
			}
				
		User tempUser = new User();
		tempUser = System_Menu.Accounts.get(userNum);
		 
		tempUser.
		.setIsBlocked(true);
		adminMenu();
		
	}
	
	public static void blockAuction() 
	{
		
		
		
	}
	
	
	

}
