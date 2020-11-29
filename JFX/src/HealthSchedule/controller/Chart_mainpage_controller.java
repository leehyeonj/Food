package HealthSchedule.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Chart_mainpage_controller implements Initializable {

	   @FXML private Button healthLog;	//기록버튼
	   @FXML private Button food;	//식단관리버튼
	   @FXML private Button chart;	//체중관리버튼
	   @FXML private Button HT;		//홈트영상버튼
	   @FXML private Button home;	//홈버튼
	   @FXML private TextField challenge2;	//목표체중 텍스트필드
	   @FXML LineChart<String, Integer> linechart;
	   XYChart.Series<String, Integer> series = null;
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   //버튼액션추가
		   healthLog.setOnAction(e->btnhealthlog(e));
		   food.setOnAction(e->btnfood(e));
		   chart.setOnAction(e->btnchart(e));
		   HT.setOnAction(e->btnHT(e));
		   home.setOnAction(e->btnhome(e));
		   drawChart();
		   
	   }
	   
	   // 차트 그리기
	   public void drawChart() {
		   SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
		   Calendar time = Calendar.getInstance();
		   String ft = sdf.format(time.getTime());
		   
		   series = new XYChart.Series<String, Integer>();
		   series.getData().add(new XYChart.Data<String, Integer>("월요일", 75)); // 특징으로 무엇을 할 때마다 getData
		   series.getData().add(new XYChart.Data<String, Integer>("화요일", 73));
		   series.getData().add(new XYChart.Data<String, Integer>("수요일", 74));
		   series.getData().add(new XYChart.Data<String, Integer>("목요일", 75));
		   series.getData().add(new XYChart.Data<String, Integer>("금요일", 73));
		   series.getData().add(new XYChart.Data<String, Integer>("토요일", 70));
		   series.getData().add(new XYChart.Data<String, Integer>("일요일", 72));
	        
		   series.setName(ft);	//String타입만 받는다
		   linechart.getData().add(series);  // 다른 라인을 추가하고 싶으면 다른 series 만들면 된다.
  
	   }
	   
	   //뒤로가기
	   public void btnhealthlog(ActionEvent event) {
	      try {
	    	 //끌어오는 주소는 페이지를 전환할 fxml명
	    	 //fxml마다 컨트롤러를 주니 한 컨트롤러에서 여러 fxml을 잡지는 말자
	         Parent Log = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_myWeightList.fxml"));
	         Scene scene = new Scene(Log);
	         Stage primaryStage= (Stage)healthLog.getScene().getWindow();
	         primaryStage.setScene(scene);
	      } catch (Exception e) {}
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
//	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
}
