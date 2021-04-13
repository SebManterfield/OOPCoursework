package core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import platform.Status;
import platform.System_Menu;
import platform.User;

public class Auction {
	

	private double auctionID;
	private double startPrice;
	private double reservePrice;
	private Date closeDate; 
	private Item itemToSell;
	private Seller seller;
	private Buyer winner;
	private Status status;
	public ArrayList<Bid> Bids = new ArrayList<>();
	public double highestBid;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public Auction(double auctionID, double startPrice, double reservePrice, Date closeDate, boolean isBlocked, Item itemToSell, Seller seller)
	{
		this.auctionID = auctionID;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.closeDate = closeDate;
		this.itemToSell = itemToSell;
		this.seller = seller;
		this.status = Status.PENDING;
	}
	
	public Auction() {
		
	}
	
	public double getAuctionID() {
		return auctionID;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public double getReservePrice() {
		return reservePrice;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public Status getStatus() {
		return this.status;
	}

	public Item getItemToSell() {
		return itemToSell;
	}

	public Seller getSeller() {
		return seller;
	}
	
	public Buyer getWinner()
	{
		
		return this.winner;
	}
	
public static void startAuction() throws IOException {
		
		
		Seller tempSeller = new Seller();
		if(!System_Menu.getLoggedIn()) {
			System.out.println("You need to be logged in!");
			System_Menu.mainMenu();
		}
		for (User user : System_Menu.Accounts) {
			if(user.getUsername().equals(System_Menu.getTempUsername())) {
				tempSeller = new Seller(user.getUsername(),user.getPassword());
				if(tempSeller.isBlocked()) {
					System.out.println("You are are blocked user!");
					System_Menu.mainMenu();
				if (user.getType().equals("Buyer")) 
				{
					System.out.println("Your account is a buyer account, please create a seller account");
					System_Menu.mainMenu();
				}
					
				
				}
			}
		}
		
		System.out.println("Enter item name: ");
		
			
		String item = System_Menu.keyboard.nextLine();
		
		
		System.out.println("Enter item description");
		String description =  System_Menu.keyboard.nextLine();
		
		Item tempItem = new Item(item,description);
		
		System.out.println("Enter Starting Price: ");
		
		double startPrice= 0;
		
		boolean flag = false;
		
		while (flag == false) {
		if( System_Menu.keyboard.hasNextDouble())
		{
			startPrice =  System_Menu.keyboard.nextDouble();
			flag = true;
		}
		else 
		{
			System.out.print("Please enter a valid number");
		}
		
		}
		
		
		System.out.println("Enter Reserve Price: ");
		double reservePrice = 0;
		flag=false;
		
		while (flag == false) {
			if( System_Menu.keyboard.hasNextDouble())
			{
				reservePrice =  System_Menu.keyboard.nextDouble();
				flag = true;
			}
			else 
			{
				System.out.println("Please enter a valid number");
			}
			
			}
				
				

		int closingDate = 8;
		
		while(closingDate > 7) {
			System.out.println("How many days would you like the auction to last (Max 7 days): ");
			closingDate =  System_Menu.keyboard.nextInt();
		}
		
		Date date = new Date(); 
		sdf.format(date);
		
		date.setDate(date.getDate()+closingDate);
		
		double auctionID = 1;
		
		if(System_Menu.Auctions.size() != 0) {
			auctionID = System_Menu.Auctions.size() + 1;
		}
		
		System.out.println("Verify these details:\nItem Name: " + tempItem.getName() + 
				"\nItem Price: " + startPrice + "\nReserve Price: " + reservePrice + 
				"\nEnd Date: " + date + "\n(Y/N): ");
		
		String verify =  System_Menu.keyboard.next();
		
		if(verify.toLowerCase().equals("y")) {
			Auction newAuction = new Auction(auctionID,startPrice,reservePrice,date,false,tempItem,tempSeller);
			 System_Menu.Auctions.add(newAuction);
			 System_Menu.exportAuctions();
			 newAuction.status = Status.ACTIVE;
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
		for (Auction auction :  System_Menu.Auctions) {
			if(auction.status.equals(Status.ACTIVE))
			{
			System.out.println(auction.getAuctionID() + "\t" + auction.getItemToSell().getName() + 
					"\t" + auction.getStatus());
			}
		}
		
		System.out.println("Do you want to bid on an auction?(Y/N): ");
		String choice =  System_Menu.keyboard.nextLine();
		
		if(choice.toLowerCase().equals("y")) {
			System_Menu.placeBid();
		}
		else { 
			 System_Menu.mainMenu();
		}
	}
	
	
	public void close()
	
	{
		Date date = new Date();
		
	
		if(date.getDate().equals(this.closeDate))
		{
			
		}
		
		
	}
	 
	public boolean isBlocked()
	{
		if(this.status.equals(Status.BLOCKED))
		{
			return true;
		
		}
		
		else
		{
			return false;
		}
		
	}
	
	public void setBlocked()
	{
		this.status = Status.BLOCKED;
	}
	
	public void setUnblocked()
	{
		this.status = Status.ACTIVE;
	}
	
	
	
	
	
	
	
	

}
