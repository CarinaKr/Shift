package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * @author Carina
 *
 */
public class Feld {

	private int zX,zY,zSize;
	private int zRealX,zRealY;
	private boolean zAcessable;
	private Color zFarbe=Color.WHITE;
	private boolean zDoor,zKey,zPlatform;
	private boolean zReturnAccessable;
	private boolean zSpike;
	
	/** 
	 * initializes a new field and gives it a X- and Y-position as field-coordinates,
	 * a size and whether it is accessible or not
	 * @param pX
	 * @param pY
	 * @param pSize
	 * @param isAcessable
	 */
	public Feld(int pX,int pY,int pSize,boolean isAcessable)
	{
		zX=pX;
		zY=pY;
		zSize=pSize;
		zAcessable=isAcessable;
		zReturnAccessable=zAcessable;
		zRealX=zX*zSize;
		zRealY=zY*zSize;
		zDoor=false;
		zKey=false;
		zPlatform=false;
	}
	
	/**
	 * changes the accessibility to the value of pAcessable
	 * @param pAcessable
	 */
	public void setAcessable(boolean pAcessable)
	{
		zAcessable=pAcessable;
		zReturnAccessable=pAcessable;
	}
	/**
	 * returns wether the field is accessible
	 * @return Acessable
	 */
	public boolean isAccessable()
	{
		return zAcessable;
	}
	
	/**
	 * returns the pixel-coordinates of the field
	 * @return realX
	 */
	public int getXPos()
	{
		return zRealX;
	}
	/**
	 * returns the pixel-coordinates of the field
	 * @return realY
	 */
	public int getYPos()
	{
		return zRealY;
	}
	
	/**
	 * checks if the field contains the point pX/pY 
	 * @param pX
	 * @param pY
	 * @return true, wenn der pX/pY in diesem Feld liegt
	 */
	public boolean contains(int pX,int pY)
	{
		if(pX>zRealX&&pX<zRealX+zSize&&pY>zRealY&&pY<zRealY+zSize)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * sets the door-value of the field to pDoor
	 * @param pDoor
	 */
	public void setDoor(boolean pDoor) {
		this.zDoor = pDoor;
	}
	/**
	 *  sets the key-value of the field to pKey
	 * @param pKey
	 */
	public void setKey(boolean pKey) {
		this.zKey = pKey;
	}
	/**
	 * sets the platform-value of the field to pPlatform
	 * @param pPlatform
	 */
	public void setPlatform(boolean pPlatform) {
		this.zPlatform = pPlatform;
		if(pPlatform)
		{zAcessable=false;}
		else if(pPlatform==false&&zReturnAccessable)
		{zAcessable=true;}
	}
	/**
	 * sets the spike-value of the field to pSpike
	 * @param pSpike
	 */
	public void setSpike(boolean pSpike)
	{
		zSpike=pSpike;
	}
	
	/**
	 * returns weather the field is a door
	 * @return
	 */
	public boolean isDoor() {
		return zDoor;
	}
	/**
	 *  returns weather the field is a key
	 * @return
	 */
	public boolean isKey() {
		return zKey;
	}
	/**
	 *  returns weather the field is a platform
	 * @return
	 */
	public boolean isPlatform() {
		return zPlatform;
	}
	/**
	 *  returns weather the field is a spike
	 * @return
	 */
	public boolean isSpike()
	{
		return zSpike;
	}
}
