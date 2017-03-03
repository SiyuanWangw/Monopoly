package edu.fudan.ss.monolopy201606.creatures;

//ҽԺ
import javax.swing.ImageIcon;

import edu.fudan.ss.monopoly201606.Cell;

public class Hospital extends Creature {
	
	public Hospital(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String detail() {
		return "���ͣ�Hospital\n���ƣ�"+name;
	}

	@Override
	public void eventHappen(Player player) {

	}

}
