package edu.fudan.ss.monopoly201606.props;

import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Chessboard;

public class AverageWealth extends Prop{

	public AverageWealth(Chessboard chessboard) {
		super("均富卡", chessboard, 1000);
	}

	@Override
	public String getName() {
		return propName;
	}

	@Override
	public void useProp() {
		int totalCash = 0;
		for(int i = 0; i < chessboard.getPlayers().size(); i++){
			totalCash += chessboard.getPlayer(i).getCash();
		}
		double averageCash = totalCash/chessboard.getPlayers().size();
		for(int i = 0; i < chessboard.getPlayers().size(); i++){
			chessboard.getPlayer(i).setCash(-chessboard.getPlayer(i).getCash()+averageCash);
		}
		chessboard.getCurPlayer().removeProp(this);
		JOptionPane.showMessageDialog(null, "所有玩家的现金均分");
		chessboard.getgame().getMainPanel().repaint();
	}

	@Override
	public String help() {
		 return "均富卡：将所有人的现金平均分配";
	}

	@Override
	public int getTicket() {
		return ticket;
	}

}
