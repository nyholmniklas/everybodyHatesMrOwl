package org.owl.gamestate.enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.gamestate.Entity;
import org.owl.physics.Physics;

public class Pinecone extends Entity{

	public Pinecone(Vector2f position) throws SlickException{
		super(position, new Vector2f(0,0), Animations.getAnimation(Animations.PINECONE));

	}

	@Override
	public void update(Physics physics, float delta) throws SlickException {
		physics.applyGravity(vector, delta);
		getPosition().add(new Vector2f((vector.x*delta), (vector.y)*delta));
	}

	@Override
	public int getDamage() {
		remove();
		return 1;
	}

}
