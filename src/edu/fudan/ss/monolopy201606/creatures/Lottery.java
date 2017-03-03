package edu.fudan.ss.monolopy201606.creatures;

//��Ʊ��
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Game;

public class Lottery extends Creature{

	public Lottery(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		String number = JOptionPane.showInputDialog("������ 7λ���ֲ�Ʊ���룺");
		if(number != null){
			while(number.length() != 7 || !(Game.isNumeric(number)&&Integer.parseInt(number)>=0)){
				number = JOptionPane.showInputDialog("���벻�Ϸ��������� 7λ���ֲ�Ʊ���룺");
				if(number == null){
					return;
				}
			}
			
			int[] prizes = new int[7];
			for(int i = 0; i < prizes.length; i++){
				prizes[i] = (int)(Math.random()*10);
			}

			int matchNum = 0;
			for(int i = 0; i < prizes.length; i++){
				if(Integer.parseInt(String.valueOf(number.charAt(i))) == prizes[i])
					matchNum++;
			}
			int lottery = 0;
			
			String prizeStr = "" + prizes[0]+ prizes[1]+ prizes[2]+ prizes[3]+ prizes[4]+ prizes[5]+ prizes[6];
			
			switch (matchNum) {
			case 0:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n�ܱ�Ǹ����Ʊû�н�");
				lottery = 0;
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�500Ԫ");
				lottery = 500;
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�1000Ԫ");
				lottery = 1000;
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�1800Ԫ");
				lottery = 1800;
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�2800Ԫ");
				lottery = 2800;
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�4000Ԫ");
				lottery = 4000;
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�5500Ԫ");
				lottery = 5500;
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "�񽱺���Ϊ"+prizeStr+",\n��ϲ�㣬��Ʊ�н�7000Ԫ");
				lottery = 7000;
				break;
			}
			player.setCash(lottery);
		}
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "���ͣ�Lottery\n���ƣ�"+name;
	}

}
