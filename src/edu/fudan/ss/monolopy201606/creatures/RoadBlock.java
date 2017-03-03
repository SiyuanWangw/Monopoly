package edu.fudan.ss.monolopy201606.creatures;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;

//路障
public class RoadBlock extends Creature{

	public RoadBlock(Cell cell) {
		super(cell, "路障", new ImageIcon("icons/barrage.png"));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		JOptionPane.showMessageDialog(null, "你在第"+player.getCurStep()+"步上遇到了路障而停止");
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return null;
	}

}
