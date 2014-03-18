package org.owl.physics;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;
import org.owl.gamestate.Entity;
import org.owl.gamestate.Player;

public class Physics {
	private Vector2f gravity;	
	private Vector2f maxFall;
	private Collision collision;
	
	public Physics() {
		//0.7
			gravity = new Vector2f(0, 0.01f);
			maxFall = new Vector2f(0, 2f);
			collision = new Collision();
		}

	public Vector2f getGravity() {
		return gravity;
	}

	public void setGravity(Vector2f gravity) {
		this.gravity = gravity;
	}

	public Vector2f getMaxFall() {
		return maxFall;
	}

	public void setMaxFall(Vector2f maxFall) {
		this.maxFall = maxFall;
	}
	
	public Vector2f applyGravity(Vector2f v, float delta) {
		if (v.y<maxFall.y) {
			v.add(new Vector2f(0f,gravity.y*delta));
		}
		return v;
	}
	
	public void handleCollisions(Player player, ArrayList<Entity> entities) {
		for (Entity e:entities) {
			collision.handle(player, e);
		}
	}
}
