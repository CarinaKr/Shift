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
	private HighscoresPanel sPanel;
	
	private JButton mStartNewGame;
	private JButton mCredits;
	private JButton mHighscores;
	private MenuBarView bar;
	
	
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

	
	public void update(PlayerModel zPlayer,BufferedImage zBackground,int[] pDoor,int[][] pKey,int[][][] pPlatform,int[] pLage,int pKeyNumbers,int[][] pSpikes,int pFarbe)
	{
		hPanel.update(zPlayer,zBackground,pDoor,pKey,pPlatform,pLage,pKeyNumbers,pSpikes,pFarbe);
		hPanel.repaint();	
	}
	
	public GamePanel getGamePanel(){
		return this.hPanel;
	}
	
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
	
	public MainMenuPanel getMenuPanel() {
		return mPanel;
	}
	
	public JButton getMNewGame(){
		return mStartNewGame;
	}
	
	public JButton getMCredits() {
		return mCredits;
	}
	
	public JButton getMHighscores() {
		return mHighscores;
	}
	
	public MenuBarView getBar() {
		return bar;
	}
	public HighscoresPanel getHighscoresPanel() {
		return sPanel;
	}
	

}
