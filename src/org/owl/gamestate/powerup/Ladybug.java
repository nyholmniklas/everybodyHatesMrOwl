package org.owl.gamestate.powerup;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.gamestate.Entity;
import org.owl.gamestate.Player;
import org.owl.physics.Physics;

public class Ladybug extends Entity {
	private Player player;
	private float maxHorizontalSpeed;
	private int moveLag;
	
	
	public Ladybug(Vector2f position, Player player) throws SlickException {
		super(position, new Vector2f(0,0), Animations.getAnimation(Animations.LADYBUG_LEFT));
		this.maxHorizontalSpeed = 1.1f;
		this.moveLag = 50;
		this.player = player;
	}

	@Override
	public void update(Physics physics, float delta) throws SlickException {
		//LOGIC FOR K-BIRD MOVEMENT(FOLLOWING PLAYER)
		if ((vector.y == 0 && player.getPosition().y < position.y - 80) || (vector.y >0 && position.y > player.getPosition().y)) vector.y = 1.3f;
		else if (vector.y < 0 && position.y < player.getPosition().y - 50) vector.y = -1f;
		if (vector.y != 0) {
			if (position.x < player.getPosition().x - moveLag){
				if (Math.abs(vector.x) < maxHorizontalSpeed){
					vector.x = -1f;
				}
				setAnimation(Animations.getAnimation(Animations.LADYBUG_RIGHT));
			}
			else if (position.x > player.getPosition().x + moveLag) {
				if (Math.abs(vector.x) < maxHorizontalSpeed){
					vector.x = 1f;
				}
				setAnimation(Animations.getAnimation(Animations.LADYBUG_LEFT));
			}
		}

		getPosition().add(new Vector2f((vector.x*delta), (vector.y)*delta));
		
	}
	
	@Override
	public void setShape() {
		shape = new Rectangle(position.x, position.y, animation.getWidth(), animation.getHeight());
	}

	@Override
	public int getDamage() {
		remove();
		return -1;
	}

}
