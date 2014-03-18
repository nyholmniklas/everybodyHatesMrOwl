package org.owl.gamestate.enemy;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.audio.Sounds;
import org.owl.gamestate.Entity;
import org.owl.gamestate.Player;
import org.owl.physics.Physics;
import org.owl.utils.Timer;

public class Kamikaze extends Entity{
	private Player player;
	private float maxHorizontalSpeed;
	private int moveLag;
	private Timer countDown;
	private Timer explosion;

	public Kamikaze(Vector2f position, Player player) throws SlickException {
		super(position, new Vector2f(0,0), Animations.getAnimation(Animations.KAMIKAZE_LEFT));
		this.player = player;
		this.maxHorizontalSpeed = 1.1f;
		this.moveLag = 50;
	}

	@Override
	public void update(Physics physics, float delta) throws SlickException {
		
		//IF EXPLOSION IS ONGOING AND IS DONE REMOVE THIS ENTITY
		if (explosion != null) {
			if (explosion.isDone()) remove();
			return;
		}
		
		//LOGIC FOR K-BIRD MOVEMENT(FOLLOWING PLAYER)
		if ((vector.y == 0 && player.getPosition().y < position.y - 80) || (vector.y >0 && position.y > player.getPosition().y)) vector.y = -1.3f;
		else if (vector.y < 0 && position.y < player.getPosition().y - 50) vector.y = 1f;
		if (vector.y != 0) {
			if (position.x < player.getPosition().x - moveLag){
				if (Math.abs(vector.x) < maxHorizontalSpeed){
					vector.x = 1f;
				}
				setAnimation(Animations.getAnimation(Animations.KAMIKAZE_RIGHT));
			}
			else if (position.x > player.getPosition().x + moveLag) {
				if (Math.abs(vector.x) < maxHorizontalSpeed){
					vector.x = -1f;
				}
				setAnimation(Animations.getAnimation(Animations.KAMIKAZE_LEFT));
			}
		}
		if (countDown != null) {
			if (vector.x <0) setAnimation(Animations.getAnimation(Animations.KAMIKAZE_LEFT_TRIGGERED));
			else setAnimation(Animations.getAnimation(Animations.KAMIKAZE_RIGHT_TRIGGERED));
			if (countDown.isDone()) explode();
		}

		getPosition().add(new Vector2f((vector.x*delta), (vector.y)*delta));
	}
	
	@Override
	public void setShape() {
		shape = new Rectangle(position.x-50, position.y-50, animation.getWidth()+100, animation.getHeight()+100);
	}
	
	@Override
	public void updateShape() {
		this.shape.setX(position.x-50);
		this.shape.setY(position.y-50);
	}

	private void explode() throws SlickException {
		Animation explosionAnimation = Animations.getAnimation(Animations.EXPLOSION);
		Sounds.playSound(Sounds.EXPLOSION);
		setPosition(new Vector2f(position.x-(explosionAnimation.getWidth()-animation.getWidth())/2, position.y-(explosionAnimation.getHeight()-animation.getHeight())/2));
		setAnimation(explosionAnimation);
		this.shape = new Rectangle(position.x, position.y, animation.getWidth(), animation.getHeight());
		if (shape.intersects(player.getShape()))player.dealDamage(3);
		explosion = new Timer(300);
	}

	@Override
	public int getDamage() {
		if (countDown == null){
			startCountdown();
			return 0;
		}
		return 0;
	}

	private void startCountdown() {
		Sounds.playSound(Sounds.TICKING);
		countDown = new Timer(2000);
	}
	
	

}
