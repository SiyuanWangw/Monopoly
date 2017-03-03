package edu.fudan.ss.monopoly201606;

//��Ʊ
public class Share {
	private double[] prices = {
			9.4, 7.2, 6.32, 21.2, 49.18,
			15.3, 67.3, 23.8, 36.6, 4.55};
	private double[] priceRates = new double[10];
	private String[] shareNames = {
			"��ý�ع�", "���¹ɷ�", "��������", "����ͨ��", "�º�ɷ�",
			"��Ͷ�ع�", "�����Ƽ�", "Ħ�Ǵ��", "��ԭҩҵ", "�緶�ɷ�"
	};
	private boolean todaySet;
	
	public void setPrices(){
		setPriceRate();
		for(int i = 0; i < prices.length; i++){
			prices[i] += prices[i]*priceRates[i]/100;
		}
	}
	
	public double getPrice(int index){
		return Game.getTwoPointNum(prices[index]);
	}
	
	public void setPriceRate() {
		for(int i = 0; i < priceRates.length; i++){
			priceRates[i] = Math.random()*20-10;
		}
	}
	
	public double getPriceRate(int index){
		return Game.getTwoPointNum(priceRates[index]);
	}
	
	public String getShareName(int index){
		return shareNames[index];
	}
	
	public int shareNum(){
		return shareNames.length;
	}

	public boolean isTodaySet() {
		return todaySet;
	}

	public void setTodaySet(boolean todaySet) {
		this.todaySet = todaySet;
	}
	
}
