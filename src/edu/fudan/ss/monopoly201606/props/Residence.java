package edu.fudan.ss.monopoly201606.props;

import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Chessboard;

public class Residence extends Prop{

	public Residence(Chessboard chessboard) {
		super("������", chessboard, 1000);
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
		JOptionPane.showMessageDialog(null, "������ʹ�óɹ���");
	}

	@Override
	public String help() {
		return "���������ûغ�ͣ����ԭ�أ����ٴδ���ԭ���¼�";
	}

}
