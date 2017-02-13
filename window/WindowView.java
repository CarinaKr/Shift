package window;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import account.*;
import game.*;
import menu.*;


public class WindowView extends JFrame{
	
	private GamePanel hPanel;
	private MainMenuPanel mPanel;
	
	private JButton mStartNewGame;
	private JButton mLogin;
	private JButton mHighscores;
	private MenuBarView bar;
	
	
	public WindowView(MenuBarView bar){
		
		mPanel = new MainMenuPanel();
		
		this.mStartNewGame = mPanel.getStartNewGame();
		this.mLogin = mPanel.getLogin();
		this.mHighscores = mPanel.getViewScores();
		
		setLayout(new BorderLayout());
		//bar = new MenuBarView(new Account("Test","Test"));
		this.bar=bar;
		add(bar, BorderLayout.NORTH);
		add(mPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize (615,600 + bar.getHeight()+60);
		System.out.println(bar.getHeight());
		setVisible(true);
	}

	
	public void update(PlayerModel zPlayer,BufferedImage zBackground,int[] pDoor,int[][] pKey,int[][][] pPlatform,int[] pLage,int pKeyNumbers,int[][] pSpikes,double pTime)
	{
		hPanel.update(zPlayer,zBackground,pDoor,pKey,pPlatform,pLage,pKeyNumbers,pSpikes,pTime);
		hPanel.repaint();	
	}
	
	public GamePanel getGamePanel(){
		return this.hPanel;
	}
	
	public void setGamePanel(GamePanel newPanel){
		this.remove(mPanel);
		this.hPanel = newPanel;
		add(this.hPanel, BorderLayout.CENTER);
		this.setVisible(true);
		this.requestFocus();
		
	}
	
	public void setMenuPanel(MainMenuPanel newPanel){
		this.remove(hPanel);
		this.mPanel = newPanel;
		add(mPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
		
	}
	
	public MainMenuPanel getMenuPanel() {
		return mPanel;
	}
	
	public JButton getMNewGame(){
		return mStartNewGame;
	}
	
	public JButton getMLogin() {
		return mLogin;
	}
	
	public JButton getMHighscores() {
		return mHighscores;
	}
	
	public MenuBarView getBar() {
		return bar;
	}

}
