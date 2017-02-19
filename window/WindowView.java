package window;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import account.*;
import game.*;
import menu.*;

/**
 *  <dd>
	 * <h3><i> WindowView </i></h3>
	 * <p>
	*The WindowView displays everything that is happening in the game. It extends JFrame.
 * @author Sven
 *
 */
public class WindowView extends JFrame{
	
	private GamePanel hPanel;
	private MainMenuPanel mPanel;
	private HighscoresPanel sPanel;
	
	private JButton mStartNewGame;
	private JButton mCredits;
	private JButton mHighscores;
	private MenuBarView bar;
	
	/**
	 *  <dd>
	 * <h3><i> WindowView </i></h3>
	 * <p>
	 * <code>{@code public WindowView({@link MenuBarView} bar)}</code>
	 * </p>
	 * Initializes the class WindowView. Has the Main Menu Panel, the Game Panel, the Highscore Panel and the Menu Bar.
	 * 
	 * @param bar
	 */
	public WindowView(MenuBarView bar){
		
		mPanel = new MainMenuPanel();
		
		
		this.mStartNewGame = mPanel.getStartNewGame();
		this.mCredits = mPanel.getCredits();
		this.mHighscores = mPanel.getViewScores();
		
		setLayout(new BorderLayout());
		this.bar=bar;
		add(bar, BorderLayout.NORTH);
		add(mPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize (615,600 + bar.getHeight()+60);
		setVisible(true);
	}

	/**
	 *  <dd>
	 * <h3><i> update </i></h3>
	 * <p>
	 *  <code>{@code public update({@link PlayerModel} pPlayer, {@link Image} pBackground,{@link integer}[] pDoor,
	 * 			{@link integer}[][] pKey,{@link integer}[][][] pPlatform,{@link integer}[] pLage,{@link integer} pKeyNumbers,
	 * 			{@link integer}[][] pSpikes,{@link integer} pFarbe)}</code>
	 * </p>
	 * gets parameters from the controller and updates the GamePanel
	 * @param zPlayer
	 * @param zBackground
	 * @param pDoor
	 * @param pKey
	 * @param pPlatform
	 * @param pLage
	 * @param pKeyNumbers
	 * @param pSpikes
	 * @param pFarbe
	 */
	public void update(PlayerModel zPlayer,BufferedImage zBackground,int[] pDoor,int[][] pKey,int[][][] pPlatform,int[] pLage,int pKeyNumbers,int[][] pSpikes,int pFarbe)
	{
		hPanel.update(zPlayer,zBackground,pDoor,pKey,pPlatform,pLage,pKeyNumbers,pSpikes,pFarbe);
		hPanel.repaint();	
	}
	
	/**
	 *  <dd>
	 * <h3><i> getGamePanel </i></h3>
	 * <p>
	 * <code>{@code public getGamePanel()}</code>
	 * </p>
	 * returns the Game Panel for the levels
	 * @return hPanel
	 */
	public GamePanel getGamePanel(){
		return this.hPanel;
	}
	
	/**
	 * <dd>
	 * <h3><i> setGamePanel </i></h3>
	 * <p>
	 * <code>{@code public setGamePanel({@link GamePanel} newPanel)}</code>
	 * </p>
	 * sets the current Panel to the Game Panel
	 * @param newPanel
	 */
	public void setGamePanel(GamePanel newPanel){
		if(mPanel!=null){
			this.remove(mPanel);
		}
		if(sPanel!=null){	
			this.remove(sPanel);
		}
		this.hPanel = newPanel;
		add(this.hPanel, BorderLayout.CENTER);
		this.setVisible(true);
		this.requestFocus();
		
	}
	
	/**
	 * <dd>
	 * <h3><i> setHighscorePanel </i></h3>
	 * <p>
	 * <code>{@code public setHighscorePanel({@link HighscoresPanel} newPanel)}</code>
	 * </p>
	 * sets the current Panel to the Highscore Panel
	 * @param newPanel
	 */
	public void setHighscoresPanel(HighscoresPanel newPanel){
		if(mPanel!=null){
			this.remove(mPanel);
			mPanel.setVisible(false);
		}
		
		if(hPanel!=null){	
			this.remove(hPanel);
			hPanel.setVisible(false);
		}
		this.sPanel = newPanel;
		add(sPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	/**
	 * <dd>
	 * <h3><i> setMenuPanel </i></h3>
	 * <p>
	 * <code>{@code public setMenuPanel({@link MainMenuPanel} newPanel)}</code>
	 * </p>
	 * sets the current Panel to the Main Menu Panel
	 * @param newPanel
	 */
	public void setMenuPanel(MainMenuPanel newPanel){
		if(hPanel!=null){	
			this.remove(hPanel);
			hPanel.setVisible(false);
		}
		if(sPanel!=null){
			this.remove(sPanel);
			sPanel.setVisible(false);
		}
		this.mPanel = newPanel;
		add(mPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
		
	}
	
	/**
	 * <dd>
	 * <h3><i> getMenuPanel </i></h3>
	 * <p>
	 * <code>{@code public getMenuPanel()}</code>
	 * </p>
	 * returns the MainMenuPanel
	 * @return mPanel
	 */
	public MainMenuPanel getMenuPanel() {
		return mPanel;
	}
	
	/**
	 * <dd>
	 * <h3><i> getMNewGame </i></h3>
	 * <p>
	 * <code>{@code public getMNewGame()}</code>
	 * </p>
	 * returns the JButton to start a new Game
	 * @return mStartNewGame
	 */
	public JButton getMNewGame(){
		return mStartNewGame;
	}
	
	/**
	 * <dd>
	 * <h3><i> getMCredits </i></h3>
	 * <p>
	 * <code>{@code public getMCredits()}</code>
	 * </p>
	 * returns the JButton to show the credits
	 * @return mCredits
	 */
	public JButton getMCredits() {
		return mCredits;
	}
	
	/**
	 * <dd>
	 * <h3><i> getMHighscores </i></h3>
	 * <p>
	 * <code>{@code public getMHighscores()}</code>
	 * </p>
	 * returns the JButton to show the Highscore list
	 * @return mHighscores
	 */
	public JButton getMHighscores() {
		return mHighscores;
	}
	
	/**
	 * <dd>
	 * <h3><i> getBar </i></h3>
	 * <p>
	 * <code>{@code public getBar()}</code>
	 * </p>
	 * returns the View for the Menu Bar
	 * @return bar
	 */
	public MenuBarView getBar() {
		return bar;
	}
	/**
	 * <dd>
	 * <h3><i> getHighscoresPanel </i></h3>
	 * <p>
	 * <code>{@code public HighscoresPanel()}</code>
	 * </p>
	 * returns the Panel for the Highscore List
	 * @return sPanel
	 */
	public HighscoresPanel getHighscoresPanel() {
		return sPanel;
	}
	

}
