package HealthSchedule.controller;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import HealthSchedule.Main.AppMain;
import HealthSchedule.model.Routines;
import javafx.event.EventHandler;
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
    @FXML private Label dayWeight;
    @FXML private Label dayWorkoutTime;
    @FXML private Label dayKcal;
    TotalListener totalListener;
	
	static String dayOfMonth; //날짜 며칠인지
	 static final String[] DAY_OF_WEEK = {"", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
	//날짜 
	static String everyday = year + month +  dayOfMonth;
	static String dayofWeek; //요일
	

	String everydayTooltip = everyday;

		
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
		//날짜 마다 메모 툴팁
		Tooltip tooltip = new Tooltip(); //툴팁객체 생성
			
			//Day에 마우스를 위치시키면 memo의 everyday와 일치하는 데이터의 타이틀을 로드함.
		lblDay.setOnMouseMoved(new EventHandler<MouseEvent>() {
	
				@Override
				public void handle(MouseEvent event) {
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
					 dayOfMonth = date.format(dtf);
					
					 Calendar date1 = Calendar.getInstance();
					 date1.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(dayOfMonth));
					dayofWeek = DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)];
	//				System.out.println(dayofWeek);
	//				 
	//				System.out.println("daycontroller:"+year + month + dayOfMonth);
					//메모 데이터의 날짜를 date.format에 맞게 정의
					everyday = year + month + dayOfMonth;
					
					memodao.tooltipMemo(everyday);
					tooltip.setText(memodao.tooltipMemo(everyday));
					lblDay.setTooltip(tooltip);
					
				}
			});

	}
	
	 public String timeview(int hour, int minute, int second) {
		   if (second>60) {
			
			minute += second/60;
			second = second%60;
		}
		   if (minute>60) {
			
			hour += minute/60;
			minute = minute%60;
		   }
		   String hourstr ="";
		   if (hour<10) {
			hourstr = "0"+ hour;
			}else {
				hourstr = hour+"";
			}
			   String minutestr ="";
			   if (minute<10) {
				   minutestr = "0"+ minute;
			}else {
				minutestr = minute+"";
			}
			   String secondstr ="";
			   if (second<10) {
				   secondstr = "0"+ second;
			}else {
				secondstr = second+"";
			}
			   return hourstr+":"+minutestr+":"+secondstr;
	   }
	 
	public void setDayLabel(LocalDate date) {
		this.date = date;
		lblDay.setText(String.valueOf(date.getDayOfMonth()));
//		RoutineDao routineDao = new RoutineDao();
		//운동 시간뜨기		
		 Calendar date1 = Calendar.getInstance();
		 date1.set(date.getYear(), date.getMonthValue(),date.getDayOfMonth());
		 year = date.getYear()+"";
		 month = date.getMonthValue()+"";
		if (date.getDayOfMonth()<10) {
			dayOfMonth = "0"+date.getDayOfMonth();
		}else {
			dayOfMonth = date.getDayOfMonth()+"";
		}
		everyday = year+month+dayOfMonth;
		if (routineDao.ifexistTime(everyday)) {
			Routines temp = routineDao.selecTotalTime(everyday);
			String str =timeview(temp.getHour(), temp.getMinute(), temp.getSecond());
			dayWorkoutTime.setText(str);
			
		}  
		
	        totalListener = new TotalListener() {
				
				@Override
				public void onClickListener(Routines routine) {
					setTotalworkoutTime(routine);
					
					}
				};
	        
		
		
	}
	   private void setTotalworkoutTime(Routines routine) {
		   RoutineDao routineDao = new RoutineDao();
			//운동 시간뜨기
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
			
			 Calendar date1 = Calendar.getInstance();
			 date1.set(date.getYear(), date.getMonthValue(),date.getDayOfMonth());
			 year = date.getYear()+"";
			 month = date.getMonthValue()+"";
			if (date.getDayOfMonth()<10) {
				dayOfMonth = "0"+date.getDayOfMonth();
			}else {
				dayOfMonth = date.getDayOfMonth()+"";
			}
			everyday = year+month+dayOfMonth;
			if (routineDao.ifexistTime(everyday)) {
				Routines temp = routineDao.selecTotalTime(everyday);
				dayWorkoutTime.setText(temp.getHour() + ":" + temp.getMinute() + ":" + temp.getSecond());
				
			}  
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
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
			 dayOfMonth = date.format(dtf);
			
			 Calendar date1 = Calendar.getInstance();
			 date1.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(dayOfMonth));
			dayofWeek = DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)];
			everyday = year + month + dayOfMonth;
		 
//		 String pageDay= everyday;
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