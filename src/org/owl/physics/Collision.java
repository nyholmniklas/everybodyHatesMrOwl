package org.owl.physics;

import org.owl.gamestate.Entity;
import org.owl.gamestate.Player;

public class Collision {

	public void handle(Player player, Entity e) {
		if (e.getShape() == null) return;
		if (player.getShape().intersects(e.getShape())) {
			System.out.println("HIT");
			player.dealDamage(e.getDamage());
		}
	}

}
