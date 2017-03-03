package edu.fudan.ss.monolopy201606.creatures;

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


//新闻点
public class News extends Creature{

	public News(Cell cell, String name, ImageIcon icon) {
		super(cell, name, icon);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player){
		Chessboard chessboard = player.getCurCell().getChessboard();
		int newsNumber = (int)(Math.random()*6);
		switch(newsNumber){
		case 0: double newsGetCash1 = Game.getTwoPointNum(Math.random()*1000);
				int mostHousesId = 0;
				for(int i = 1; i < chessboard.getPlayers().size(); i++){
					if(chessboard.getPlayer(mostHousesId).totalHousesPrice() < chessboard.getPlayer(i).totalHousesPrice()){
						mostHousesId = i;
					}
				}
				chessboard.getPlayer(mostHousesId).setCash(newsGetCash1);
				JOptionPane.showMessageDialog(null, "公开表扬第一地主"+chessboard.getPlayer(mostHousesId).getName()+"奖励"+newsGetCash1+"元");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 1: double newsGetCash2 = Game.getTwoPointNum(Math.random()*1000);
				int leastHousesId = 0;
				for(int i = 1; i < chessboard.getPlayers().size(); i++){
					if(chessboard.getPlayer(leastHousesId).totalHousesPrice() > chessboard.getPlayer(i).totalHousesPrice()){
						leastHousesId = i;
					}
				}
				chessboard.getPlayer(leastHousesId).setCash(newsGetCash2);
				JOptionPane.showMessageDialog(null, "公开补助土地最少者"+chessboard.getPlayer(leastHousesId).getName()+" "+newsGetCash2+"元");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 2: 
				for(int i = 0; i < chessboard.getPlayers().size(); i++){
					chessboard.getPlayer(i).setDespoit(chessboard.getPlayer(i).getDespoit()/10);
				}
				JOptionPane.showMessageDialog(null, "银行加发储金红利每个人得到存款10%");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 3: 
				for(int i = 0; i < chessboard.getPlayers().size(); i++){
					chessboard.getPlayer(i).setDespoit(-chessboard.getPlayer(i).getDespoit()/10);
				}
				JOptionPane.showMessageDialog(null, "所有人缴纳财产税10%");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 4: JOptionPane.showMessageDialog(null, "每个人得到一张卡片");
				for(Player everyone: chessboard.getPlayers()){
					getProp(everyone, chessboard);
				}
				break;
		case 5:
				//受伤
				chessboard.getCurPlayer().removeSelf();
				chessboard.getCurPlayer().moveTo(chessboard.getCellAt(19));
				chessboard.getCurPlayer().setDirection(Direction.eastern);
				chessboard.getCurPlayer().setHurt(true);
				chessboard.getCurPlayer().setTreatday(2);
				JOptionPane.showMessageDialog(null, "受伤，被关入医院两回合，2回合后逆时针转向");
				break;
		}
	}
	
	public void getProp(Player player, Chessboard chessboard) {
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
		JOptionPane.showMessageDialog(null, player.getName()+"获得"+addprop.getName()+"一张");
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "类型：News\n名称："+name;
	}
	
}
