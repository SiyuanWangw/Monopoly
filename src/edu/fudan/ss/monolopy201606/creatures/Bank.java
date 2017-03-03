package edu.fudan.ss.monolopy201606.creatures;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Game;

//����
public class Bank extends Creature{
	private double changeMoney = 0;

	public Bank(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		ImageIcon icon2 = icon;
		icon2.setImage(icon2.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		boolean leaveBank = true;
		do {
			Object[] operation = {" ȡ�� "," ��� "," �뿪"};
			int choice = JOptionPane.showOptionDialog(null, "Ŀǰӵ���ֽ�"+player.getCash()+"Ԫ�����"+player.getDespoit()+"Ԫ", "��������",0, JOptionPane.QUESTION_MESSAGE, icon2, operation, operation[2]);
			
			switch(choice){
			case 0:	String changeMoneyStr = JOptionPane.showInputDialog("��������Ҫȡ��Ľ�");
					if(changeMoneyStr != null){
						while(!Game.isDouble(changeMoneyStr) || (Game.isDouble(changeMoneyStr)&&Double.parseDouble(changeMoneyStr)<0)){
							changeMoneyStr = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
							if(changeMoneyStr == null){
								return;
							}
						}
						changeMoney = Game.getTwoPointNum(Double.parseDouble(changeMoneyStr));
						while(changeMoney > player.getDespoit()){
							changeMoneyStr = JOptionPane.showInputDialog("ȡ�������������������룺");
							while(!Game.isDouble(changeMoneyStr) || (Game.isDouble(changeMoneyStr)&&Double.parseDouble(changeMoneyStr)<0)){
								changeMoneyStr = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
								if(changeMoneyStr == null){
									return;
								}
							}
							if(changeMoneyStr == null){
								return;
							}
							changeMoney = Game.getTwoPointNum(Double.parseDouble(changeMoneyStr));
						}
						player.setCash(changeMoney);
						player.setDespoit(-changeMoney);
					}
					break;
			case 1: String changeMoneyStr2 = JOptionPane.showInputDialog("��������Ҫ���Ľ�");
					if(changeMoneyStr2 != null){
						while(!Game.isDouble(changeMoneyStr2) || (Game.isDouble(changeMoneyStr2)&&Double.parseDouble(changeMoneyStr2)<0)){
							changeMoneyStr2 = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
							if(changeMoneyStr2 == null){
								return;
							}
						}
				    	changeMoney = Game.getTwoPointNum(Double.parseDouble(changeMoneyStr2));
				    	while(changeMoney > player.getCash()){
							changeMoneyStr2 = JOptionPane.showInputDialog("������ӵ���ֽ����������룺");
							while(!Game.isDouble(changeMoneyStr2) || (Game.isDouble(changeMoneyStr2)&&Double.parseDouble(changeMoneyStr2)<0)){
								changeMoneyStr2 = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
								if(changeMoneyStr2 == null){
									return;
								}
							}
							if(changeMoneyStr2 == null){
								return;
							}
							changeMoney = Game.getTwoPointNum(Double.parseDouble(changeMoneyStr2));
						}
						player.setCash(-changeMoney);
						player.setDespoit(changeMoney);
					}
					
			    	break;
			case 2: leaveBank = false;
					break;	
			case -1: leaveBank = false;
					break;	
			}
			
		} while (leaveBank);
		
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "���ͣ�Bank\n���ƣ�"+name;
	}

	
}
