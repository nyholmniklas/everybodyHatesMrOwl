package org.owl.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseState extends BasicGameState{
	private int ID;
	private GamePlayState gameState;
	private Image pauseImage;
	
	public PauseState(int ID, GamePlayState gameState) {
		this.ID = ID;
		this.gameState = gameState;
	}
	
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		try {
			pauseImage = new Image("res/owlpausescreen.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(pauseImage, 0, 0);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if (arg0.getInput().isKeyPressed(Input.KEY_P)) {
			gameState.mainDrunkOwlState.togglePause();
			arg1.enterState(EverybodyHatesMrOwl.GAMEPLAYSTATE);
		}
	}

}
