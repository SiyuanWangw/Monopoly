package edu.fudan.ss.monopoly201606.props;

import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monopoly201606.Chessboard;

public class PayTax extends Prop{
	
	public PayTax(Chessboard chessboard) {
		super("查税卡", chessboard, 1000);
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
		Player curPlayer = chessboard.getCurPlayer();
		boolean hasUseObject = false;
		String actionObjects = "";
		for(int i = 0; i < chessboard.getPlayers().size(); i++){
			if(chessboard.getPlayer(i) == curPlayer){
				continue;
			}
			if(chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() <= 5 && chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() >= -5){
				chessboard.getPlayer(i).setDespoit(-chessboard.getPlayer(i).getDespoit()*3/10);
				actionObjects += chessboard.getPlayer(i).getName()+"\n";
				hasUseObject = true;
			}
		}
		chessboard.getCurPlayer().removeProp(this);
		if(!hasUseObject){
			JOptionPane.showMessageDialog(null, "五步之内没有其他玩家！");
		}
		else{
			JOptionPane.showMessageDialog(null, "缴获玩家以下玩家30%的存款："+"\n"+actionObjects);
		}
		
	}

	@Override
	public String help() {
		return "查税卡：强行将距离自己五步以内的对手30%的存款缴税";
	}

}
