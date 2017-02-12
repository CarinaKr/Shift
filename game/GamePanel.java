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
	private int[][] zKey,zSpikes;
	private int[][][] zPlatform;
	private int zKeyNumbers;
	private Image zDoorImage,zKeyImage,zPlatformImage,zSpikeImage,zCharIMG;
	private double zTime;
	
	private int zSize=40;
	
	/**
	 * erstellt das Panel, auf dem das Level dargestellt wird
	 */
	public GamePanel()
	{
		try {
			zDoorImage=ImageIO.read(new File("Shift/images/Exit.png"));
			zKeyImage=ImageIO.read(new File("Shift/images/Key.png"));
			zPlatformImage=ImageIO.read(new File("Shift/images/Platform.png"));
			zSpikeImage=ImageIO.read(new File("Shift/images/Spikes.png"));
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
			g.drawImage(zDoorImage,zDoor[0]*zSize,zDoor[1]*zSize, null);
			for(int i=0;i<zKeyNumbers;i++)
			{
				if(zLage[i]==0)
				{g.drawImage(zKeyImage, zKey[i][0]*zSize, zKey[i][1]*zSize, null);}
				
				for(int j=0;j<zPlatform[i].length;j++)
				{
					g.drawImage(zPlatformImage,zPlatform[i][j][0]*zSize,zPlatform[i][j][1]*zSize,null);
				}
			}
//			for(int i=0;i<zSpikes.length;i++)
//			{
//				g.setColor(Color.GREEN);
//				g.fillRect(zSpikes[i][0]*zSize,zSpikes[i][1]*zSize,zSize,zSize);
//			}
		
			g.drawImage(zPlayer.getCharImg(), zPlayer.getXPos()-PAINTING_OFFSET, zPlayer.getYPos(), zPlayer.getXPos() + zPlayer.getWidth() + PAINTING_OFFSET, zPlayer.getYPos()+40, 
						zPlayer.getCoords()[0], zPlayer.getCoords()[1], zPlayer.getCoords()[2], zPlayer.getCoords()[3], null);
			g.setColor(Color.BLACK);
			double pTime=(int)(zTime*100);
			double pTime2=pTime/100;
			String pTimeString=""+pTime2;
			g.drawChars(pTimeString.toCharArray(), 0, pTimeString.length(), 14*zSize, zSize/2);
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
	public void update(PlayerModel pPlayer,Image pBackground,int[] pDoor,int[][] pKey,int[][][] pPlatform,int[] pLage,int pKeyNumbers,int[][] pSpikes,double pTime)
	{
		zPlayer=pPlayer;
		zCharIMG=pPlayer.getCharImg();
		zBackground=pBackground;
		zDoor=pDoor;
		zKey=pKey;
		zPlatform=pPlatform;
		zLage=pLage;
		zKeyNumbers=pKeyNumbers;
		zSpikes=pSpikes;
		zTime=pTime;
	}
}
