package edu.fudan.ss.monopoly201606;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.fudan.ss.monolopy201606.creatures.Player;

//当前玩家信息页面
public class PlayerPanel extends JPanel {
	private Player player;
	
	public PlayerPanel(Player player){
		this.player = player;
		this.setBackground(new Color(155, 178, 194));
	}
	
	 protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int width = this.getWidth();
		
		g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		g.setColor(new Color(247, 210, 183));
		g.drawString("Player "+player.getI(), width/2-50, 80);
		
		g.setFont(new Font("宋体", Font.BOLD, 16));
		g.drawImage(player.toImageIcon().getImage(), width/2-50, 100, 50, 50, this);
		g.drawString(player.getName(), width/2+30, 128);
		g.drawString("点券  "+player.getTicketPoint(), width/2-50, 180);
		g.drawString("现金  "+player.getCash(), width/2-50, 205);
		g.drawString("存款  "+player.getDespoit(), width/2-50, 230);
		g.drawString("房产  "+player.totalHousesPrice(), width/2-50, 255);
		g.drawString("资金总额 "+player.totalAsset(), width/2-55, 280);
	 }

}
