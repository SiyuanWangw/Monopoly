package edu.fudan.ss.monopoly201606.props;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monopoly201606.Chessboard;

public class RobCard extends Prop{
	public RobCard(Chessboard chessboard) {
		super("�ӶῨ", chessboard, 1000);
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
		String askStr = "�������岽���ڵ���������У�";
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
			askStr += "\n��ѡ�����ʹ�ö���";
			String name = (String)JOptionPane.showInputDialog(null, askStr+"\n", "�ӶῨ", JOptionPane.PLAIN_MESSAGE, null, actionObjects.toArray(), null);  
			if(name != null){
				Player useObject = chessboard.getPlayer(name);
				int propIndex = (int)(Math.random()*curPlayer.getProps().size());
				Prop prop = useObject.removeProp(propIndex);
				curPlayer.addProp(prop);
				chessboard.getCurPlayer().removeProp(this);
				JOptionPane.showMessageDialog(null, "������"+useObject.getName()+"��һ��"+prop.getName());
			}
		}
		else{
			chessboard.getCurPlayer().removeProp(this);
			JOptionPane.showMessageDialog(null, "�岽����û���������");
		}
		
	}

	@Override
	public String help() {
		return "�ӶῨ����������ֵ�һ�ſ��ƾ�Ϊ����";
	}

}
