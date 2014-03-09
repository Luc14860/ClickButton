package fr.click;

import fr.click.gui.Frame;

public class Timer implements Runnable {
	
	public static boolean buttonHaveBeenClicked = true;
	public static long score;
	
	public void run() {
		while(buttonHaveBeenClicked) {
			buttonHaveBeenClicked = false;
			
			if(3000 - (score * 10) > 0) {
				try {
					Thread.sleep(3000 - (score * 10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Frame.clickButton.setBounds((int)(Math.random() * ((Frame.frame.getWidth() - 100) - 0)) + 0, (int)(Math.random() * ((Frame.frame.getHeight() - 100) - 0)) + 0, 100, 100);
			Frame.clickButton.setVisible(true);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Frame.clickButton.setEnabled(false);
		
		Frame.loseMessage.setVisible(true);
		Frame.quitButton.setVisible(true);
		Frame.quitButton.setBounds((Frame.frame.getWidth() / 2) - 100, (Frame.frame.getHeight() / 2) + 30, 200, 50);
	}
}
