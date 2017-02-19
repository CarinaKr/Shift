package window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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


/**<dd>
* <h3><i> WindowController </i></h3>
* The WindowController controls all the actions happening in the WindowView. It has methods to
* switch between a GamePanel and a MainMenuPanel.
* It also adds ActionListeners too the Buttons of the WindowView as well as the MenuBarView.
 * @author Sven
 *
 */

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
	
	private String zFile="Shift/menu/highscore.txt";
	
	
	/**<dd>
	 * <h3><i> GameController </i></h3>
	 * <p>
	 * <code>{@code public WindowController()}</code>
	 * </p>
	 * 
	 * initializes the controller for the window and creates its components
	 */
	public WindowController(){		
		
		bar = new MenuBarView(new Account("Gast","Test"));
		bar.getSound().setIcon(soundIcon[0]);
		
		this.view = new WindowView(bar);
		view.setTitle("Shift");
		this.hPanel = view.getGamePanel();
		this.mPanel = view.getMenuPanel();
		this.userIcon = new ImageIcon("Shift/images/userIcon.png");
		this.accounts = new ArrayList<Account>();
		File file=new File(zFile);
		if(file.exists())
		{
			try(FileInputStream fis=new FileInputStream(zFile);
					ObjectInputStream ois=new ObjectInputStream(fis))
			{
				Object object=ois.readObject();
				if(object instanceof ArrayList)
				{
					this.accounts=(ArrayList)object;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		this.gameController = new GameController(this.view,this);
		
		
		mPanel.getStartNewGame().addActionListener(l -> {
				gameController.getSoundBox().select();
				gameController.getSoundBox().start();
				hPanel = new GamePanel(); 
				view.setGamePanel(hPanel);
				this.gameController.initGame();
				this.bar.setAccount(new Account(bar.getPlayerAccount().getName(),"Test"));
		});
		
		mPanel.getCredits().addActionListener(l->{
			mPanel.showCredits();
		});
		
		mPanel.getViewScores().addActionListener(l-> {
			gameController.getSoundBox().select();
			showScoreList();
			
		});
		
		
		bar.getViewScore().addActionListener(l->{
			this.gameController.pauseGame();
			showScoreList();
		});
		bar.getMain().addActionListener(l->{
			toMainMenu();
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
		
		mPanel.getCredits().getModel().addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if (model.isRollover()) {
		            mPanel.getCredits().setIcon(new ImageIcon("Shift/images/invertedCredits.png"));
		        } else {
		        	mPanel.getCredits().setIcon(new ImageIcon("Shift/images/Button1.png", "Login"));
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
	/**<dd>
	 * <h3><i> toggleSound </i></h3>
	 * <p>
	 * <code>{@code public toggleSound()}</code>
	 * </p>
	 * switches the sound between muted and active
	 */
	private void toggleSound() {
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
	
	/**<dd>
	 * <h3><i> setTime </i></h3>
	 * <p>
	 * <code>{@code public setTime(double pTime)}</code>
	 * </p>
	 * Update the time in the MenuBarView
	 */

	public void setTime(double pTime)
	{
		bar.setTime(pTime);
	}
	
	/**<dd>
	 * <h3><i> addToScores </i></h3>
	 * <p>
	 * <code>{@code public addToScores()}</code>
	 * </p>
	 * adds the current score to accounts
	 */
	public void addToScores(){
		this.accounts.add(bar.getPlayerAccount());
	}
	
	public static void main(String[] args) {
		
		new WindowController();

	}
	
	/**<dd>
	 * <h3><i> showScoreList </i></h3>
	 * <p>
	 * <code>{@code public showScoreList()}</code>
	 * </p>
	 * Creates and opens a JDialog and a HighscoresPanel witch is then added.
	 * Also implements ActionListeners for the Buttons.
	 */
	
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
			toMainMenu();
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
		
		saveHighscore();
	}
	
	/**<dd>
	 * <h3><i> toMainMenu </i></h3>
	 * <p>
	 * <code>{@code public toMainMenu()}</code>
	 * </p>
	 * switches the active Panel to the Menu
	 */
	
	private void toMainMenu() {	
		gameController.getSoundBox().select();
		view.setMenuPanel(mPanel);
		if(this.gameController!=null){
			this.gameController.pauseGame();
		}
		gameController.getSoundBox().stop();
	}
	
	/**<dd>
	 * <h3><i> getScorePanel </i></h3>
	 * <p>
	 * <code>{@code public getScorePanel()}</code>
	 * </p>
	 * returns the scorePanel
	 */
	
	public HighscoresPanel getScorePanel() {
		return scorePanel;
	}
	
	/**<dd>
	 * <h3><i> getAccounts </i></h3>
	 * <p>
	 * <code>{@code public getAccounts()}</code>
	 * </p>
	 * returns accounts
	 */
	
	public ArrayList getAccounts() {
		return accounts;
	}
	

	/**<dd>
	 * <h3><i> getBar </i></h3>
	 * <p>
	 * <code>{@code public getBar()}</code>
	 * </p>
	 * returns bar
	 */
	
	public MenuBarView getBar() {
		return bar;
	}
	
	/**<dd>
	 * <h3><i> saveHighscore </i></h3>
	 * <p>
	 * <code>{@code public saveHighscore()}</code>
	 * </p>
	 * saves accounts in zFile
	 */
	
	public void saveHighscore()
	{
		try(FileOutputStream fos=new FileOutputStream(new File(zFile));
				ObjectOutputStream oos=new ObjectOutputStream(fos))
		{
			oos.writeObject(this.accounts);
			oos.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}