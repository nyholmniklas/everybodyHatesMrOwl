package org.owl.gamestate.enemy;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.gamestate.Entity;
import org.owl.physics.Physics;
import org.owl.utils.Timer;

public class Arrow extends Entity{
	private Timer timer;
	private double angle;
	
	public Arrow(Vector2f position, Vector2f vector) throws SlickException {
		super(position, vector, Animations.getAnimation(Animations.ARROW));
		timer = new Timer(20000);

	}

	@Override
	public void update(Physics physics, float delta) throws SlickException {
		physics.applyGravity(vector, delta);
		getPosition().add(new Vector2f((vector.x*delta), (vector.y)*delta));
//		updateShape();
//		this.shape.setCenterX(shape.getX());
//		this.shape.setCenterY(shape.getY());
		setRotation();
		if (timer.isDone()) remove();
	}
	
	@Override
	public void updateShape() {
		if (this.shape == null) return;
		this.shape.setX(position.x);
		this.shape.setY(position.y);
		this.shape.setCenterX(shape.getX());
		this.shape.setCenterY(shape.getY());
	}
	
	public void setRotation(){
		double oldAngle = angle;
		angle = Math.atan2(vector.y, vector.x);
		double angleDifference = angle - oldAngle;
//		System.out.println(angleDifference);
		animation.getCurrentFrame().setRotation((float) Math.toDegrees(angle));

		shape = shape.transform(Transform.createRotateTransform((float)angleDifference));
//		System.out.println(shape.getCenterX());
	}

	@Override
	public int getDamage() {
		remove();
		return 1;
	}
	
}
