package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * @author Carina
 *
 */
public class GamePanel extends JPanel{
	
	private PlayerModel zPlayer;
	private Image zBackground;
	
	private int[] zDoor,zLage;
	private int[][] zKey;
	private int[][][] zPlatform;
	private int zKeyNumbers;
	private Image[] zDoorImage,zKeyImage,zPlatformImage;
	private Image zCharIMG;
	private int zFarbe;
	
	private int zSize=40;
	private final int PAINTING_OFFSET = 6;
		
	/**
	 * erstellt das Panel, auf dem das Level dargestellt wird
	 */
	public GamePanel()
	{
		zDoorImage=new Image[3];
		zKeyImage=new Image[3];
		zPlatformImage=new Image[3];
		try {
			zDoorImage[0]=ImageIO.read(new File("Shift/images/Exit.png"));
			zKeyImage[0]=ImageIO.read(new File("Shift/images/Key.png"));
			zPlatformImage[0]=ImageIO.read(new File("Shift/images/Platform.png"));
			zDoorImage[1]=ImageIO.read(new File("Shift/images/ExitRot.png"));
			zKeyImage[1]=ImageIO.read(new File("Shift/images/KeyRot.png"));
			zPlatformImage[1]=ImageIO.read(new File("Shift/images/PlatformRot.png"));
			zDoorImage[2]=ImageIO.read(new File("Shift/images/ExitBlau.png"));
			zKeyImage[2]=ImageIO.read(new File("Shift/images/KeyBlau.png"));
			zPlatformImage[2]=ImageIO.read(new File("Shift/images/PlatformBlau.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * zeichnet alle Elemente des Levels
	 * @Override paintComponent(Graphics g)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.RED);
		if(zBackground!=null&&zPlayer!=null)
		{
			g.drawImage(zBackground, 0, 0 ,this);
			g.drawImage(zDoorImage[zFarbe],zDoor[0]*zSize,zDoor[1]*zSize, null);
			for(int i=0;i<zKeyNumbers;i++)
			{
				if(zLage[i]==0)
				{g.drawImage(zKeyImage[zFarbe], zKey[i][0]*zSize, zKey[i][1]*zSize, null);}
				
				for(int j=0;j<zPlatform[i].length;j++)
				{
					g.drawImage(zPlatformImage[zFarbe],zPlatform[i][j][0]*zSize,zPlatform[i][j][1]*zSize,null);
				}
			}
		
			g.drawImage(zCharIMG, zPlayer.getXPos()-PAINTING_OFFSET, zPlayer.getYPos(), zPlayer.getXPos() + zPlayer.getWidth() + PAINTING_OFFSET, zPlayer.getYPos()+40, 
						zPlayer.getCoords()[0], zPlayer.getCoords()[1], zPlayer.getCoords()[2], zPlayer.getCoords()[3], null);

		}
	}
	
	/**
	 * bekommt alle wichtigen Variablen vom Controller und übernimmt sie für eigene Klassenvariablen
	 * @param pPlayer
	 * @param pBackground
	 * @param pDoor
	 * @param pKey
	 * @param pPlatform
	 * @param pLage
	 * @param pKeyNumbers
	 * @param pSpikes
	 * @param pTime
	 */
	public void update(PlayerModel pPlayer,Image pBackground,int[] pDoor,int[][] pKey,int[][][] pPlatform,int[] pLage,int pKeyNumbers,int[][] pSpikes,int pFarbe)
	{
		zPlayer=pPlayer;
		zFarbe=pFarbe;
		zCharIMG=pPlayer.getCharImg()[zFarbe];
		zBackground=pBackground;
		zDoor=pDoor;
		zKey=pKey;
		zPlatform=pPlatform;
		zLage=pLage;
		zKeyNumbers=pKeyNumbers;
	}
}
