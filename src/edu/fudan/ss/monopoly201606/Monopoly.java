package edu.fudan.ss.monopoly201606;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//开始程序页面
public class Monopoly extends JFrame{
	public Monopoly(){
		add(new NewGame(this));
	}
	
	public static void main(String[] args){ 
		JFrame frame = new Monopoly();
		frame.setTitle("Monopoly");
		frame.setSize(800,560);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class NewGame extends JPanel{
	private JFrame frame;
	private Game game;
	private JLabel enter = new JLabel(">>> 进入游戏");
	
	public NewGame(Monopoly frame){
		this.frame = frame;
		add(enter);
		
		enter.addMouseListener(new NewGameListener());
		
		
	}
	
	class NewGameListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			game = new Game();
			game.setVisible(true);
			frame.setVisible(false);
			game.run();			
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
			
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		ImageIcon backImage = new ImageIcon("icons/back8.jpg");
		Image back = backImage.getImage();
		g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
		
		enter.setFont(new Font("宋体", Font.BOLD, 20));
		enter.setForeground(Color.WHITE);
		enter.setLocation(65, 120);
	}
}