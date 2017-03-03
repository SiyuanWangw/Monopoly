package edu.fudan.ss.monolopy201606.creatures;

import javax.swing.ImageIcon;

import edu.fudan.ss.monopoly201606.Cell;

//¿ÕµØ
public abstract class Creature {
	protected Cell cell;
	protected String name;
	protected ImageIcon icon;
	public Creature(Cell cell, String name, ImageIcon icon){
		this.cell = cell;
		this.name = name;
		this.icon = icon;
	}
	public abstract ImageIcon toImageIcon();
	public abstract String getName();
	public abstract String detail(); 
	public abstract void eventHappen(Player player);
}
