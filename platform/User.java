package platform;

public class User {
	
	private String username;
	private String password;
	private String type;

	
	public User(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}
	

	public User() {
		
	}
	
	


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
		
	}
	


	public boolean checkPassword()
	{
		
		return false;
		
	}
	

}
