package edu.fudan.ss.monopoly201606;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import edu.fudan.ss.monolopy201606.creatures.Bank;
import edu.fudan.ss.monolopy201606.creatures.Creature;
import edu.fudan.ss.monolopy201606.creatures.Direction;
import edu.fudan.ss.monolopy201606.creatures.News;
import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monolopy201606.creatures.RoadBlock;

//地图中每一个格子
public class Cell {
	private int x;
	private Chessboard chessboard;
	private ArrayList<Creature> creatures = new ArrayList<Creature>();
	private int xLocation;
	private int yLocation;
	
	public Cell(int x, Chessboard chessboard){
		this.x = x;
		this.chessboard = chessboard;
	}

	public int getX(){
		return x;
	}
	
	public Chessboard getChessboard(){
		return chessboard;
	}
	
	public void addCreature(Creature creature){
		creatures.add(creature);
	}
	
	public void removeCreature(Creature creature) {
		creatures.remove(creature);
	}
	
	public Creature getCreature(int index){
		return creatures.get(index);
	}
	
	//得到当前格子的图标
	public ImageIcon toImageIcon(String playerName) {
		Creature cHelp1 = creatures.stream().filter(item -> item instanceof RoadBlock ).findFirst().orElse(creatures.get(0));
		Creature cHelp2 = creatures.stream().filter(item -> item instanceof Player ).findFirst().orElse(cHelp1);
		Creature c = creatures.stream().filter(item -> item.getName()==playerName ).findFirst().orElse(cHelp2);
		return c.toImageIcon();
	}

	public Cell getCellAt(Direction direction, int stepNum) {
		switch (direction) {
		case clockwise:
			return chessboard.getCellAt((x + stepNum)%48);
		case eastern:
			if(x-stepNum < 0){
				return chessboard.getCellAt((x-stepNum)%48+48);
			}
			else {
				return chessboard.getCellAt((x-stepNum)%48);
			}
		default:
			return chessboard.getCellAt((x + stepNum)%48);
		}
	}

	public int getxLocation() {
		return xLocation;
	}

	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public boolean hasRoadBlock() {
		return creatures.stream().anyMatch(e->e instanceof RoadBlock);
	}
	
	public Creature getRoadBlock() {
		return creatures.stream().filter(item -> item instanceof RoadBlock ).findFirst().orElse(creatures.get(0));
	}
	
	public boolean hasBank() {
		return creatures.stream().anyMatch(e->e instanceof Bank);
	}
	
	public Creature getBank() {
		return creatures.stream().filter(item -> item instanceof Bank ).findFirst().orElse(creatures.get(0));
	}
	
	public boolean hasNews() {
		return creatures.stream().anyMatch(e->e instanceof News);
	}
}
