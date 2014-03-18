package org.owl.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	
	public static final int EXPLOSION = 0;
	public static final int TICKING = 1;
	public static final int PAIN = 2;
	
	private static Sound explosion;
	private static Sound ticking;
	private static Sound pain;
	
	public static void loadSounds() throws SlickException {
		explosion = new Sound("res/explosion_sound.ogg");
		ticking = new Sound("res/ticking.ogg");
		pain = new Sound("res/pain.ogg");
	}
	
	public static void playSound(int sound) {
		switch (sound) {
		case EXPLOSION:
			explosion.play();
			break;
			
		case PAIN:
			pain.play();
			break;
		
		case TICKING:
			ticking.play();
			break;

		default:
			break;
		}
	}

}
