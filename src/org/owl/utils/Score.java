package org.owl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Score implements Comparable{

	private int score;
	private String name;
	
	public Score(int score, String name) {
		this.score = score;
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static ArrayList<Score> parseScores() {
		ArrayList<Score> scores = new ArrayList<Score>();
		File file = new File("res/highscores.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNext()){

				String line = scanner.nextLine();
				String[] score = line.split(",");
				scores.add(new Score(Integer.parseInt(score[0]), score[1]));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scores;
	}
	
	public static void addScore(Score score) throws IOException {
		File file = new File("res/highscores.txt");
		try {
			ArrayList<Score> scores = parseScores();
			scores.add(score);
			Collections.sort(scores);
			FileWriter writer = new FileWriter(file);
			for (int i =0;i<scores.size();i++) {
				if (i!=0) writer.write("\r\n");
				writer.write(scores.get(i).getScore()+","+scores.get(i).getName());
			}
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Score getHighScore(){
		ArrayList<Score> scores = parseScores();
		Collections.sort(scores);
		return scores.get(0);
	}
	
	@Override
	public int compareTo(Object s) {
		Score otherScore = (Score)s;
		if (otherScore.getScore() < score) return -1;
		else return 1;
	}
}
