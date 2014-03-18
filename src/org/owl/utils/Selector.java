package org.owl.utils;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.owl.main.EverybodyHatesMrOwl;

public class Selector {

	private int current;
	private Image image;
	
	public Image getImage(){
		return image;
	}
	
	public void setImage() throws SlickException{
		image = new Image("res/point.png");
	}
	
	public Selector() {
		current = 0;
	}
	
	public void down() {
		current ++;
		if (current > 2) current = 0;
	}
	
	public void up() {
		current --;
		if (current < 0) current = 2;
	}

	public int getSelection(){
		return current;
	}
	
	public void select(GameContainer cont, StateBasedGame game) throws SlickException {
		switch (current) {
		case 0:
			game.getState(EverybodyHatesMrOwl.GAMEPLAYSTATE).init(cont, game);
			game.enterState(EverybodyHatesMrOwl.GAMEPLAYSTATE);
			break;
			
		case 1:
//			game.getState(EverybodyHatesMrOwl.INFOSTATE).init(cont, game);
			game.enterState(EverybodyHatesMrOwl.INFOSTATE);
			break;
			
		case 2:
			cont.exit();
			break;
						
		default:
			break;
		}
	}
	
}
