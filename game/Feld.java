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
	 * erstellt ein neues Feld und gibt diesem eine X- und Y-Position als Feldkoordinaten, 
	 * eine Gr��e und ob es standartm��ig Begehbar ist 
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
	 * �ndert die Begehbarkeit des Feldes auf den Wert von pAcessable
	 * @param pAcessable
	 */
	public void setAcessable(boolean pAcessable)
	{
		zAcessable=pAcessable;
		zReturnAccessable=pAcessable;
	}
	/**
	 * gibt zur�ck, ob das Feld begehbar ist
	 * @return Acessable
	 */
	public boolean isAccessable()
	{
		return zAcessable;
	}
	
	/**
	 * gibt die Pixelposition des Feldes zur�ck
	 * @return realX
	 */
	public int getXPos()
	{
		return zRealX;
	}
	/**
	 * gibt die Pixelposition des Feldes zur�ck
	 * @return realY
	 */
	public int getYPos()
	{
		return zRealY;
	}
	
	/**
	 * pr�ft ob der Punkt pX/pY in diesem Feld liegt
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
	 * setzt den T�rwert des Feldes auf den Wert von pDoor
	 * @param pDoor
	 */
	public void setDoor(boolean pDoor) {
		this.zDoor = pDoor;
	}
	/**
	 * setzt den Schl�sselwert des Feldes auf den Wert von pKey
	 * @param pKey
	 */
	public void setKey(boolean pKey) {
		this.zKey = pKey;
	}
	/**
	 * setzt den Platformwert des Feldes auf den Wert von pPlatform
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
	 * setzt den Spiekwert des Feldes auf den Wert von pSpike
	 * @param pSpike
	 */
	public void setSpike(boolean pSpike)
	{
		zSpike=pSpike;
	}
	
	/**
	 * gibt zur�ck ob das Feld eine T�r ist
	 * @return
	 */
	public boolean isDoor() {
		return zDoor;
	}
	/**
	 * gibt zur�ck ob das Feld ein Schl�ssel ist
	 * @return
	 */
	public boolean isKey() {
		return zKey;
	}
	/**
	 * gibt zur�ck ob das Feld eine Platform ist
	 * @return
	 */
	public boolean isPlatform() {
		return zPlatform;
	}
	/**
	 * gibt zur�ck ob das Feld ein Spike ist
	 * @return
	 */
	public boolean isSpike()
	{
		return zSpike;
	}
}
