package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import HealthSchedule.Dao.MainDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainController implements Initializable{

	public MainDao mainDao  = new MainDao();
	@FXML
	private AnchorPane pane;
//	  RoutineDao routineDao = new RoutineDao();
	   @FXML private Button food;	//식단관리버튼
	   @FXML private Button chart;	//체중관리버튼
	   @FXML private Button HT;		//홈트영상버튼
	   @FXML private Button home;	//홈버튼
	   @FXML private Button btnPrev;	//저번달
	   @FXML private Button btnNext;	//이번달
	   @FXML private Label lblDate;
	   @FXML private Label lblDay;
	   @FXML private Label lblMonth;
	   @FXML private Label monthText;
		
	   @FXML private JFXHamburger hamburger;
	   @FXML private JFXDrawer drawer;
	   
	   @FXML private GridPane gridCalendar;	//달력표
	 
	   static String year;
	   static String month;
	   
	   
	   private Pane root; //가장 위쪽의 루트 저장

		public Pane getRoot() {
			return root;
		}

		public void setRoot(Pane root) {
			this.root = root ;
		}
	   
	   
		
	   private YearMonth currentYM; //현재의 년월을 저장하는 변수
	   LocalDate date;
	   
	   //GridPane의 행과 열에 집어넣기 위해 선언
	   private List<DayController> dayList;
	   private Map<String, String> monthOfYear = new HashMap<>();
	   
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("mainController initialize실행");
			 
//			
//			System.out.println("settoday호출");
			//stage 조정
			stageDragableMoveWindow();
			   
		   //햄버거 버튼
			try {
				VBox vbox = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/mainDrawer.fxml"));
				drawer.setSidePane(vbox);
				  
				   HamburgerSlideCloseTransition burger = new HamburgerSlideCloseTransition(hamburger);
//					   HamburgerBackArrowBasicTransition burger = new HamburgerBackArrowBasicTransition(hamburger);
				   System.out.println("1");
				   burger.setRate(-1);
				   hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
					   burger.setRate(burger.getRate()*-1);
					   burger.play();
					   
					   if(drawer.isOpened()) {
						   drawer.close();
					   }
					   else {
						   drawer.open();
					   }
				   });
			} catch (IOException e1) {}
			
			   
			 
			   //버튼액션
//			   food.setOnAction(e->btnfood(e));
//			   chart.setOnAction(e->btnchart(e));
//			   HT.setOnAction(e->btnHT(e));
//			   home.setOnAction(e->btnhome(e));
			   
			   
			  
			   //달력
			   dayList = new ArrayList<>();
			   //달력(GridPane)에 반복문을 사용해 행과 열마다 날짜를 입력
			   for(int i = 0; i < 5; i++) { //달력의 행
				   for(int j = 0; j < 7; j++) { //달력의 열
//					  if(lblDay.getText().equals(date.getDayOfMonth().toString())) {
//						  
//					  }
					   FXMLLoader loader = new FXMLLoader();
					   loader.setLocation(getClass().getResource("/HealthSchedule/resources/calendarDayLayout.fxml"));
//					   System.out.printf("j : %d번째 그리기 성공\n", j);
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
			   String[] engDay = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
//			   String[] korDay = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
			   
			   for(int i = 0; i < engDay.length; i++) {
				   monthOfYear.put(engDay[i], engDay[i]); 	//put(String key, String value)
			   }
				
			   loadMonthData(YearMonth.now());
			   setToday(LocalDate.now());
//			   System.out.println("main: year :"+year);
//			   System.out.println("main: year :"+month);
			
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
	   
	   
	   
	 
	   //중앙상단 날짜 세팅 현재 년도, 달
	   public void setToday(LocalDate date) {
//		   System.out.println("settoday실행");
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		   DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");
		   lblDate.setText(date.format(dtf)); //2020
		   year = date.format(dtf);
//		   year = lblDate.getText();
		   month = date.format(dtf2);
//		   month = monthText.getText();
		   lblMonth.setText(monthOfYear.get(date.getMonth().toString())); //december
		   monthText.setText(date.format(dtf2));//12
		   month = monthText.getText();
	   }
		
	   
	  
	   //중앙상단 오늘 요일
	   public void loadMonthData(YearMonth ym) {
		   System.out.println("loadMonthData실행");
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
		   System.out.println("setClickData실행");
		   setToday(date); //날짜 설정하고
		   for(DayController dc : dayList) {
			   dc.outFocus(); //모든 DayController에서 "active"를 제거한다.
		   }
	   }
	   
	   
	   //////////////////////////
	   private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //화면 움직일때 투명으로 변하게 하기
	   private void stageDragableMoveWindow() {
		   pane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   pane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // 창 투명화
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // 창 투명화
		   });
		   pane.setOnDragDone((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
		   pane.setOnMouseReleased((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
	   }

	   //화면 숨기기
	   @FXML
	   private void actionMinWindow(MouseEvent event) {
	   // Launcher.stage.setIconified(true);
	   stage = (Stage) pane.getScene().getWindow();
	   stage.setIconified(true);
	   }

	   
	   //화면 끄기
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }



	
	  
		
	 
}
