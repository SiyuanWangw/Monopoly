package edu.fudan.ss.monopoly201606.props;

import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monopoly201606.Chessboard;

public class PayTax extends Prop{
	
	public PayTax(Chessboard chessboard) {
		super("��˰��", chessboard, 1000);
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
			JOptionPane.showMessageDialog(null, "�岽֮��û��������ң�");
		}
		else{
			JOptionPane.showMessageDialog(null, "�ɻ�����������30%�Ĵ�"+"\n"+actionObjects);
		}
		
	}

	@Override
	public String help() {
		return "��˰����ǿ�н������Լ��岽���ڵĶ���30%�Ĵ���˰";
	}

}
