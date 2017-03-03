package edu.fudan.ss.monopoly201606.props;


import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Chessboard;

public class RemoteDice extends Prop{

	public RemoteDice(Chessboard chessboard) {
		super("遥控骰子", chessboard, 1000);
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
		Object[] diceObj = {""+1,""+2,""+3,""+4,""+5,""+6};
		String step = (String)JOptionPane.showInputDialog(null,"请设定你的点数：", "遥控骰子", JOptionPane.PLAIN_MESSAGE, null, diceObj, null);
		if(step != null){
			chessboard.getgame().getMainPanel().getDice().setDiceNumber(Integer.parseInt(step));
			chessboard.getgame().getMainPanel().getDice().setUnderControl(true);
			chessboard.getCurPlayer().removeProp(this);
			JOptionPane.showMessageDialog(null, "遥控骰子成功！");
		}
	}

	@Override
	public String help() {
		return "遥控骰子：可以任意控制骰子的结果，结果只能是1-6";
	}

}
