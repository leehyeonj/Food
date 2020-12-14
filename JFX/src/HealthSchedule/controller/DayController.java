package HealthSchedule.controller;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import HealthSchedule.Main.AppMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DayController extends MainController   implements Initializable{
	@FXML private Label lblDay;		//일(day)
//	@FXML private Label lblCount;	//일정카운트
	@FXML private AnchorPane calendarDay ;
	
	static String dayOfMonth; //날짜 며칠인지
	 static final String[] DAY_OF_WEEK = {"", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
	//날짜 
	static String everyday = year + month +  dayOfMonth;
	static String dayofWeek; //요일
	private LocalDate date;
	private boolean isFocused = false;
	
	private Pane root; //가장 위쪽의 루트 저장

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}
	
	MemoDao memodao = new MemoDao();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		System.out.println("dayController initialize실행");
		System.out.println("dayController initialize실행");
//		memodao.ifexistMemo(everyday);
		Tooltip tooltip = new Tooltip("메모 추가하기");
		lblDay.setTooltip(tooltip);
	}
	
	
	public void setDayLabel(LocalDate date) {
		this.date = date;
		lblDay.setText(String.valueOf(date.getDayOfMonth()));
//		dayOfMonth = lblDay.getText();
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
	
	//페이지 이동
	 @FXML
	   private void pageMove(MouseEvent event) {
		 //날짜를 먼저 저장해주어야함.
//			 System.out.println("dayController pagemove실행");
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
			 dayOfMonth = date.format(dtf);
			
			 Calendar date1 = Calendar.getInstance();
			 date1.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(dayOfMonth));
			dayofWeek = DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)];
//			System.out.println(dayofWeek);
//			 
//			System.out.println("daycontroller:"+year + month + dayOfMonth);
			everyday = year + month + dayOfMonth;
			try {
				//달력중 한 날짜를 클릭하면 main_everydayRecord페이지로 넘어감
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main_everydayRecord.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)calendarDay.getScene().getWindow();
				primaryStage.setScene(scene);
				System.out.println("페이지 클릭"+ everyday);
				
			} catch (Exception e2) {}
	   }
	
	
}