package edu.fudan.ss.monolopy201606.creatures;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;

//票券点
public class TicketPoint extends Creature{
	private int addTicketPoint = 0;

	public TicketPoint(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		addTicketPoint = (int)(Math.random()*10000+1);
		player.setTicketPoint(addTicketPoint);
		JOptionPane.showMessageDialog(null, "获得点券"+addTicketPoint);
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "类型：TicketPoint\n名称："+name;
	}

}
