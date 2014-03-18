package org.owl.audio;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class AudioUtils {

	public Audio test;
	
	public AudioUtils() {
		
	}
	
	public void	loadAudio() throws IOException{
		test = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/testAudio.ogg"));
//		test.playAsSoundEffect(pitch, gain, loop, x, y, z)p
	}
	
	public void playAudio() {
		test.playAsMusic(1, 1, true);
	}
	
	public void stopAudioLoop() {
		test.stop();
	}
	
}
