package org.owl.controls;

import java.io.IOException;


import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.owl.audio.AudioUtils;
import org.owl.gamestate.DrunkOwlState;
import org.owl.gamestate.Player;
import org.owl.main.GamePlayState;

public class Controls {
	public static final int JUMP = Input.KEY_SPACE;
	public static final int MOVE_LEFT = Input.KEY_LEFT;
	public static final int MOVE_RIGHT = Input.KEY_RIGHT;
	public static final int MOVE_DOWN = Input.KEY_DOWN;
	public static final int RELOAD = Input.KEY_R;
	public static final int PAUSE = Input.KEY_P;
	
	//temporary code for audio test
//	static AudioUtils testUtil;
//	static boolean playing;
	
	public static DrunkOwlState updateDrunkOwlState(DrunkOwlState state, Input input, float delta) throws SlickException {
		Player player = state.getPlayer();
		
		//reload
		if (input.isKeyPressed(RELOAD)) {
			state.reload();
		}
		
		//pause
		if (input.isKeyPressed(PAUSE)) {
			state.togglePause();
		}
		
		//temp audio code
//		if (testUtil == null) {
//			testUtil = new AudioUtils();
//			try {
//				testUtil.loadAudio();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			playing = false;
//		}
		

		
		//temp audio test
//		input.disableKeyRepeat();
//		if (input.isKeyDown(input.KEY_ENTER)){
//			if (!playing) {
//				testUtil.playAudio();
//				playing = true;
//			}
//			else if (playing) {
//				testUtil.test.stop();
//
//								playing = false;
//			}
//		}
//		input.enableKeyRepeat();
		
		//GAMEPLAY CONTROLS
		if (!state.isPaused() && !player.isParalyzed()) {
			
			
			if (input.isKeyDown(JUMP)) {
				player.fly(delta);
				player.setBoost(true);
			}
			else player.setBoost(false);
			
			if(input.isKeyDown(MOVE_DOWN)) {
				player.moveDown(delta);
				player.setBoost(false); 
				player.setSwoosh(true);
			}
			else player.setSwoosh(false);
			
			
			if(input.isKeyDown(MOVE_LEFT)) {
				player.moveLeft(delta);
			}
			else if(input.isKeyDown(MOVE_RIGHT)) {
				player.moveRight(delta);
			}
		}
		
		
		return state;
	}
	
}
