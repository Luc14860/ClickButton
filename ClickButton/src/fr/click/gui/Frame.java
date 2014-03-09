package fr.click.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.click.Timer;

public class Frame {
	
	public final static JFrame frame = new JFrame();
	private final static JPanel panel = new JPanel();
	private final static JLabel title = new JLabel("ClickButton");
	private final static JButton startButton = new JButton("Start");
	public final static JButton clickButton = new JButton("Click!");
	public final static JLabel scoreDisplayer = new JLabel("0");
	public final static JLabel loseMessage = new JLabel("You lose !");
	public final static JButton quitButton = new JButton("Quit");
	
	public static void open() {
		frame.setSize(frame.getToolkit().getScreenSize().width, frame.getToolkit().getScreenSize().height);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setLayout(null);
		
		panel.setBackground(Color.CYAN);
		panel.add(title);
		panel.add(startButton);
		panel.add(quitButton);
		
		title.setBounds((frame.getWidth() / 2) - 30, (frame.getHeight() / 2) - 75, 200, 50);
		
		startButton.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) - 25, 200, 50);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.removeAll();
				panel.add(clickButton);
				panel.add(scoreDisplayer);
				panel.add(loseMessage);
				panel.add(quitButton);
				quitButton.setVisible(false);
				panel.repaint();
				
				Thread timerThread = new Thread(new Timer());
				timerThread.start();
			}
		});
		
		clickButton.setVisible(false);
		clickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Timer.buttonHaveBeenClicked = true;
				
				clickButton.setVisible(false);
				panel.repaint();
				
				Timer.score++;
				Frame.scoreDisplayer.setText("" + Timer.score);
			}
		});
		
		scoreDisplayer.setBounds(0, 0, 500, 20);
		
		loseMessage.setVisible(false);
		loseMessage.setBounds((frame.getWidth() / 2) - 60, (frame.getHeight() / 2) - 25, 200, 50);
		
		quitButton.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) + 30, 200, 50);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		frame.setVisible(true);
	}
}
