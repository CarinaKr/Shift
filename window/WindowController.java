package window;

import java.util.ArrayList;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import account.Account;
import game.GameController;
import game.GamePanel;
import menu.HighscoresPanel;
import menu.MainMenuPanel;


public class WindowController {
	
	private WindowView view;
	private MenuBarView bar;
	private GameController gameController;
	private ArrayList<Account> accounts;
			
	private GamePanel hPanel;
	private MainMenuPanel mPanel;
	private HighscoresPanel scorePanel;
	private boolean mute;
	
	private Icon userIcon;
	private Icon[] soundIcon = {new ImageIcon("Shift/images/Lautsprecher.png"),new ImageIcon("Shift/images/Lautsprecher2.png")};
	
	
	public WindowController(){
		
//		InputStream in;
//		try {
//			in = new FileInputStream("Shift/sounds/sound.wav");
//			AudioStream as = new AudioStream(in); 
//			AudioPlayer.player.start(as); 
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		
		
		
		bar = new MenuBarView(new Account("Gast","Test"));
		bar.getSound().setIcon(soundIcon[0]);
		
		this.view = new WindowView(bar);
		view.setTitle("Shift");
		this.hPanel = view.getGamePanel();
		this.mPanel = view.getMenuPanel();
		this.userIcon = new ImageIcon("Shift/images/userIcon.png");
		this.accounts = new ArrayList<Account>();
		//wthis.sPanel = new HighscoresPanel(this.accounts);
		this.gameController = new GameController(this.view,this);
		
		
				
				
		mPanel.getStartNewGame().addActionListener(l -> {
				gameController.getSoundBox().select();
				gameController.getSoundBox().start();
				hPanel = new GamePanel(); 
				view.setGamePanel(hPanel);
				this.gameController.initGame();
				this.bar.setAccount(new Account(bar.getPlayerAccount().getName(),"Test"));
		});
		
		mPanel.getViewScores().addActionListener(l-> {
			//view.setHighscoresPanel(sPanel);
			gameController.getSoundBox().select();
			showScoreList();
			
		});
		
//		sPanel.getBackToMain().addActionListener(l-> {
//			view.setMenuPanel(mPanel);
//			if(this.gameController!=null){
//				this.gameController.stopTimer();
//			}
//		});
		
		
		bar.getViewScore().addActionListener(l->{
			this.gameController.pauseGame();
			showScoreList();
//			this.sPanel = new HighscoresPanel(this.accounts);
//			view.setHighscoresPanel(this.sPanel);
		});
		bar.getMain().addActionListener(l->{
			view.setMenuPanel(mPanel);
			if(this.gameController!=null){
				this.gameController.pauseGame();
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
		bar.getSound().addActionListener(l->toggleSound());
		bar.getExit().addActionListener(l->System.exit(0));
		
		
		
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
	
	private void toggleSound() {
		// TODO Auto-generated method stub
		if(mute) {
			bar.getSound().setIcon(soundIcon[0]);
			mute = false;
			gameController.getSoundBox().toggleSound(mute);
			bar.setVisible(true);
		} else {
			bar.getSound().setIcon(soundIcon[1]);
			mute = true;
			gameController.getSoundBox().toggleSound(mute);
			bar.setVisible(true);
		}
		view.requestFocus();
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
		scorePanel = new HighscoresPanel(this.accounts);
		scoreDialog.add(scorePanel);
		scoreDialog.setVisible(true);
		
		if(mPanel.isShowing()) scorePanel.getBackToGame().setEnabled(false);
		else scorePanel.getBackToGame().setEnabled(true);
		
		scorePanel.getBackToMain().addActionListener(l-> {
			gameController.getSoundBox().select();
			view.setMenuPanel(mPanel);
			if(this.gameController!=null){
				this.gameController.pauseGame();
			}
			gameController.getSoundBox().stop();
			scoreDialog.dispose();
		});
		
		scorePanel.getBackToGame().addActionListener(l-> {
			gameController.getSoundBox().select();
			if(!(this.gameController==null)) this.gameController.resumeGame();
			scoreDialog.dispose();			
		});
		for(Account a : accounts) {
			System.out.println(a.getName());
		}
		view.requestFocus();
		scoreDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		scoreDialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		scoreDialog.toFront();
		
	}

	public HighscoresPanel getScorePanel() {
		return scorePanel;
	}
	
	public ArrayList getAccounts() {
		return accounts;
	}
	public MenuBarView getBar() {
		return bar;
	}
}