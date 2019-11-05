package main;

import lejos.hardware.Button;
import robot.runs.StopListener;
import user.HardwareCreator;
import user.RunCreator;

public class MainClass {
	
	public static void main(String[] args) {
		System.out.println("Started");
		
		System.out.println("Init hardware");
		HardwareCreator.init();
		Button.ESCAPE.addKeyListener(new StopListener());
		
		System.out.println("Init runs");
		RunCreator.init();
		
		System.out.println("Ready");
		
		MainMenu.init();
		
		System.out.println("Over");
	}
	
}
