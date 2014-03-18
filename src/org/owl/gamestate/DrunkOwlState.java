package org.owl.gamestate;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.owl.animations.Animations;
import org.owl.gamestate.enemy.Archer;
import org.owl.gamestate.enemy.Arrow;
import org.owl.gamestate.enemy.Branch;
import org.owl.level.Level;
import org.owl.physics.Physics;

public class DrunkOwlState {

	private Player player;
	private ArrayList<Entity> entities;
	private Physics physics;
	public static int width;
	private Camera camera;
	private Image bgImage;
	private Image bgFarImage;
	private boolean pause;
	private Level level;
	private int score;
	private int highscore;
	
	public DrunkOwlState() throws SlickException {
		init();
	}
	
	public void reload() throws SlickException {
		init();
	}
	
	public boolean isPaused(){
		return pause;
	}
	
	public void togglePause() {
		pause = !pause;
	}
	
	private void init() throws SlickException {
		this.physics = new Physics();
		this.score = 0;
		
		player = new Player(new Vector2f(200, 600), new Vector2f(0, 0), 
				Animations.getAnimation(Animations.PLAYER_DEFAULT));
		width = 1024;
		level = new Level(physics, player, width);
		camera = new Camera(new Vector2f(0,0), new Vector2f(0,0), width);
		bgImage = new Image("res/bg_front.png");
		bgFarImage = new Image("res/bg_far.png");
		entities = new ArrayList<Entity>();
	}
	


	public void drawEntities(Graphics g, Camera camera) {
		for (Entity e:entities) {
			e.draw(g, camera);
			
			if (e.getShape()!= null){
				Shape s = e.getShape();
				Rectangle tempShape = new Rectangle((int)s.getX(), (int)s.getY()-camera.getPosition().y, (int)s.getWidth(), (int)s.getHeight());
//				g.draw(e.getShape());
//				g.draw(tempShape);
			}
		}
	}

	public void update(float delta) throws SlickException {
		//if paused do nothing
		if (pause) {
			return;
		}
		
		updateScore();
		
		//update gravity scaled to delta
		player.update(physics, delta);
		camera.update(getPlayer().getPosition(), player.isSwoosh());
		
		//Update level
		level.update(delta);
		entities = level.getEntities();
		physics.handleCollisions(player, entities);
	}

	private void updateScore() {
		if ((int)-player.position.y/10 > score) score = (int) -player.position.y/10;
		
	}

	public Player getPlayer(){
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}


	public int getWidth() {
		return width;
	}


	public Camera getCamera() {
		return camera;
	}


	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	//used for debugging and stuff remove in the end
	public Level getLevel(){
		return this.level;
	}

	public int getScore() {
		return score;
	}
	
	public Image getBgImage() {
		return bgImage;
	}

	public Image getFarBgImage() {
		return bgFarImage;
	}
	
}
