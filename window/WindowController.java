package window;

import game.*;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import account.Account;
import menu.*;

public class WindowController {
	
	private WindowView view;
	private MenuBarView bar;
	private GameController gameController;
	
	private GamePanel hPanel;
	private MainMenuPanel mPanel;
	
	
	public WindowController(){
		
		bar = new MenuBarView(new Account("Test","Test"));
		this.view = new WindowView(bar);
		this.hPanel = view.getGamePanel();
		this.mPanel = view.getMenuPanel();
			
		mPanel.getStartNewGame().addActionListener(l -> {
				hPanel = new GamePanel(); 
				view.setGamePanel(hPanel);
				
				System.out.println("hi");
				gameController=new GameController(this.view,this);});
		
		bar.getRestart().addActionListener(e->{
			gameController.resetLevel(gameController.getLevel());
		});
		bar.getColor().addActionListener(e->{
			gameController.newColor();
		});
	}
	
	public void setTime(double pTime)
	{
		bar.setTime(pTime);
	}
	
	public static void main(String[] args) {
		new WindowController();

	}
	
}