package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Kevin 
 * <p>
 * <h3><i> SoundBox </i></h3>
 * This class contains all the sounds implemented in the game and the methods
 * to start/stop them. It is managed by {@link GameController}.
 */
public class SoundBox {
	
	private AudioInputStream ais;
	private AudioFormat af;
	private int audioSize;
	private byte[] audio; 
	private DataLine.Info[] info = new DataLine.Info[8];
	private Clip[] gameMusic = new Clip[8];
	private boolean mute;
	
	/**<dd>
	 * <h3><i> SoundBox </i></h3>
	 * <p>
	 * <code>{@code public SoundBox()}</code>
	 * </p>
	 * Creates a new SoundBox object, loading all the sounds into gameMusic[] 
	 */
	public SoundBox() {
		try {
			for(int i = 0; i < 8; i++) {
				switch (i) {
				case 0: 
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/GameSound.wav"));
					break;
				case 1:
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Jump1.wav"));
					break;
				case 2:
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Chime2.wav"));
					break;
				case 3: 
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Open5.wav"));
					break;
				case 4:
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Push.wav"));
					break;
				case 5:
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Victory1.wav"));
					break;
				case 6:
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Cursor2.wav"));
					break;
				case 7:
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Cry2.wav"));
					break;
				}
				af = ais.getFormat();
				audioSize = (int) (af.getFrameSize() * ais.getFrameLength());
				audio = new byte[audioSize];
				info[i] = new DataLine.Info(Clip.class, af, audioSize);
				ais.read(audio, 0, audioSize);
				
				gameMusic[i] = (Clip) AudioSystem.getLine(info[i]);
				gameMusic[i].open(af, audio, 0, audioSize);
				ais.close();
			}			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**<dd>
	 * <h3><i> toggleSound(boolean mute) </i></h3>
	 * <p>
	 * <code>{@code public void toggleSound(boolean mute)}</code>
	 * </p>
	 * Depending on the mute status, the sound is either set active or inactive. 
	 * @param mute - used to toggle the sound. false to activate, true to deactivate.
	 */
	public void toggleSound(boolean mute) {
		if(mute) {
			this.mute = mute;
			gameMusic[0].stop();
		}
		else {
			this.mute = mute;
			gameMusic[0].start();
			gameMusic[0].loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	/**<dd>
	 * <h3><i> start() </i></h3>
	 * <p>
	 * <code>{@code public void start()}</code>
	 * </p>
	 * Starts the background music, if not mute.
	 */
	public void start() {
		if(!mute){
			gameMusic[0].start();
			gameMusic[0].loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	/**<dd>
	 * <h3><i> stop() </i></h3>
	 * <p>
	 * <code>{@code public void stop()}</code>
	 * </p>
	 * Stops the background music and resets the song.
	 */
	public void stop() {
		gameMusic[0].stop();
		gameMusic[0].setFramePosition(0);
	}
	
	/**<dd>
	 * <h3><i> jump() </i></h3>
	 * <p>
	 * <code>{@code public void jump()}</code>
	 * </p>
	 * Sound for a simple jump. Resets the sound after it has been played.
	 * Only played if not muted.
	 */
	public void jump() {
		if(!mute) {
			gameMusic[1].start();
			gameMusic[1].setFramePosition(0);
		}
	}
	
	/**<dd>
	 * <h3><i> collect() </i></h3>
	 * <p>
	 * <code>{@code public void collect()}</code>
	 * </p>
	 * Sound for picking up the keys. Resets the sound after it has been played.
	 * Only played if not muted.
	 */
	public void collect() {
		if(!mute) {
			gameMusic[2].start();
			gameMusic[2].setFramePosition(0);
		}
	}
	
	/**<dd>
	 * <h3><i> door() </i></h3>
	 * <p>
	 * <code>{@code public void door()}</code>
	 * </p>
	 * Sound for a simple door. Resets the sound after it has been played.
	 * Only played if not muted.
	 */
	public void door() {
		if(!mute) {
			gameMusic[3].start();
			gameMusic[3].setFramePosition(0);
		}
	}
	
	/**<dd>
	 * <h3><i> shift() </i></h3>
	 * <p>
	 * <code>{@code public void shift()}</code>
	 * </p>
	 * Sound for shifting. Resets the sound after it has been played.
	 * Only played if not muted.
	 */
	public void shift() {
		if(!mute) {
			gameMusic[4].start();
			gameMusic[4].setFramePosition(0);
		}
	}
	
	/**<dd>
	 * <h3><i> victory() </i></h3>
	 * <p>
	 * <code>{@code public void victory()}</code>
	 * </p>
	 * Sound played, when the game is won. Resets the sound after it has been played.
	 * Only played if not muted. Deactivates BGM.
	 */
	public void victory() {
		if(!mute) {
			gameMusic[0].stop();
			gameMusic[5].start();
			gameMusic[5].setFramePosition(0);
			gameMusic[0].setFramePosition(0);
		}
	}
	
	/**<dd>
	 * <h3><i> select() </i></h3>
	 * <p>
	 * <code>{@code public void select()}</code>
	 * </p>
	 * Sound for a selection in the main menu. Resets the sound after it has been played.
	 * Only played if not muted.
	 */
	public void select() {
		if(!mute) {
			gameMusic[6].start();
			gameMusic[6].setFramePosition(0);	
		}
	}
	
	/**<dd>
	 * <h3><i> die() </i></h3>
	 * <p>
	 * <code>{@code public void die()}</code>
	 * </p>
	 * Deathsound. Resets the sound after it has been played.
	 * Only played if not muted.
	 */
	public void die() {
		if(!mute) {
			gameMusic[7].start();
			gameMusic[7].setFramePosition(0);
		}
	}
}
