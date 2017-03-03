package edu.fudan.ss.monopoly201606.props;


import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Direction;
import edu.fudan.ss.monolopy201606.creatures.RoadBlock;
import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Game;

public class BlockCard extends Prop{

	public BlockCard(Chessboard chessboard) {
		super("·�Ͽ�", chessboard, 1000);
	}

	@Override
	public String getName() {
		return propName;
	}

	@Override
	public void useProp() {
		String distance = JOptionPane.showInputDialog("������Ҫ���뼸������·��(ǰ��˲�֮�ڣ������ø�����)��");
		if(distance != null){
			while(!(Game.isNumeric(distance) && Math.abs(Integer.parseInt(distance))<9 && Math.abs(Integer.parseInt(distance))>0)){
				distance =  JOptionPane.showInputDialog("���벻�Ϸ������������룺");
				if(distance == null){
					return ;
				}
			}
			Direction nextDir = chessboard.getCurPlayer().getDirection();
			if(Integer.parseInt(distance) < 0){
				if(chessboard.getCurPlayer().getDirection() == Direction.clockwise)
					nextDir = Direction.eastern;
				else 
					nextDir = Direction.clockwise;
			}
			Cell aimCell = chessboard.getCurPlayer().getCurCell().getCellAt(nextDir, Math.abs(Integer.parseInt(distance)));
			aimCell.addCreature(new RoadBlock(aimCell));
			chessboard.getCurPlayer().removeProp(this);
			JOptionPane.showMessageDialog(null, "Ͷ��·�ϳɹ���");
			chessboard.getgame().getMainPanel().repaint();
		}
	}

	@Override
	public String help() {
		return "·�Ͽ�����ǰ��8��֮�ڰ���һ��·�ϣ�������Ҿ���·��ʱ��ͣ��·������λ�ò���ǰ��";
	}
	
	@Override
	public int getTicket() {
		return ticket;
	}

}
