package edu.fudan.ss.monopoly201606;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import edu.fudan.ss.monolopy201606.creatures.Player;

//���ƹ�Ʊҳ��
public class SharePanel extends JFrame{
	private Share share;
	private Player curPlayer;
	
	public SharePanel(Share share, Player curPlayer){
		this.share = share;
		this.curPlayer = curPlayer;
		this.setBackground(new Color(155, 178, 194));
		intiComponent();  
	}
	
	private void intiComponent(){  
	    
	    JTable table = new JTable(new MyTableModel());
	    table.setBackground(new Color(155, 178, 194));
	    table.setRowHeight(25);
	     
	    //˫�������֧��Ʊ����
	    table.addMouseListener(new MouseAdapter(){ 
	          public void mouseClicked(MouseEvent e){
	        	  if(e.getClickCount() == 2){
	                  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
	                  if(Game.time.isWeekDay()){
	                	  Object[] op = {" ���� "," ���� "," �뿪 "};
		              		int option = -1;
		              		while(option == -1){
		              			option = JOptionPane.showOptionDialog(null, "��Ҫ����֧��Ʊ���β�����", "��Ʊ����",0, JOptionPane.QUESTION_MESSAGE, null, op, null);
		              		}
		              		if(option == 0){
		              			String buyNum = JOptionPane.showInputDialog("��������Ҫ�����������");
		              			if(buyNum != null) {
		              				while(!Game.isNumeric(buyNum) || (Game.isNumeric(buyNum)&& Integer.parseInt(buyNum) < 0)){
			              				buyNum = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
			              				if(buyNum == null){
			              					return;
			              				}
			              			}
		              				int num = Integer.parseInt(buyNum);
		              				double fee = Game.getTwoPointNum(share.getPrice(row)*num);
		              				if(curPlayer.getDespoit() >= fee){
		              					JOptionPane.showMessageDialog(null, "�ɹ�����"+share.getShareName(row)+num+"��");
		            					curPlayer.setDespoit(-fee);
		            					curPlayer.setOwnShares(num, row);
		            				}
		            				else if(curPlayer.getDespoit()+curPlayer.getCash() >= fee){
		            					double help = fee - curPlayer.getDespoit();
		            					curPlayer.setDespoit(-curPlayer.getDespoit());
		            					curPlayer.setCash(-help);
		            					curPlayer.setOwnShares(num, row);
		            					JOptionPane.showMessageDialog(null, "�ɹ�����"+share.getShareName(row)+num+"��");
		            				}
		            				else {
		            					JOptionPane.showMessageDialog(null, "�����ʽ𲻹�����Щ��Ʊ");
		            				}
		              				table.setModel(new MyTableModel());
								}
		              			
		              		}
		              		else if(option == 1){
		              			String sellNum = JOptionPane.showInputDialog("��������Ҫ������������");
		              			if(sellNum != null) {
		              				while(!Game.isNumeric(sellNum)|| (Game.isNumeric(sellNum)&& Integer.parseInt(sellNum) < 0)){
		              					sellNum = JOptionPane.showInputDialog("���벻�Ϸ������������룺");
			              				if(sellNum == null){
			              					return;
			              				}
			              			}
		              				int num = Integer.parseInt(sellNum);
		              				double fee = Game.getTwoPointNum(share.getPrice(row)*num);
		              				if(curPlayer.getOwnShare(row) >= num){
		            					curPlayer.setDespoit(fee);
		            					curPlayer.setOwnShares(-num, row);;
		            					JOptionPane.showMessageDialog(null, "�ɹ�����"+share.getShareName(row)+num+"��");
		            				}
		            				else {
		            					JOptionPane.showMessageDialog(null, "ӵ�й�Ʊ��������");
		            				}
		              				table.setModel(new MyTableModel());
								}
		              		}
		              		else if(option == 2){
		              			
		              		}
	                  }
	                  else{
	                	  JOptionPane.showMessageDialog(null, "����Ϊ˫���գ��������У�");
	                  }
	        	  } 
	        	  else 
	        		  return; 
	        	  
	          } 
	    });

	    TableColumn column = null;  
	    int colunms = table.getColumnCount();  
	    for(int i = 0; i < colunms; i++)  {  
	         column = table.getColumnModel().getColumn(i);  
	         column.setPreferredWidth(100); 
	    }  
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
	    
	    JScrollPane scroll = new JScrollPane(table);  
	    scroll.setSize(300, 200);  
	    add(scroll);  
	    scroll.setLocation(100, 50);
	    this.setTitle("��Ʊ����");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true); 

	}
	
	private class MyTableModel extends AbstractTableModel{
		String[] columnNames = { "��Ʊ��", "�ɽ���", "�ǵ���", "������"};  
	    Object[][] obj = new Object[10][4];  
	    
	    public MyTableModel(){
	    	for (int i = 0; i < 10; i++){
		    	obj[i][0] = share.getShareName(i);
		    	obj[i][1] = share.getPrice(i)+"Ԫ";
		    	obj[i][2] = share.getPriceRate(i)+"%";
		    	obj[i][3] = curPlayer.getOwnShare(i);
		    }
	    }
	    
	    @Override  
	    public String getColumnName(int column)  {  
	    	return columnNames[column];  
	    }

	    
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return obj.length; 
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			 return obj[rowIndex][columnIndex];  
		}
		
		@Override  
		public boolean isCellEditable(int rowIndex, int columnIndex){  
			return false;
		}

		
	}

}
