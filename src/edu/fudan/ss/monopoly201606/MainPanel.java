package edu.fudan.ss.monopoly201606;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.fudan.ss.monolopy201606.creatures.Player;

//绘制游戏主界面
public class MainPanel extends JPanel{
	private Image im;  
	private Chessboard chessboard;
	private JButton diceButton = new JButton();
	private Dice dice = new Dice();
	private ImageIcon diceIcon = dice.getDiceImage(2);
	
    public MainPanel(Image im, Chessboard chessboard)  
    {  
    	this.setBounds(0, 0, 800, 560);
    	this.im=im;  
        this.setOpaque(true);  
        this.chessboard = chessboard;
        
        add(diceButton);
		diceButton.setIcon(dice.getDiceImage(2));
		diceButton.setBorderPainted(false); 
        diceButton.setBorder(BorderFactory.createRaisedBevelBorder()); 
		
		diceButton.addMouseListener(new RollActionListener());
    } 
    
    public Dice getDice(){
    	return dice;
    }
    
    //骰子按钮的监听事件
    class RollActionListener implements MouseListener{
		
		Timer timer =  new Timer(80, new ActionListener(){
			int x = 0;
			public void actionPerformed(ActionEvent e){
				diceIcon = dice.getDiceImage(x%6);
				diceButton.setIcon(diceIcon);
				x++;
				if(x==10){
					timer.stop();
					x = 0;
					if(!dice.getUnderControl()){
						dice.setDiceNumber((int)(Math.random() * 6 + 1));
					}
					int diceNum = dice.getDiceNumber();
					
					if(diceNum > 0){
						diceIcon = dice.getDiceImage(diceNum-1);
					}
					else {
						diceIcon = new ImageIcon();
					}
					diceButton.setIcon(diceIcon);
					
					//掷骰子之后的前进
					chessboard.getCurPlayer().step(dice.getDiceNumber());
					
					//下一个玩家
					int nextIndex = chessboard.getPlayerIndex(chessboard.getCurPlayer())+1;
					if(nextIndex >= chessboard.getPlayers().size()){
						chessboard.getgame().setRound();
						Game.time.setCurrentTime(chessboard.getgame().getRound());
						
						nextIndex = nextIndex % chessboard.getPlayers().size();
					}
					chessboard.setCurPlayer(chessboard.getPlayer(nextIndex));
					repaint();
					Game.giveInterest(Game.time, chessboard);
					
					//判断游戏是否结束
					if(chessboard.getPlayers().size() <= 1){
						JOptionPane.showMessageDialog(null,
								"游戏结束！获胜者是"+chessboard.getPlayer(0).getName());			
						System.exit(0);
					}
				}
			}
		});
    	
    

		@Override
		public void mouseClicked(MouseEvent e) {
			timer.start();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}}
    
    protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int width = this.getWidth();
        int height = this.getHeight();
        g.drawImage(im,0,0,width,height,this);  
        
        /*
        diceButton.setIcon(diceIcon);
        add(diceButton);*/
        diceButton.setBounds(300, 200, 55, 55);
        
        for(int i = 0; i < 12; i++){
			chessboard.getCells().get(i).setxLocation(20 + i*(width-30)/15);
			chessboard.getCells().get(i).setyLocation(20);
		}
		
        for(int k = 12; k < 18; k++){
        	chessboard.getCells().get(k).setxLocation(20 + 11*(width-30)/15);
			chessboard.getCells().get(k).setyLocation(20 + (k-11)*(height-25)/11);
        }
        
        for(int l = 18; l < 20; l++){
        	chessboard.getCells().get(l).setxLocation(20 + (l-6)*(width-30)/15);
			chessboard.getCells().get(l).setyLocation(20 + 6*(height-25)/11);
        }
        
		for(int j = 20; j < 24; j++){
			chessboard.getCells().get(j).setxLocation(20 + 14*(width-30)/15);
			chessboard.getCells().get(j).setyLocation(20 + (j-14)*(height-25)/11);
		}
		
		for(int m = 24; m < 39; m++){
			chessboard.getCells().get(m).setxLocation((width-10)-(m-23)*(width-30)/15);
			chessboard.getCells().get(m).setyLocation(height - 5 - (height-25)/11);
		}
		
		for(int n = 39; n < 48; n++){
			chessboard.getCells().get(n).setxLocation(20);
			chessboard.getCells().get(n).setyLocation((height-5)-(n-37)*(height-25)/11);
		}
          
		for(int z = 0; z < chessboard.getCellLength(); z++){
			if(chessboard.getCurPlayer() == null){
				g.drawImage(chessboard.getCells().get(z).toImageIcon("").getImage(), chessboard.getCells().get(z).getxLocation(), chessboard.getCells().get(z).getyLocation(), (width-30)/14-20, (height-30)/12-10, this);
		
			}
			else{
				g.drawImage(chessboard.getCells().get(z).toImageIcon(chessboard.getCurPlayer().getName()).getImage(), chessboard.getCells().get(z).getxLocation(), chessboard.getCells().get(z).getyLocation(), (width-30)/14-20, (height-30)/12-10, this);
			}
		}
		
		//当前时间
		g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 25));
		g.setColor(new Color(230, 147, 103));
		g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		g.drawString("Today is ", width-160, 30);
		g.drawString(Game.time.getDay()+"/"+Game.time.getMonth()+"/"+Game.time.getYear(), width-120, 52);
		g.setFont(new Font("Segoe Print", Font.BOLD, 16));
		g.drawString(Game.time.getWeekDay().toString(), width-115, 75);
		
		//当前玩家信息
		if(chessboard.getCurPlayer() != null){
			Player curPlayer = chessboard.getCurPlayer();
			g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
			g.setColor(new Color(247, 210, 183));
			g.drawString("Current Player ", width-160, 105);
			
			g.setFont(new Font("宋体", Font.BOLD, 15));
			g.drawImage(curPlayer.toImageIcon().getImage(), width-150, 115, 40, 40, this);
			g.drawString(curPlayer.getName(), width-90, 140);
			g.drawString("点券  "+curPlayer.getTicketPoint(), width-147, 175);
			g.drawString("现金  "+curPlayer.getCash(), width-147, 195);
			g.drawString("存款  "+curPlayer.getDespoit(), width-147, 215);
			g.drawString("房产  "+curPlayer.totalHousesPrice(), width-147, 235);
			g.drawString("资金总额 "+curPlayer.totalAsset(), width-155, 255);
		
		}
	}
}
