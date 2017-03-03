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
			"��","��","��","��","��","��","ȯ","��","��","��",
			"��","��","��","��","��","��","��","��","��","ҽ",
			"��","��","��","��","��","��","��","��","��","��",
			"��","��","��","ȯ","��","��","��","��","��","��",
			"��","��","��","��","��","��","��","��"};
	private	String[] positionNames = {//��ͼ��ÿ��λ�õ�����
			"�յ�","���1��","���2��","���3��","���4��","���5��","Ʊȯ��","���ߵ�","ţ��1��","ţ��2��",
			"ţ��3��","ţ��4��","ţ��5��","����","����1��","����2��","����3��","����4��","����5��","ҽԺ",
			"����","�ý�1��","�ý�2��","�ý�3��","�ý�4��","�ý�5��","��Ʊ��","���͵��߿�","����1��","����2��",
			"����3��","����4��","����5��","Ʊȯ��","���ߵ�","�߽�1��","�߽�2��","�߽�3��","�߽�4��","�߽�5��",
			"����","���͵��߿� ","���1��","���2��","���3��","���4��","���5��","����"};
	private String[] propNames = {
			"ת��","������","ң������","·�Ͽ�","��˰��","������","�ӶῨ"
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
	
	//��ʼ��Ϸ
	public void run(){
		time = new Time(this);
		//����JOptionpane������
		Font f = new Font("΢���ź�",Font.BOLD,14);
		UIManager.put("OptionPane.font", f);
	    UIManager.put("OptionPane.messageFont", f);
	    UIManager.put("OptionPane.buttonFont", f);
 
		Chessboard chessboard = buildBoard();
		menus(chessboard);
		mainPanel = new MainPanel((new ImageIcon("icons/back7.jpg")).getImage(), chessboard); 
	    this.add(mainPanel);
	    setPlayers(chessboard);   
	    
	}
	
	//��ʼ����ͼ
	private Chessboard buildBoard(){
		int houseNum = 0;
		Chessboard chessboard = new Chessboard(character.length, this);
		for(int i = 0; i < character.length; i++){
			Cell curCell = chessboard.getCellAt(i);
			switch(character[i]){
			case "��": curCell.addCreature(new Blank(curCell, positionNames[i], new ImageIcon())); break;
			case "��": curCell.addCreature(new House(curCell, positionNames[i], houseNum/5, new ImageIcon("icons/ownerA0.png"))); 
					houseNum++;	
					break;
			case "��": curCell.addCreature(new News(curCell, positionNames[i],new ImageIcon("icons/news.jpg"))); break;
			case "��": curCell.addCreature(new Lottery(curCell, positionNames[i], new ImageIcon("icons/lottery.gif"))); break;
			case "ȯ": curCell.addCreature(new TicketPoint(curCell, positionNames[i], new ImageIcon("icons/ticket.png"))); break;
			case "��": curCell.addCreature(new PropShop(curCell, positionNames[i], new ImageIcon("icons/toolsShop.png"))); break;
			case "��": curCell.addCreature(new PropPoint(curCell, positionNames[i], new ImageIcon("icons/propPoint.jpg"))); break;
			case "��": curCell.addCreature(new Bank(curCell, positionNames[i], new ImageIcon("icons/bank.png")));break;
			case "ҽ": curCell.addCreature(new Hospital(curCell, positionNames[i], new ImageIcon("icons/hospital.jpg")));break;
			}
		}
		return chessboard;
	}
	
	//�������
	private void setPlayers(Chessboard chessboard){
		Object[] playerNum = {" 2 "," 3 "," 4 "};
		int option = -1;
		while(option == -1){
			option = JOptionPane.showOptionDialog(null, "��ѡ���������", "�������",0, JOptionPane.QUESTION_MESSAGE, null, playerNum, playerNum[0]);
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
				name = JOptionPane.showInputDialog("�������"+(i+1)+"����ҵ�����:");
			}
			chessboard.getCellAt(0).addCreature(chessboard.createPlayer(i+1, name));
		}
		
		chessboard.setCurPlayer(chessboard.getPlayer(0));
		mainPanel.repaint();
	}
	
	//���ò˵�
	private void menus(Chessboard chessboard) {
		
		JMenuBar menuBar = new JMenuBar();

		JMenu endMenu = new JMenu("����");
		JMenu lookMenu = new JMenu("�鿴");
		JMenu propsMenu = new JMenu("����");
		JMenu shareMenu = new JMenu("����");
		endMenu.setFont(new Font("΢���ź�", Font.BOLD, 14));
		lookMenu.setFont(new Font("΢���ź�", Font.BOLD, 14));
		propsMenu.setFont(new Font("΢���ź�", Font.BOLD, 14));
		shareMenu.setFont(new Font("΢���ź�", Font.BOLD, 14));
		
		menuBar.add(endMenu);
		menuBar.add(lookMenu);
		menuBar.add(propsMenu);
		menuBar.add(shareMenu);

		JMenuItem end = new JMenuItem("�����ˣ�����", KeyEvent.VK_N);
		JMenuItem look1 = new JMenuItem("ǰ��ʮ����Ԥ��", KeyEvent.VK_N);
		JMenuItem look2 = new JMenuItem("ǰ��ָ������������Ϣ", KeyEvent.VK_N);
		JMenuItem look3 = new JMenuItem("����ʲ���Ϣ", KeyEvent.VK_N);
		JMenuItem props = new JMenuItem("ʹ�õ���", KeyEvent.VK_N);
		JMenuItem shareMarket = new JMenuItem("�������", KeyEvent.VK_N);
		end.setFont(new Font("΢���ź�", Font.BOLD, 14));
		look1.setFont(new Font("΢���ź�", Font.BOLD, 14));
		look2.setFont(new Font("΢���ź�", Font.BOLD, 14));
		look3.setFont(new Font("΢���ź�", Font.BOLD, 14));
		props.setFont(new Font("΢���ź�", Font.BOLD, 14));
		shareMarket.setFont(new Font("΢���ź�", Font.BOLD, 14));

		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "��������");
				
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
							"��Ϸ��������ʤ����"+chessboard.getPlayer(0).getName());			
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
					JOptionPane.showMessageDialog(null, "ǰ����"+step+"������·��");
				else 
					JOptionPane.showMessageDialog(null, "ǰ��ʮ����û��·��");
				
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
				lookFrame.setTitle("����ʲ���Ϣ");
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
	
	//�鿴����һ�������ľ�����Ϣ 
	private void lookDetail(Direction direction, Cell curcell){
		//����Ϸ���
		String detailStep = JOptionPane.showInputDialog("���������ѯ�ĵ��������Ĳ��������ø�������");
		if(detailStep != null){
			while(!isNumeric(detailStep)){
				detailStep = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
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
	
	//ʹ�õ���
	private void useProp(Player curplayer) {
		boolean end = false;
		String propName = "";
		ArrayList<String> obj1 = new ArrayList<String>();
		String askStr = "������ӵ�еĵ������£�";
		for(int i = 0; i < propNames.length; i++){
			askStr += "\n" + i+"��"+propNames[i]+curplayer.getPropNums()[i]+"��";
			if(curplayer.getPropNums()[i] > 0){
				obj1.add(propNames[i]);
			}
		}
		askStr += "\n��ѡ������Ҫʹ�õĿ�Ƭ���<ѡ��h��ð���>��";
		obj1.add("h");
		String choice = "";
		boolean continueProp = true;
		do{
			choice = (String)JOptionPane.showInputDialog(null,askStr+"\n", "ʹ�õ���", JOptionPane.PLAIN_MESSAGE, null, obj1.toArray(), obj1.get(0));  

			if(choice != null){
				if(choice.compareToIgnoreCase("h")==0){
					String help = "������";
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
	
	//��ĩ����Ϣ
	public static void giveInterest(Time time, Chessboard chessboard){
		if(time.isMonthEnd()){
			for(int i = 0; i < chessboard.getPlayers().size(); i++){
				chessboard.getPlayer(i).setDespoit(chessboard.getPlayer(i).getDespoit()/10);
			}
			JOptionPane.showMessageDialog(null,
					"��ĩ�����з���10%������Ϣ");
			chessboard.getgame().getMainPanel().repaint();
		}
	}
	
	//�ж������Ƿ�������
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
	
	//��ȡ��λС������
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

