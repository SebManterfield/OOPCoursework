package platform;

public class User {
	
	private String username;
	private String password;
	
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	public User() {
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


	public boolean checkPassword()
	{
		
		return false;
		
	}
	

}
