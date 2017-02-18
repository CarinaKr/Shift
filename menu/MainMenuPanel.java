package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

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
	
	public MainMenuPanel(){
		
		setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		try{
			this.mainMenuGraphics = ImageIO.read(new File("Shift/images/background.png"));
			this.creditGraphics=ImageIO.read(new File("Shift/images/credits.png"));
			this.backgroundGraphics=mainMenuGraphics;
//			this.login = ImageIO.read(new File("C:/Users/SvenM/Desktop/UNI/P2/Shift/src/Bilder/button1.png"));
//			this.startNewGame = ImageIO.read(new File("C:/Users/SvenM/Desktop/UNI/P2/Shift/src/Bilder/button.png"));
//			this.viewScores = ImageIO.read(new File("C:/Users/SvenM/Desktop/UNI/P2/Shift/src/Bilder/button2.png"));
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
	
	
	
	
	public JButton getStartNewGame() {
		return startNewGame;
	}
	
	public JButton getViewScores() {
		return viewScores;
	}
	
	public JButton getCredits() {
		return credit;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.drawImage(this.backgroundGraphics,0,0,this);
	}
	
	public void showCredits()
	{
		creditTimer.start();
		this.backgroundGraphics=creditGraphics;
		menuButtons.setVisible(false);
	}
	public void backToMain()
	{
		creditTimer.stop();
		this.backgroundGraphics=mainMenuGraphics;
		menuButtons.setVisible(true);
	}
	
}
