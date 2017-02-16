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
	
	/**<dd>
	 * <h3><i> Feld </i></h3>
	 * <p>
	 * <code>{@code public Feld({@link integer} pX, {@link integer} pY,{@link integer} pSize,{@link bool} isAcessable)}</code>
	 * </p>
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
	
	/**<dd>
	 * <h3><i> setAcessable </i></h3>
	 * <p>
	 * <code>{@code public setAcessable({@link bool} pAcessable)}</code>
	 * </p>
	 *  
	 * changes the accessibility to the value of pAcessable
	 * @param pAcessable
	 */
	public void setAcessable(boolean pAcessable)
	{
		zAcessable=pAcessable;
		zReturnAccessable=pAcessable;
	}
	/**<dd>
	 * <h3><i> isAcessable </i></h3>
	 * <p>
	 * <code>{@code public isAcessable()}</code>
	 * </p>
	 *  
	 * returns wether the field is accessible
	 * @return Acessable
	 */
	public boolean isAccessable()
	{
		return zAcessable;
	}
	
	/**<dd>
	 * <h3><i> getXPos </i></h3>
	 * <p>
	 * <code>{@code public getXPos()}</code>
	 * </p>
	 *  
	 * returns the pixel-coordinates of the field
	 * @return realX
	 */
	public int getXPos()
	{
		return zRealX;
	}
	/**<dd>
	 * <h3><i> getYPos </i></h3>
	 * <p>
	 * <code>{@code public getYPos()}</code>
	 * </p>
	 * 
	 * returns the pixel-coordinates of the field
	 * @return realY
	 */
	public int getYPos()
	{
		return zRealY;
	}
	
	/**<dd>
	 * <h3><i> contains </i></h3>
	 * <p>
	 * <code>{@code public contains({@link integer} pX, {@link integer} pY)}</code>
	 * </p>
	 * 
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
	
	/**<dd>
	 * <h3><i> setDoor </i></h3>
	 * <p>
	 * <code>{@code public setDoor({@link bool} pDoor)}</code>
	 * </p>
	 * 
	 * sets the door-value of the field to pDoor
	 * @param pDoor
	 */
	public void setDoor(boolean pDoor) {
		this.zDoor = pDoor;
	}
	/**<dd>
	 * <h3><i> setKey </i></h3>
	 * <p>
	 * <code>{@code public setKey({@link bool} pKey)}</code>
	 * </p>
	 *  sets the key-value of the field to pKey
	 * @param pKey
	 */
	public void setKey(boolean pKey) {
		this.zKey = pKey;
	}
	/**<dd>
	 * <h3><i> setPlatform </i></h3>
	 * <p>
	 * <code>{@code public setPlatform({@link bool} pPlatform)}</code>
	 * </p>
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
	/**<dd>
	 * <h3><i> setSpike </i></h3>
	 * <p>
	 * <code>{@code public setSpike({@link bool} pSpike)}</code>
	 * </p>
	 * sets the spike-value of the field to pSpike
	 * @param pSpike
	 */
	public void setSpike(boolean pSpike)
	{
		zSpike=pSpike;
	}
	
	/**<dd>
	 * <h3><i> isDoor </i></h3>
	 * <p>
	 * <code>{@code public isDoor()}</code>
	 * </p>
	 * returns weather the field is a door
	 * @return
	 */
	public boolean isDoor() {
		return zDoor;
	}
	/**<dd>
	 * <h3><i> isKey </i></h3>
	 * <p>
	 * <code>{@code public isKey()}</code>
	 * </p>
	 *  returns weather the field is a key
	 * @return
	 */
	public boolean isKey() {
		return zKey;
	}
	/**<dd>
	 * <h3><i> isPlatform </i></h3>
	 * <p>
	 * <code>{@code public isPlatform()}</code>
	 * </p>
	 *  returns weather the field is a platform
	 * @return
	 */
	public boolean isPlatform() {
		return zPlatform;
	}
	/**<dd>
	 * <h3><i> isSpike </i></h3>
	 * <p>
	 * <code>{@code public isSpike()}</code>
	 * </p>
	 *  returns weather the field is a spike
	 * @return
	 */
	public boolean isSpike()
	{
		return zSpike;
	}
}
