package edu.fudan.ss.monopoly201606.props;

import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Chessboard;

public class Residence extends Prop{

	public Residence(Chessboard chessboard) {
		super("滞留卡", chessboard, 1000);
	}

	@Override
	public String getName() {
		return propName;
	}
	
	@Override
	public int getTicket() {
		return ticket;
	}

	@Override
	public void useProp() {
		chessboard.getgame().getMainPanel().getDice().setDiceNumber(0);
		chessboard.getgame().getMainPanel().getDice().setUnderControl(true);
		chessboard.getCurPlayer().removeProp(this);
		JOptionPane.showMessageDialog(null, "滞留卡使用成功！");
	}

	@Override
	public String help() {
		return "滞留卡：该回合停留在原地，并再次触发原地事件";
	}

}
