package HealthSchedule.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	   
	   private Connection conn;    //DB 커넥션(연결) 객체
	   private static final String USERNAME = "root";   //DB 접속시 ID
	   private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
	   private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//사용자주소/db주소
	   
	   public Chart_mainpage_controller() {
	       // connection객체를 생성해서 DB에 연결함.
	       try {
	    	   //동적 객체를 만들어줌 
	           Class.forName("com.mysql.jdbc.Driver"); 
	           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	           //System.out.println("드라이버 로딩 성공!!");
	           
	       } catch (Exception e) {
	           //System.out.println("드라이버 로드 실패!!");
	       }
	   }
	   
	   
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
		   
		   Chart_mainpage_controller hcc = new Chart_mainpage_controller();
		   series = new XYChart.Series<String, Integer>();
		   series.getData().add(new XYChart.Data<String, Integer>("월요일", hcc.Monday(1))); // 특징으로 무엇을 할 때마다 getData
		   series.getData().add(new XYChart.Data<String, Integer>("화요일", hcc.Tuesday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("수요일", hcc.Wednesday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("목요일", hcc.Thursday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("금요일", hcc.Friday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("토요일", hcc.Saturday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("일요일", hcc.Sunday(1)));
	        
		   series.setName(ft);	//String타입만 받는다
		   linechart.getData().add(series);  // 다른 라인을 추가하고 싶으면 다른 series 만들면 된다.
  
	   }
	   
	   //월요일 체중 호출
	   public Integer Monday(int monday) {
		   String sql = "select monday from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, monday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("monday");
			   }
		   } catch (Exception e) {
			   //System.out.println("월요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //화요일 체중 호출
	   public Integer Tuesday(int tuesday) {
		   String sql = "select tuesday from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, tuesday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("tuesday");
			   }
		   } catch (Exception e) {
			   //System.out.println("화요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //수요일 체중 호출
	   public Integer Wednesday(int wednesday) {
		   String sql = "select * from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, wednesday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("wednesday");
			   }
		   } catch (Exception e) {
			   //System.out.println("수요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //목요일 체중 호출
	   public Integer Thursday(int thursday) {
		   String sql = "select * from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, thursday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("thursday");
			   }
		   } catch (Exception e) {
			   //System.out.println("목요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //금요일 체중 호출
	   public Integer Friday(int friday) {
		   String sql = "select * from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, friday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("friday");
			   }
		   } catch (Exception e) {
			   //System.out.println("금요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //토요일 체중 호출
	   public Integer Saturday(int saturday) {
		   String sql = "select * from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, saturday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("saturday");
			   }
		   } catch (Exception e) {
			   //System.out.println("토요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //일요일 체중 호출
	   public Integer Sunday(int sunday) {
		   String sql = "select * from Weight where id = ?";
		   PreparedStatement pstmt = null;
		   int i = 0;
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, sunday);
			   ResultSet rs = pstmt.executeQuery();
			   if(rs.next()) {	
				   i = rs.getInt("sunday");
			   }
		   } catch (Exception e) {
			   //System.out.println("일요일 체중 호출 실패");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   
	   //체중기록
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
	   
	   //식단관리메인페이지
	   public void btnfood(ActionEvent event) {
		   try {
		     Parent foodList = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
		     Scene scene = new Scene(foodList);
		     Stage primaryStage= (Stage)food.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //체중메인페이지
	   public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //홈트메인페이지
	   public void btnHT(ActionEvent event) {
		   try {
		     Parent hometranning = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		     Scene scene = new Scene(hometranning);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //전체메인페이지
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }   
}
