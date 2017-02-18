package window;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.MenuItem;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import account.Account;


public class MenuBarView extends JMenuBar{
	
	private Account playerAccount;
	private String accountName;
	
	private JMenu accountMenu;
	private JMenu one;
	private JMenu options;
	private JButton sound;
	
	private JLabel currentTime;

	private JMenuItem restart,load,save,main,exit;
	private JMenuItem viewScore,changeUserName,changePassword,logout;
	private JMenuItem language,color,controls;
	
	public MenuBarView(Account player){
		
		this.playerAccount = player;
		this.accountName = player.getName();
		
		this.one =	new JMenu("Game");
		restart = new JMenuItem("Restart current level");
		one.add(restart);
		load = new JMenuItem("Load");
		one.add(load);
		save = new JMenuItem("Save");
		one.add(save);
		main = new JMenuItem("Main Menu");
		one.add(main);
		exit = new JMenuItem("Exit");
		one.add(exit);
		
		
		this.accountMenu = new JMenu(accountName);
		viewScore = new JMenuItem("Show score");
		changeUserName = new JMenuItem("Change Username");
		changePassword = new JMenuItem("Change password");
		logout = new JMenuItem("Logout");
		accountMenu.add(viewScore);
		accountMenu.add(changeUserName);
		accountMenu.add(changePassword);
		accountMenu.add(logout);
		
		this.options = new JMenu("Options");
		 language = new JMenuItem("Language");
		options.add(language);
		 color = new JMenuItem("Color");
		options.add(color);
		 controls = new JMenuItem("Controls");
		options.add(controls);
		
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
	
	public void setAccount(Account newAccount){
		this.playerAccount = newAccount;
	}
	public Account getPlayer(){
		return this.playerAccount;
	}
	
	public JMenu getAccountMenu(){
		return this.accountMenu;
	}
	
	public JMenu getGame(){
		return this.one;
	}
	
	public JButton getSound() {
		return sound;
	}
	
	public JMenuItem getRestart()
	{
		return restart;
	}
	
	public JMenuItem getColor()
	{
		return color;
	}
	
	public void setTime(double pTime)
	{
		this.playerAccount.setTime(pTime);
		currentTime.setText(String.valueOf(playerAccount.getTime()));
	}
	
	public void setUserName(String newName){
		this.playerAccount.setName(newName);
		this.accountMenu.setText(this.playerAccount.getName());
	}
	
	public JMenuItem getChangeUserName() {
		return changeUserName;
	}
	
	public JMenuItem getChangePassword() {
		return changePassword;
	}
	
	public Account getPlayerAccount() {
		return playerAccount;
	}
	
	public JMenuItem getMain() {
		return main;
	}
	
	public JMenuItem getExit() {
		return exit;
	}
	
	public JMenuItem getViewScore() {
		return viewScore;
	}

}
