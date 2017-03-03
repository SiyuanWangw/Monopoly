package edu.fudan.ss.monolopy201606.creatures;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;

//Ʊȯ��
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
		JOptionPane.showMessageDialog(null, "��õ�ȯ"+addTicketPoint);
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "���ͣ�TicketPoint\n���ƣ�"+name;
	}

}
