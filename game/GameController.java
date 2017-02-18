package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import account.Account;
import window.WindowController;
import window.WindowView;

/**<dd>
* <h3><i> GameController </i></h3>
* The GameController controlls all the actions happening in the level. It has methods to
* update the player movement and the game panel.
* It also has a key-listener for shifting the game field and reacts to actions, such as 
* collecting keys, walking into spikes and exiting the level.
 * @author Carina
 *
 */
public class GameController implements KeyListener{
	
	private WindowView hView;
	private WindowController hWindowController;
	private Levels hLevels;
	private Feld[][] hFelder;
	private PlayerModel hPlayer;
	private PlayerController hPController;
	private BufferedImage[] hBackground;
	private BufferedImage hBackgroundTurn;
	private Timer hTimer, hTimerTurn;
	
	private int[] zDoor;
	private int[][] zKey={{},{},{}};
	private int[][][] zPlatform={{},{},{}};
	private int[] zLage={0,0,0}; //Platform1, Platform2
	private int[][] zSpikes;
	
	private int zLevelNummer=1;
	private int zVersion=0;
	private int zFarbe=0;
	private int zSize=40;
	private boolean zJump;
	private int zTurn,zDrehpunktX,zDrehpunktY;
	private boolean zShift;
	private double zTime;
	
	private SoundBox soundBox;
	
	/**<dd>
	 * <h3><i> GameController </i></h3>
	 * <p>
	 * <code>{@code public GameController({@link WindowView} view, {@link WindowController} winController)}</code>
	 * </p>
	 * 
	 * initializes the controller for the level
	 */
	public GameController(WindowView view,WindowController winController)
	{
		hView=view;
		hWindowController=winController;
		hView.addKeyListener(this);
		soundBox = new SoundBox();
		hLevels=new Levels();
<<<<<<< HEAD
=======

>>>>>>> origin/master
		hPlayer=new PlayerModel(hLevels.getPlayerPosition(zLevelNummer)[0]*zSize,hLevels.getPlayerPosition(zLevelNummer)[1]*zSize,14,40);
		hPController=new PlayerController(hPlayer,hFelder, soundBox);
		hView.addKeyListener(hPController);
		
		hTimerTurn=new Timer(10, e->turn());
		hTimer=new Timer(10, e->update());
<<<<<<< HEAD
=======
		
		hTimerTurn=new Timer(10, e->turn());
		hTimer=new Timer(10, e->update());
>>>>>>> origin/master
	}

	/**<dd>
	 * <h3><i> initGame </i></h3>
	 * <p>
	 * <code>{@code public initGame()}</code>
	 * </p>
	 * Initializes new game
	 */
	public void initGame() {
		zTime = 0;
		nextLevel(1);
	}
	
	/**<dd>
	 * <h3><i> pauseGame </i></h3>
	 * <p>
	 * <code>{@code public pauseGame()}</code>
	 * </p>
	 * pauses the game
	 */
	public void pauseGame(){
		hTimer.stop();
	}
	
	/**<dd>
	 * <h3><i> resumeGame </i></h3>
	 * <p>
	 * <code>{@code public resumeGame()}</code>
	 * </p>
	 * resumes the current game
	 */
	public void resumeGame(){
		hTimer.start();
	}
	
	/**<dd>
	 * <h3><i> shift </i></h3>
	 * <p>
	 * <code>{@code public shift()}</code>
	 * </p>
	 * inverts the playing field and turns it by 180 degrees
	 */
	public void shift()
	{	
		zShift=false;
		hTimer.stop();
		hTimerTurn.start();
		zDrehpunktX = hBackground[zFarbe].getWidth(null) / 2;
		zDrehpunktY = hBackground[zFarbe].getHeight(null) / 2;
		zVersion=(zVersion+1)%2;
		hFelder=hLevels.getLevel(zLevelNummer,zVersion);
		hPlayer.setPosition((15*zSize-hPlayer.getXPos())-hPlayer.getWidth(), (15*zSize-hPlayer.getYPos())-(2*hPlayer.getHeight()));
		zDoor=hLevels.getDoor(zLevelNummer,zVersion);
		zKey[0]=hLevels.getKey1(zLevelNummer,zVersion);
		zKey[1]=hLevels.getKey2(zLevelNummer,zVersion);
		zKey[2]=hLevels.getKey3(zLevelNummer,zVersion);
		for(int i=0;i<hLevels.getKeyNumbers(zLevelNummer);i++)
		{
			zPlatform[i]=hLevels.getPlatform(zLevelNummer, zVersion, zLage[i], i);
		}
		for(int i=0;i<zSpikes.length;i++)
		{
			zSpikes=hLevels.getSpikes(zLevelNummer, zVersion);
		}
		hPController.setFelder(hFelder);
		this.soundBox.shift();
	}
	/**<dd>
	 * <h3><i> turn </i></h3>
	 * <p>
	 * <code>{@code public turn()}</code>
	 * </p>
	 * turns the background image by 180 degrees
	 */
	public void turn()
	{
		//Rotate Image
		
		if(zTurn<180)
		{
			double pRotation = Math.toRadians (zTurn);
			AffineTransform tx = AffineTransform.getRotateInstance(pRotation, zDrehpunktX, zDrehpunktY);		
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			hBackgroundTurn = op.filter(hBackground[zFarbe], null);
			hView.update(hPlayer, hBackgroundTurn,zDoor,zKey,zPlatform,zLage,hLevels.getKeyNumbers(zLevelNummer),zSpikes,zFarbe);
			zTurn+=10;
		}
		else if(zTurn==180)
		{
			zTurn=0;
			double pRotation = Math.toRadians (180);
			AffineTransform tx = AffineTransform.getRotateInstance(pRotation, zDrehpunktX, zDrehpunktY);		
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			for(int i=0;i<hBackground.length;i++)
			{
				hBackground[i] = op.filter(hBackground[i], null);
			}
			hTimerTurn.stop();
			hTimer.start();
			zShift=true;
		}
	}
	
	/**<dd>
	 * <h3><i> update </i></h3>
	 * <p>
	 * <code>{@code public update()}</code>
	 * </p>
	 * gets calles once per frame: 	- asks, if the playing field can be shifted
	 * 								- updates the PlayerController (collision detection of the player) 
	 * 								- asks, if a key was collected, changes the platforms accordingly
	 * 								- asks, if the level is finished
	 * 								- updates the time
	 */
	public void update()
	{
		zShift=false;
		int a,b,c;
		a=hPlayer.getXPos()/zSize;
		b=(hPlayer.getYPos()+hPlayer.getHeight())/zSize;
		c=(hPlayer.getXPos()+hPlayer.getWidth()-1)/zSize;
		try {
			if(hFelder[a][b].isAccessable()==false&&hFelder[c][b].isAccessable()==false)
			{zShift=true;}
			if(hFelder[a][b].isPlatform()||hFelder[c][b].isAccessable())
			{zShift=false;}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		hPController.updatePlayer();

		for(int i=0;i<hLevels.getKeyNumbers(zLevelNummer);i++)
		{
			if(hFelder[zKey[i][0]][zKey[i][1]].isKey()&&(hFelder[zKey[i][0]][zKey[i][1]].contains(hPlayer.getXPos()+1, hPlayer.getYPos()+1)|| //obere linke Ecke
				hFelder[zKey[i][0]][zKey[i][1]].contains(hPlayer.getXPos()+hPlayer.getWidth()-1, hPlayer.getYPos()+hPlayer.getHeight()-1)))
			{
				hFelder[zKey[i][0]][zKey[i][1]].setKey(false);
				soundBox.collect();
				zLage[i]=1;
				zPlatform[i]=hLevels.getPlatform(zLevelNummer, zVersion, zLage[i],i);
				hLevels.setPlatform(zLevelNummer, zLage[i],i);
				hPController.setFelder(hFelder);
			}
		}
		
		if(hFelder[zDoor[0]][zDoor[1]].contains(hPlayer.getXPos()+1, hPlayer.getYPos()+1)&&
				hFelder[zDoor[0]][zDoor[1]].contains(hPlayer.getXPos()+hPlayer.getWidth()-1, hPlayer.getYPos()+hPlayer.getHeight()-1))
		{
			nextLevel(zLevelNummer+1);
		}
		
		for(int i=0;i<zSpikes.length;i++)
		{
			if(hFelder[zSpikes[i][0]][zSpikes[i][1]].contains(hPlayer.getXPos()-1, hPlayer.getYPos()+hPlayer.getHeight())||
					hFelder[zSpikes[i][0]][zSpikes[i][1]].contains(hPlayer.getXPos()+hPlayer.getWidth()-1, hPlayer.getYPos()+hPlayer.getHeight()))
			{
				soundBox.die();
				resetLevel(zLevelNummer);
			}
		}
		
		zTime+=0.01;
		double pTime=(int)(zTime*10);
		double pTime2=pTime/10;
		hWindowController.setTime(pTime2);
		hView.update(hPlayer, hBackground[zFarbe],zDoor,zKey,zPlatform,zLage,hLevels.getKeyNumbers(zLevelNummer),zSpikes,zFarbe);
	}
	
	/**<dd>
	 * <h3><i> resetLevel </i></h3>
	 * <p>
	 * <code>{@code public resetLevel({@link integer} pLevel)}</code>
	 * </p>
	 * resets the playing field to the given level
	 * @param pLevel
	 */
	public void resetLevel(int pLevel)
	{
		hBackground=hLevels.getBackground(pLevel);
		if(zVersion==1)
		{
			double pRotation = Math.toRadians (180);
			AffineTransform tx = AffineTransform.getRotateInstance(pRotation, zDrehpunktX, zDrehpunktY);		
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			for(int i=0;i<hBackground.length;i++)
			{
				hBackground[i] = op.filter(hBackground[i], null);
			}
		}
		zVersion=0;
		hFelder=hLevels.getLevel(pLevel, zVersion);
		hPlayer.setPosition(hLevels.getPlayerPosition(pLevel)[0]*zSize,hLevels.getPlayerPosition(pLevel)[1]*zSize);
		zDoor=hLevels.getDoor(pLevel,zVersion);
		zKey[0]=hLevels.getKey1(pLevel,zVersion);
		zKey[1]=hLevels.getKey2(pLevel,zVersion);
		zKey[2]=hLevels.getKey3(pLevel,zVersion);

		zSpikes=hLevels.getSpikes(pLevel, zVersion);
		for(int i=0;i<hLevels.getKeyNumbers(pLevel);i++)
		{
			hFelder[zKey[i][0]][zKey[i][1]].setKey(true);
			zLage[i]=0;
			zPlatform[i]=hLevels.getPlatform(pLevel, zVersion, zLage[i],i);
			hLevels.setPlatform(pLevel, zLage[i],i);
		}
		hLevels.resetKeys(pLevel);
		hPController.setFelder(hFelder);
	}
	
	/**<dd>
	 * <h3><i> nextLevel </i></h3>
	 * <p>
	 * <code>{@code public nextLevel({@link integer} pLevelNummber)}</code>
	 * </p>
	 * starts the level given in pLevelNummer
	 */
	public void nextLevel(int pLevelNummer)
	{
		if(!(pLevelNummer == 1)) soundBox.door();
		hTimer.stop();
		if(pLevelNummer<3)
		{
			resetLevel(pLevelNummer);
			zLevelNummer = pLevelNummer;
			hTimer.start();
		}
		else if(pLevelNummer==3)
		{
			//TODO:
			//Game Over Funktion, Hausptmenü?
			soundBox.victory();
<<<<<<< HEAD
			hWindowController.getAccounts().add(hWindowController.getBar().getPlayerAccount());
			hWindowController.showScoreList();
			hWindowController.getScorePanel().getBackToGame().setEnabled(false);
			hWindowController.getBar().setAccount(new Account(hWindowController.getBar().getPlayerAccount().getName(),"Test"));

=======
>>>>>>> origin/master
		}
	}

	
	/**<dd>
	 * <h3><i> keyReleased </i></h3>
	 * <p>
	 * <code>{@code public keyReleased({@link KeyEvent} arg0)}</code>
	 * </p>
	 * keyboard action
	 * @Override keyReleased(KeyEvent arg0)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if(arg0.getKeyCode()==16)//shift
		{
			if(hPlayer.getYPos()+hPlayer.getHeight()+1<15*zSize&&zJump==false&&zShift)
			{this.shift();}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	/**<dd>
	 * <h3><i> getLevel </i></h3>
	 * <p>
	 * <code>{@code public getLevel()}</code>
	 * </p>
	 * returns the current level number
	 * @return zLevelNummer
	 */
	public int getLevel()
	{
		return zLevelNummer;
	}
	
	/**<dd>
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
	 * <h3><i> getSoundBox </i></h3>
	 * <p>
	 * <code>{@code public getSoundBox()}</code>
	 * </p>
	 * returns the soundBox	
	 * 
	 * @return soundBox
	 */
	public SoundBox getSoundBox() {
		return soundBox;
	}
	
	/**<dd>
<<<<<<< HEAD
=======
=======
>>>>>>> origin/master
>>>>>>> origin/master
	 * <h3><i> newColor </i></h3>
	 * <p>
	 * <code>{@code public newColor()}</code>
	 * </p>
	 * sets the next color, according to the current color
	 */
	public void newColor()
	{
		zFarbe++;
		if(zFarbe>=3)
		{
			zFarbe=0;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
