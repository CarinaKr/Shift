package window;

import game.*;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import account.Account;
import menu.*;

public class WindowController {
	
	private WindowView view;
	private MenuBarView bar;
	private GameController gameController;
	private ArrayList accounts;
	
	private GamePanel hPanel;
	private MainMenuPanel mPanel;
	private HighscoresPanel sPanel;
	
	private Icon userIcon;
	
	
	public WindowController(){
		
		bar = new MenuBarView(new Account("Gast","Test"));
		
		
		this.view = new WindowView(bar);
		view.setTitle("Shift");
		this.hPanel = view.getGamePanel();
		this.mPanel = view.getMenuPanel();
		this.userIcon = new ImageIcon("Shift/images/userIcon.png");
		this.accounts = new ArrayList();
		this.sPanel = new HighscoresPanel(this.accounts);
		
		
				
				
		mPanel.getStartNewGame().addActionListener(l -> {
				hPanel = new GamePanel(); 
				view.setGamePanel(hPanel);
				
				System.out.println("hi");
				gameController = new GameController(this.view,this);
		});
		
		mPanel.getViewScores().addActionListener(l-> {
			//view.setHighscoresPanel(sPanel);
			showScoreList();
			
		});
		
//		sPanel.getBackToMain().addActionListener(l-> {
//			view.setMenuPanel(mPanel);
//			if(this.gameController!=null){
//				this.gameController.stopTimer();
//			}
//		});
		
		
		bar.getViewScore().addActionListener(l->{
			this.accounts.add(bar.getPlayerAccount());
			showScoreList();
//			this.sPanel = new HighscoresPanel(this.accounts);
//			view.setHighscoresPanel(this.sPanel);
		});
		bar.getMain().addActionListener(l->{
			view.setMenuPanel(mPanel);
			if(this.gameController!=null){
				this.gameController.stopTimer();
			}
		});
		bar.getRestart().addActionListener(e->{
			gameController.resetLevel(gameController.getLevel());
		});
		bar.getColor().addActionListener(e->{
			gameController.newColor();
		});
		bar.getChangeUserName().addActionListener(l->{
			JFrame frame = new JFrame("test");
			String newUserName = (String)JOptionPane.showInputDialog(frame, "New Username:","Change your username!", JOptionPane.PLAIN_MESSAGE,userIcon, null, "Your new username");
			bar.setUserName(newUserName);
			
		});
		bar.getChangePassword().addActionListener(l->{
			JFrame frame = new JFrame("test");
			String newPassword = (String)JOptionPane.showInputDialog(frame, "New Password:","Change your password!", JOptionPane.PLAIN_MESSAGE,userIcon, null, "Your new password");
			bar.getPlayerAccount().setPassword(newPassword);
		});
		
		
		
		
		
		mPanel.getStartNewGame().getModel().addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if (model.isRollover()) {
		            mPanel.getStartNewGame().setIcon(new ImageIcon("Shift/images/invertedStart.png"));
		        } else {
		        	mPanel.getStartNewGame().setIcon(new ImageIcon("Shift/images/Button.png", "startNewGame"));
		        }
		    }
		
		});
		
		mPanel.getLogin().getModel().addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if (model.isRollover()) {
		            mPanel.getLogin().setIcon(new ImageIcon("Shift/images/invertedLogin.png"));
		        } else {
		        	mPanel.getLogin().setIcon(new ImageIcon("Shift/images/Button1.png", "Login"));
		        }
		    }
		
		});
		
		mPanel.getViewScores().getModel().addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if (model.isRollover()) {
		        	mPanel.getViewScores().setIcon(new ImageIcon("Shift/images/invertedHighscores.png", "highscores"));
		           
		        } else {
		        	mPanel.getViewScores().setIcon(new ImageIcon("Shift/images/Button2.png"));
		        }
		    }
		
		});
		
	}
	
	public void setTime(double pTime)
	{
		bar.setTime(pTime);
	}
	
	public void addToScores(){
		this.accounts.add(bar.getPlayerAccount());
	}
	
	public static void main(String[] args) {
		
		new WindowController();

	}
	
	public void showScoreList(){
		JDialog scoreDialog = new JDialog();
		scoreDialog.setTitle("Highscores");
		scoreDialog.setSize(600,600);
		HighscoresPanel scorePanel = new HighscoresPanel(this.accounts);
		scoreDialog.add(scorePanel);
		scoreDialog.setVisible(true);
		
		scorePanel.getBackToMain().addActionListener(l-> {
			view.setMenuPanel(mPanel);
			if(this.gameController!=null){
				this.gameController.stopTimer();
			}
		});
		
	}

	
}