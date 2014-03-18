package org.owl.gamestate;

import org.newdawn.slick.SlickException;
import org.owl.physics.Physics;

public interface Updatable {
	public void update(Physics physics, float delta) throws SlickException;
	public void remove();
}
