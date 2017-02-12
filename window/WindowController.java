package window;

import game.*;
import javax.swing.JButton;
import menu.*;

public class WindowController {
	
	private WindowView view;
	
	private GamePanel hPanel;
	private MainMenuPanel mPanel;
	
	public WindowController(){
		
		this.view = new WindowView();
		this.hPanel = view.getGamePanel();
		this.mPanel = view.getMenuPanel();
			
	
	
	mPanel.getStartNewGame().addActionListener(l -> {
		
		
		hPanel = new GamePanel(); 
		view.setGamePanel(hPanel);
		
		System.out.println("hi");
		new GameController(this.view);
	
	});
	
	
	
	
	

	}
	
	public static void main(String[] args) {
		new WindowController();

	}
	
}