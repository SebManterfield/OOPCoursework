package core;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
	

	private double auctionID;
	private double startPrice;
	private double reservePrice;
	private Date closeDate; 
	private boolean isBlocked;
	private Item itemToSell;
	private Seller seller;
	public ArrayList<Bid> Bids = new ArrayList<>();
	
	public Auction(double auctionID, double startPrice, double reservePrice, Date closeDate, boolean isBlocked, Item itemToSell, Seller seller)
	{
		this.auctionID = auctionID;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.closeDate = closeDate;
		this.isBlocked = isBlocked;
		this.itemToSell = itemToSell;
		this.seller = seller;
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

	public boolean getStatus() {
		return isBlocked;
	}

	public Item getItemToSell() {
		return itemToSell;
	}

	public Seller getSeller() {
		return seller;
	}
	
	
	public void placeBid()
	{}
	
	public void verify()
	{}
	
	public void close()
	{}
	
	public boolean isBlocked()
	{
		return false;}
	
	public void setBlocked()
	{}
	
	
	
	
	
	
	

}
