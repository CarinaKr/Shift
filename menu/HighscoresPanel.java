package menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import account.*;
import game.*;
import window.*;

/**
 * <dd>
	 * <h3><i> HighscorePanel </i></h3>
	 * <p>
	 * The class creates the Panel for the Highscore. The class extends JPanel.
 * @author Sven
 *
 */
public class HighscoresPanel extends JPanel{
	
	private ArrayList<Account> accounts;
	
	private String[] scoreArray;
	private JList timeList;
	private JList nameList;
	
	private JButton backToMain;
	private JButton backToGame;
	
	/**
	 * <dd>
	 * <h3><i> HighscoresPanel </i></h3>
	 * <p>
	 * <code>{@code public HighscoresPanel({@link ArrayList} accounts)}</code>
	 * </p>
	 * Initializes the Panel for the Highscore List, with JLists to show the player's name and time and buttons to
	 * continue the game or return to the Main Menu.
	 * @param accounts
	 */
	public HighscoresPanel(ArrayList accounts){
		
		this.accounts = accounts;
		
		this.setLayout(new GridLayout(2,2));
	
		Collections.sort(this.accounts);
		
		DefaultListModel timeListModel = new DefaultListModel(); 
		DefaultListModel nameListModel = new DefaultListModel();
		
		if(this.accounts.size() == 0){
			timeListModel.addElement("No scores");
			nameListModel.addElement("No players");
		}
		else{
			for(int i = 0; i<this.accounts.size();i++){
				int tr = this.accounts.size();
				double time = this.accounts.get(i).getTime();
				timeListModel.add(i,String.valueOf(time));
				String name = this.accounts.get(i).getName();
				nameListModel.add(i,name);
			}
		}
		
		this.timeList = new JList(timeListModel);
		this.nameList = new JList(nameListModel);
		
		this.timeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.backToMain = new JButton("Back to menu");
		this.backToGame = new JButton("Back to the game");
		
		add(timeList);
		add(nameList);
		add(backToMain);
		add(backToGame);
		
		
	}
	
	/**
	 * <dd>
	 * <h3><i> getBackToMain </i></h3>
	 * <p>
	 * <code>{@code public getBackToMain()}</code>
	 * </p>
	 * returns the Button to go back to Main Menu
	 * @return JButton backToMain
	 */
	public JButton getBackToMain() {
		return backToMain;
	}

	/**
	 * <dd>
	 * <h3><i> getBackTOGame </i></h3>
	 * <p>
	 * <code>{@code public getBackToGame(}</code>
	 * </p>
	 * returns the Button to resume the game
	 * @return JButton backToGame
	 */
	public JButton getBackToGame() {
		return backToGame;
	}
}
