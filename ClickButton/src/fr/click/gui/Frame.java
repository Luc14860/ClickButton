package fr.click.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.click.OnlineManager;
import fr.click.Game;
import fr.click.sound.Sound;

public class Frame {
	
	public final static JFrame frame = new JFrame();
	private final static JPanel panel = new JPanel();
	private final static JLabel title = new JLabel("ClickButton");
	private final static JButton startButton = new JButton("Start");
	private final static JButton onlineRankButton = new JButton("Online Rank");
	private final static JLabel rankTitleLabel = new JLabel("Top 10 score:");
	private static JTable rankTable;
	private final static JButton backButton = new JButton("Back");
	public final static JTextField usernameField = new JTextField();
	private final static JLabel usernameLabel = new JLabel("Username:");
	private final static JPasswordField passwordField = new JPasswordField();
	private final static JLabel passwordLabel = new JLabel("Password:");
	private final static JButton loginButton = new JButton("Login");
	private final static JLabel createAccountLabel = new JLabel("You don't have account ? Create one on http://tc-server.redirectme.net:8080/");
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
		panel.add(onlineRankButton);
		panel.add(quitButton);
		
		title.setBounds((frame.getWidth() / 2) - 30, (frame.getHeight() / 2) - 75, 200, 50);
		
		startButton.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) - 25, 200, 50);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.removeAll();
				panel.add(usernameField);
				panel.add(usernameLabel);
				panel.add(passwordField);
				panel.add(passwordLabel);
				panel.add(loginButton);
				panel.add(createAccountLabel);
				panel.repaint();
			}
		});
		
		onlineRankButton.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) + 30, 200, 50);
		onlineRankButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.removeAll();
				panel.repaint();
				
				String[] title = {"Nickname", "Score"};
				
				rankTable = new JTable(OnlineManager.getRank(), title);
				
				panel.add(rankTitleLabel);
				panel.add(rankTable);
				panel.add(backButton);
				
				rankTable.setBounds((panel.getWidth() / 2) - 75, (panel.getHeight() / 2) - 80, 150, 160);
				rankTable.setEnabled(false);
			}
		});
		
		rankTitleLabel.setBounds((frame.getWidth() / 2) - 75, (frame.getHeight() / 2) - 110, 100, 30);
		
		backButton.setBounds((frame.getWidth() / 2) - 45, (frame.getHeight() / 2) + 90, 100, 30);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.removeAll();
				panel.repaint();
				
				panel.add(title);
				panel.add(startButton);
				panel.add(onlineRankButton);
				panel.add(quitButton);
			}
		});
		
		usernameField.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) - 10, 200, 20);
		
		usernameLabel.setBounds((frame.getWidth() / 2) - 200, (frame.getHeight() / 2) - 10, 200, 20);
		
		passwordField.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) + 30, 200, 20);
		
		passwordLabel.setBounds((frame.getWidth() / 2) - 200, (frame.getHeight() / 2) + 30, 200, 20);
		
		loginButton.setBounds((frame.getWidth() / 2) - 50, (frame.getHeight() / 2) + 60, 100, 30);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(OnlineManager.login(usernameField.getText(), new String(passwordField.getPassword()))) {
					panel.removeAll();
					panel.add(clickButton);
					panel.add(scoreDisplayer);
					panel.add(loseMessage);
					panel.add(quitButton);
					quitButton.setVisible(false);
					panel.repaint();
					
					Thread gameThread = new Thread(new Game());
					gameThread.start();
				} else {
					frame.setVisible(false);
					
					JOptionPane.showMessageDialog(null, "Login or password invalide", "Error", JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);
				}
			}
		});
		
		createAccountLabel.setBounds((frame.getWidth() / 2) - 225, (frame.getHeight() / 2) + 100, 450, 20);
		
		clickButton.setVisible(false);
		clickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Game.buttonHaveBeenClicked = true;
				Sound.play = true;
				
				clickButton.setVisible(false);
				panel.repaint();
				
				Game.score++;
				Frame.scoreDisplayer.setText("" + Game.score);
			}
		});
		
		scoreDisplayer.setBounds(0, 0, 500, 20);
		
		loseMessage.setVisible(false);
		loseMessage.setBounds((frame.getWidth() / 2) - 50, (frame.getHeight() / 2) - 25, 100, 50);
		
		quitButton.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) + 85, 200, 50);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		frame.setVisible(true);
	}
}
