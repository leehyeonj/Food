package HealthSchedule.model;

public class Food {

	private String eattime;
	private String imgSrc;
	private String everyday;
	 String foodname;
	 String foodunit;
	 String cal;
	 public int totalKcal;
	 
	 public int getTotalKcal() {
		return totalKcal;
	}
	public void setTotalKcal(int totalKcal) {
		this.totalKcal = totalKcal;
	}
	public String getFoodunit() {
		return foodunit;
	}
	public void setFoodunit(String foodunit) {
		this.foodunit = foodunit;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public String getEattime() {
		return eattime;
	}
	public void setEattime(String eattime) {
		this.eattime = eattime;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getEveryday() {
		return everyday;
	}
	public void setEveryday(String everyday) {
		this.everyday = everyday;
	}
	
	
}
