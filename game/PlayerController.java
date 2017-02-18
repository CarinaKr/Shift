
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * 
 * @author Kevin 
 * <p>
 * <h3><i> PlayerController </i></h3>
 * This Class is used to manage the PlayerModel. It contains a method to detect 
 * collision, a keylistener managing the controls and an updatePlayer method. 
 */
public class PlayerController implements KeyListener {
	
	private PlayerModel player;

	private Feld[][] felder;
	
	private boolean moveDir[] = {false, false, false, true}; //left, right, up, down
	private boolean wPressed = false;
	private int leftXToObstacle, rightXToObstacle, downYToObstacle, upYToObstacle, aniCount;
	private final int TILESIZE = 40;
	private SoundBox soundBox;
	
	/**<dd>
	 * <h3><i> PlayerController </i></h3>
	 * <p>
	 * <code>{@code public PlayerController({@link PlayerModel} player, {@link Feld}[][] felder)}</code>
	 * </p>
	 * The Constructor creates a new Object of PlayerController setting the references to
	 * the PlayerModel to control and the array of class Feld. The Feld-array is needed for
	 * collision detection. 
	 * @param player - Expects a PlayerModel to control
	 * @param felder - Expects the fields from the Game for checking the Collision
	 */
	public PlayerController(PlayerModel player, Feld[][] felder, SoundBox soundBox) {
		this.player = player;
		this.felder = felder;
		this.soundBox = soundBox;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * After detecting the pressed key, the moveDir is set true for the direction in 
	 * which the player is supposed to move. Furthermore the TargetSpeed is set according
	 * to the move direction. Whenever a Key is released, moveDir is set false again and the 
	 * targetSpeed is set to 0 (or 8, if falling).
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.setxTargetSpeed(3);
			moveDir[1] = true;
			moveDir[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.setxTargetSpeed(-3);
			moveDir[0] = true;
			moveDir[1] = false;
		}
		if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) && (!wPressed)) {
			wPressed = true;
			if(this.player.getGrounded()) {
				this.player.setyTargetSpeed(-6);
				moveDir[2] = true;
				moveDir[3] = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveDir[1] = false;
			this.player.setxTargetSpeed(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveDir[0] = false;
			this.player.setxTargetSpeed(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
			wPressed = false;
			this.player.setyTargetSpeed(8);
			moveDir[2] = false;
			moveDir[3] = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**<dd>
	 * <h3><i> checkDistanceToObstacle() </i></h3>
	 * <p>
	 * <code>{@code private void checkDistanceToObstacle()</code>
	 * </p>
	 * This method checks the distance towards the direction in which the character is moving.
	 * Depending on the player input (key), and the current movement speed, it determines
	 * the direction in which to search for obstacles. It then loops through the felder-array
	 * calling the isAccessible() method, until it returns false. For every loop an iterator
	 * is incremented. The value is stored in a member variable. 
	 * The method also toggles the possibility to jump via distance.
	 */
	private void checkDistanceToObstacle() {
		int i,j,tmp;
		leftXToObstacle = 0;
		rightXToObstacle = 0;
		upYToObstacle = 0;
		downYToObstacle = 0;
		moveDir[3] = true;
		if(this.player.getJumpHeight() <= 5) moveDir[2] = false;
		
		if (moveDir[0] && this.player.getxSpeed() <= 0) {
			if (this.player.getXPos() - this.player.getPAINTING_OFFSET() > 0 ) {
				for(int x = 0; x < 2; x++) {
					tmp = 0;
					i = (int) (this.player.getXPos()) / TILESIZE;
					j = (int) ((this.player.getYPos() + ((this.player.getHeight()-1)*x)) / TILESIZE);
					
					while(this.felder[i][j].isAccessable()) {
						tmp--;
						i = (int) (this.player.getXPos() + tmp) / TILESIZE;
						if (i < 0){
							i = 0;
							break;
						}
					}
					tmp++;
					if (x == 0 || (x == 1 && tmp > leftXToObstacle)) leftXToObstacle = tmp;
				}
			}
		}
		else if (moveDir[1] && this.player.getxSpeed() >= 0) {
			if (this.player.getXPos() + this.player.getWidth() + this.player.getPAINTING_OFFSET() < 600) {
				for (int x = 0; x < 2; x++) {
					tmp = 0;
					i = (int) (this.player.getXPos() + this.player.getWidth()) / TILESIZE;
					j = (int) ((this.player.getYPos() + ((this.player.getHeight()-1)*x)) / TILESIZE);
					
					while(this.felder[i][j].isAccessable()) {
						tmp++;
						i = (int) (this.player.getXPos() + this.player.getWidth() + tmp) / TILESIZE;
						if (i > 14) {
							i = 14;
							break;
						}
					}
					if (x == 0 || (x == 1 && tmp < rightXToObstacle)) rightXToObstacle = tmp;
				}
			}
		}
		
		if (moveDir[2] && this.player.getySpeed() <= 0) {
			moveDir[3] = false;
			if (this.player.getYPos() > 0) {
				for (int x = 0; x < 2; x++) {
					tmp = 0;
					i = (int) ((this.player.getXPos() + ((this.player.getWidth()-1)*x)) / TILESIZE);
					j = (int) (this.player.getYPos() - 1) / TILESIZE;
					
					while(this.felder[i][j].isAccessable()) {
						tmp--;
						j = (int) (this.player.getYPos() + tmp) / TILESIZE;
						if (j < 0) {
							j = 0;
							break;
						}
					}
					if (x == 0 || (x == 1 && tmp > upYToObstacle)) upYToObstacle = tmp;
				}
			}
		} 
		else if (moveDir[3] && this.player.getySpeed() >= 0) {
			if(this.player.getYPos() + this.player.getHeight() < 600) {	
				for (int x = 0; x < 2; x++) {
					tmp = 0;
					i = (int) ((this.player.getXPos() + ((this.player.getWidth()-1)*x)) / TILESIZE);
					j = (int) (this.player.getYPos() + this.player.getHeight()) / TILESIZE;
					
					while(this.felder[i][j].isAccessable()) {
						tmp++;
						j = (int) (this.player.getYPos() + this.player.getHeight() + tmp) / TILESIZE;
						if (j > 14) {
							j = 14;
							break;
						}
					}
					if (x == 0 || (x == 1 && tmp < downYToObstacle)) downYToObstacle = tmp;
				}
			} 
		}
		
		if (moveDir[2] && this.player.getGrounded()) soundBox.jump();
		if (downYToObstacle == 0 && moveDir[3]) {
			moveDir[3] = false;
			this.player.setJumpHeight(50);
			this.player.setGrounded(true);
		}
		else if (upYToObstacle == 0 && moveDir[2]) {
			moveDir[2] = false;
			moveDir[3] = true;
			this.player.setyTargetSpeed(8);
			this.player.setJumpHeight(0);
		}
	}
	
	/**
	 * 	<dd>
	 * <h3><i> setFelder(Feld[][] pFelder) </i></h3>
	 * <p>
	 * <code>{@code public void setFelder(Feld[][] pFelder)}</code>
	 * </p>
	 * This method updates the link to the felder-array (in case the player shifts)
	 * @param pFelder - contains the current felder-array
	 */
	public void setFelder(Feld[][] pFelder)
	{
		felder=pFelder;
	}
	
	/**
	 * 	<dd>
	 * <h3><i> updatePlayer() </i></h3>
	 * <p>
	 * <code>{@code public void updatePlayer()}</code>
	 * </p>
	 * This method is called by the update()-method from the {@link GameController} .
	 * It calls the {@link checkDistanceToObstacle()}-method to update the current distance towards the movement 
	 * direction. After this, the move-method from the PlayerModel is called, to actually move the player. 
	 */
	public void updatePlayer() {
		checkDistanceToObstacle();
		this.player.move(leftXToObstacle, rightXToObstacle, downYToObstacle, upYToObstacle, aniCount);
		aniCount++;
	}
}
