package fr.click;

import fr.click.gui.Frame;
import fr.click.sound.Sound;

public class Game implements Runnable {
	
	public static boolean buttonHaveBeenClicked = true;
	public static int score;
	private static int buttonSize = 100;
	// private Timer timer = new Timer();
	
	/* public Game() {
		timer.start();
	} */
	
	public void run() {
		
		/* if (Frame.clickButton.isVisible())
		{
			
		}
		else
		{
			
		}
		
		if (buttonHaveBeenClicked)
		{
			buttonHaveBeenClicked = false;
			if (timer.get_ticks() == 1000 - (score * 100))
			{
				buttonHaveBeenClicked = false;
			}
			
			if((score >= 10) && (buttonSize >= 20)) {
				buttonSize--;
			}
			
			Frame.clickButton.setBounds((int)(Math.random() * (Frame.frame.getWidth() - buttonSize)), (int)(Math.random() * (Frame.frame.getHeight() - buttonSize)) + 0, buttonSize, buttonSize);
			Frame.clickButton.setVisible(true);
			
		}
		else
		{
			
			Frame.frame.getWidth() + Frame.frame.getHeight();
		}
		
		new Thread(new Sound()).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} */
		
		// rudelune code /\ Luc14860 code \/
		
		new Thread(new Sound()).start();
		
		while(buttonHaveBeenClicked) {
			buttonHaveBeenClicked = false;
			if(1000 - (score * 100) > 0) {
				try {
					Thread.sleep(1000 - (score * 100)); //Avant de faire apparaitre le bouton
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if((score >= 10) && (buttonSize >= 20)) {
				buttonSize--;
			}
			
			Frame.clickButton.setBounds((int)(Math.random() * (Frame.frame.getWidth() - buttonSize)), (int)(Math.random() * (Frame.frame.getHeight() - buttonSize)) + 0, buttonSize, buttonSize);
			Frame.clickButton.setVisible(true);
			
			try {
				Thread.sleep((Frame.frame.getWidth() + Frame.frame.getHeight())); //Avant de cliquer dessus
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		OnlineManager.setScore(Frame.usernameField.getText(), score);
		
		Frame.clickButton.setEnabled(false);
		
		Frame.loseMessage.setVisible(true);
		Frame.quitButton.setVisible(true);
		Frame.quitButton.setBounds((Frame.frame.getWidth() / 2) - 100, (Frame.frame.getHeight() / 2) + 30, 200, 50);
	}
}
