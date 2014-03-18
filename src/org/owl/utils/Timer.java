package org.owl.utils;

public class Timer {
	//NOT YET IMPLEMENTS, GENERAL PURPOSE TIMER
	private long timeMs;
	private long startTime;
	private long stopTime;
	
	public Timer(int timeMs) {
		this.timeMs = timeMs;
		reset();
	}
	
	public long getTimeLeft() {
		return stopTime - System.currentTimeMillis(); 
	}
	
	public boolean isDone() {
		if (System.currentTimeMillis() > stopTime) return true;
		else return false;
	}
	
	public void reset() {
		startTime = System.currentTimeMillis();
		stopTime = startTime + this.timeMs;
	}
}
