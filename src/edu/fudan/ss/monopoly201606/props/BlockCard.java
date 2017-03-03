package edu.fudan.ss.monopoly201606.props;


import javax.swing.JOptionPane;

import edu.fudan.ss.monolopy201606.creatures.Direction;
import edu.fudan.ss.monolopy201606.creatures.RoadBlock;
import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Game;

public class BlockCard extends Prop{

	public BlockCard(Chessboard chessboard) {
		super("路障卡", chessboard, 1000);
	}

	@Override
	public String getName() {
		return propName;
	}

	@Override
	public void useProp() {
		String distance = JOptionPane.showInputDialog("请问你要距离几步放置路障(前后八步之内，反向用负数：)：");
		if(distance != null){
			while(!(Game.isNumeric(distance) && Math.abs(Integer.parseInt(distance))<9 && Math.abs(Integer.parseInt(distance))>0)){
				distance =  JOptionPane.showInputDialog("输入不合法，请重新输入：");
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
			JOptionPane.showMessageDialog(null, "投放路障成功！");
			chessboard.getgame().getMainPanel().repaint();
		}
	}

	@Override
	public String help() {
		return "路障卡：在前后8步之内安放一个路障，任意玩家经过路障时会停在路障所在位置不能前行";
	}
	
	@Override
	public int getTicket() {
		return ticket;
	}

}
