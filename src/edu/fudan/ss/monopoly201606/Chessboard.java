package edu.fudan.ss.monopoly201606;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import edu.fudan.ss.monolopy201606.creatures.Player;

//游戏中的棋盘/地图
public class Chessboard {
	private int cellLength;
	private int gameRound;
	private Game game;
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player curPlayer = null;
	private ImageIcon[] icons = {new ImageIcon("icons/ava1.jpg"),new ImageIcon("icons/ava2.jpg"),
			new ImageIcon("icons/ava3.jpg"),new ImageIcon("icons/ava4.jpg")}; 
	
	public Chessboard(int x, Game game){
		super();
		this.cellLength = x;
		this.gameRound = 1;
		this.game = game;
	}
	
	public Game getgame(){
		return game;
	}
	
	public int getCellLength() {
		return cellLength;
	}
	
	public ArrayList<Cell> getCells(){
		return cells;
	}
	
	public Cell getCellAt(int x){
		return cells.stream().filter(item -> (item.getX() == x)).findFirst()
				.orElse(createCell(x));
	}
	
	public Cell createCell(int x){
		Cell c = new Cell(x, this);
		cells.add(c);
		return c;
	}
	
	public Player createPlayer(int i, String name){
		Player player = new Player(i, name, cells.get(0), icons[i-1]);
		players.add(player);
		return player;
	}
	
	public Player getPlayer(int index){
		return players.get(index);
	}
	
	public Player getPlayer(String name){
		return players.stream().filter(item -> (item.getName().equals(name))).findFirst()
				.orElse(getPlayer(0));
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}

	public int getGameRound() {
		return gameRound;
	}

	public void setGameRound(int gameRound) {
		this.gameRound = gameRound;
	}

	public Player getCurPlayer() {
		return curPlayer;
	}
	
	public int getPlayerIndex(Player player){
		int index = 0;
		for(int i = 0; i < players.size(); i++){
			if(players.get(i) == player){
				index = i;
				break;
			}
		}
		return index;
	}

	public void setCurPlayer(Player curPlayer) {
		if(curPlayer.isHurt()){
			curPlayer.setTreatday(curPlayer.getTreatday()-1);
			if(curPlayer.getTreatday() <= 0){
				curPlayer.setHurt(false);
			}
			int nextIndex = getPlayerIndex(curPlayer)+1;
			if(nextIndex >= players.size()){
				game.setRound();
				Game.time.setCurrentTime(game.getRound());
				nextIndex = nextIndex % players.size();
			}
			setCurPlayer(players.get(nextIndex));
		}
		else{
			this.curPlayer = curPlayer;
		}
	}
	
	public boolean hasSameName(String name){
		int sameName = players.stream().filter(item-> item.getName().equals(name)).collect(Collectors.toList()).size();
		if(sameName == 0)
			return false;
		else 
			return true;
	}
}
