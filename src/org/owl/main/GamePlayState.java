package org.owl.main;
 import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import org.owl.animations.Animations;
import org.owl.audio.Sounds;
import org.owl.controls.Controls;
import org.owl.gamestate.Camera;
import org.owl.gamestate.DrunkOwlState;
import org.owl.gamestate.Player;
import org.owl.gamestate.enemy.Archer;
import org.newdawn.slick.TrueTypeFont;


/** GamePlayState is where the main game loop is located. It extends BasicGameState which is part of slick
 * The methods
 * init(), 
 * render() and
 *  update() 
 *  are called consecutively about 60 times per second(framerate). 
 *  The update() method receives int d, also know as delta, which is the number of milliseconds 
 *  that passed since the last update. This is to compensate for frame rate variation in other methods that are called from within update()
 **/

@SuppressWarnings("deprecation")
public class GamePlayState extends BasicGameState {

	public static DrunkOwlState mainDrunkOwlState;
	private int ID;
	private TrueTypeFont scoreFont;
	
	public GamePlayState(int ID) {
		this.ID = ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		Animations.loadAnimations();
		Sounds.loadSounds();
		mainDrunkOwlState = new DrunkOwlState();
		
		//load font
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/score_font.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(50f); // set font size
			scoreFont = new TrueTypeFont(awtFont2, true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		
		Camera camera = mainDrunkOwlState.getCamera();	
		
		//Draw background image
		Image bgFar = mainDrunkOwlState.getFarBgImage();
		Vector2f adjustedCameraPosition = camera.getAdjustedPositionForBackground(bgFar.getHeight(), 0.2f); 
		g.drawImage(bgFar, adjustedCameraPosition.x, -adjustedCameraPosition.y + bgFar.getHeight());
		g.drawImage(bgFar, adjustedCameraPosition.x, -adjustedCameraPosition.y);
		g.drawImage(bgFar, adjustedCameraPosition.x, -adjustedCameraPosition.y - bgFar.getHeight());
		Image bg = mainDrunkOwlState.getBgImage();
		adjustedCameraPosition = camera.getAdjustedPositionForBackground(bg.getHeight(), 0.5f); 
		g.drawImage(bg, adjustedCameraPosition.x, -adjustedCameraPosition.y + bg.getHeight());
		g.drawImage(bg, adjustedCameraPosition.x, -adjustedCameraPosition.y);
		g.drawImage(bg, adjustedCameraPosition.x, -adjustedCameraPosition.y - bg.getHeight());
		
		Player player = mainDrunkOwlState.getPlayer();
		
		//DEBUGGING INFO
		g.setColor(Color.red);
		scoreFont.drawString(20, 690, Integer.toString((int)mainDrunkOwlState.getScore()), Color.white);
//		g.drawString("Player y:"+player.getPosition().y, 700, 65);
//		g.drawString("Player velX:"+player.getVector().x, 700, 80);
//		g.drawString("Player velY:"+player.getVector().y, 700, 95);
//		g.drawString("Health: "+player.getHP(), 15,50);
//		g.draw(player.getShape());
		//Draw chunk limits
//		int[] chunkHeights = mainDrunkOwlState.getLevel().getChunkHeights();
//		for (int i=0;i<chunkHeights.length;i++){
//			g.setColor(Color.blue);
//			g.setLineWidth(5);
//			g.drawLine(0, chunkHeights[i]-camera.getPosition().getY(), 4000, chunkHeights[i]-camera.getPosition().getY());
//		}
		
		//Draw player
		player.draw(g, camera);
		//Draw entities
		mainDrunkOwlState.drawEntities(g, camera);
		//Draw health
		for (int i = 0;i<3;i++){
			if (i<player.getHP()) g.drawAnimation(Animations.getAnimation(Animations.HEART_FULL), 800+61*i, 690);
			else g.drawAnimation(Animations.getAnimation(Animations.HEART_EMPTY), 800+61*i, 690);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int d)
			throws SlickException {
//		System.out.println(delta);
		if(mainDrunkOwlState.isPaused()) {
			game.enterState(EverybodyHatesMrOwl.PAUSESTATE);
		}
		//check if player is dead
		if (mainDrunkOwlState.getPlayer().isDead()) {
			((HighScoreState)game.getState(EverybodyHatesMrOwl.HIGH_SCORE_STATE)).init(container, game, mainDrunkOwlState.getScore());
			game.enterState(EverybodyHatesMrOwl.HIGH_SCORE_STATE);
		}
		float delta = (float)d/5;
		//Update state based on controls & then general update based on world
		mainDrunkOwlState = Controls.updateDrunkOwlState(mainDrunkOwlState, 
				container.getInput(), delta);
		mainDrunkOwlState.update(delta);
	}

	@Override
	public int getID() {
		return ID;
	}
}
