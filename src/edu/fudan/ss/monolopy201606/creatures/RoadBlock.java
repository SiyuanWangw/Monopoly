package edu.fudan.ss.monolopy201606.creatures;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;

//·��
public class RoadBlock extends Creature{

	public RoadBlock(Cell cell) {
		super(cell, "·��", new ImageIcon("icons/barrage.png"));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		JOptionPane.showMessageDialog(null, "���ڵ�"+player.getCurStep()+"����������·�϶�ֹͣ");
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
