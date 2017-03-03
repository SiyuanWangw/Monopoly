package edu.fudan.ss.monolopy201606.creatures;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Game;

//����
public class House extends Creature{
	private HouseType houseType = HouseType.sale;
	private Player owner = null;
	private int originPrice = 2000;
	private int grade = 1;
	private int streetNum = 0;
	
	public House(Cell cell, String name, int streetNum, ImageIcon icon) {
		super(cell, name, icon);
		this.streetNum = streetNum;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
		if(this.owner == null){
			houseType = HouseType.sale;
		}
		else{
			switch (owner.getI()) {
			case 1:
				houseType = HouseType.own1 ;
				break;
			case 2:
				houseType = HouseType.own2 ;
				break;
			case 3:
				houseType = HouseType.own3 ;
				break;
			case 4:
				houseType = HouseType.own4 ;
				break;
			}
		}
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public void upgrade() {
		this.grade++;
	}
	
	public int getGrade(){
		return grade;
	}

	public int getStreetNum() {
		return streetNum;
	}
	
	public int getOriginPrice(){
		return originPrice;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eventHappen(Player player) {
		//�ɳ��۷���
		if(owner == null){
			int answer1 = JOptionPane.showConfirmDialog(null, "�㵽����"+name+"���Ƿ����ݣ�","���ݹ���", JOptionPane.YES_NO_OPTION);
			if(answer1 == 0 ){
				if(player.getCash() >= originPrice*grade){
					player.setCash(-originPrice*grade);
					setOwner(player);
					player.addOwnHouse(this);
					JOptionPane.showMessageDialog(null, "�ɹ�����"+name);
				}
				else if(player.getCash()+player.getDespoit() >= originPrice*grade){
					player.setDespoit(player.getCash()-originPrice*grade);
					player.setCash(-player.getCash());
					setOwner(player);
					player.addOwnHouse(this);
					JOptionPane.showMessageDialog(null, "�ɹ�����"+name);
				}	
				else{
					JOptionPane.showMessageDialog(null, "�ʽ𲻹������ܹ���˷��ݣ�");
				}
			}
		}
		//�Լ��ķ���
		else if(owner.getName().equals(player.getName())){
			if(grade < 6){
				int answer2 = JOptionPane.showConfirmDialog(null, "�˷��������㣬��Ҫ������","��������", JOptionPane.YES_NO_OPTION);
				if(answer2 == 0 ){
					if(player.getCash() >= originPrice/2){
						upgrade();
						owner.setCash(-originPrice/2);
						JOptionPane.showMessageDialog(null, "�����ɹ���Ŀǰ�ȼ�Ϊ"+grade);
					}
					else {
						JOptionPane.showMessageDialog(null, "���ֽ𲻹����޷�������");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "�˷���Ŀǰ�ȼ�Ϊ�����޷��ٽ���������");
			}
		}
		//���˵ķ���
		else{
			ArrayList<House> markupHouses = owner.markupHouse(streetNum, this);
			double fee = originPrice*grade*0.3;
			for(House house:markupHouses){
				fee += house.getOriginPrice()*house.getGrade()*0.1;
			}
			if(fee <= player.getCash()){
				JOptionPane.showMessageDialog(null, "�㵽�������"+owner.getName()+"�ķ��ӣ���Ҫ����·��\n"+"�۳��ֽ�"+fee+"Ԫ ");
				player.setCash(-fee);
				owner.setCash(fee);
			}
			else if(fee <= player.getCash()+player.getDespoit()){
				JOptionPane.showMessageDialog(null, "�㵽�������"+owner.getName()+"�ķ��ӣ���Ҫ����·��\n"+"�۳�ȫ���ֽ�"+player.getCash()+"Ԫ֮�󣬻��Ӵ���п۳�"+(fee-player.getCash()) +"Ԫ");
				player.setDespoit(-fee+player.getCash());
				player.setCash(-player.getCash());
				owner.setCash(fee);
			}
			else{
				JOptionPane.showMessageDialog(null, "�㵽�������"+owner.getName()+"�ķ��ӣ���Ҫ����·��\n"+"����������ʽ�����ֽ����֧����·�ѣ���Ҫ������:");
				double curMoney = player.getCash()+player.getDespoit();
				ArrayList<House> removeHouses = new ArrayList<House>();
				for(House house: player.getOwnHouses()){
					removeHouses.add(house);
					curMoney += house.getOriginPrice()*house.getGrade();
					JOptionPane.showMessageDialog(null, "��ӵ�е�"+house.getName()+"����Ѻ");
					if(curMoney > fee){
						break;
					}
				}
				for(House removeHouse: removeHouses){
					removeHouse.setOwner(null);
					player.removeOwnHouse(removeHouse);
				}
				if(curMoney >= fee){
					player.setDespoit(-player.getDespoit());
					player.setCash(-player.getCash()+curMoney-fee);
					owner.setCash(fee);
					JOptionPane.showMessageDialog(null, "�������"+owner.getName()+fee+"Ԫ");
				}
				else{
					JOptionPane.showMessageDialog(null, "�����Ʋ����뿪��Ϸ�������ʽ������·��");
					owner.setCash(curMoney);
					
					Chessboard chessboard = player.getCurCell().getChessboard();
					
					int nextIndex = chessboard.getPlayerIndex(chessboard.getCurPlayer());
					player.removeSelf();
					player.getCurCell().getChessboard().removePlayer(player);
					
					if(nextIndex >= chessboard.getPlayers().size()){
						chessboard.getgame().setRound();
						Game.time.setCurrentTime(chessboard.getgame().getRound());
						
						nextIndex = nextIndex % chessboard.getPlayers().size();
					}
					chessboard.setCurPlayer(chessboard.getPlayer(nextIndex));
					chessboard.getgame().getMainPanel().repaint();
					Game.giveInterest(Game.time, chessboard);
					
					if(chessboard.getPlayers().size() <= 1){
						JOptionPane.showMessageDialog(null,
								"��Ϸ��������ʤ����"+chessboard.getPlayer(0).getName());			
						System.exit(0);
					}
				}
			}
		}
		
	}

	@Override
	public ImageIcon toImageIcon() {
		switch (houseType) {
		case own1:
			return new ImageIcon("icons/ownerA1.png");
		case own2:
			return new ImageIcon("icons/ownerA2.png");
		case own3:
			return new ImageIcon("icons/ownerA3.png");
		case own4:
			return new ImageIcon("icons/ownerA4.png");
		case sale:
			return icon;
		default:
			return icon;
		}
	}

	@Override
	public String detail() {
		if(owner == null){
			return "���ͣ�House\n���ƣ�"+name+"\n���ؼ۸�2000Ԫ \n��ǰ�ȼ���1\nӵ���ߣ�(�ɹ�����״̬)";
		}
		else{
			return "���ͣ�House\n���ƣ�"+name+"\n���ؼ۸�"+originPrice*grade+"Ԫ \n��ǰ�ȼ���"+grade+"\nӵ���ߣ�"+owner.getName();
		}
	}

}
