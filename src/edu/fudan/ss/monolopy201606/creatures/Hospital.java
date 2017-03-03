package edu.fudan.ss.monolopy201606.creatures;

//医院
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
		return "类型：Hospital\n名称："+name;
	}

	@Override
	public void eventHappen(Player player) {

	}

}
