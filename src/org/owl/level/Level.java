package org.owl.level;

import java.util.ArrayList;

import org.owl.gamestate.*;
import org.owl.physics.Physics;

public class Level {
	
	private ArrayList<Entity> entities;
	private ChunkManager chunkManager;
	private Physics physics;
	
	public Level(Physics physics, Player player, int width){
		init(player, width);
		this.physics = physics;
	}
	
	public void init(Player player, int width){
		chunkManager = new ChunkManager(player, width);
		entities = chunkManager.getEntities();
	}
	
	public void update(float delta){
		chunkManager.updateChunks(physics, delta);
		entities = chunkManager.getEntities();
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public int[] getChunkHeights(){
		ArrayList<Chunk> chunks = chunkManager.getChunks();
		int[] chunkHeights = new int[chunks.size()];
		for(int i=0;i<chunkHeights.length;i++) {
			chunkHeights[i] = chunks.get(i).getHeight();
		}
		return chunkHeights;
	}

}
