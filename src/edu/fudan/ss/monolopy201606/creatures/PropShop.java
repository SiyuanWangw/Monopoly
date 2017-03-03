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


//���ߵ�
public class PropShop extends Creature {
	private String[] propNames = {"ת��", "������", "ң������", "·�Ͽ�", "��˰��", "������", "�ӶῨ"};

	public PropShop(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public Prop newProp(Chessboard chessboard, String propName){
		switch (propName) {
		case "ת��":
			return new ChangeDirection(chessboard);
		case "������":
			return new Residence(chessboard);
		case "ң������":
			return new RemoteDice(chessboard);
		case "·�Ͽ�":
			return new BlockCard(chessboard);
		case "��˰��":
			return new PayTax(chessboard);
		case "������":
			return new AverageWealth(chessboard);
		case "�ӶῨ":
			return new RobCard(chessboard);
		}
		return null;
	}
	
	@Override
	public void eventHappen(Player player) { 
		Chessboard chessboard = player.getCurCell().getChessboard();
		int answer = JOptionPane.showConfirmDialog(null, "�㵽���˵��ߵ꣬�Ƿ�Ҫ������ߣ�", "���ߵ�", JOptionPane.YES_NO_OPTION);
		if(answer==0){
			boolean leaveShop = false;
			String askStr = "����Թ������µ��ߣ�";
			for(int i = 0; i < propNames.length; i++){
				askStr += "\n"+propNames[i];
			}
			askStr += "\n��ѡ����Ҫ����ĵ��ߣ�";
			do{
				String opProp = (String)JOptionPane.showInputDialog(null,askStr+"\n", "���ߵ�", JOptionPane.PLAIN_MESSAGE, null, propNames, null);  
				if(opProp == null){
					leaveShop = true;
				}
				else{
					String propNum = JOptionPane.showInputDialog("������Ҫ����"+ opProp +"��������");
					if(propNum != null){
						while(!(Game.isNumeric(propNum)&&Integer.parseInt(propNum)>=0)){
							propNum = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
							if(propNum == null){
								return;
							}
						}
						Prop prop = newProp(chessboard, opProp);
						while(prop.getTicket()*Integer.parseInt(propNum) > player.getTicketPoint()){
							propNum = JOptionPane.showInputDialog("��ȯ����������������������");
							if(propNum != null){
								while(!(Game.isNumeric(propNum)&&Integer.parseInt(propNum)>=0)){
									propNum = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
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
							JOptionPane.showMessageDialog(null, "�ɹ�����"+prop.getName()+Integer.parseInt(propNum)+"��");
						}
						player.setTicketPoint(-prop.getTicket()*Integer.parseInt(propNum));
						if(player.getTicketPoint() == 0){
							JOptionPane.showMessageDialog(null, "��ȯ�����꣬�޷��������");
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
		return "���ͣ�PropShop\n���ƣ�"+name;
	}

}
