package account;

public class Account implements Comparable<Account> {
	
	private String userName;
	private String password;
	private double time;
	
	public Account(String name, String password){
		this.userName = name;
		this.password = password;
		this.time = 0;
	}
	
	public String getName(){
		return this.userName;
	}
	
	public double getTime(){
		return this.time;
	}
	
	public void setTime(double newTime){
		this.time = newTime;
	}
	
	public void setName(String newName){
		this.userName = newName;
	}
	
	public void setPassword(String newPassword){
		this.password = newPassword;
	}

	@Override
	public int compareTo(Account other) {
		// TODO Auto-generated method stub
		if(this.time < other.getTime()){
			return -1;
		}
		else if(this.time > other.getTime()){
			return 1;
		}
		else return 0;
		
	}
	
	
}
