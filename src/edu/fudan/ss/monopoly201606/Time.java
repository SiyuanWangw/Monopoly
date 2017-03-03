package edu.fudan.ss.monopoly201606;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Time{
	private LocalDate date;
	private int year;
	private int month;
	private int day;
	private DayOfWeek weekDay;
	private Game game;
	
	public Time(Game game){
		this.game = game;
		setCurrentTime(0);
	}
	
	//得到当前时间及每轮时间
	public void setCurrentTime(int addDate){
		if(addDate > 0){
			game.getShare().setTodaySet(false);
		}
		date = LocalDate.now().plusDays(addDate);
		String[] str = (date.toString()).split("-");
		year = Integer.parseInt(str[0]);
		month = Integer.parseInt(str[1]);
		day = Integer.parseInt(str[2]);
		
		LocalDate independenceDay = LocalDate.of(year, month, day);
		weekDay = independenceDay.getDayOfWeek();
	}
	
	public int getYear(){
		return year;
	}
	
	public int getMonth(){
		return month;
	}
	
	public int getDay(){
		return day;
	}
	
	public DayOfWeek getWeekDay() {
		return weekDay;
	}
	
	public boolean isWeekDay(){
		if(weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY"))
			return false;
		else
			return true;
	}
	
	public boolean isMonthEnd(){
		if(date.getDayOfMonth()== date.lengthOfMonth())
			return true;
		else
			return false;
	}
	
	
}
