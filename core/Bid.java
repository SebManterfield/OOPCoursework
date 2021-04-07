package core;

import java.util.Date;

public class Bid {
	
	private double amount;
	private Buyer who;
	private Date when;
	
	public Bid(double amount, Buyer who, Date when) {
		super();
		this.amount = amount;
		this.who = who;
		this.when = when;
	}
	
	public Bid() {
		
	}

	public double getAmount() {
		return amount;
	}

	public Buyer getWho() {
		return who;
	}

	public Date getWhen() {
		return when;
	}

}
