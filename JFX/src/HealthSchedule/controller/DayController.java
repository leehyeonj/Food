package HealthSchedule.controller;


import java.time.LocalDate;

import HealthSchedule.Main.AppMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DayController extends MasterController{
	@FXML private Label lblDay;		//��(day)
//	@FXML private Label lblCount;	//����ī��Ʈ
	@FXML private AnchorPane calendarDay ;
	
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
	
	//������ �̵�
	 @FXML
	   private void pageMove(MouseEvent event) {
			try {
				//�޷��� �� ��¥�� Ŭ���ϸ� main_everydayRecord�������� �Ѿ
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main_everydayRecord.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)calendarDay.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e2) {}
	   }
	
	
}