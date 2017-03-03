package edu.fudan.ss.monolopy201606.creatures;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Game;
import edu.fudan.ss.monopoly201606.props.AverageWealth;
import edu.fudan.ss.monopoly201606.props.BlockCard;
import edu.fudan.ss.monopoly201606.props.ChangeDirection;
import edu.fudan.ss.monopoly201606.props.PayTax;
import edu.fudan.ss.monopoly201606.props.Prop;
import edu.fudan.ss.monopoly201606.props.RemoteDice;
import edu.fudan.ss.monopoly201606.props.Residence;
import edu.fudan.ss.monopoly201606.props.RobCard;

public class Player extends Creature {
	private int i = 1;
	private Direction direction = Direction.clockwise;
	private int ticketPoint = 10000;
	private double cash = 10000;
	private double despoit = 10000;
	private ArrayList<House> ownHouses = new ArrayList<House>();
	private int[] ownShares = new int[10];
	private int[] propNums = {2,2,2,2,2,2,2};//changeDirection, residence,remoteDice,BlockCard,PayTax,averageWealth,RobCard
	private ArrayList<Prop> ownProps = new ArrayList<Prop>();
	private int curStep = 0;
	private boolean isHurt = false;
	private int treatday = 0;
	
	public Player(int i, String name, Cell cell, ImageIcon icon){ 
		super(cell, name, icon);
		this.i = i;
		this.name = name;
		//初始化道具
		ownProps.add(new ChangeDirection(getCurCell().getChessboard()));
		ownProps.add(new ChangeDirection(getCurCell().getChessboard()));
		ownProps.add(new Residence(getCurCell().getChessboard()));
		ownProps.add(new Residence(getCurCell().getChessboard()));
		ownProps.add(new RemoteDice(getCurCell().getChessboard()));
		ownProps.add(new RemoteDice(getCurCell().getChessboard()));
		ownProps.add(new BlockCard(getCurCell().getChessboard()));
		ownProps.add(new BlockCard(getCurCell().getChessboard()));
		ownProps.add(new PayTax(getCurCell().getChessboard()));
		ownProps.add(new PayTax(getCurCell().getChessboard()));
		ownProps.add(new AverageWealth(getCurCell().getChessboard()));
		ownProps.add(new AverageWealth(getCurCell().getChessboard()));
		ownProps.add(new RobCard(getCurCell().getChessboard()));
		ownProps.add(new RobCard(getCurCell().getChessboard()));
	}
	
	public int getI(){
		return i;
	}
	
	public String getName(){
		return name;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public String directionTexture(){
		if(direction == Direction.clockwise){
			return "顺时针";
		}
		else{
			return "逆时针";
		}
	}

	public Cell getCurCell(){
		return cell;
	}
	
	public int[] getPropNums(){
		return propNums;
	}
	
	public void setPropNums(int index, int num){
		propNums[index] += num;
	}
	
	public void addProp(Prop prop){
		ownProps.add(prop);
		switch (prop.getName()) {
		case "转向卡":
			propNums[0]++;
			break;
		case "滞留卡":
			propNums[1]++;
			break;
		case "遥控骰子":
			propNums[2]++;
			break;
		case "路障卡":
			propNums[3]++;
			break;
		case "查税卡":
			propNums[4]++;
			break;
		case "均富卡":
			propNums[5]++;
			break;
		case "掠夺卡":
			propNums[6]++;
			break;
		default:
			break;
		}
	}
	
	public Prop getProp(String name){
		return ownProps.stream().filter(item -> (item.getName() == name)).findFirst()
				.orElse(ownProps.get(0));
	}
	
	public boolean hasProp(String name){
		return ownProps.stream().anyMatch(e->e.getName() == name);
	}
	
	public Prop removeProp(int index){
		Prop removeProp =  ownProps.remove(index);
		switch (removeProp.getName()) {
		case "转向卡":
			propNums[0]--;
			break;
		case "滞留卡":
			propNums[1]--;
			break;
		case "遥控骰子":
			propNums[2]--;
			break;
		case "路障卡":
			propNums[3]--;
			break;
		case "查税卡":
			propNums[4]--;
			break;
		case "均富卡":
			propNums[5]--;
			break;
		case "掠夺卡":
			propNums[6]--;
			break;
		default:
			break;
		}
		return removeProp;
	}
	
	public void removeProp(Prop prop){
		ownProps.remove(prop);
		switch (prop.getName()) {
		case "转向卡":
			propNums[0]--;
			break;
		case "滞留卡":
			propNums[1]--;
			break;
		case "遥控骰子":
			propNums[2]--;
			break;
		case "路障卡":
			propNums[3]--;
			break;
		case "查税卡":
			propNums[4]--;
			break;
		case "均富卡":
			propNums[5]--;
			break;
		case "掠夺卡":
			propNums[6]--;
			break;
		default:
			break;
		}
	}
	
	public ArrayList<Prop> getProps(){
		return ownProps;
	}
	
	
	public int getCurStep(){
		return curStep;
	}
	
	//前进stepNum步
	public void step(int stepNum) {
		curStep = stepNum;
		boolean meetRoadBlock = false;
		for(int i = 0; i < stepNum-1; i++){
			Cell crossCell = cell.getCellAt(direction, i+1);
			if(crossCell.hasBank()){
				crossCell.getBank().eventHappen(this);
			}
			else if(crossCell.hasRoadBlock()){
				curStep = i+1;
				meetRoadBlock = true;
				break;
			}
		}
		Cell movingCell = cell.getCellAt(direction, curStep);
		
		if(meetRoadBlock){
			movingCell.getRoadBlock().eventHappen(this);
			movingCell.removeCreature(movingCell.getRoadBlock());
		}
		else{
			if(stepNum == 0)
				JOptionPane.showMessageDialog(null, "你停在原地没动");
			else 
				JOptionPane.showMessageDialog(null, "你顺利走了"+stepNum+"步");
		}
		
		removeSelf();
		moveTo(movingCell);
		
	}

	public void removeSelf(){
		cell.removeCreature(this);
	}
	
	public void moveTo(Cell movingCell) {
		movingCell.addCreature(this);
		cell = movingCell;
		cell.getCreature(0).eventHappen(this);
	}

	public int getTicketPoint() {
		return ticketPoint;
	}

	public void setTicketPoint(int addticketpoint){
		ticketPoint += addticketpoint;
	}

	public double getCash() {
		return Game.getTwoPointNum(cash);
	}

	public void setCash(double addcash){
		cash += addcash;
	}

	public double getDespoit() {
		return Game.getTwoPointNum(despoit);
	}

	public void setDespoit(double adddespoit){
		despoit += adddespoit;
	}

	public ArrayList<House> getOwnHouses() {
		return ownHouses;
	}

	public void setOwnHouses(ArrayList<House> ownHouses) {
		this.ownHouses = ownHouses;
	}
	
	public void addOwnHouse(House house){
		ownHouses.add(house);
	}
	
	public void removeOwnHouse(House house){
		ownHouses.remove(house);
	}
	
	public ArrayList<House> markupHouse(int streetNum, House house){
		ArrayList<House> markupHouse = (ArrayList<House>) ownHouses.stream().filter(item-> item.getStreetNum()==streetNum && !item.equals(house)).collect(Collectors.toList());
		return markupHouse;
	}

	public double totalHousesPrice(){
		double totalPrice = 0;
		for(House house: ownHouses){
			totalPrice += house.getOriginPrice()*house.getGrade();
		}
		return Game.getTwoPointNum(totalPrice);
	}
	
	public double totalAsset(){
		return Game.getTwoPointNum(cash+despoit+totalHousesPrice());
	}

	@Override
	public void eventHappen(Player player) {
		
	}

	public int getOwnShare(int index) {
		return ownShares[index];
	}

	public void setOwnShares(int number, int index) {
		this.ownShares[index] += number;
	}

	@Override
	public ImageIcon toImageIcon() {
		return icon;
	}

	@Override
	public String detail() {
		return "";
	}

	public boolean isHurt() {
		return isHurt;
	}

	public void setHurt(boolean isHurt) {
		this.isHurt = isHurt;
	}

	public int getTreatday() {
		return treatday;
	}

	public void setTreatday(int treatday) {
		this.treatday = treatday;
	}
	
}
