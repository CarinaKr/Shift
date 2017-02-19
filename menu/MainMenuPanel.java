package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * <dd>
	 * <h3><i> MainMenuPanel </i></h3>
	 * <p>
	 * 
	 * The class creates the Panel for the Main Menu. The class extends JPanel.
 * @author Sven
 *
 */
public class MainMenuPanel extends JPanel{
	
	private JButton startNewGame;
	private JButton credit;
	private JButton viewScores;
	
	private Icon startNewGameIcon;
	private Icon creditIcon;
	private Icon viewScoresTryIcon;
	private Image backgroundGraphics,mainMenuGraphics,creditGraphics;
	private Image startNewGameGraphics;
	private Image loginGraphics;
	private Image viewScoresGraphics;
	Box menuButtons;
	
	private Timer creditTimer;
	
	/**
	 * <dd>
	 * <h3><i> MainMenuPanel </i></h3>
	 * <p>
	 * <code>{@code public MainMenuPanel({@link ArrayList} accounts)}</code>
	 * </p>
	 * Initializes the Panel for the Main Menu, with Buttons to start a new game, show the Highscore list and show the credits.
	 * 
	 * @param accounts
	 */
	public MainMenuPanel(){
		
		setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		try{
			this.mainMenuGraphics = ImageIO.read(new File("Shift/images/background.png"));
			this.creditGraphics=ImageIO.read(new File("Shift/images/credits.png"));
			this.backgroundGraphics=mainMenuGraphics;
		}catch(IOException e){
			e.printStackTrace();
		}	
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		menuButtons = Box.createVerticalBox();
		
		startNewGame = new JButton();
		startNewGame.setAlignmentX(CENTER_ALIGNMENT);
		startNewGame.setBorder(emptyBorder);
		startNewGame.setContentAreaFilled(false); 
		startNewGame.setFocusPainted(true); 
		startNewGame.setOpaque(false);
		
		viewScores = new JButton();
		viewScores.setBounds(startNewGame.getBounds());
		viewScores.setAlignmentX(CENTER_ALIGNMENT);
		viewScores.setBorder(emptyBorder);
		viewScores.setContentAreaFilled(false); 
        viewScores.setFocusPainted(true); 
        viewScores.setOpaque(false);
        
        credit = new JButton();
		credit.setBounds(startNewGame.getBounds());
		credit.setAlignmentX(CENTER_ALIGNMENT);
		credit.setContentAreaFilled(false); 
        credit.setFocusPainted(false); 
        credit.setOpaque(false);
        credit.setBorder(emptyBorder);
		
		startNewGameIcon = new ImageIcon("Shift/images/Button.png", "startNewGame");
		creditIcon = new ImageIcon("Shift/images/Button1.png", "credits");
		viewScoresTryIcon = new ImageIcon("Shift/images/Button2.png", "highscores");
		
		credit.setIcon(creditIcon);
		startNewGame.setIcon(startNewGameIcon);
		viewScores.setIcon(viewScoresTryIcon);
		
		menuButtons.add(Box.createVerticalStrut(200));
		menuButtons.add(startNewGame);
		menuButtons.add(Box.createVerticalStrut(50));
		menuButtons.add(viewScores);
		menuButtons.add(Box.createVerticalStrut(50));
		menuButtons.add(credit);
		
		this.add(menuButtons, BorderLayout.CENTER);
		
		creditTimer=new Timer(10000,e->backToMain());
	}
	
	
	
	/**
	 * <dd>
	 * <h3><i> getStartNewGame </i></h3>
	 * <p>
	 * <code>{@code public getStartNewGame()}</code>
	 * </p>
	 * returns the Button to start a new game.
	 * @return JButton startNewGame
	 */
	public JButton getStartNewGame() {
		return startNewGame;
	}
	
	/**<dd>
	 * <h3><i> getViewScores </i></h3>
	 * <p>
	 * <code>{@code public getViewScores()}</code>
	 * </p>
	 * returns the Button to show the Highscore List
	 * @return JButton viewScores
	 */
	public JButton getViewScores() {
		return viewScores;
	}
	
	/**
	 * <dd>
	 * <h3><i> getCredits </i></h3>
	 * <p>
	 * <code>{@code public getCredits()}</code>
	 * </p>
	 * returns the Button to show the credits
	 * @return JButton credits
	 */
	public JButton getCredits() {
		return credit;
	}
	
	/**
	 * <dd>
	 * <h3><i> paintComponent </i></h3>
	 * <p>
	 * <code>{@code public paintCompenent({@link Graphics} g)}</code>
	 * </p>
	 * overrides the Panel's paintComponent method
	 * @override paintComponent
	 */
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.drawImage(this.backgroundGraphics,0,0,this);
	}
	
	/**
	 * <dd>
	 * <h3><i> showCredits </i></h3>
	 * <p>
	 * <code>{@code public showCredits()}</code>
	 * </p>
	 * displays the credits for 10 seconds
	 */
	public void showCredits()
	{
		creditTimer.start();
		this.backgroundGraphics=creditGraphics;
		menuButtons.setVisible(false);
	}
	
	/**
	 * <dd>
	 * <h3><i> backToMain </i></h3>
	 * <p>
	 * <code>{@code public backToMain()}</code>
	 * </p>
	 * returns back to the Main Menu after the Credits are shown
	 */
	public void backToMain()
	{
		creditTimer.stop();
		this.backgroundGraphics=mainMenuGraphics;
		menuButtons.setVisible(true);
	}
	
}
