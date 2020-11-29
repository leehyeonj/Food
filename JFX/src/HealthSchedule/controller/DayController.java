package HealthSchedule.controller;


import java.time.LocalDate;

import HealthSchedule.Main.AppMain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DayController extends MasterController{
	@FXML private Label lblDay;		//일(day)
	@FXML private Label lblCount;	//일정카운트
	
	private LocalDate date;
	private boolean isFocused = false;
	
	public void setDayLabel(LocalDate date) {
		this.date = date;
		lblDay.setText(String.valueOf(date.getDayOfMonth()));
	}
	
	public void setCountLabel(Integer count) {
		lblCount.setText(count.toString());
	}
	
	public void setFocus() {
		AppMain.app.setFocus(date);
		isFocused = true;
		getRoot().getStyleClass().add("active");
	}
	
	public void outFocus() {
		isFocused = false;
		getRoot().getStyleClass().remove("active");
	}
}