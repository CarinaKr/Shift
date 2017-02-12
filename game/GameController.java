package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import window.WindowView;

/**
 * @author Carina
 *
 */
public class GameController implements KeyListener{
	
	private WindowView hView;
	private Levels hLevels;
	private Feld[][] hFelder;
	private PlayerModel hPlayer;
	private PlayerController hPController;
	private BufferedImage hBackground,hBackgroundTurn;
	private Timer hTimer, hTimerTurn;
	
	private int[] zDoor;
	private int[][] zKey={{},{},{}};
	private int[][][] zPlatform={{},{},{}};
	private int[] zLage={0,0,0}; //Platform1, Platform2
	private int[][] zSpikes;
	
	private int zLevelNummer=1;
	private int zVersion=0;
	private int zSize=40;
	private boolean zDownFree,zUpFree,zLeftFree,zRightFree;
	private boolean zLeftKey,zRightKey;
	private int zJumpCount;
	private boolean zJump;
	private int zTurn,zDrehpunktX,zDrehpunktY;
	private boolean zShift;
	private double zTime;
	
	/**
	 * erstellt den Controller für die Level
	 */
	public GameController(WindowView view)
	{
		hView=view;
		hView.addKeyListener(this);
		hLevels=new Levels();
		hBackground=hLevels.getBackground(zLevelNummer);
		hFelder=hLevels.getLevel(zLevelNummer, zVersion);
		hPlayer=new PlayerModel(hLevels.getPlayerPosition(zLevelNummer)[0]*zSize,hLevels.getPlayerPosition(zLevelNummer)[1]*zSize,zSize,zSize);
		hPController=new PlayerController(hPlayer,hFelder);
		hView.addKeyListener(hPController);
		zDoor=hLevels.getDoor(zLevelNummer,zVersion);
		zKey[0]=hLevels.getKey1(zLevelNummer,zVersion);
		zPlatform[0]=hLevels.getPlatform(zLevelNummer,zVersion,0,0);
		zSpikes=hLevels.getSpikes(zLevelNummer, zVersion);
		
		hTimerTurn=new Timer(10, e->turn());
		hTimer=new Timer(10, e->update());
		hTimer.start();
	}

	/**
	 * invertieret das Spielfeld und dreht es um 180 Grad
	 */
	public void shift()
	{	
		zShift=false;
		hTimer.stop();
		hTimerTurn.start();
		zDrehpunktX = hBackground.getWidth(null) / 2;
		zDrehpunktY = hBackground.getHeight(null) / 2;
		zVersion=(zVersion+1)%2;
		hFelder=hLevels.getLevel(zLevelNummer,zVersion);
		hPlayer.setPosition((15*zSize-hPlayer.getXPos())-zSize, (15*zSize-hPlayer.getYPos())-(2*zSize));
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
	}
	/**
	 * dreht das Hintergrundbild um 180 Grad
	 */
	public void turn()
	{
		//Rotate Image
		
		if(zTurn<180)
		{
			double pRotation = Math.toRadians (zTurn);
			AffineTransform tx = AffineTransform.getRotateInstance(pRotation, zDrehpunktX, zDrehpunktY);		
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			hBackgroundTurn = op.filter(hBackground, null);
			hView.update(hPlayer, hBackgroundTurn,zDoor,zKey,zPlatform,zLage,hLevels.getKeyNumbers(zLevelNummer),zSpikes,zTime);
			zTurn+=10;
		}
		else if(zTurn==180)
		{
			zTurn=0;
			double pRotation = Math.toRadians (180);
			AffineTransform tx = AffineTransform.getRotateInstance(pRotation, zDrehpunktX, zDrehpunktY);		
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			hBackground = op.filter(hBackground, null);
			hTimerTurn.stop();
			hTimer.start();
			zShift=true;
		}
	}
	
	/**
	 * wird einmal pro Frame aufgerufen: - fragt ab, ob das Spielfeld drehbar ist
	 * 									 - aktualisiert den PlayerController (Kollisionsabrage des Spielers)
	 * 									 - fragt ab, ob ein Schlüssel eingesammt wurde und ändert dementprechend die Plattformen
	 * 									 - fragt ab, ob das Level beendet wurde
	 * 									 - aktualisiert die Zeit
	 */
	public void update()
	{
		zDownFree=true;
		zUpFree=true;
		zLeftFree=true;
		zRightFree=true;
		zShift=false;
		int a,b,c;
		a=hPlayer.getXPos()/40;
		b=(hPlayer.getYPos()+40)/40;
		c=(hPlayer.getXPos()+39)/40;
		try {
			if(hFelder[a][b].isAccessable()==false&&hFelder[c][b].isAccessable()==false)
			{zShift=true;}
			if(hFelder[a][b].isPlatform()||hFelder[c][b].isAccessable())
			{zShift=false;}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		hPController.updatePlayer();
//		for(int i=0;i<15;i++)
//		{for(int j=0;j<15;j++)
//			{
//				if(hFelder[i][j].isAccessable()==false)
//				{
//					//down
//					if(hFelder[i][j].contains(hPlayer.getXPos()+1, hPlayer.getYPos()+zSize+1)||
//						hFelder[i][j].contains(hPlayer.getXPos()+zSize-1, hPlayer.getYPos()+zSize+1)||
//						hPlayer.getYPos()+zSize+1>15*zSize)
//					{
//						zDownFree=false;
//						zJump=false;
////						if(zJump&&zJumpCount==0)
////						{zJump=false;}
//						if(hFelder[i][j].isPlatform())
//						{zShift=false;}
//					}
//					//up
//					if(hFelder[i][j].contains(hPlayer.getXPos()+1, hPlayer.getYPos()-1)||
//							hFelder[i][j].contains(hPlayer.getXPos()+zSize-1, hPlayer.getYPos()-1)||
//							hPlayer.getYPos()-1<0)
//					{
//						zUpFree=false;
//						zJumpCount=0;
//					}
//					//left 
//					if(hFelder[i][j].contains(hPlayer.getXPos()-1, hPlayer.getYPos()+1)||
//								hFelder[i][j].contains(hPlayer.getXPos()-1, hPlayer.getYPos()+zSize-1)||
//								hPlayer.getXPos()-1<0)
//					{
//						zLeftFree=false;
//					}
//			
//					//right
//					if(hFelder[i][j].contains(hPlayer.getXPos()+zSize+1, hPlayer.getYPos()+1)||
//								hFelder[i][j].contains(hPlayer.getXPos()+zSize+1, hPlayer.getYPos()+zSize-1)||
//								hPlayer.getXPos()+zSize+1>15*zSize)
//					{
//						zRightFree=false;
//					}
//				}				
//			}
//		}
//		
		for(int i=0;i<hLevels.getKeyNumbers(zLevelNummer);i++)
		{
			if(hFelder[zKey[i][0]][zKey[i][1]].isKey()&&(hFelder[zKey[i][0]][zKey[i][1]].contains(hPlayer.getXPos()+1, hPlayer.getYPos()+1)|| //obere linke Ecke
				hFelder[zKey[i][0]][zKey[i][1]].contains(hPlayer.getXPos()+zSize-1, hPlayer.getYPos()+zSize-1)))
			{
				hFelder[zKey[i][0]][zKey[i][1]].setKey(false);
				zLage[i]=1;
				zPlatform[i]=hLevels.getPlatform(zLevelNummer, zVersion, zLage[i],i);
				hLevels.setPlatform(zLevelNummer, zLage[i],i);
				hPController.setFelder(hFelder);
			}
		}
		
		if(hFelder[zDoor[0]][zDoor[1]].getXPos()==hPlayer.getXPos()&&hFelder[zDoor[0]][zDoor[1]].getYPos()==hPlayer.getYPos())
		{
			nextLevel();
		}
		
		for(int i=0;i<zSpikes.length;i++)
		{
			if(hFelder[zSpikes[i][0]][zSpikes[i][1]].contains(hPlayer.getXPos()-1, hPlayer.getYPos()+zSize)||
					hFelder[zSpikes[i][0]][zSpikes[i][1]].contains(hPlayer.getXPos()+zSize-1, hPlayer.getYPos()+zSize))
			{
				resetLevel(zLevelNummer);
			}
		}
		
//		if(zDownFree&&zJumpCount==0)
//		{hPlayer.moveY(1);}
//		if(zUpFree&&zJumpCount>0)
//		{
//			hPlayer.moveY(-1);
//			zJumpCount--;
//		}
//		if(zLeftKey&&zLeftFree)
//		{hPlayer.moveX(-1);}
//		if(zRightKey&&zRightFree)
//		{hPlayer.moveX(1);}
		zTime+=0.01;
		hView.update(hPlayer, hBackground,zDoor,zKey,zPlatform,zLage,hLevels.getKeyNumbers(zLevelNummer),zSpikes,zTime);
	}
	
	/**
	 * setz das Spielfeld auf den Start des übergebenen Levels zurück
	 * @param pLevel
	 */
	public void resetLevel(int pLevel)
	{
		zVersion=0;
		hBackground=hLevels.getBackground(pLevel);
		hFelder=hLevels.getLevel(pLevel, zVersion);
		hPlayer.setPosition(hLevels.getPlayerPosition(pLevel)[0]*zSize,hLevels.getPlayerPosition(pLevel)[1]*zSize);
		zDoor=hLevels.getDoor(pLevel,zVersion);
		zKey[0]=hLevels.getKey1(pLevel,zVersion);
		zKey[1]=hLevels.getKey2(pLevel,zVersion);
		zKey[2]=hLevels.getKey3(pLevel,zVersion);
//		for(int i=0;i<zLage.length;i++)
//		{zLage[i]=0;}
//		for(int i=0;i<zPlatform.length;i++)
//		{
//			zPlatform[i]=hLevels.getPlatform(zLevelNummer, zVersion, zLage[i], i);
//		}
		zSpikes=hLevels.getSpikes(pLevel, zVersion);
		for(int i=0;i<hLevels.getKeyNumbers(pLevel);i++)
		{
			hFelder[zKey[i][0]][zKey[i][1]].setKey(true);
			zLage[i]=0;
			zPlatform[i]=hLevels.getPlatform(pLevel, zVersion, zLage[i],i);
			hLevels.setPlatform(pLevel, zLage[i],i);
		}
		hLevels.resetKeys(zLevelNummer);
		hPController.setFelder(hFelder);
	}
	
	/**
	 * startet das nächste Level, ausgehend von dem aktuellen Level
	 */
	public void nextLevel()
	{
		hTimer.stop();
		zLevelNummer++;
		if(zLevelNummer<=2)
		{
			resetLevel(zLevelNummer);
			hTimer.start();
		}
		else
		{
			//TODO:
			//Game Over Funktion, Hausptmenü?
		}
	}

	/**
	 * Tastaturabfrage
	 * @Override keyPressed(KeyEvent arg0)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==37)//left arrow key
		{
			zLeftKey=true;
		}
		else if(arg0.getKeyCode()==39)//right arrow key
		{
			zRightKey=true;
		}
		//TODO: kann man die arrows weglassen? 
		
//		if(arg0.getKeyCode()==32&&zJump==false&&hPlayer.getY()>hPlayer.getHeight())
//		{
//			zJumpCount=hPlayer.getHeight();
//			zJump=true;
//		}
	}

	/**
	 * Tastaturabfrage
	 * @Override keyReleased(KeyEvent arg0)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==37)//left arrow key
		{
			zLeftKey=false;
		}
		else if(arg0.getKeyCode()==39)//right arrow key
		{
			zRightKey=false;
		}
		
		if(arg0.getKeyCode()==32&&zJump==false&&hPlayer.getYPos()>hPlayer.getHeight())
		{
			zJumpCount=hPlayer.getHeight();
			zJump=true;
		}
		//TODO: arrows weglassen und jump weglassen
		
		if(arg0.getKeyCode()==16)//shift
		{	System.out.println("shift");
			if(hPlayer.getYPos()+zSize+1<15*zSize&&zJump==false&&zShift)
			{this.shift();}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
