package edu.fudan.ss.monolopy201606.creatures;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Game;

//房屋
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
		//可出售房屋
		if(owner == null){
			int answer1 = JOptionPane.showConfirmDialog(null, "你到达了"+name+"，是否购买房屋？","房屋购买", JOptionPane.YES_NO_OPTION);
			if(answer1 == 0 ){
				if(player.getCash() >= originPrice*grade){
					player.setCash(-originPrice*grade);
					setOwner(player);
					player.addOwnHouse(this);
					JOptionPane.showMessageDialog(null, "成功购买"+name);
				}
				else if(player.getCash()+player.getDespoit() >= originPrice*grade){
					player.setDespoit(player.getCash()-originPrice*grade);
					player.setCash(-player.getCash());
					setOwner(player);
					player.addOwnHouse(this);
					JOptionPane.showMessageDialog(null, "成功购买"+name);
				}	
				else{
					JOptionPane.showMessageDialog(null, "资金不够，不能购买此房屋！");
				}
			}
		}
		//自己的房屋
		else if(owner.getName().equals(player.getName())){
			if(grade < 6){
				int answer2 = JOptionPane.showConfirmDialog(null, "此房屋属于你，你要升级吗？","房屋升级", JOptionPane.YES_NO_OPTION);
				if(answer2 == 0 ){
					if(player.getCash() >= originPrice/2){
						upgrade();
						owner.setCash(-originPrice/2);
						JOptionPane.showMessageDialog(null, "升级成功，目前等级为"+grade);
					}
					else {
						JOptionPane.showMessageDialog(null, "你现金不够，无法升级！");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "此房屋目前等级为六，无法再进行升级！");
			}
		}
		//别人的房屋
		else{
			ArrayList<House> markupHouses = owner.markupHouse(streetNum, this);
			double fee = originPrice*grade*0.3;
			for(House house:markupHouses){
				fee += house.getOriginPrice()*house.getGrade()*0.1;
			}
			if(fee <= player.getCash()){
				JOptionPane.showMessageDialog(null, "你到达了玩家"+owner.getName()+"的房子，需要付过路费\n"+"扣除现金"+fee+"元 ");
				player.setCash(-fee);
				owner.setCash(fee);
			}
			else if(fee <= player.getCash()+player.getDespoit()){
				JOptionPane.showMessageDialog(null, "你到达了玩家"+owner.getName()+"的房子，需要付过路费\n"+"扣除全部现金"+player.getCash()+"元之后，还从存款中扣除"+(fee-player.getCash()) +"元");
				player.setDespoit(-fee+player.getCash());
				player.setCash(-player.getCash());
				owner.setCash(fee);
			}
			else{
				JOptionPane.showMessageDialog(null, "你到达了玩家"+owner.getName()+"的房子，需要付过路费\n"+"但你的现有资金包括现金存款不够支付过路费，需要卖土地:");
				double curMoney = player.getCash()+player.getDespoit();
				ArrayList<House> removeHouses = new ArrayList<House>();
				for(House house: player.getOwnHouses()){
					removeHouses.add(house);
					curMoney += house.getOriginPrice()*house.getGrade();
					JOptionPane.showMessageDialog(null, "你拥有的"+house.getName()+"被抵押");
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
					JOptionPane.showMessageDialog(null, "付给玩家"+owner.getName()+fee+"元");
				}
				else{
					JOptionPane.showMessageDialog(null, "你已破产，离开游戏，所有资金充作过路费");
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
								"游戏结束！获胜者是"+chessboard.getPlayer(0).getName());			
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
			return "类型：House\n名称："+name+"\n土地价格：2000元 \n当前等级：1\n拥有者：(可供出售状态)";
		}
		else{
			return "类型：House\n名称："+name+"\n土地价格："+originPrice*grade+"元 \n当前等级："+grade+"\n拥有者："+owner.getName();
		}
	}

}
