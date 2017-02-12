package menu;

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
	
	
	public MenuBarView(Account player){
		
		this.player = player;
		this.accountName = player.getName();
		
		this.one =	new JMenu("Game");
		JMenuItem restart = new JMenuItem("Restart current level");
		one.add(restart);
		JMenuItem load = new JMenuItem("Load");
		one.add(load);
		JMenuItem save = new JMenuItem("Save");
		one.add(save);
		JMenuItem main = new JMenuItem("Main Menu");
		one.add(main);
		JMenuItem exit = new JMenuItem("Exit");
		one.add(exit);
		
		
		this.account = new JMenu(accountName);
		JMenuItem viewScore = new JMenuItem("Show score");
		JMenuItem changePassword = new JMenuItem("Change password");
		JMenuItem logout = new JMenuItem("Logout");
		account.add(viewScore);
		account.add(changePassword);
		account.add(logout);
		
		this.options = new JMenu("Options");
		JMenuItem language = new JMenuItem("Language");
		options.add(language);
		JMenuItem color = new JMenuItem("Color");
		options.add(color);
		JMenuItem controls = new JMenuItem("Controls");
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
	
	

}
