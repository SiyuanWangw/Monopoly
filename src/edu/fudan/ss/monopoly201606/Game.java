package edu.fudan.ss.monopoly201606;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import edu.fudan.ss.monopoly201606.props.Prop;
import edu.fudan.ss.monopoly201606.Share;
import edu.fudan.ss.monolopy201606.creatures.Bank;
import edu.fudan.ss.monolopy201606.creatures.Blank;
import edu.fudan.ss.monolopy201606.creatures.Direction;
import edu.fudan.ss.monolopy201606.creatures.Hospital;
import edu.fudan.ss.monolopy201606.creatures.House;
import edu.fudan.ss.monolopy201606.creatures.Lottery;
import edu.fudan.ss.monolopy201606.creatures.News;
import edu.fudan.ss.monolopy201606.creatures.Player;
import edu.fudan.ss.monolopy201606.creatures.PropPoint;
import edu.fudan.ss.monolopy201606.creatures.PropShop;
import edu.fudan.ss.monolopy201606.creatures.TicketPoint;
import edu.fudan.ss.monopoly201606.Cell;
import edu.fudan.ss.monopoly201606.Chessboard;
import edu.fudan.ss.monopoly201606.Time;

public class Game extends JFrame{
	private String[] character = new String[]{
			"空","◎","◎","◎","◎","◎","券","道","◎","◎",
			"◎","◎","◎","新","◎","◎","◎","◎","◎","医",
			"银","◎","◎","◎","◎","◎","彩","卡","◎","◎",
			"◎","◎","◎","券","道","◎","◎","◎","◎","◎",
			"新","卡","◎","◎","◎","◎","◎","银"};
	private	String[] positionNames = {//地图上每个位置的名称
			"空地","鼠街1号","鼠街2号","鼠街3号","鼠街4号","鼠街5号","票券点","道具店","牛街1号","牛街2号",
			"牛街3号","牛街4号","牛街5号","新闻","虎街1号","虎街2号","虎街3号","虎街4号","虎街5号","医院",
			"银行","兔街1号","兔街2号","兔街3号","兔街4号","兔街5号","彩票点","赠送道具卡","龙街1号","龙街2号",
			"龙街3号","龙街4号","龙街5号","票券点","道具店","蛇街1号","蛇街2号","蛇街3号","蛇街4号","蛇街5号",
			"新闻","赠送道具卡 ","马街1号","马街2号","马街3号","马街4号","马街5号","银行"};
	private String[] propNames = {
			"转向卡","滞留卡","遥控骰子","路障卡","查税卡","均富卡","掠夺卡"
	};
	private int playerNumber;
	public static Time time;
	private JPanel mainPanel;
	private int round = 0;
	private Share share = new Share();
	
	public Game(){
		setTitle("Monopoly");
		setSize(800, 560);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public MainPanel getMainPanel(){
		return (MainPanel) mainPanel;
	}
	
	//开始游戏
	public void run(){
		time = new Time(this);
		//设置JOptionpane的字体
		Font f = new Font("微软雅黑",Font.BOLD,14);
		UIManager.put("OptionPane.font", f);
	    UIManager.put("OptionPane.messageFont", f);
	    UIManager.put("OptionPane.buttonFont", f);
 
		Chessboard chessboard = buildBoard();
		menus(chessboard);
		mainPanel = new MainPanel((new ImageIcon("icons/back7.jpg")).getImage(), chessboard); 
	    this.add(mainPanel);
	    setPlayers(chessboard);   
	    
	}
	
	//初始化地图
	private Chessboard buildBoard(){
		int houseNum = 0;
		Chessboard chessboard = new Chessboard(character.length, this);
		for(int i = 0; i < character.length; i++){
			Cell curCell = chessboard.getCellAt(i);
			switch(character[i]){
			case "空": curCell.addCreature(new Blank(curCell, positionNames[i], new ImageIcon())); break;
			case "◎": curCell.addCreature(new House(curCell, positionNames[i], houseNum/5, new ImageIcon("icons/ownerA0.png"))); 
					houseNum++;	
					break;
			case "新": curCell.addCreature(new News(curCell, positionNames[i],new ImageIcon("icons/news.jpg"))); break;
			case "彩": curCell.addCreature(new Lottery(curCell, positionNames[i], new ImageIcon("icons/lottery.gif"))); break;
			case "券": curCell.addCreature(new TicketPoint(curCell, positionNames[i], new ImageIcon("icons/ticket.png"))); break;
			case "道": curCell.addCreature(new PropShop(curCell, positionNames[i], new ImageIcon("icons/toolsShop.png"))); break;
			case "卡": curCell.addCreature(new PropPoint(curCell, positionNames[i], new ImageIcon("icons/propPoint.jpg"))); break;
			case "银": curCell.addCreature(new Bank(curCell, positionNames[i], new ImageIcon("icons/bank.png")));break;
			case "医": curCell.addCreature(new Hospital(curCell, positionNames[i], new ImageIcon("icons/hospital.jpg")));break;
			}
		}
		return chessboard;
	}
	
	//设置玩家
	private void setPlayers(Chessboard chessboard){
		Object[] playerNum = {" 2 "," 3 "," 4 "};
		int option = -1;
		while(option == -1){
			option = JOptionPane.showOptionDialog(null, "请选择玩家数量", "玩家数量",0, JOptionPane.QUESTION_MESSAGE, null, playerNum, playerNum[0]);
		}
		if(option == 0){
			playerNumber = 2;
		}
		else if(option == 1){
			playerNumber = 3;
		}
		else if(option == 2){
			playerNumber = 4;
		}
		
		for(int i = 0; i < playerNumber; i++){
			String name = null;
			while(name == null){
				name = JOptionPane.showInputDialog("请输入第"+(i+1)+"个玩家的姓名:");
			}
			chessboard.getCellAt(0).addCreature(chessboard.createPlayer(i+1, name));
		}
		
		chessboard.setCurPlayer(chessboard.getPlayer(0));
		mainPanel.repaint();
	}
	
	//设置菜单
	private void menus(Chessboard chessboard) {
		
		JMenuBar menuBar = new JMenuBar();

		JMenu endMenu = new JMenu("认输");
		JMenu lookMenu = new JMenu("查看");
		JMenu propsMenu = new JMenu("道具");
		JMenu shareMenu = new JMenu("股市");
		endMenu.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lookMenu.setFont(new Font("微软雅黑", Font.BOLD, 14));
		propsMenu.setFont(new Font("微软雅黑", Font.BOLD, 14));
		shareMenu.setFont(new Font("微软雅黑", Font.BOLD, 14));
		
		menuBar.add(endMenu);
		menuBar.add(lookMenu);
		menuBar.add(propsMenu);
		menuBar.add(shareMenu);

		JMenuItem end = new JMenuItem("不玩了，认输", KeyEvent.VK_N);
		JMenuItem look1 = new JMenuItem("前方十步内预警", KeyEvent.VK_N);
		JMenuItem look2 = new JMenuItem("前后指定步数具体信息", KeyEvent.VK_N);
		JMenuItem look3 = new JMenuItem("玩家资产信息", KeyEvent.VK_N);
		JMenuItem props = new JMenuItem("使用道具", KeyEvent.VK_N);
		JMenuItem shareMarket = new JMenuItem("进入股市", KeyEvent.VK_N);
		end.setFont(new Font("微软雅黑", Font.BOLD, 14));
		look1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		look2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		look3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		props.setFont(new Font("微软雅黑", Font.BOLD, 14));
		shareMarket.setFont(new Font("微软雅黑", Font.BOLD, 14));

		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "你认输了");
				
				int nextIndex = chessboard.getPlayerIndex(chessboard.getCurPlayer());
				
				chessboard.getCurPlayer().removeSelf();
				chessboard.removePlayer(chessboard.getCurPlayer());
				
				if(nextIndex >= chessboard.getPlayers().size()){
					round++;
					time.setCurrentTime(round);
					
					nextIndex = nextIndex % chessboard.getPlayers().size();
				}
				chessboard.setCurPlayer(chessboard.getPlayer(nextIndex));
				repaint();
				giveInterest(time, chessboard);
				
				if(chessboard.getPlayers().size() <= 1){
					JOptionPane.showMessageDialog(null,
							"游戏结束！获胜者是"+chessboard.getPlayer(0).getName());			
					System.exit(0);
				}
			}
		});
		
		look1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean hasBlock = false;
				int step = 0;
				for(int i = 0; i < 10; i++){
					if(chessboard.getCurPlayer().getCurCell().getCellAt(chessboard.getCurPlayer().getDirection(), i+1).hasRoadBlock()){
						hasBlock = true;
						step = i+1;
						break;
					}
				}
				if(hasBlock)
					JOptionPane.showMessageDialog(null, "前方第"+step+"步上有路障");
				else 
					JOptionPane.showMessageDialog(null, "前方十步内没有路障");
				
			}
		});

		look2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lookDetail(chessboard.getCurPlayer().getDirection(), chessboard.getCurPlayer().getCurCell());
			}
		});

		look3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(1, chessboard.getPlayers().size()));
				for(int i = 0; i < chessboard.getPlayers().size(); i++){
					panel.add(new PlayerPanel(chessboard.getPlayer(i)));
				}

				JFrame lookFrame = new JFrame();
				lookFrame.add(panel);
				lookFrame.setTitle("玩家资产信息");
				lookFrame.setSize(750, 500);
				lookFrame.setLocationRelativeTo(null);
				lookFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				lookFrame.setVisible(true);
			}
		});

		props.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useProp(chessboard.getCurPlayer());
			}
		});
		
		shareMarket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!share.isTodaySet() && time.isWeekDay()){
					share.setPrices();
					share.setTodaySet(true);
				}
				SharePanel sharePanel = new SharePanel(share, chessboard.getCurPlayer());
			}
		});
		
		endMenu.add(end);
		lookMenu.add(look1);
		lookMenu.add(look2);
		lookMenu.add(look3);
		propsMenu.add(props);
		shareMenu.add(shareMarket);

		this.setJMenuBar(menuBar);
	}
	
	//查看距离一定点数的具体信息 
	private void lookDetail(Direction direction, Cell curcell){
		//输入合法性
		String detailStep = JOptionPane.showInputDialog("请输入想查询的点与您相差的步数（后方用负数）：");
		if(detailStep != null){
			while(!isNumeric(detailStep)){
				detailStep = JOptionPane.showInputDialog("输入不合法，请重新输入：");
				if(detailStep == null){
					return ;
				}
			}
			Direction nextDir = direction;
			if(Integer.parseInt(detailStep) < 0){
				if(direction == Direction.clockwise)
					nextDir = Direction.eastern;
				else 
					nextDir = Direction.clockwise;
			}
			Cell lookCell = curcell.getCellAt(nextDir, Math.abs(Integer.parseInt(detailStep)));
			JOptionPane.showMessageDialog(null,lookCell.getCreature(0).detail());
		}
	}
	
	//使用道具
	private void useProp(Player curplayer) {
		boolean end = false;
		String propName = "";
		ArrayList<String> obj1 = new ArrayList<String>();
		String askStr = "你现在拥有的道具如下：";
		for(int i = 0; i < propNames.length; i++){
			askStr += "\n" + i+"、"+propNames[i]+curplayer.getPropNums()[i]+"张";
			if(curplayer.getPropNums()[i] > 0){
				obj1.add(propNames[i]);
			}
		}
		askStr += "\n请选择你想要使用的卡片编号<选择h获得帮助>：";
		obj1.add("h");
		String choice = "";
		boolean continueProp = true;
		do{
			choice = (String)JOptionPane.showInputDialog(null,askStr+"\n", "使用道具", JOptionPane.PLAIN_MESSAGE, null, obj1.toArray(), obj1.get(0));  

			if(choice != null){
				if(choice.compareToIgnoreCase("h")==0){
					String help = "帮助：";
					for(int i = 0; i < propNames.length; i++){
						if(curplayer.hasProp(propNames[i])){
							help += "\n"+curplayer.getProp(propNames[i]).help();;
						}
					}
					JOptionPane.showMessageDialog(null, help);
				}
				else{
					propName = choice;
					if(curplayer.hasProp(propName)){
						Prop prop = curplayer.getProp(propName);
						prop.useProp();
						//curplayer.removeProp(prop);
						continueProp = false;
					}
				}
			}
			else{
				continueProp = false;
			}
		}while (continueProp);
		
	}
	
	//月末发利息
	public static void giveInterest(Time time, Chessboard chessboard){
		if(time.isMonthEnd()){
			for(int i = 0; i < chessboard.getPlayers().size(); i++){
				chessboard.getPlayer(i).setDespoit(chessboard.getPlayer(i).getDespoit()/10);
			}
			JOptionPane.showMessageDialog(null,
					"月末，银行发放10%储金利息");
			chessboard.getgame().getMainPanel().repaint();
		}
	}
	
	//判断输入是否是数字
	public static boolean isNumeric(String str){
		boolean isNum = true;
		if(str.equals("")){
			return false;
		}
		 if(str.charAt(0)=='-' || Character.isDigit(str.charAt(0))){
			for (int i = 1; i < str.length(); i++){
				if(!Character.isDigit(str.charAt(i))){
				    isNum = false;
				    break;
				}
			}
		 }
		 else{
			 isNum = false;
		 }
		 return isNum;
	}
	
	public static boolean isDouble(String str){
		 boolean isDouble = true;
		 int pointNum = 0;
		 if(str.equals("")){
			 return false;
		 }
		 if(str.charAt(0)=='-' || Character.isDigit(str.charAt(0))){
			for (int i = 1; i < str.length(); i++){
				if(!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.'){
					isDouble = false;
				    break;
				}
				else if(str.charAt(i) == '.'){
					pointNum++;
				}
			}
			if(pointNum > 1){
				isDouble = false;
			}
		 }
		 else{
			 isDouble = false;
		 }
		 if(pointNum == 1){
			 String[] splitStrs = str.split("\\.");
			 if(splitStrs[splitStrs.length-1].length() > 2)
				 isDouble = false;
		 }
		 return isDouble;
	}
	
	public static boolean inputLegal(ArrayList<Player> players, String name){
		boolean inputLegal = false;
		for(Player player: players){
			if(player.getName().equals(name)){
				inputLegal = true;
				break;
			}
		}
		return inputLegal;
	}
	
	//截取两位小数数字
	public static double getTwoPointNum(double a){
		DecimalFormat df = new DecimalFormat("#.00");  
		return Double.parseDouble(df.format(a));
	}
	
	public Share getShare(){
		return share;
	}
	
	public int getRound(){
		return round;
	}

	public void setRound(){
		this.round++;
	}
}

