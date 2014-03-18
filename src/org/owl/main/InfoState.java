package org.owl.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InfoState extends BasicGameState{
	private int ID;
	private Image infoImage;
	
	public InfoState(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		try {
			infoImage = new Image("res/owlinfo.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(infoImage, 0, 0);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(EverybodyHatesMrOwl.TITLESTATE);
		}
	}


}
