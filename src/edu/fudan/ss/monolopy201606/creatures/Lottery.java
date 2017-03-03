package edu.fudan.ss.monolopy201606.creatures;

//彩票点
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
		String number = JOptionPane.showInputDialog("请输入 7位数字彩票号码：");
		if(number != null){
			while(number.length() != 7 || !(Game.isNumeric(number)&&Integer.parseInt(number)>=0)){
				number = JOptionPane.showInputDialog("输入不合法，请输入 7位数字彩票号码：");
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
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n很抱歉，彩票没中奖");
				lottery = 0;
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖500元");
				lottery = 500;
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖1000元");
				lottery = 1000;
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖1800元");
				lottery = 1800;
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖2800元");
				lottery = 2800;
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖4000元");
				lottery = 4000;
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖5500元");
				lottery = 5500;
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "获奖号码为"+prizeStr+",\n恭喜你，彩票中奖7000元");
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
		return "类型：Lottery\n名称："+name;
	}

}
