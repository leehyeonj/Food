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

	   @FXML private Button healthLog;	//��Ϲ�ư
	   @FXML private Button food;	//�Ĵܰ�����ư
	   @FXML private Button chart;	//ü�߰�����ư
	   @FXML private Button HT;		//ȨƮ�����ư
	   @FXML private Button home;	//Ȩ��ư
	   @FXML private TextField challenge2;	//��ǥü�� �ؽ�Ʈ�ʵ�
	   @FXML LineChart<String, Integer> linechart;
	   XYChart.Series<String, Integer> series = null;
	   
	   private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
	   private static final String USERNAME = "root";   //DB ���ӽ� ID
	   private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
	   private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//������ּ�/db�ּ�
	   
	   public Chart_mainpage_controller() {
	       // connection��ü�� �����ؼ� DB�� ������.
	       try {
	    	   //���� ��ü�� ������� 
	           Class.forName("com.mysql.jdbc.Driver"); 
	           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	           //System.out.println("����̹� �ε� ����!!");
	           
	       } catch (Exception e) {
	           //System.out.println("����̹� �ε� ����!!");
	       }
	   }
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   //��ư�׼��߰�
		   healthLog.setOnAction(e->btnhealthlog(e));
		   food.setOnAction(e->btnfood(e));
		   chart.setOnAction(e->btnchart(e));
		   HT.setOnAction(e->btnHT(e));
		   home.setOnAction(e->btnhome(e));
		   drawChart();
		   
	   }
	   
	   // ��Ʈ �׸���
	   public void drawChart() {
		   SimpleDateFormat sdf = new SimpleDateFormat("MM�� dd��");
		   Calendar time = Calendar.getInstance();
		   String ft = sdf.format(time.getTime());
		   
		   Chart_mainpage_controller hcc = new Chart_mainpage_controller();
		   series = new XYChart.Series<String, Integer>();
		   series.getData().add(new XYChart.Data<String, Integer>("������", hcc.Monday(1))); // Ư¡���� ������ �� ������ getData
		   series.getData().add(new XYChart.Data<String, Integer>("ȭ����", hcc.Tuesday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("������", hcc.Wednesday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("�����", hcc.Thursday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("�ݿ���", hcc.Friday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("�����", hcc.Saturday(1)));
		   series.getData().add(new XYChart.Data<String, Integer>("�Ͽ���", hcc.Sunday(1)));
	        
		   series.setName(ft);	//StringŸ�Ը� �޴´�
		   linechart.getData().add(series);  // �ٸ� ������ �߰��ϰ� ������ �ٸ� series ����� �ȴ�.
  
	   }
	   
	   //������ ü�� ȣ��
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
			   //System.out.println("������ ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //ȭ���� ü�� ȣ��
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
			   //System.out.println("ȭ���� ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //������ ü�� ȣ��
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
			   //System.out.println("������ ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //����� ü�� ȣ��
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
			   //System.out.println("����� ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //�ݿ��� ü�� ȣ��
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
			   //System.out.println("�ݿ��� ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //����� ü�� ȣ��
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
			   //System.out.println("����� ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   //�Ͽ��� ü�� ȣ��
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
			   //System.out.println("�Ͽ��� ü�� ȣ�� ����");
		   }finally {
			   try {
				   if(pstmt != null && !pstmt.isClosed()) {
					   pstmt.close();
				   }
			   } catch (Exception e2) {}
		   }
		return i;
	   }
	   
	   //ü�߱��
	   public void btnhealthlog(ActionEvent event) {
	      try {
	    	 //������� �ּҴ� �������� ��ȯ�� fxml��
	    	 //fxml���� ��Ʈ�ѷ��� �ִ� �� ��Ʈ�ѷ����� ���� fxml�� ������ ����
	         Parent Log = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_myWeightList.fxml"));
	         Scene scene = new Scene(Log);
	         Stage primaryStage= (Stage)healthLog.getScene().getWindow();
	         primaryStage.setScene(scene);
	      } catch (Exception e) {}
	   }
	   
	   //�Ĵܰ�������������
	   public void btnfood(ActionEvent event) {
		   try {
		     Parent foodList = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
		     Scene scene = new Scene(foodList);
		     Stage primaryStage= (Stage)food.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //ü�߸���������
	   public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //ȨƮ����������
	   public void btnHT(ActionEvent event) {
		   try {
		     Parent hometranning = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		     Scene scene = new Scene(hometranning);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //��ü����������
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }   
}
