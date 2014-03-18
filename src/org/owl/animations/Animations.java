package org.owl.animations;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Animations {
	public static final int PLAYER_FLYING = 0;
	public static final int PLAYER_DEFAULT = 1;
	public static final int ARROW = 2;
	public static final int PINECONE = 3;
	public static final int BRANCH_LEFT = 4;
	public static final int BRANCH_RIGHT = 5;
	public static final int PLAYER_HIT = 6;
	public static final int KAMIKAZE_LEFT = 7;
	public static final int KAMIKAZE_RIGHT = 8;
	public static final int KAMIKAZE_LEFT_TRIGGERED = 9;
	public static final int KAMIKAZE_RIGHT_TRIGGERED = 10;
	public static final int EXPLOSION = 11;
	public static final int HEART_FULL = 12;
	public static final int HEART_EMPTY = 13;
	public static final int LADYBUG_LEFT = 14;
	public static final int LADYBUG_RIGHT = 15;
	public static final int SWOOSH = 16;
	
	
	private static Animation flyingAnim;
	private static Animation default_Anim;
	private static Animation arrow;
	private static Animation pinecone;
	private static Animation branch_left;
	private static Animation branch_right;
	private static Animation player_hit;
	private static Animation kamikaze_left;
	private static Animation kamikaze_right;
	private static Animation kamikaze_left_triggered;
	private static Animation kamikaze_right_triggered;	
	private static Animation explosion;
	private static Animation heartFullAnim;
	private static Animation heartEmptyAnim;
	private static Animation ladybug_left;
	private static Animation ladybug_right;
	private static Animation swooshAnim;
	
	ArrayList<Animation> animations;
	
	public static void loadAnimations() throws SlickException {

//		System.out.println("HORIZONTAL COUNTS: "+sheetFlying.getHorizontalCount());
//		flyingAnim = new Animation();
//		for (int frame=0;frame<sheetFlying.getHorizontalCount();frame++) {
//			flyingAnim.addFrame(sheetFlying.getSprite(frame,0), 300);
//		}

		
		SpriteSheet sheetFlying = new SpriteSheet("res/player_flying.png", 180, 188);
//		SpriteSheet sheet = new SpriteSheet("res/player_default.png", 180, 188);
		default_Anim = new Animation();
		default_Anim.addFrame(sheetFlying.getSprite(0, 0),300);
		flyingAnim = new Animation(sheetFlying, 220);
//		default_Anim.setAutoUpdate(true);
		flyingAnim.setAutoUpdate(true);
		
		SpriteSheet sheetSwoosh = new SpriteSheet("res/swoosh.png", 180, 188);
		swooshAnim = new Animation(sheetSwoosh, 70);
		swooshAnim.setAutoUpdate(true);
		
		SpriteSheet playerHitSheet = new SpriteSheet("res/player_hit.png", 148, 144);
		player_hit = new Animation();
		player_hit.addFrame(playerHitSheet.getSprite(0, 0),300);
				
		SpriteSheet pineconeSheet = new SpriteSheet("res/pinecone.png", 28, 36);
		pinecone = new Animation();
		pinecone.addFrame(pineconeSheet.getSprite(0, 0),300);
		
		SpriteSheet branchLeftSheet = new SpriteSheet("res/branch_left.png", 413, 122);
		branch_left = new Animation();
		branch_left.addFrame(branchLeftSheet.getSprite(0, 0),300);
		
		SpriteSheet branchRightSheet = new SpriteSheet("res/branch_right.png", 322, 121);
		branch_right = new Animation();
		branch_right.addFrame(branchRightSheet.getSprite(0, 0),300);	
	
		SpriteSheet kamikaze_leftSheet = new SpriteSheet("res/kamikazeleft.png", 103, 89);
		kamikaze_left = new Animation(kamikaze_leftSheet, 220);
		kamikaze_left.setAutoUpdate(true);
		
		SpriteSheet kamikaze_rightSheet = new SpriteSheet("res/kamikazeright.png", 103, 89);
		kamikaze_right = new Animation(kamikaze_rightSheet, 220);
		kamikaze_right.setAutoUpdate(true);

		SpriteSheet kamikaze_right_t_Sheet = new SpriteSheet("res/kamikazerighttriggered.png", 103, 89);
		kamikaze_right_triggered = new Animation(kamikaze_right_t_Sheet, 220);
		kamikaze_right_triggered.setAutoUpdate(true);
		
		SpriteSheet kamikaze_left_t_Sheet = new SpriteSheet("res/kamikazelefttriggered.png", 103, 89);
		kamikaze_left_triggered = new Animation(kamikaze_left_t_Sheet, 220);
		kamikaze_left_triggered.setAutoUpdate(true);
		
		SpriteSheet explosionSheet = new SpriteSheet("res/explosionbig.png", 280, 310);
		explosion = new Animation(explosionSheet, 100);
		explosion.setAutoUpdate(true);
		
		SpriteSheet heartFull = new SpriteSheet("res/heart_full.png", 61, 57);
		heartFullAnim = new Animation(heartFull, 100);
		
		SpriteSheet heartEmpty = new SpriteSheet("res/heart_empty.png", 61, 57);
		heartEmptyAnim = new Animation(heartEmpty, 100);
//		explosion.setLooping(false);
		
		SpriteSheet ladybugSheet = new SpriteSheet("res/ladybug.png", 40, 40);
		ladybug_left = new Animation();
		ladybug_right = new Animation();
		ladybug_left.addFrame(ladybugSheet.getSprite(0, 0), 300);
		ladybug_left.addFrame(ladybugSheet.getSprite(1, 0), 300);
		ladybug_right.addFrame(ladybugSheet.getSprite(0, 1), 300);
		ladybug_right.addFrame(ladybugSheet.getSprite(1, 1), 300);
		ladybug_left.setAutoUpdate(true);
		ladybug_right.setAutoUpdate(true);
	}
	
	
	public static  Animation getAnimation(int animationType) throws SlickException {
		Animation animation;
		switch (animationType) {
		case PLAYER_FLYING:
			animation = flyingAnim;
			break;
			
		case PLAYER_DEFAULT:
			animation = default_Anim;
			break;
			
		case ARROW:
			animation = new Animation();
			animation.addFrame(new Image("res/arrow.png"), 100);
			break;
			
		case PINECONE:
			animation = pinecone;
			break;
		
		case BRANCH_LEFT:
			animation = branch_left;
			break;
			
		case BRANCH_RIGHT:
			animation = branch_right;
			break;
		
		case PLAYER_HIT:
			animation = player_hit;
			break;
			
		case KAMIKAZE_LEFT:
			animation = kamikaze_left;
			break;
			
		case KAMIKAZE_RIGHT:
			animation = kamikaze_right;
			break;
			
		case KAMIKAZE_RIGHT_TRIGGERED:
			animation = kamikaze_right_triggered;
			break;
			
		case KAMIKAZE_LEFT_TRIGGERED:
			animation = kamikaze_left_triggered;
			break;
			
		case EXPLOSION:
			animation = explosion;
			break;
			
		case HEART_FULL:
			animation = heartFullAnim;
			break;
			
		case HEART_EMPTY:
			animation = heartEmptyAnim;
			break;
			
		case LADYBUG_LEFT:
			animation = ladybug_left;
			break;
		
		case LADYBUG_RIGHT:
			animation = ladybug_right;
			break;
			
		case SWOOSH:
			animation = swooshAnim;
			break;
			
		default:
			return null;
		}
		return animation;
	}
}
