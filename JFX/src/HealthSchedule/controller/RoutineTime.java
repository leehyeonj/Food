package HealthSchedule.controller;

public class RoutineTime {

	String everday;
	String bodypart;
	int timehour;
	int timeminute;
	int timesecond;
	public RoutineTime() {
		// TODO Auto-generated constructor stub
	}
	public RoutineTime(String everday, String bodypart, int timehour, int timeminute, int timesecond) {
		super();
		this.everday = everday;
		this.bodypart = bodypart;
		this.timehour = timehour;
		this.timeminute = timeminute;
		this.timesecond = timesecond;
	}
	
	public String getEverday() {
		return everday;
	}
	public void setEverday(String everday) {
		this.everday = everday;
	}
	public String getBodypart() {
		return bodypart;
	}
	public void setBodypart(String bodypart) {
		this.bodypart = bodypart;
	}
	public int getTimehour() {
		return timehour;
	}
	public void setTimehour(int timehour) {
		this.timehour = timehour;
	}
	public int getTimeminute() {
		return timeminute;
	}
	public void setTimeminute(int timeminute) {
		this.timeminute = timeminute;
	}
	public int getTimesecond() {
		return timesecond;
	}
	public void setTimesecond(int timesecond) {
		this.timesecond = timesecond;
	}

}
