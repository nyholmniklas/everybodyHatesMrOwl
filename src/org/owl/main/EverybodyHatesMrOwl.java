package org.owl.main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
           


public class EverybodyHatesMrOwl extends StateBasedGame{
	public static final int TITLESTATE = 0;
    public static final int GAMEPLAYSTATE = 1;
    public static final int INFOSTATE = 2;
    public static final int PAUSESTATE  =  4;
	public static final int HIGH_SCORE_STATE = 5;

	    public EverybodyHatesMrOwl() throws SlickException  {
	        super("DrunkOwl");
	        this.addState(new TitleState(TITLESTATE));
	        this.addState(new GamePlayState(GAMEPLAYSTATE));
	        this.addState(new InfoState(INFOSTATE));
	        this.addState(new PauseState(PAUSESTATE, (GamePlayState) this.getState(GAMEPLAYSTATE)));
	        this.addState(new HighScoreState(HIGH_SCORE_STATE));
	        
	    } 
	 
	    public static void main(String[] args) throws SlickException     {
	        AppGameContainer app = new AppGameContainer(new ScalableGame(new EverybodyHatesMrOwl(), 1024, 768));
	        app.setDisplayMode(1024, 768,false);  
	        app.setFullscreen(true);
	         	         app.setShowFPS(false);
	         app.setTargetFrameRate(60);
	         app.setVSync(true);
	         app.setMouseGrabbed(true);
//	         app.setMaximumLogicUpdateInterval(50);          
	         app.start();
	         
	    }
	 
	    @Override
	    public void initStatesList(GameContainer gameContainer) throws SlickException {
	    	getState(TITLESTATE).init(gameContainer, this);
	    	getState(GAMEPLAYSTATE).init(gameContainer, this);
	    	getState(PAUSESTATE).init(gameContainer, this);
	    	getState(TITLESTATE).init(gameContainer, this);
	    	getState(INFOSTATE).init(gameContainer, this);
	    	getState(HIGH_SCORE_STATE).init(gameContainer, this);
	    }
		
}
	

