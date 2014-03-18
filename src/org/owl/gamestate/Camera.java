package org.owl.gamestate;

import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	
	private Vector2f position;
	private Vector2f vector;
	
	private int width;
	
	public Camera(Vector2f position, Vector2f vector, int width) {
		this.position = position;
		this.vector = vector;
		this.width = width;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getVector() {
		return vector;
	}

	public void setVector(Vector2f vector) {
		this.vector = vector;
	}
	
	public void update(Vector2f charPosition, boolean swoosh) {
		position.y = charPosition.y -300;
//		if (swoosh) {
//			Random random = new Random();
//			position.x += random.nextInt(30) - 15;
//			position.y += random.nextInt(15) - 15;
//		}
//		else position.x = 0;
	}
	
	public Vector2f getAdjustedPositionForBackground(int bgHeight, float scale) {
		Vector2f adjustedPosition = new Vector2f(position);
		adjustedPosition.y = adjustedPosition.y*scale % bgHeight;
		return adjustedPosition;
	}

}
