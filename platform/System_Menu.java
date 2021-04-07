package platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import core.Auction;
import core.Bid;
import core.Item;
import core.Seller;

public class System_Menu {
	
	public static Scanner keyboard = new Scanner(System.in);
	public static ArrayList<Auction> Auctions  = new ArrayList<>();
	public static ArrayList<User> Accounts = new ArrayList<>();
	private static boolean loggedIn = false;
	private static boolean adminLoggedIn = false;
	private static String tempUsername;
	
	
	public static void main (String[] args) throws IOException
	{
		importAuctions();
		mainMenu();
			
	}
	
	
	public static void mainMenu() throws IOException 
	{
		User test = new User("bob","bobpass");
		Accounts.add(test);
		System.out.println("------------------------");
		System.out.println("Welcome to Auction System");
		System.out.println("------------------------");
		
		if(loggedIn == true) {
			System.out.println("Logged In: " + tempUsername);
		}
		
		System.out.println("Please select:");
		System.out.println("1. Browse Auctions\n2. Setup Account\n3. Start Auction");
		
		if(loggedIn) {
			System.out.println("4. Sign Out\n5. Exit");
		}
		else {
			System.out.println("4. Login\n5. Exit");
		}
		
		String choice = keyboard.nextLine(); 
		
		//Need login for admin
		//Need validation for file(try/catch) and every input
		
		switch (choice) 
		{
		case "1":
			browseAuction();
			break;
		case "2":
			setupAccount();
			break;
		case "3":
			startAuction();
			break;
		case "4":
			if(!loggedIn) {
				login();
				break;
			}
			else if(loggedIn) {
				signOut();
				break;
			}
			break;
		case "5":
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


	public static void importAuctions() throws IOException 
	{
		String AuctionPath = "auctions.txt";
		Path pathToFile =Paths.get(AuctionPath);

		try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII))
		{
			String line = br.readLine();
			
			while (line != null) {
					String[] attributes = line.split(",");

			}
		}

	}
	
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
	
	public static void startAuction() throws IOException {
		Seller tempSeller = null;
		if(!loggedIn) {
			System.out.println("You need to be logged in!");
			mainMenu();
		}
		for (User user : Accounts) {
			if(user.getUsername().equals(tempUsername)) {
				tempSeller = new Seller(user.getUsername(),user.getPassword());
				if(tempSeller.getIsBlocked()) {
					System.out.println("You are are blocked user!");
					mainMenu();
				}
			}
		}
		
		System.out.println("Enter item name: ");
		String item = keyboard.nextLine();
		
		System.out.println("Enter item description");
		String description = keyboard.nextLine();
		
		Item tempItem = new Item(item,description);
		
		System.out.println("Enter Starting Price: ");
		double startPrice = keyboard.nextDouble();
		
		System.out.println("Enter Reserve Price: ");
		double reservePrice = keyboard.nextDouble();
		
		int closingDate = 8;
		
		while(closingDate > 7) {
			System.out.println("How many days would you like the auction to last (Max 7 days): ");
			closingDate = keyboard.nextInt();
		}
		
		Date date = new Date(); 
		date.setDate(date.getDate()+closingDate);
		
		double auctionID = 1;
		
		if(Auctions.size() != 0) {
			auctionID = Auctions.size() + 1;
		}
		
		System.out.println("Verify these details:\nItem Name: " + tempItem.getName() + 
				"\nItem Price: " + startPrice + "\nReserve Price: " + reservePrice + 
				"\nEnd Date: " + date + "\n(Y/N): ");
		
		String verify = keyboard.next();
		
		if(verify.toLowerCase().equals("y")) {
			Auction newAuction = new Auction(auctionID,startPrice,reservePrice,date,false,tempItem,tempSeller);
			Auctions.add(newAuction);
			exportAuctions();
			System.out.println("Auction Started!");
				
		}
		else if (verify.toLowerCase().equals("n")) {
			startAuction();
		}
		else {
			System.out.println("Error");
		}
	}
	
	public static void browseAuction() throws IOException {
		System.out.print("Auction ID\tItem\tStatus");
		for (Auction auction : Auctions) {
			System.out.println(auction.getAuctionID() + "\t" + auction.getItemToSell().getName() + 
					"\t" + auction.getStatus());
		}
		
		System.out.println("Do you want to bid on an auction?(Y/N): ");
		String choice = keyboard.nextLine();
		
		if(choice.toLowerCase().equals("y")) {
			placeBid();
		}
		else {
			mainMenu();
		}
	}
	
	public static void placeBid() throws IOException {
		if(loggedIn == false) {
			System.out.println("Need to login to place bid!\nDo you want to create an account?(Y/N): ");
			String choice = keyboard.nextLine();
			if(choice.toLowerCase().equals("y")) {
				setupAccount();
			}
		}
	}
	
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
		
		User newAccount = new User(username,password);
		
		Accounts.add(newAccount);
		System.out.print("Account Created!");
		
		loggedIn = true;
		tempUsername = newAccount.getUsername();
		
		mainMenu();
		
	}
	
	

}
