package account;

import java.io.Serializable;

import window.WindowController;
import window.WindowView;

public class Account implements Comparable<Account>,Serializable {
	
	/**<dd>
	* <h3><i> Account </i></h3>
	 * <p>
	 * In the Account class the player's name, password and time gets saved and passed over to the Highscore.
	 * The class implements the interfaces Comparable<Account> and Serializable.
	 */
	private static final long serialVersionUID = 18022017;
	private String userName;
	private String password;
	private double time;
	
	/**<dd>
	 * <h3><i> Account </i></h3>
	 * <p>
	 * <code>{@code public Account({@link String} name, {@link String} passwort)}</code>
	 * </p>
	 * 
	 * creates a new account with the given name and passwort
	 * 
	 * @param name
	 * @param password
	 */
	public Account(String name, String password){
		this.userName = name;
		this.password = password;
		this.time = 0;
	}
	
	/**<dd>
	 * <h3><i> getName </i></h3>
	 * <p>
	 * <code>{@code public getName()}</code>
	 * </p>
	 * returns the username of the account's owner
	 * @return username
	 */
	public String getName(){
		return this.userName;
	}
	
	/**
	 * <dd>
	 * <h3><i> getTime </i></h3>
	 * <p>
	 * <code>{@code public getTime()}</code>
	 * </p>
	 * returns the time
	 * @return time
	 */
	public double getTime(){
		return this.time;
	}
	
	/**
	 * <dd>
	 * <h3><i> setTime </i></h3>
	 * <p>
	 * <code>{@code public setTime({@link double} newTime)}</code>
	 * </p>
	 * sets the time to newTime
	 * @param newTime
	 */
	public void setTime(double newTime){
		this.time = newTime;
	}
	
	/**
	 * <dd>
	 * <h3><i> setName </i></h3>
	 * <p>
	 * <code>{@code public setName({@link String} newName)}</code>
	 * </p>
	 * sets the account users name to newName
	 * @param newName
	 */
	public void setName(String newName){
		this.userName = newName;
	}
	
	/**
	 * <dd>
	 * <h3><i> setPassword </i></h3>
	 * <p>
	 * <code>{@code public setPassword({@link String} newPassword)}</code>
	 * </p>
	 * sets the account user's password to newPassword
	 * @param newPassword
	 */
	public void setPassword(String newPassword){
		this.password = newPassword;
	}

	/**
	 * <dd>
	 * <h3><i> compareTo </i></h3>
	 * <p>
	 * <code>{@code public setTime({@link Account} other)}</code>
	 * </p>
	 * 
	 * overrides the compareTo method of the Comparable interface
	 * 
	 * @override compareTo
	 */
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
