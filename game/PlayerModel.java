package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * @author Kevin
 *
 */

public class PlayerModel {
	
	private int xPos, yPos, width, height;
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
		 
		charImg = new ImageIcon("Shift/shift/images/Stick-Running.gif").getImage(); // ImageIO.read(new File("Shift/shift/images/Stick-Running.gif"));			
		charImg = charImg.getScaledInstance(20, 45, java.awt.Image.SCALE_FAST);
	}
	
	public void move(int xDist, int yDist) {
		xSpeed = ACCELARATION * xTargetSpeed + (1-ACCELARATION) * xSpeed;
		ySpeed = J_ACC * yTargetSpeed + (1-J_ACC) * ySpeed;
		
		if(xDist > 0) this.xPos += (int) (Math.min((xDist), xSpeed));
		else if (xDist < 0) this.xPos += (int) (Math.max((xDist), xSpeed));
		else this.xSpeed = 0;
		
		if (yDist > 0) this.yPos += (int) (Math.min((yDist), ySpeed));
		else if (yDist < 0 && jumpHeight > 0)  {
			this.yPos += (int) (Math.max((yDist), ySpeed));
			jumpHeight += (int) (Math.max((yDist), ySpeed));
			if(jumpHeight <= 10) {
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
	
	public void setPosition(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}
	
	public void setJc(int jc) {
		this.jumpHeight = jc;
	}
}

