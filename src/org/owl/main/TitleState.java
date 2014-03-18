package org.owl.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.owl.utils.Selector;

public class TitleState extends BasicGameState{
	
	private int ID;
	private Image titleSplash;
	private Selector selector;
	
	public TitleState(int ID) throws SlickException {
		this.ID = ID;
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		try {
			titleSplash = new Image("res/title.png");
			selector = new Selector();
			selector.setImage();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(titleSplash, 0, 0);
		g.drawImage(selector.getImage(), 700, 500+(selector.getSelection()*85));
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(arg0.getInput().isKeyPressed(Input.KEY_ENTER)){
			selector.select(arg0, arg1);
		}
		if(arg0.getInput().isKeyPressed(Input.KEY_UP)) {
			selector.up();
		}
		
		if(arg0.getInput().isKeyPressed(Input.KEY_DOWN)) {
			selector.down();
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}

	public void resetSelection(){
		selector = new Selector();
	}
}

	