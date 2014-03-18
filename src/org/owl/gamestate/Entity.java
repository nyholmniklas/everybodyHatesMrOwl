package org.owl.gamestate;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

abstract public class Entity implements Updatable{
	public Animation animation;
	protected Vector2f position;
	private boolean delete;
	protected Shape shape;
	
	protected Vector2f vector;
	
	public Entity(Vector2f position, Vector2f vector, Animation initAnimation) {
		this.position = position;
		this.vector = vector;
		if (initAnimation != null)this.animation = initAnimation;
		this.delete = false;
		setShape();
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
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

	public Animation getAnimation() {
		return animation;
	}
	
	public void draw(Graphics g, Camera camera) {
		if (!(animation == null))g.drawAnimation(animation, position.x+camera.getPosition().x, position.y-camera.getPosition().y);
	}
	
	public void remove() {
		this.delete = true;
	}

	public boolean getDelete() {
		return delete;
	}
	
	public abstract int getDamage();
	
	public void setShape() {
		if (animation == null) return;
		this.shape = new Rectangle(position.x, position.y, this.animation.getWidth(), this.animation.getHeight());
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void updateShape() {
		if (this.shape == null) return;
		this.shape.setX(position.x);
		this.shape.setY(position.y);

	}
}
