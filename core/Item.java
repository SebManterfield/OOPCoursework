package core;

public class Item {
	
	private String Name;
	private String description;
	
	public Item(String name, String description) {
		super();
		Name = name;
		this.description = description;
	}
	
	public String getName() {
		return Name;
	}
	public String getDescription() {
		return description;
	}
	
	

}
