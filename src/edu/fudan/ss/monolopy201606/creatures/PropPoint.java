package edu.fudan.ss.monolopy201606.creatures;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.props.AverageWealth;
import edu.fudan.ss.monopoly201606.props.BlockCard;
import edu.fudan.ss.monopoly201606.props.ChangeDirection;
import edu.fudan.ss.monopoly201606.props.PayTax;
import edu.fudan.ss.monopoly201606.props.Prop;
import edu.fudan.ss.monopoly201606.props.RemoteDice;
import edu.fudan.ss.monopoly201606.props.Residence;
import edu.fudan.ss.monopoly201606.props.RobCard;

//赠送道具点
public class PropPoint extends Creature{

	public PropPoint(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		Chessboard chessboard = player.getCurCell().getChessboard();
		Prop addprop = new ChangeDirection(chessboard);
		int propIndex = (int) (Math.random()*7);
		switch (propIndex) {
		case 0: addprop = new ChangeDirection(chessboard);
			break;
		case 1: addprop = new Residence(chessboard);
			break;
		case 2: addprop = new RemoteDice(chessboard);
			break;
		case 3: addprop = new BlockCard(chessboard);
			break;
		case 4: addprop = new PayTax(chessboard);
			break;
		case 5: addprop = new AverageWealth(chessboard);
			break;
		case 6: addprop = new RobCard(chessboard);
			break;
		default:
			break;
		}
		player.addProp(addprop);
		JOptionPane.showMessageDialog(null, "恭喜你获得"+addprop.getName()+"一张", "赠送道具卡", 1);
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "类型：PropPoint\n名称："+name;
	}
	
}
