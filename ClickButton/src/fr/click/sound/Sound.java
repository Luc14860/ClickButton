package fr.click.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sound implements Runnable {
	
	public static boolean play = false;
	
	public void run() {
		while(true) {
			if(play) {
				File soundFile = null;
				AudioInputStream audioStream = null;
				SourceDataLine sourceLine = null;
				
				try {
					soundFile = new File("res/sound/click.wav");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					audioStream = AudioSystem.getAudioInputStream(soundFile);
				} catch (Exception e){
					e.printStackTrace();
				}
				
				AudioFormat audioFormat = audioStream.getFormat();
				
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				
				try {
					sourceLine = (SourceDataLine) AudioSystem.getLine(info);
					sourceLine.open(audioFormat);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				sourceLine.start();
				
				int nBytesRead = 0;
				byte[] abData = new byte[1024];
				while (nBytesRead != -1) {
					try {
						nBytesRead = audioStream.read(abData, 0, abData.length);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (nBytesRead >= 0) {
						@SuppressWarnings("unused")
						int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
					}
				}
				
				sourceLine.drain();
				sourceLine.close();
				
				play = false;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
