package org.owl.level;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.owl.gamestate.DrunkOwlState;
import org.owl.gamestate.Entity;
import org.owl.gamestate.Player;
import org.owl.physics.Physics;

class ChunkManager {

	private HashMap<Integer, Chunk> chunks;
	private HashMap<Integer, Chunk> activeChunks;
	private int currentChunkID;
	private Player player;
	private int width;
	
	public ChunkManager(Player player, int width) {
		chunks = new HashMap<Integer, Chunk>();
		activeChunks = new HashMap<Integer, Chunk>();
		this.player = player;
		this.width = width;
		init();
	}
	
	private void init() {
		updateCurrentChunkId();
		chunks.put(currentChunkID, new Chunk(currentChunkID, width, player));
		activeChunks.put(currentChunkID, chunks.get(currentChunkID));
		addNewChunks();
		setActiveChunks();
		
	}
	
	public void updateChunks(Physics physics, float delta) {
		updateCurrentChunkId();
		addNewChunks();
		setActiveChunks();
		
		
		Iterator iterator = activeChunks.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Chunk c = (Chunk) entry.getValue();
			c.update(physics, delta);
		}
	}

	private void updateCurrentChunkId() {
		int oldID = this.currentChunkID;
		this.currentChunkID = (int) (player.getPosition().y / Chunk.HEIGHT);
		if (oldID != currentChunkID) System.out.println("Entered chunk id " + this.currentChunkID);
		
	}

	private void addNewChunks() {
		for (int heightChunks = -10;heightChunks<10;heightChunks++) {
			int tempId = heightChunks+currentChunkID;
			if (!chunks.containsKey(tempId)) {
				System.out.println("created new chunk with id " + tempId);
				chunks.put(tempId, new Chunk(tempId, width, player));
			}
		}
	}

	private void setActiveChunks() {
		// TODO Optimize this!! Do not clear and remake list everytime!!!
		
		activeChunks.clear();
		for (int heightChunks = -5;heightChunks<6;heightChunks++) {
			int tempId = heightChunks+currentChunkID;
			activeChunks.put(tempId, chunks.get(tempId));
		}
		
	}

	public ArrayList<Entity> getEntities() {
		ArrayList<Entity> e = new ArrayList<Entity>();
		Iterator iterator = activeChunks.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Chunk c = (Chunk) entry.getValue();
			e.addAll(c.getEntities());
		}
		return e;
	}
	
	public ArrayList<Chunk> getChunks(){
		ArrayList<Chunk> returnChunks = new ArrayList<Chunk>();
		returnChunks.addAll(chunks.values());
		return returnChunks;
	}
	
}
