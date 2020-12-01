package HealthSchedule.controller;


import java.time.LocalDate;

import HealthSchedule.Main.AppMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DayController extends MasterController{
	@FXML private Label lblDay;		//일(day)
//	@FXML private Label lblCount;	//일정카운트
	@FXML private AnchorPane calendarDay;
	
	private LocalDate date;
	private boolean isFocused = false;
	
	public void setDayLabel(LocalDate date) {
		this.date = date;
		lblDay.setText(String.valueOf(date.getDayOfMonth()));
	}
	
	public void setCountLabel(Integer count) {
//		lblCount.setText(count.toString());
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
	 @FXML
	   private void pageMove(MouseEvent event) {
			try {
				//확인버튼을 누르면 다시 todayFood화면으로 돌아감
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)calendarDay.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e2) {}
	   }
	
}