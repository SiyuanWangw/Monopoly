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


//���ŵ�
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
				JOptionPane.showMessageDialog(null, "���������һ����"+chessboard.getPlayer(mostHousesId).getName()+"����"+newsGetCash1+"Ԫ");
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
				JOptionPane.showMessageDialog(null, "������������������"+chessboard.getPlayer(leastHousesId).getName()+" "+newsGetCash2+"Ԫ");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 2: 
				for(int i = 0; i < chessboard.getPlayers().size(); i++){
					chessboard.getPlayer(i).setDespoit(chessboard.getPlayer(i).getDespoit()/10);
				}
				JOptionPane.showMessageDialog(null, "���мӷ��������ÿ���˵õ����10%");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 3: 
				for(int i = 0; i < chessboard.getPlayers().size(); i++){
					chessboard.getPlayer(i).setDespoit(-chessboard.getPlayer(i).getDespoit()/10);
				}
				JOptionPane.showMessageDialog(null, "�����˽��ɲƲ�˰10%");
				chessboard.getgame().getMainPanel().repaint();
				break;
		case 4: JOptionPane.showMessageDialog(null, "ÿ���˵õ�һ�ſ�Ƭ");
				for(Player everyone: chessboard.getPlayers()){
					getProp(everyone, chessboard);
				}
				break;
		case 5:
				//����
				chessboard.getCurPlayer().removeSelf();
				chessboard.getCurPlayer().moveTo(chessboard.getCellAt(19));
				chessboard.getCurPlayer().setDirection(Direction.eastern);
				chessboard.getCurPlayer().setHurt(true);
				chessboard.getCurPlayer().setTreatday(2);
				JOptionPane.showMessageDialog(null, "���ˣ�������ҽԺ���غϣ�2�غϺ���ʱ��ת��");
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
		JOptionPane.showMessageDialog(null, player.getName()+"���"+addprop.getName()+"һ��");
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "���ͣ�News\n���ƣ�"+name;
	}
	
}
