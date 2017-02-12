/**
 * 
 */
package shift;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import shift.Feld;

/**
 * @author Kevin
 *
 */
public class PlayerController implements KeyListener {
	
	private PlayerModel player;

	private Feld[][] felder;
	
	private boolean moveDir[] = {false, false, false, true}; //left, right, up, down
	private boolean zWPressed = false;
	private int xToObstacle, yToObstacle, aniCount;
	private final int TILESIZE = 40;
	
	public PlayerController(PlayerModel player, Feld[][] felder) {
		this.player = player;
		this.felder = felder;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.player.setxTargetSpeed(3);
			moveDir[1] = true;
			moveDir[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.player.setxTargetSpeed(-3);
			moveDir[0] = true;
			moveDir[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W && (!zWPressed)) {
			zWPressed = true;
			if(this.player.getGrounded()) {
				this.player.setyTargetSpeed(-6);
				moveDir[2] = true;
				moveDir[3] = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			moveDir[1] = false;
			this.player.setxTargetSpeed(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			moveDir[0] = false;
			this.player.setxTargetSpeed(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			zWPressed = false;
			this.player.setyTargetSpeed(8);
			moveDir[2] = false;
			moveDir[3] = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/* TODO: 1. Herausfinden wie genau Enums funktionieren in JAVA, damit die Abfrage besser ist.
	* 		 2. MagicNumbers: 0 (linke Spielfeldgrenze), 600 (rechte Spielfeldgrenze)
	*		 3. D-Button fixen
	*/
	
	private void checkDistanceToObstacle() {
		int i,j,tmp;
		xToObstacle = 0;
		yToObstacle = 0;
		moveDir[3] = true;
		if(this.player.getJumpHeight() <= 5) moveDir[2] = false;
		
		if (moveDir[0]) {
			if (this.player.getXPos() > 0 ) {
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
					if (x == 0 || (x == 1 && tmp > xToObstacle)) xToObstacle = tmp;
				}
			}
		}
		else if (moveDir[1]) {
			if (this.player.getXPos() + this.player.getWidth() < 600) {
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
					if (x == 0 || (x == 1 && tmp < xToObstacle)) xToObstacle = tmp;
				}
			}
		}
		
		if (moveDir[2]) {
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
					if (x == 0 || (x == 1 && tmp > yToObstacle)) yToObstacle = tmp;
				}
			}
		} 
		else if (moveDir[3]) {
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
					if (x == 0 || (x == 1 && tmp < yToObstacle)) yToObstacle = tmp;
				}
			} 
		}
		
		if (yToObstacle == 0) {
			moveDir[3] = false;
			this.player.setJumpHeight(40);
			this.player.setGrounded(true);
		}
	}
	
	public void setFelder(Feld[][] pFelder)
	{
		felder=pFelder;
	}
	
	public void updatePlayer() {
		checkDistanceToObstacle();
		this.player.move(xToObstacle, yToObstacle, aniCount);
		aniCount++;
	}
}
