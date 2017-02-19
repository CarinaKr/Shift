package window;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import account.Account;

/**
 * <dd>
	 * <h3><i> MenuBarView </i></h3>
	 * <p>
	 * The class creates the Menu Bar for the entire game. Extends JMenuBar.
 * @author Sven
 *
 */
public class MenuBarView extends JMenuBar{
	
	private Account playerAccount;
	private String accountName;
	
	private JMenu accountMenu;
	private JMenu one;
	private JMenu options;
	private JButton sound;
	
	private JLabel currentTime;

	private JMenuItem restart,main,exit;
	private JMenuItem viewScore,changeUserName,changePassword;
	private JMenuItem color;
	
	/**
	 * <dd>
	 * <h3><i> MenuBarView </i></h3>
	 * <p>
	 * <code>{@code public MenuBarView({@link Account} player)}</code>
	 * </p>
	 * initializes the Menu Bar with the following menu structure:
	 * 		- Game: 
	 * 			- Restart current level
	 * 			- Main Menu
	 * 			- Exit
	 * 		- Account:
	 * 			- Show score
	 * 			- Change Username
	 * 		- Options:
	 * 			- Color
	 * @param player
	 */
	public MenuBarView(Account player){
		
		this.playerAccount = player;
		this.accountName = player.getName();
		
		this.one =	new JMenu("Game");
		restart = new JMenuItem("Restart current level");
		one.add(restart);
		main = new JMenuItem("Main Menu");
		one.add(main);
		exit = new JMenuItem("Exit");
		one.add(exit);
		
		
		this.accountMenu = new JMenu(accountName);
		viewScore = new JMenuItem("Show score");
		changeUserName = new JMenuItem("Change Username");
		accountMenu.add(viewScore);
		accountMenu.add(changeUserName);
		
		this.options = new JMenu("Options");
		 color = new JMenuItem("Color");
		options.add(color);
		
		this.sound = new JButton();
		this.sound.setPreferredSize(new Dimension(20,17));
		
		this.currentTime = new JLabel(String.valueOf(this.playerAccount.getTime()));
		
		this.add(one);
		this.add(accountMenu);
		this.add(options);
		this.add(Box.createHorizontalGlue());
		this.add(currentTime);
		this.add(Box.createHorizontalStrut(20));
		this.add(sound);
		this.add(Box.createHorizontalStrut(5));
	}
	
	/**
	 * <dd>
	 * <h3><i> setAccount </i></h3>
	 * <p>
	 * <code>{@code public setAccount({@link Account} newAccount)}</code>
	 * </p>
	 * sets the current Account to newAccount
	 * @param newAccount
	 */
	public void setAccount(Account newAccount){
		this.playerAccount = newAccount;
	}
	/**
	 *  <dd>
	 * <h3><i> getPlayer </i></h3>
	 * <p>
	 * <code>{@code public setAccount()}</code>
	 * </p>
	 * returns the current Player Account
	 * @return playerAccount
	 */
	public Account getPlayer(){
		return this.playerAccount;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getAccountMenu </i></h3>
	 * <p>
	 * <code>{@code public getAccountMenu()}</code>
	 * </p>
	 * returns the JMenu for the account menu
	 * @return accountMenu
	 */
	public JMenu getAccountMenu(){
		return this.accountMenu;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getGame </i></h3>
	 * <p>
	 * <code>{@code public getGame()}</code>
	 * </p>
	 * returns the JMenu of Game menu
	 * @return one
	 */
	public JMenu getGame(){
		return this.one;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getSound </i></h3>
	 * <p>
	 * <code>{@code public getSound()}</code>
	 * </p>
	 * returns the JButton to switch on and off the Game sound
	 * @return sound
	 */
	public JButton getSound() {
		return sound;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getRestart </i></h3>
	 * <p>
	 * <code>{@code public getRestart()}</code>
	 * </p>
	 * returns the JMenuItem for restart
	 * @return restart
	 */
	public JMenuItem getRestart()
	{
		return restart;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getColor </i></h3>
	 * <p>
	 * <code>{@code public getColor()}</code>
	 * </p>
	 * returns the JMenuItem to change the color
	 * @return color
	 */
	public JMenuItem getColor()
	{
		return color;
	}
	
	/**
	 *  <dd>
	 * <h3><i> setTime </i></h3>
	 * <p>
	 * <code>{@code public setTime({@link double} pTime)}</code>
	 * </p>
	 * sets the current Time to pTime
	 * @param pTime
	 */
	public void setTime(double pTime)
	{
		this.playerAccount.setTime(pTime);
		currentTime.setText(String.valueOf(playerAccount.getTime()));
	}
	
	/**
	 *  <dd>
	 * <h3><i> setUserName </i></h3>
	 * <p>
	 * <code>{@code public setUserName({@link String} newName)}</code>
	 * </p>
	 * sets the current user Name to newName
	 * @param newName
	 */
	public void setUserName(String newName){
		this.playerAccount.setName(newName);
		this.accountMenu.setText(this.playerAccount.getName());
	}
	
	/**
	 *  <dd>
	 * <h3><i> getChangeUserName </i></h3>
	 * <p>
	 * <code>{@code public getChangeUserName()}</code>
	 * </p>
	 * returns the JMenuItem to change the user name
	 * @return changeUserName
	 */
	public JMenuItem getChangeUserName() {
		return changeUserName;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getPlayerAccount </i></h3>
	 * <p>
	 * <code>{@code public getPlayerAccount()}</code>
	 * </p>
	 * returns the current Player Account
	 * @return playerAccount
	 */
	public Account getPlayerAccount() {
		return playerAccount;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getMain </i></h3>
	 * <p>
	 * <code>{@code public getMain()}</code>
	 * </p>
	 * returns the JMenuItem to get back to the Main Menu
	 * @return main
	 */
	public JMenuItem getMain() {
		return main;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getExit </i></h3>
	 * <p>
	 * <code>{@code public getExit()}</code>
	 * </p>
	 * returns the JMenuItem to exit the game
	 * @return exit
	 */
	public JMenuItem getExit() {
		return exit;
	}
	
	/**
	 *  <dd>
	 * <h3><i> getViewScore </i></h3>
	 * <p>
	 * <code>{@code public getViewScore()}</code>
	 * </p>
	 * returns the JMenuItem to view the Highscore List
	 * @return viewScore
	 */
	public JMenuItem getViewScore() {
		return viewScore;
	}

}
