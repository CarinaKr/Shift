package account;

public class Account {
	
	private String userName;
	private String password;
	private double time;
	
	public Account(String name, String password){
		this.userName = name;
		this.password = password;
		this.time = 1.5;
	}
	
	public String getName(){
		return this.userName;
	}
	
	public double getTime(){
		return this.time;
	}
}
