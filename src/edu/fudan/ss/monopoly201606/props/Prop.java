package edu.fudan.ss.monopoly201606.props;

import edu.fudan.ss.monopoly201606.Chessboard;

//µÀ¾ß¿¨
public abstract class Prop {
	protected String propName;
	protected Chessboard chessboard;
	protected int ticket;
	public Prop(String propName, Chessboard chessboard, int ticket){
		this.propName = propName;
		this.chessboard = chessboard;
		this.ticket = ticket;
	}
	
	public abstract String getName();
	public abstract int getTicket();
	public abstract void useProp();
	public abstract String help();
}
