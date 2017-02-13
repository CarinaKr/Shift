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
	 * eine Größe und ob es standartmäßig Begehbar ist 
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
	 * ändert die Begehbarkeit des Feldes auf den Wert von pAcessable
	 * @param pAcessable
	 */
	public void setAcessable(boolean pAcessable)
	{
		zAcessable=pAcessable;
		zReturnAccessable=pAcessable;
	}
	/**
	 * gibt zurück, ob das Feld begehbar ist
	 * @return Acessable
	 */
	public boolean isAccessable()
	{
		return zAcessable;
	}
	
	/**
	 * gibt die Pixelposition des Feldes zurück
	 * @return realX
	 */
	public int getXPos()
	{
		return zRealX;
	}
	/**
	 * gibt die Pixelposition des Feldes zurück
	 * @return realY
	 */
	public int getYPos()
	{
		return zRealY;
	}
	
	/**
	 * prüft ob der Punkt pX/pY in diesem Feld liegt
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
	 * setzt den Türwert des Feldes auf den Wert von pDoor
	 * @param pDoor
	 */
	public void setDoor(boolean pDoor) {
		this.zDoor = pDoor;
	}
	/**
	 * setzt den Schlüsselwert des Feldes auf den Wert von pKey
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
	 * gibt zurück ob das Feld eine Tür ist
	 * @return
	 */
	public boolean isDoor() {
		return zDoor;
	}
	/**
	 * gibt zurück ob das Feld ein Schlüssel ist
	 * @return
	 */
	public boolean isKey() {
		return zKey;
	}
	/**
	 * gibt zurück ob das Feld eine Platform ist
	 * @return
	 */
	public boolean isPlatform() {
		return zPlatform;
	}
	/**
	 * gibt zurück ob das Feld ein Spike ist
	 * @return
	 */
	public boolean isSpike()
	{
		return zSpike;
	}
}
