package edu.fudan.ss.monopoly201606.props;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Direction;
import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Game;

public class ChangeDirection extends Prop{

	public ChangeDirection(Chessboard chessboard) {
		super("转向卡", chessboard, 1000);
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
		ArrayList<String> actionObjects = new ArrayList<String>();
		Player curPlayer = chessboard.getCurPlayer();
		
		String askStr = "距离你五步以内的任意玩家有： ";
		for(int i = 0; i < chessboard.getPlayers().size(); i++){
			if(chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() <= 5 && chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() >= -5){
				askStr += "\n"+chessboard.getPlayer(i).getName();
				actionObjects.add(chessboard.getPlayer(i).getName());
			}
		}
		askStr += "\n请选择你的使用对象：";
		String name = (String)JOptionPane.showInputDialog(null, askStr+"\n", "转向卡", JOptionPane.PLAIN_MESSAGE, null, actionObjects.toArray(), null);  
		if(name != null){
			Player useObject = chessboard.getPlayer(name);
			if(useObject.getDirection() == Direction.clockwise)
				useObject.setDirection(Direction.eastern);
			else
				useObject.setDirection(Direction.clockwise);
			chessboard.getCurPlayer().removeProp(this);
			JOptionPane.showMessageDialog(null, "使"+name+"转向成功！");
		}
	}

	@Override
	public String help() {
		return "转向卡：使自己或距离自己五步以内的对手调转方向";
	}
	
	

}
