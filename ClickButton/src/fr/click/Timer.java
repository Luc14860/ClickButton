package fr.click;

public class Timer {
	long startTicks;
	long pausedTicks;
	boolean paused;
	boolean started;
	
	public Timer() {
		startTicks = 0;
		pausedTicks = 0;
		paused = false;
		started = false;
	}
	
	public void start() {
		started = true;
		paused = false;
		startTicks = System.currentTimeMillis();
	}
	
	public void stop() {
		started = false;
		paused = false;
	}
	
	public void pause() {
		if (started == true && paused == false) {
			paused = true;
			pausedTicks = System.currentTimeMillis() - startTicks;
		}
	}
	
	public void unpaused() {
		if (paused == true) {
			paused = false;
			startTicks = System.currentTimeMillis() - pausedTicks;
			pausedTicks = 0;
		}
	}
	
	public int get_ticks() {
		if (started == true) {
			if (paused == true) {
				return (int) pausedTicks;
			} else {
				return (int) (System.currentTimeMillis() - startTicks);
			}
		}
		
		return 0;
	}
	
	public boolean is_started() {
		return started;
	}
	
	public boolean is_paused() {
		return paused;
	}
}
