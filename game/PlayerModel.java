package game;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * 
 * @author Kevin
 *
 */

public class PlayerModel {
	
	private int xPos, yPos, width, height;
	private int[] coords = {26, 0, 52, 45}; //SourceX1, SourceY1, SourceX2, SourceY2
	private Image charImg;
	
	private float xSpeed,ySpeed;
	private int xTargetSpeed = 0;
	private int yTargetSpeed = 5;
	private final float ACCELARATION = 0.09f;
	private final float J_ACC = 0.05f;
	private boolean isGrounded;
	private int jumpHeight = 40;
	
	public PlayerModel(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		 
		charImg = new ImageIcon("Shift/images/StickySheet.png").getImage(); // ImageIO.read(new File("Shift/shift/images/Stick-Running.gif"));			
	}
	
	public void move(int xDist, int yDist, int aniCount) {
		xSpeed = ACCELARATION * xTargetSpeed + (1-ACCELARATION) * xSpeed;
		ySpeed = J_ACC * yTargetSpeed + (1-J_ACC) * ySpeed;
		
		if(xDist > 0) {
			this.xPos += (int) (Math.min((xDist), xSpeed));
			this.coords[1] = 0;
			this.coords[3] = 45;
			if(this.coords[0] < 208 && aniCount % 8 == 0 && (Math.min((yDist), ySpeed) == 0 || Math.max((yDist), ySpeed) == 0)) {
				this.coords[0] += 26;
				this.coords[2] += 26;
			} else if (this.coords[0] == 208) {
				this.coords[0] = 0;
				this.coords[2] = 26;
			}
		}
		else if (xDist < 0) {
			this.xPos += (int) (Math.max((xDist), xSpeed));
			this.coords[1] = 56;
			this.coords[3] = 101;
			if(this.coords[0] < 208 && aniCount % 8 == 0 && (Math.min((yDist), ySpeed) == 0 || Math.max((yDist), ySpeed) == 0)) {
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
		
		if (yDist > 0) {
			isGrounded = false;
			this.yPos += (int) (Math.min((yDist), ySpeed));
		}
		else if (yDist < 0 && jumpHeight > 0)  {
			this.yPos += (int) (Math.max((yDist), ySpeed));
			jumpHeight += (int) (Math.max((yDist), ySpeed));
			if(jumpHeight <= 5) {
				yTargetSpeed = 8;
			}
			this.isGrounded = false;
		}
		else this.ySpeed = 0;
	}
	
	public void setxTargetSpeed(int xTargetSpeed) {
		this.xTargetSpeed = xTargetSpeed;
	}
	
	public void setyTargetSpeed(int yTargetSpeed) {
		this.yTargetSpeed = yTargetSpeed;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean getGrounded() {
		return isGrounded;
	}
	
	public int getJumpHeight() {
		return jumpHeight;
	}

	public Image getCharImg() {
		return charImg;
	}
	
	public int[] getCoords() {
		return coords;
	}
	
	public void setCoords(int[] coords) {
		this.coords = coords;
	}
	
	public void setPosition(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}
	
	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}
}

