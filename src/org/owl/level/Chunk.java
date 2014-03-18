package org.owl.level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.owl.gamestate.DrunkOwlState;
import org.owl.gamestate.Entity;
import org.owl.gamestate.Player;
import org.owl.gamestate.enemy.Archer;
import org.owl.gamestate.enemy.Branch;
import org.owl.gamestate.enemy.Kamikaze;
import org.owl.gamestate.enemy.Pinecone;
import org.owl.gamestate.powerup.Ladybug;
import org.owl.physics.Physics;

public class Chunk {
	
	public static final int HEIGHT = 1200;
	private int chunkID;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> tempEntities;
	private int width;
	private Player player;
	
	@SuppressWarnings("unchecked")
	public Chunk(int chunkID, int width, Player player) {
		this.chunkID =chunkID;
		this.width = width;
		this.player = player;
		entities = new ArrayList<Entity>();
		try {
			spawnChunckEntities();
		} catch (SlickException e) {
			e.printStackTrace();
		}
//		try {
//			entities.addAll(SpawnMachine.getEntities(this));
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		tempEntities = (ArrayList<Entity>) entities.clone();
		tempEntities = new ArrayList<Entity>();
	}
	
	private void spawnChunckEntities() throws SlickException {
		Random random = new Random();
		int diff = -chunkID;
		//SPAWN LADYBUG
		if (random.nextInt(100)< 30)addEntity(new Ladybug((new Vector2f(new Random().nextInt(width), chunkID*HEIGHT+300)), player));
		//SPAWN ARCHER
		if (random.nextInt(100) < diff)addEntity(new Archer(new Vector2f(0, chunkID*HEIGHT), this));
		if (random.nextInt(75) < diff)addEntity(new Archer(new Vector2f(0, chunkID*HEIGHT+300), this));
		//SPAWN PINECONE
		if (random.nextInt(1) > 0)addEntity(new Pinecone(new Vector2f(new Random().nextInt(width), chunkID*HEIGHT)));
		if (random.nextInt(5) > 3)addEntity(new Pinecone(new Vector2f(new Random().nextInt(width), chunkID*HEIGHT)));
		if (random.nextInt(100) < diff)addEntity(new Pinecone(new Vector2f(new Random().nextInt(width), chunkID*HEIGHT)));
		//SPAWN BRANCH
		if (random.nextInt(5) > 3)addEntity(new Branch(new Vector2f(random.nextInt(2), chunkID*HEIGHT)));
		if (random.nextInt(5) > 2)addEntity(new Branch(new Vector2f(random.nextInt(2), chunkID*HEIGHT+random.nextInt(50))));
		//SPAWN KAMIKAZE
		if (random.nextInt(100)< 50)addEntity(new Kamikaze(new Vector2f(new Random().nextInt(width), chunkID*HEIGHT), player));
	}

	public void update(Physics physics, float delta){
		LinkedList<Integer> toRemove = new LinkedList<Integer>();
		for (int i =0;i < entities.size();i++){
			Entity e = entities.get(i);
			try {
				e.update(physics, delta);
				e.updateShape();
				if (e.getAnimation() != null)e.getAnimation().update((long) delta);
				if (e.getDelete()) {
					entities.remove(e);
				}
			} catch (SlickException e1) {
				e1.printStackTrace();
			}
		}		
		
		//temp entities is used for arrows etc see method addtempentity below
		entities.addAll(tempEntities);
		tempEntities.clear();
		
//		for (Integer i:toRemove) {
//			System.out.println(i);
//			System.out.println(entities.get(i).getPosition().y);
//			entities.remove(i);
//		}
//		toRemove.clear();
	}
	
	public int getHeight() {
		return HEIGHT*chunkID;
	}
	
	public int getChunkID() {
		return chunkID;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	//used to avoid java.util.ConcurrentModificationException when looping in update() for archer arrows etc
	public void addTempEntity(Entity entity) {
		tempEntities.add(entity);
	}

	public int getWidth() {
		return width;
	}
	
}
