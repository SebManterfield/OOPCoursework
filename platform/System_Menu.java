package platform;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import core.Auction;
import core.Bid;
import core.Buyer;
import core.Item;
import core.Seller;



/**
 * @author ebbsc
 *
 */
public class System_Menu {
	
	public static Scanner keyboard = new Scanner(System.in);
	public static ArrayList<Auction> Auctions  = new ArrayList<>();
	public static ArrayList<User> Accounts = new ArrayList<>();
	private static boolean loggedIn = false;
	private static String tempUsername;
	

	public static void main (String[] args) throws IOException
	{
		importAuctions();
		mainMenu();
			
	}
	
	
	/**
	 * @throws IOException
	 */
	public static void mainMenu() throws IOException 
	{
		Buyer test = new Buyer("bob","bobpass");
		Accounts.add(test);
		System.out.println("------------------------");
		System.out.println("Welcome to Auction System");
		System.out.println("------------------------");
		
		if(loggedIn == true) {
			System.out.println("Logged In: " + tempUsername);
		}
		
		System.out.println("Please select:");
		System.out.println("1. Browse Auctions\n2. Setup Account\n3. Start Auction\n4. Admin Login");
		
		if(loggedIn) {
			System.out.println("5. Sign Out\n6. Exit");
		}
		else {
			System.out.println("5. Login\n6. Exit");
		}
		
		String choice = keyboard.nextLine(); 
		
		
		
		
		
	
		
		switch (choice) 
		{
		case "1":
			Auction.browseAuction();
			break;
		case "2":
			setupAccount();
			break;
		case "3":
			Auction.startAuction();
			break;
		case "4":
			Admin.adminLogin();
			break;
			
			
		case "5":
			if(!loggedIn) {
				login();
				break;
			}
			else if(loggedIn) {
				signOut();
				break;
			}
			break;
		case "6":
			System.exit(0);
			break;
		
		default:
			System.out.println("Invalid input, please try again");
			mainMenu();
			break;
		}
	}
	
	

	public static void signOut() throws IOException {
		loggedIn = false;
		tempUsername = "";
		System.out.println("Successfully Logged Out!");
		mainMenu();
	}



	/**
	 * @throws IOException
	 */
	public static void importAuctions() throws IOException 
	{
		

	}
	
	/**
	 * @throws IOException
	 */
	public static void exportAuctions() throws IOException 
	{
		String AuctionPath = "auctions.txt";
		
		PrintWriter write = new PrintWriter(AuctionPath);
		for (Auction auction : Auctions) {
			write.print(auction.getAuctionID() + "," + auction.getStartPrice() + "," + 
					auction.getReservePrice() + "," + auction.getCloseDate() + "," + auction.getStatus()
					+ "," + auction.getItemToSell() + "," + auction.getSeller() + "," + "[" );
			
			for (Bid bid : auction.Bids) {
				write.printf("(" + bid.getAmount() + "," + bid.getWho() + "," + bid.getWhen() + ")");
			}
			write.printf("]");
		}
		write.close();
		mainMenu();
	}
	
	
	
	public static void placeBid() throws IOException {
		if(loggedIn == false) {
			System.out.println("Need to login to place bid!\nDo you want to:\n1: Log in\n2. Sign up\n3. Return to main menu ");
			String choice = keyboard.nextLine();
			
			switch(choice)
			{
			case "1":
				login();
				break;
			case "2":
				setupAccount();
				break;
			case "3":
				mainMenu();
				break;
			default:
				System.out.println("Invalid input, please try again");
				placeBid();
				break;
			
			}
			
			for (Auction auction: Auctions)
			{
				System.out.println("Auction No: "+ auction.getAuctionID() +"\tItem on sale: "+auction.getItemToSell()+"\tStarting Price " + auction.getStartPrice()+"\tHighest Bidder: "+auction.getHighestBid() )
				
				
			}
			
			
		
		}
	
	
	
	
	}
	
	/**
	 * @throws IOException
	 */
	public static void login() throws IOException {
		System.out.println("Enter Username: ");
		String username = keyboard.nextLine();
		
		System.out.println("Enter Password: ");
		String password = keyboard.nextLine();
		
		for (User account : Accounts) {
			if(!username.equals(account.getUsername()) || !password.equals(account.getPassword())) {
				System.out.println("Username or Password incorrect");
				mainMenu();
			}
		}
		
		loggedIn = true;
		tempUsername = username;
		
		System.out.println("You have successfully logged in!");
		mainMenu();
		
	}
	
	

	/**
	 * @throws IOException
	 */
	public static void setupAccount() throws IOException {
	
		System.out.println("Enter Username: ");
		String username = keyboard.nextLine();
		
		for (User account : Accounts) {
			if(username.equals(account.getUsername())) {
				System.out.println("Username already taken!");
				setupAccount();
			}
		}
		
		System.out.println("Enter Password: ");
		String password = keyboard.nextLine();
		
		System.out.println("Would you like a Seller or Buyer account? (S/B)");
		String type = keyboard.nextLine().toLowerCase();
		
		if(type.equals("s")) {
			type = "Seller";		
		}
		else if (type.equals("b"))
		{
			type = "Buyer";
		}
		else
		{
			System.out.println("Invlaid type");
			setupAccount();
		}
		
		if (type.equals("Seller"))
		{
			Accounts.add(new Seller(username,password));
		}
		
		else if (type.equals("Buyer"))
		{
			Accounts.add(new Buyer(username,password));
		}
		
		
		System.out.print("Account Created!");
		
		loggedIn = true;
		tempUsername = username;
		
		mainMenu();
		
	}


	public static void setloggedIn(boolean b) {
		
		loggedIn = b;
		
	}
	
	public static boolean getLoggedIn()
	{
		return loggedIn;
	}
	
	public static String getTempUsername()
	{
		return tempUsername;
	}
	
	

}
