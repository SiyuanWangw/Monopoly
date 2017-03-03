package edu.fudan.ss.monopoly201606.props;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monopoly201606.Chessboard;

public class RobCard extends Prop{
	public RobCard(Chessboard chessboard) {
		super("掠夺卡", chessboard, 1000);
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
		String askStr = "距离你五步以内的其他玩家有：";
		boolean hasUseObject = false;
		for(int i = 0; i < chessboard.getPlayers().size(); i++){
			if(chessboard.getPlayer(i) == curPlayer){
				continue;
			}
			if(chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() <= 5 && chessboard.getPlayer(i).getCurCell().getX()-curPlayer.getCurCell().getX() >= -5){
				askStr += "\n"+chessboard.getPlayer(i).getName();
				hasUseObject = true;
				actionObjects.add(chessboard.getPlayer(i).getName());
			}
		}
		if(hasUseObject){
			askStr += "\n请选择你的使用对象";
			String name = (String)JOptionPane.showInputDialog(null, askStr+"\n", "掠夺卡", JOptionPane.PLAIN_MESSAGE, null, actionObjects.toArray(), null);  
			if(name != null){
				Player useObject = chessboard.getPlayer(name);
				int propIndex = (int)(Math.random()*curPlayer.getProps().size());
				Prop prop = useObject.removeProp(propIndex);
				curPlayer.addProp(prop);
				chessboard.getCurPlayer().removeProp(this);
				JOptionPane.showMessageDialog(null, "获得玩家"+useObject.getName()+"的一张"+prop.getName());
			}
		}
		else{
			chessboard.getCurPlayer().removeProp(this);
			JOptionPane.showMessageDialog(null, "五步以内没有其他玩家");
		}
		
	}

	@Override
	public String help() {
		return "掠夺卡：随机将对手的一张卡牌据为己有";
	}

}
