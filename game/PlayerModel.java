package game;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Kevin
 * <p>
 * <h3><i> PlayerModell</i></h3>
 * This Class stores all the relevant information for the Character, such as the position, 
 * width, height, currentXSpeed, currentYSpeed, the Images etc. It also provides methods to 
 * navigate the character.  
 */

public class PlayerModel {
	private int xPos, yPos, width, height;
	private int[] coords = {26, 0, 52, 45}; //SourceX1, SourceY1, SourceX2, SourceY2
	private Image[] charImg;
	
	private float xSpeed,ySpeed;
	private int xTargetSpeed = 0;
	private int yTargetSpeed = 5;
	private final float ACCELARATION = 0.09f;
	private final float J_ACC = 0.05f;
	private final int PAINTING_OFFSET = 6;
	private boolean isGrounded;
	private int jumpHeight = 50;
	
	/**
	 * <dd>
	 * <h3><i> PlayerModel(int xPos, int yPos, int width, int height) </i></h3>
	 * <p>
	 * <code>{@code public PlayerModel(int xPos, int yPos, int width, int height)} </code>
	 * </p>
	 * Creates a new PlayerModel with the initial x and y Position, and width and height
	 * given in the GameController. It then defines the charImg-array, loading the 3 different
	 * colors into the array.
	 * @param xPos - starting Position of the character (x-axis)
	 * @param yPos - starting Position of the character (x-axis)
	 * @param width - width of the player
	 * @param height - height of the player
	 */
	public PlayerModel(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
		charImg=new Image[3];
		charImg[0] = new ImageIcon("Shift/images/StickySheet.png").getImage();	
		charImg[1] = new ImageIcon("Shift/images/StickySheetRot.png").getImage();
		charImg[2] = new ImageIcon("Shift/images/StickySheetBlau.png").getImage();
	}
	
	public void move(int leftXDist,int rightXDist, int botYDist, int topYDist, int aniCount) {
		xSpeed = ACCELARATION * xTargetSpeed + (1-ACCELARATION) * xSpeed;
		ySpeed = J_ACC * yTargetSpeed + (1-J_ACC) * ySpeed;
		
		if(rightXDist > 0) {
			this.xPos += (int) (Math.min((rightXDist), xSpeed));
			this.coords[1] = 0;
			this.coords[3] = 45;
			if(this.coords[0] < 208 && aniCount % 8 == 0 && (Math.min((botYDist), ySpeed) == 0 || Math.max((botYDist), ySpeed) == 0)) {
				this.coords[0] += 26;
				this.coords[2] += 26;
			} else if (this.coords[0] == 208) {
				this.coords[0] = 0;
				this.coords[2] = 26;
			}
		}
		else if (leftXDist < 0) {
			this.xPos += (int) (Math.max((leftXDist), xSpeed));
			this.coords[1] = 56;
			this.coords[3] = 101;
			if(this.coords[0] < 208 && aniCount % 8 == 0 && (Math.min((botYDist), ySpeed) == 0 || Math.max((botYDist), ySpeed) == 0)) {
				this.coords[0] += 26;
				this.coords[2] += 26;
			} else if (this.coords[0] == 208) {
				this.coords[0] = 0;
				this.coords[2] = 26;
			}
		}
		else {
			this.xSpeed = 0;
			this.coords[0] = 0;
			this.coords[2] = 26;
			this.coords[1] = 164;
			this.coords[3] = 198;
		}
		
		if (botYDist > 0) {
			isGrounded = false;
			this.yPos += (int) (Math.min((botYDist), ySpeed));
		}
		else if (topYDist < 0 && jumpHeight > 0)  {
			this.yPos += (int) (Math.max((topYDist), ySpeed));
			jumpHeight += (int) (Math.max((topYDist), ySpeed));
			if(jumpHeight <= 5) {
				yTargetSpeed = 8;
			}
			this.isGrounded = false;
		}
		else this.ySpeed = 0;
	}
	
	/**
	 * <dd>
	 * <h3><i> setxTargetSpeed(int xTargetSpeed) </i></h3>
	 * <p>
	 * <code>{@code public void setxTargetspeed(int xTargetSpeed)}</code>
	 * </p>
	 * Sets the xTargetspeed, depending whether player is falling (8) oder jumping(-6).
	 * @param xTargetSpeed - the target Speed to reach on the X-Axis. 
	 */
	public void setxTargetSpeed(int xTargetSpeed) {
		this.xTargetSpeed = xTargetSpeed;
	}
	
	/**
	 * <dd>
	 * <h3><i> setyTargetSpeed(int yTargetSpeed) </i></h3>
	 * <p>
	 * <code>{@code public void setyTargetspeed(int yTargetSpeed)}</code>
	 * </p>
	 * Sets the yTargetspeed, depending whether player is falling (8) oder jumping(-6).
	 * @param yTargetSpeed - the target Speed to reach on the Y-Axis. 
	 */
	public void setyTargetSpeed(int yTargetSpeed) {
		this.yTargetSpeed = yTargetSpeed;
	}
	
	/**
	 * <dd>
	 * <h3><i> getXPos() </i></h3>
	 * <p>
	 * <code>{@code public int getXPos()}</code>
	 * </p>
	 * This method returns the X-Position of the player.
	 * @return x-position of the player
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * <dd>
	 * <h3><i> getYPos() </i></h3>
	 * <p>
	 * <code>{@code public int getYPos()}</code>
	 * </p>
	 * This method returns the Y-Position of the player.
	 * @return y-position of the player
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * <dd>
	 * <h3><i> getWidth() </i></h3>
	 * <p>
	 * <code>{@code public int getWidth()}</code>
	 * </p>
	 * This method returns the width of the player.
	 * @return width of the player
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * <dd>
	 * <h3><i> getHeight() </i></h3>
	 * <p>
	 * <code>{@code public int getHeight()}</code>
	 * </p>
	 * This method returns the Height of the player.
	 * @return height of the player
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * <dd>
	 * <h3><i> getGrounded() </i></h3>
	 * <p>
	 * <code>{@code public boolean getGrounded()}</code>
	 * </p>
	 * This method returns whether the player is grounded or not
	 * @return whether the player is grounded or not
	 */
	public boolean getGrounded() {
		return isGrounded;
	}
	
	/**
	 * <dd>
	 * <h3><i> getxSpeed() </i></h3>
	 * <p>
	 * <code>{@code public float getxSpeed()}</code>
	 * </p>
	 * This method returns the current Speed of the player on the x-axis.
	 * @return speed of the player on the x-axis
	 */
	public float getxSpeed() {
		return xSpeed;
	}
	
	/**
	 * <dd>
	 * <h3><i> getySpeed() </i></h3>
	 * <p>
	 * <code>{@code public float getySpeed()}</code>
	 * </p>
	 * This method returns the current Speed of the player on the y-axis.
	 * @return speed of the player on the y-axis
	 */
	public float getySpeed() {
		return ySpeed;
	}
	
	/**
	 * <dd>
	 * <h3><i> getJumpHeight() </i></h3>
	 * <p>
	 * <code>{@code public int getJumpHeight()}</code>
	 * </p>
	 * This method returns the jump height of the player
	 * @return JumpHeight of the player
	 */
	public int getJumpHeight() {
		return jumpHeight;
	}

	/**
	 * <dd>
	 * <h3><i> getCharImg() </i></h3>
	 * <p>
	 * <code>{@code public Image[] getCharImg()}</code>
	 * </p>
	 * This method returns the images to display the character
	 * @return Images of the player
	 */
	public Image[] getCharImg() {
		return charImg;
	}
	
	/**
	 * <dd>
	 * <h3><i> getPAINTING_OFFSET() </i></h3>
	 * <p>
	 * <code>{@code public int getPAINTING_OFFSET()}</code>
	 * </p>
	 * This method returns the offset from the players hitbox, which is needed to paint the player correctly
	 * @return PAINTING_OFFSET of the player's hitbox
	 */
	public int getPAINTING_OFFSET() {
		return PAINTING_OFFSET;
	}
	
	/**
	 * <dd>
	 * <h3><i> getCoords() </i></h3>
	 * <p>
	 * <code>{@code public int[] getCoords()}</code>
	 * </p>
	 * This method returns the Coordinates from the {@link charIMG}.
	 * @return the coordinates from the charIMG
	 */
	public int[] getCoords() {
		return coords;
	}
	
	/**
	 * <dd>
	 * <h3><i> setCoords(int[] coords) </i></h3>
	 * <p>
	 * <code>{@code public void setCoords(int[] coords)}</code>
	 * </p>
	 * Sets the coordinates array which determines the area to paint in the {@link charIMG}.
	 * @param coords - stores the coordinates from the charIMG
	 */
	public void setCoords(int[] coords) {
		this.coords = coords;
	}
	
	/**
	 * <dd>
	 * <h3><i> setPosition(int xPos, int yPos)</i></h3>
	 * <p>
	 * <code>{@code public void setPosition(int xPos, int yPos)}</code>
	 * </p>
	 * Sets the x and y Position from the player.
	 * @param xPos - The new xPosition of the player
	 * @param yPos - The new yPosition of the player
	 */
	public void setPosition(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * <dd>
	 * <h3><i> setGrounded(boolean isGrounded) </i></h3>
	 * <p>
	 * <code>{@code public void setGrounded(boolean isGrounded)}</code>
	 * </p>
	 * Sets the grounded status.
	 * @param isGrounded - determines whether the player is mid-air or not.
	 */
	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}
	
	/**
	 * <dd>
	 * <h3><i> setJumpHeight(int jumpHeight) </i></h3>
	 * <p>
	 * <code>{@code public void setJumpHeight(int jumpHeight)}</code>
	 * </p>
	 * Sets the jumpHeight, determining how much height is left.
	 *  @param jumpHeight
	 */
	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}
}

