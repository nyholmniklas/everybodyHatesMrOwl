package org.owl.main;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import org.owl.utils.Score;

@SuppressWarnings("deprecation")
public class HighScoreState extends BasicGameState {
	private int ID;
	private TrueTypeFont scoreFont;
	private ArrayList<Score> scores;
	private TextField textField;
	private boolean prompting;
	private int newScore;
	private Image background;
	
	public HighScoreState(int ID) {
		this.ID = ID;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1, int score) {
		try {
			newScore = score;
			init(arg0, arg1);

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (scores.size() >= 10 && score <= scores.get(9).getScore()) {
			prompting = false;
			return;
		}
		textField = new TextField(arg0, scoreFont, 400, 500, 400, 70);
		textField.setMaxLength(10);
//		textField.setBackgroundColor(Color.white);
		textField.setFocus(true);
		prompting = true;

	}
	
	private void addNewScore(int score, String name) {
		if (scores.size() < 10 || score > scores.get(9).getScore())
			try {
				Score.addScore(new Score(score, name));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		scores = Score.parseScores();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		prompting = false;
		
		try {
			background = new Image("res/owlgameoverscreen.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//load font
		try {
				InputStream inputStream = ResourceLoader.getResourceAsStream("res/score_font.ttf");
				Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
				awtFont2 = awtFont2.deriveFont(18f); // set font size
				scoreFont = new TrueTypeFont(awtFont2, true);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		
		scores = Score.parseScores();
		Collections.sort(scores);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
//		System.out.println("stuck");
//		g.setBackground(Color.red);
		g.drawImage(background, 0, 0);
		g.setFont(scoreFont);
		g.setColor(new Color(Color.white));
		for (int i = 0; i<10; i++) {
			if (scores.size() <= i) break;
			Score score = scores.get(i);
			g.drawString(score.getName(), 200, 250+(25*i));
			g.drawString(Integer.toString(score.getScore()), 600, 250+(25*i));
			if (prompting) {
				textField.render(arg0, g);	
			}	
		}
		
		g.setColor(Color.black);
		g.drawString("Congratulations! Your score: "+Integer.toString(newScore), 300, 150);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(prompting && arg0.getInput().isKeyPressed(Input.KEY_ENTER)){
			addNewScore(newScore, textField.getText());
			prompting = false;
		}
		if(arg0.getInput().isKeyPressed(Input.KEY_ENTER)){
			arg1.getState(EverybodyHatesMrOwl.TITLESTATE).init(arg0, arg1);
			arg1.enterState(EverybodyHatesMrOwl.TITLESTATE);
		}
	}

	@Override
	public int getID() {
		return ID;
	}
}