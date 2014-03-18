package org.owl.gamestate.enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.owl.gamestate.Camera;
import org.owl.gamestate.Entity;
import org.owl.level.Chunk;
import org.owl.main.GamePlayState;
import org.owl.physics.Physics;
import org.owl.utils.Timer;

public class Archer extends Entity{
	int difficulty;
	Timer interval;
	private Chunk chunk;
	
	public Archer(Vector2f position, Chunk chunk) {
		super(position, new Vector2f(0,0), null);
		interval = new Timer(1);
		this.chunk = chunk;
	}
	
	public void shoot() throws SlickException {
		//problem here
		chunk.addTempEntity(new Arrow(new Vector2f(-160,position.y), new Vector2f(1.3f, -6)));
	}
	

	@Override
	public void update(Physics physics, float delta) throws SlickException {
		if (interval.isDone()) {
			shoot();
			interval = new Timer(3000);
		}
		shape = null;
	}

	@Override
	public int getDamage() {
		return 0;
	}

	
}
