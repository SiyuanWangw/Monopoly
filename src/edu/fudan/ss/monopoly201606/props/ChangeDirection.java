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
		super("ת��", chessboard, 1000);
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
		
		String askStr = "�������岽���ڵ���������У� ";
		for(int i = 0; i < chessboard.getPlayers().size(); i++){
			if(chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() <= 5 && chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() >= -5){
				askStr += "\n"+chessboard.getPlayer(i).getName();
				actionObjects.add(chessboard.getPlayer(i).getName());
			}
		}
		askStr += "\n��ѡ�����ʹ�ö���";
		String name = (String)JOptionPane.showInputDialog(null, askStr+"\n", "ת��", JOptionPane.PLAIN_MESSAGE, null, actionObjects.toArray(), null);  
		if(name != null){
			Player useObject = chessboard.getPlayer(name);
			if(useObject.getDirection() == Direction.clockwise)
				useObject.setDirection(Direction.eastern);
			else
				useObject.setDirection(Direction.clockwise);
			chessboard.getCurPlayer().removeProp(this);
			JOptionPane.showMessageDialog(null, "ʹ"+name+"ת��ɹ���");
		}
	}

	@Override
	public String help() {
		return "ת�򿨣�ʹ�Լ�������Լ��岽���ڵĶ��ֵ�ת����";
	}
	
	

}
