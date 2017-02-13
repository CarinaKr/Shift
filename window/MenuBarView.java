package window;

import java.awt.ComponentOrientation;
import java.awt.MenuItem;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import account.Account;


public class MenuBarView extends JMenuBar{
	
	private Account player;
	private String accountName;
	
	private JMenu account;
	private JMenu one;
	private JMenu options;
	
	private JLabel currentTime;

	private JMenuItem restart,load,save,main,exit;
	private JMenuItem viewScore,changePassword,logout;
	private JMenuItem language,color,controls;
	
	public MenuBarView(Account player){
		
		this.player = player;
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
		
		
		this.account = new JMenu(accountName);
		 viewScore = new JMenuItem("Show score");
		 changePassword = new JMenuItem("Change password");
		 logout = new JMenuItem("Logout");
		account.add(viewScore);
		account.add(changePassword);
		account.add(logout);
		
		this.options = new JMenu("Options");
		 language = new JMenuItem("Language");
		options.add(language);
		 color = new JMenuItem("Color");
		options.add(color);
		 controls = new JMenuItem("Controls");
		options.add(controls);
		
		
		this.currentTime = new JLabel(String.valueOf(this.player.getTime()));
		
		
		this.add(one);
		this.add(account);
		this.add(options);
		this.add(Box.createHorizontalGlue());
		this.add(currentTime);
		this.add(Box.createHorizontalStrut(20));
	}
	
	public Account getPlayer(){
		return this.player;
	}
	
	public JMenu getAccount(){
		return this.account;
	}
	
	public JMenu getGame(){
		return this.one;
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
		currentTime.setText(""+pTime);
	}
	
	

}
