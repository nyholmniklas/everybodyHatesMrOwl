package org.owl.gamestate.enemy;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.gamestate.DrunkOwlState;
import org.owl.gamestate.Entity;
import org.owl.physics.Physics;
import org.owl.utils.Timer;

public class Branch extends Entity{
	Timer harmless;

	public Branch(Vector2f position) throws SlickException {
		super(position, new Vector2f(0,0), null);
		Random random = new Random();
		//CREATE A BRANCH ON THE LEFT
		if (position.x == 0) {
			setAnimation(Animations.getAnimation(Animations.BRANCH_LEFT));
			setPosition(new Vector2f(-random.nextInt(70), position.y));
		}
		//ELSE CREATE BRANCH ON THE RIGHT
		else {
			setAnimation(Animations.getAnimation(Animations.BRANCH_RIGHT));
			setPosition(new Vector2f(DrunkOwlState.width-animation.getWidth()+random.nextInt(100), position.y));
		}
		setShape();
		updateShape();
		harmless = new Timer(1);
	}

	@Override
	public void update(Physics physics, float delta) throws SlickException {
	}

	@Override
	public int getDamage() {
		if (harmless.isDone()){
			harmless = new Timer(1000);
			return 1;
		}
		else return 0;
		
	}

}
