package HealthSchedule.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//평소에 하던 것처럼 Initializable를 상속받지 않는다
public class MainController extends MasterController{

	  
	   @FXML private Button food;	//식단관리버튼
	   @FXML private Button chart;	//체중관리버튼
	   @FXML private Button HT;		//홈트영상버튼
	   @FXML private Button home;	//홈버튼
	   @FXML private Button btnPrev;	//저번달
	   @FXML private Button btnNext;	//이번달
	   @FXML private Label lblDate;
	   @FXML private Label lblDay;
		
	   @FXML private GridPane gridCalendar;	//달력표
		
	   private YearMonth currentYM; //현재의 년월을 저장하는 변수
	   
	   //GridPane의 행과 열에 집어넣기 위해 선언
	   private List<DayController> dayList;
	   private Map<String, String> dayOfWeek = new HashMap<>();
	   
	   @FXML 
	   public void initialize() {
		   
		   //버튼액션
		   food.setOnAction(e->btnfood(e));
		   chart.setOnAction(e->btnchart(e));
		   HT.setOnAction(e->btnHT(e));
		   home.setOnAction(e->btnhome(e));
		   
		   //달력
		   dayList = new ArrayList<>();
		   //달력(GridPane)에 반복문을 사용해 행과 열마다 날짜를 입력
		   for(int i = 0; i < 5; i++) { //달력의 행
			   for(int j = 0; j < 7; j++) { //달력의 열
				   FXMLLoader loader = new FXMLLoader();
				   loader.setLocation(getClass().getResource("/HealthSchedule/resources/calendarDayLayout.fxml"));
				   System.out.printf("j : %d번째 그리기 성공\n", j);
				   try {
					   AnchorPane ap = loader.load();
					   gridCalendar.add(ap, j, i);
					   DayController dc = loader.getController();
					   dc.setRoot(ap);
					   dayList.add(dc);
					   //System.out.println("1");
				   } catch (Exception e) {
					   e.printStackTrace();
					   System.out.printf("j : %d, i : %d 번째 그리는 중 오류 발생\n", j, i);
				   }
			   }	//이중for
		   }	//for
		   
		   //중앙상단 오늘 요일 표시
		   String[] engDay = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
		   String[] korDay = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
		   
		   for(int i = 0; i < engDay.length; i++) {
			   dayOfWeek.put(engDay[i], korDay[i]); 	//put(String key, String value)
		   }
			
		   loadMonthData(YearMonth.now());
		   setToday(LocalDate.now());
	   }
	   
	   //한달 뺀 달력을 로드
	   public void prevMonth() {
		   loadMonthData(currentYM.minusMonths(1));
		   //LocalDate.of(int year, int month, int dayOfMonth)
		   LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		   setToday(firstDay);	//달력을 넘기면 1일로 변경
	   }

	   //한달 뺀 달력을 로드
	   public void nextMonth() {
		   loadMonthData(currentYM.plusMonths(1));
		   LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		   setToday(firstDay);	//달력을 넘기면 1일로 변경
	   }
	   
	   //중앙상단 오늘 날짜
	   public void setToday(LocalDate date) {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		   lblDate.setText(date.format(dtf));
		   lblDay.setText(dayOfWeek.get(date.getDayOfWeek().toString()));
	   }
		
	   //중앙상단 오늘 요일
	   public void loadMonthData(YearMonth ym) {
		   LocalDate calendarDate = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1); //해당 년월의 1일을 가져온다.
		   while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) { //일요일이 아닐때까지 하루씩 빼간다.
			   calendarDate = calendarDate.minusDays(1); //하루씩 감소
		   }
		   //여기까지 오면 해당주간의 첫째날이 오게된다.
		   for(DayController day : dayList) {
			   day.setDayLabel(calendarDate);
			   calendarDate = calendarDate.plusDays(1); //하루씩 증가
			   day.setCountLabel(0); //처음은 무조건 0으로 고정한다.
		   }
		   currentYM = ym;
		}
		
	   public void setClickData(LocalDate date) {
		   setToday(date); //날짜 설정하고
		   for(DayController dc : dayList) {
			   dc.outFocus(); //모든 DayController에서 "active"를 제거한다.
		   }
	   }
	   
	   //식단관리
	   public void btnfood(ActionEvent event) {
		   try {
		     Parent foodList = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
		     Scene scene = new Scene(foodList);
		     Stage primaryStage= (Stage)food.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnHT(ActionEvent event) {
		   try {
		     Parent hometranning = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		     Scene scene = new Scene(hometranning);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
}
