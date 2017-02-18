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

public class SoundBox {
	
	private AudioInputStream ais;
	private AudioFormat af;
	private int audioSize;
	private byte[] audio; 
	private DataLine.Info[] info = new DataLine.Info[8];
	private Clip[] gameMusic = new Clip[8];
	private boolean mute;
	
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
					ais = AudioSystem.getAudioInputStream(new File("Shift/sounds/Miss.wav"));
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
			
			gameMusic[0].start();
			gameMusic[0].loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public void jump() {
		if(!mute) {
			gameMusic[1].start();
			gameMusic[1].setFramePosition(0);
		}
	}
	
	public void collect() {
		if(!mute) {
			gameMusic[2].start();
			gameMusic[2].setFramePosition(0);
		}
	}
	
	public void door() {
		if(!mute) {
			gameMusic[3].start();
			gameMusic[3].setFramePosition(0);
		}
	}
	
	public void shift() {
		if(!mute) {
			gameMusic[4].start();
			gameMusic[4].setFramePosition(0);
		}
	}
	
	public void victory() {
		if(!mute) {
			gameMusic[0].stop();
			gameMusic[5].start();
		}
	}
	
	public void select() {
		if(!mute) {
			gameMusic[6].start();
			gameMusic[6].setFramePosition(0);	
		}
	}
	
	public void die() {
		if(!mute) {
			gameMusic[7].start();
			gameMusic[7].setFramePosition(0);
		}
	}
}
