package edu.fudan.ss.monolopy201606.creatures;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Game;
import edu.fudan.ss.monopoly201606.props.AverageWealth;
import edu.fudan.ss.monopoly201606.props.BlockCard;
import edu.fudan.ss.monopoly201606.props.ChangeDirection;
import edu.fudan.ss.monopoly201606.props.PayTax;
import edu.fudan.ss.monopoly201606.props.Prop;
import edu.fudan.ss.monopoly201606.props.RemoteDice;
import edu.fudan.ss.monopoly201606.props.Residence;
import edu.fudan.ss.monopoly201606.props.RobCard;


//道具店
public class PropShop extends Creature {
	private String[] propNames = {"转向卡", "滞留卡", "遥控骰子", "路障卡", "查税卡", "均富卡", "掠夺卡"};

	public PropShop(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public Prop newProp(Chessboard chessboard, String propName){
		switch (propName) {
		case "转向卡":
			return new ChangeDirection(chessboard);
		case "滞留卡":
			return new Residence(chessboard);
		case "遥控骰子":
			return new RemoteDice(chessboard);
		case "路障卡":
			return new BlockCard(chessboard);
		case "查税卡":
			return new PayTax(chessboard);
		case "均富卡":
			return new AverageWealth(chessboard);
		case "掠夺卡":
			return new RobCard(chessboard);
		}
		return null;
	}
	
	@Override
	public void eventHappen(Player player) { 
		Chessboard chessboard = player.getCurCell().getChessboard();
		int answer = JOptionPane.showConfirmDialog(null, "你到达了道具店，是否要购买道具？", "道具店", JOptionPane.YES_NO_OPTION);
		if(answer==0){
			boolean leaveShop = false;
			String askStr = "你可以购买以下道具：";
			for(int i = 0; i < propNames.length; i++){
				askStr += "\n"+propNames[i];
			}
			askStr += "\n请选择你要购买的道具：";
			do{
				String opProp = (String)JOptionPane.showInputDialog(null,askStr+"\n", "道具店", JOptionPane.PLAIN_MESSAGE, null, propNames, null);  
				if(opProp == null){
					leaveShop = true;
				}
				else{
					String propNum = JOptionPane.showInputDialog("请输入要购买"+ opProp +"的数量：");
					if(propNum != null){
						while(!(Game.isNumeric(propNum)&&Integer.parseInt(propNum)>=0)){
							propNum = JOptionPane.showInputDialog("输入不合法，请重新输入：");
							if(propNum == null){
								return;
							}
						}
						Prop prop = newProp(chessboard, opProp);
						while(prop.getTicket()*Integer.parseInt(propNum) > player.getTicketPoint()){
							propNum = JOptionPane.showInputDialog("点券不够，请重新输入数量：");
							if(propNum != null){
								while(!(Game.isNumeric(propNum)&&Integer.parseInt(propNum)>=0)){
									propNum = JOptionPane.showInputDialog("输入不合法，请重新输入：");
									if(propNum == null){
										return;
									}
								}
							}
							else
								return;
						}
						if(Integer.parseInt(propNum) > 0){
							player.addProp(prop);
							for(int j = 1; j < Integer.parseInt(propNum); j++){
								player.addProp(newProp(chessboard, opProp));
							}
							JOptionPane.showMessageDialog(null, "成功买入"+prop.getName()+Integer.parseInt(propNum)+"张");
						}
						player.setTicketPoint(-prop.getTicket()*Integer.parseInt(propNum));
						if(player.getTicketPoint() == 0){
							JOptionPane.showMessageDialog(null, "点券已用完，无法再买道具");
							break;	
						}	
					}	
				}	
			} while (!leaveShop);
		}
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}
	
	@Override
	public String detail() {
		return "类型：PropShop\n名称："+name;
	}

}
