package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import game.*;

public class MainMenuPanel extends JPanel{
	
	private JButton startNewGame;
	private JButton login;
	private JButton viewScores;
	
	private Icon startNewGameIcon;
	private Icon loginIcon;
	private Icon viewScoresTryIcon;
	private Image backgroundGraphics;
	private Image startNewGameGraphics;
	private Image loginGraphics;
	private Image viewScoresGraphics;
	
	public MainMenuPanel(){
		
		setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		try{
			this.backgroundGraphics = ImageIO.read(new File("Shift/images/background.png"));
//			this.login = ImageIO.read(new File("C:/Users/SvenM/Desktop/UNI/P2/Shift/src/Bilder/button1.png"));
//			this.startNewGame = ImageIO.read(new File("C:/Users/SvenM/Desktop/UNI/P2/Shift/src/Bilder/button.png"));
//			this.viewScores = ImageIO.read(new File("C:/Users/SvenM/Desktop/UNI/P2/Shift/src/Bilder/button2.png"));
		}catch(IOException e){
			e.printStackTrace();
		}	
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		Box menuButtons = Box.createVerticalBox();
		
		startNewGame = new JButton();
		startNewGame.setAlignmentX(CENTER_ALIGNMENT);
		startNewGame.setBorder(emptyBorder);
		startNewGame.setContentAreaFilled(false); 
		startNewGame.setFocusPainted(true); 
		startNewGame.setOpaque(false);
		
		login = new JButton();
		login.setBounds(startNewGame.getBounds());
		login.setAlignmentX(CENTER_ALIGNMENT);
		login.setContentAreaFilled(false); 
        login.setFocusPainted(false); 
        login.setOpaque(false);
        login.setBorder(emptyBorder);
		
		viewScores = new JButton();
		viewScores.setBounds(startNewGame.getBounds());
		viewScores.setAlignmentX(CENTER_ALIGNMENT);
		viewScores.setBorder(emptyBorder);
		viewScores.setContentAreaFilled(false); 
        viewScores.setFocusPainted(true); 
        viewScores.setOpaque(false);
		
		startNewGameIcon = new ImageIcon("Shift/images/Button.png", "startNewGame");
		loginIcon = new ImageIcon("Shift/images/Button1.png", "Login");
		viewScoresTryIcon = new ImageIcon("Shift/images/Button2.png", "highscores");
		
		
		login.setIcon(loginIcon);
		startNewGame.setIcon(startNewGameIcon);
		viewScores.setIcon(viewScoresTryIcon);
		
		
		
		
		
		menuButtons.add(Box.createVerticalStrut(200));
		menuButtons.add(startNewGame);
		menuButtons.add(Box.createVerticalStrut(50));
		menuButtons.add(login);
		menuButtons.add(Box.createVerticalStrut(50));
		menuButtons.add(viewScores);
		
		
		
		
		
		this.add(menuButtons, BorderLayout.CENTER);
	}
	
	
	
	
	public JButton getStartNewGame() {
		return startNewGame;
	}
	
	public JButton getViewScores() {
		return viewScores;
	}
	
	public JButton getLogin() {
		return login;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.drawImage(this.backgroundGraphics,0,0,this);
//		int buttonsHeight = 100 + login.getHeight(getParent())+login.getHeight(getParent())+startNewGame.getHeight(getParent());
//		g.drawImage(this.startNewGame, 300 - startNewGame.getWidth(getParent())/2 ,300 - buttonsHeight/2 , this);
//		g.drawImage(this.login, 300 - login.getWidth(getParent())/2 ,300 - buttonsHeight/2 +login.getHeight(getParent())+50 , this);
//		g.drawImage(this.viewScores, 300 - viewScores.getWidth(getParent())/2 , 300 - buttonsHeight/2 +login.getHeight(getParent())+ 50 + viewScores.getHeight(getParent()) +50 , this);
	}
	
}
