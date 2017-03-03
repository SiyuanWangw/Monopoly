package edu.fudan.ss.monolopy201606.creatures;

import javax.swing.ImageIcon;

import edu.fudan.ss.monopoly201606.Cell;

//空地
public class Blank extends Creature{

	public Blank(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "类型：Blank\n名称："+name;
	}

}
