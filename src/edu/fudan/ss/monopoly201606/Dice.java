package edu.fudan.ss.monopoly201606;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Dice{
	private int diceNumber = 0;
	private boolean underControl = false;
	private ImageIcon[] diceSixImage = {new ImageIcon("icons/dice1.png"),
			new ImageIcon("icons/dice2.png"),new ImageIcon("icons/dice3.png"),
			new ImageIcon("icons/dice4.png"),new ImageIcon("icons/dice5.png"),
			new ImageIcon("icons/dice6.png")
	};
	
	public int getDiceNumber() {
		setUnderControl(false);
		return diceNumber;
	}
	
	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}
	
	public void setUnderControl(boolean underControl) {
		this.underControl = underControl;
	}
	
	public boolean getUnderControl(){
		return underControl;
	}
	
	public ImageIcon getDiceImage(int index){
		for(int i = 0; i < 6; i++){
			diceSixImage[i].setImage(diceSixImage[i].getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		}
		return diceSixImage[index];
	}
	
	
}
