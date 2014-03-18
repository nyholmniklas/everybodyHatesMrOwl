package org.owl.gamestate;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.audio.Sounds;
import org.owl.physics.Physics;
import org.owl.utils.Timer;

public class Player extends Entity{
	private boolean boost;
	private float flightAcceleration;
	private float maxFlySpeed;
	private float horizontalSpeed;
	private float maxHorizontalSpeed;
	private float horizontalResetSpeed;
	private float fallSpeed;
	private float maxFallSpeed;
	private float verticalResetSpeed;
	private int HP;
	private boolean paralyzed;
	private boolean swoosh;
	Timer paralyzedTimer;
	
	public Player(Vector2f position, Vector2f vector, Animation animation) throws SlickException {
		super(position, vector, animation);
		
		maxFlySpeed = -2.2f;
		flightAcceleration = -0.02f;
		boost = false;
		horizontalSpeed = 0.1f;
		maxHorizontalSpeed = 2.1f;
		horizontalResetSpeed = 0.01f;
		fallSpeed = 0.09f;
		maxFallSpeed = 4.3f;
		verticalResetSpeed = -0.04f;
		HP = 3;
		paralyzed = false;
		setAnimation(Animations.getAnimation(Animations.PLAYER_DEFAULT));
	}
	;
	public void setBoost(boolean boost) {
		this.boost = boost;
	}

	
	//TODO WHY IS FLY NOT AS CLEAR AS MOVE LEFT/RIGHT!?!
	public void fly(float delta) {
		if (getVector().getY() > maxFlySpeed){
			
			getVector().add(new Vector2f(0, flightAcceleration).scale(delta));
		}
		if (getVector().getY() < maxFlySpeed) getVector().set(getVector().getX(), maxFlySpeed);
	}

	public void moveLeft(float delta) {
		if (getVector().x > -maxHorizontalSpeed)move(new Vector2f(-horizontalSpeed, 0).scale(delta));
	}

	public void moveRight(float delta) {
		if (getVector().x < maxHorizontalSpeed)move(new Vector2f(horizontalSpeed, 0).scale(delta));
	}
	
	private void move(Vector2f vector) {
		getVector().add(vector);
		
	}
	
	//slowHorizontal tends the x vector towards 0 and y towards grav if it is above and not "moving down"
	public void slow(float delta, Physics physics) {
		//slow horizontal
		if (getVector().x > -horizontalResetSpeed && getVector().x < horizontalResetSpeed) setVector(new Vector2f(0, getVector().y));
		if (getVector().x < -horizontalResetSpeed) getVector().add(new Vector2f(horizontalResetSpeed, 0).scale(delta));
		else if (getVector().x > horizontalResetSpeed) getVector().add(new Vector2f(-horizontalResetSpeed, 0).scale(delta));
		
		//tend fast fall towards gravity
		if (getVector().y>physics.getMaxFall().y) getVector().add(new Vector2f(0, verticalResetSpeed).scale(delta));
		
	}

	public void moveDown(float delta) {
		if (getVector().y < maxFallSpeed)move(new Vector2f(0, fallSpeed).scale(delta));
	}
	
	public void setSwoosh(boolean swoosh) {
		this.swoosh = swoosh;
	}

	public void update(Physics physics, float delta) throws SlickException {
		if (paralyzed) {
			if (paralyzedTimer.isDone()) paralyzed = false;
		}
		physics.applyGravity(vector, delta);
		updateShape();
		//update position based on direction scaled to delta
//		System.out.println(getVector().y);
		getPosition().add(new Vector2f((getVector().x*delta), (getVector().y*delta)));
		slow(delta, physics);
		
		if (boost) setAnimation(Animations.getAnimation(Animations.PLAYER_FLYING));
		else setAnimation(Animations.getAnimation(Animations.PLAYER_DEFAULT));
		
		if (paralyzed) setAnimation(Animations.getAnimation(Animations.PLAYER_HIT));
		else if (swoosh) setAnimation(Animations.getAnimation(Animations.SWOOSH));
		
		if (swoosh) {
			Random random = new Random();
			position.x += random.nextInt(20) -10;
			
		}
		
		if(position.x <0) position.set(0, position.y);
		else if (position.x+animation.getWidth() > DrunkOwlState.width) position.set(DrunkOwlState.width-animation.getWidth(), position.y);
	}
	
	//return true if player dead
	public boolean dealDamage(int damage) {
		//if health
		if (this.HP <3 && damage < 0) {
			this.HP -= damage;
		}
		//if damage
		if (damage > 0 && !paralyzed) {
			this.HP -= damage;
			Sounds.playSound(Sounds.PAIN);
			vector = new Vector2f(0,0);        
			paralyze();
		}
		if (this.HP <= 0) return true;
		else return false;
	}
	
	private void paralyze() {
		paralyzedTimer = new Timer(2000);
		paralyzed = true;
	}
	
	public boolean isSwoosh(){
		return swoosh;
	}
	
	@Override
	public int getDamage() {
		return 0;
	}
	
	@Override
	public void setShape() {
		int wingMargin = (int)((float)this.animation.getWidth()*(float)(5f/18f));
		int shapeWidth = this.animation.getWidth()-(wingMargin*2);
		this.shape = new Rectangle(position.x+wingMargin*2   , position.y+this.animation.getHeight()/4, shapeWidth, this.animation.getHeight()/2);
	}
	
	@Override
	public void updateShape() {
		int wingMargin = this.animation.getWidth()/4;
		if (this.shape == null) return;
		this.shape.setX(position.x+wingMargin*2);
		this.shape.setY(position.y+this.animation.getHeight()/4);
		this.shape.setCenterX(shape.getX());
		this.shape.setCenterY(shape.getY());
	}
	
	public int getHP() {
		return this.HP;
	}
	public boolean isParalyzed() {
		return paralyzed;
	}
	
	public boolean isDead() {
		if (HP <= 0) return true;
		else return false;
	}
	
}
