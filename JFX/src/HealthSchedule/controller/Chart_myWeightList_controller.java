package HealthSchedule.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Chart_myWeightList_controller implements Initializable {
 
	   @FXML private Button goBack;	//�ڷΰ����ư
	   @FXML private Button food;	//�Ĵܰ�����ư
	   @FXML private Button chart;	//ü�߰�����ư
	   @FXML private Button HT;		//ȨƮ�����ư
	   @FXML private Button home;	//Ȩ��ư
	   @FXML private Button weightsave;	//ü�������ư
	   @FXML private TextField challenge1;	//��ǥü��
	   @FXML private TextField mon, tue, wed, thu, fri, sat, sun;	//����
	   
	   private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
	   private static final String USERNAME = "root";   //DB ���ӽ� ID
	   private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
	   private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//������ּ�/db�ּ�
	   
	   public Chart_myWeightList_controller() {
	       // connection��ü�� �����ؼ� DB�� ������.
	       try {
	    	   //���� ��ü�� ������� 
	           Class.forName("com.mysql.jdbc.Driver"); 
	           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	           //System.out.println("����̹� �ε� ����!!");
	           
	       } catch (Exception e) {
	           //System.out.println("����Ʈ����̹� �ε� ����!!");
	       }
	   }
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
		   food.setOnAction(e->btnfood(e));
		   chart.setOnAction(e->btnchart(e));
		   HT.setOnAction(e->btnHT(e));
		   home.setOnAction(e->btnhome(e));
		   weightsave.setOnAction(e->btnsave(e));
		   
	   }
	   
	   //����
	   public void btnsave(ActionEvent e) {
		   int Challenge = Integer.parseInt(challenge1.getText());
		   int Umonday = Integer.parseInt(mon.getText());
		   int Utuesday = Integer.parseInt(tue.getText());
		   int Uwednesday = Integer.parseInt(wed.getText());
		   int Uthursday = Integer.parseInt(thu.getText());
		   int Ufriday = Integer.parseInt(fri.getText());
		   int Usaturday = Integer.parseInt(sat.getText());
		   int Usunday = Integer.parseInt(sun.getText());
	   
		   PreparedStatement pstmt = null;
		   String sql = "";
		   new Chart_myWeightList_controller();		   
		   
		   //ü�ߵ����ͻ���
		   try { 
			   sql = "insert into Weight values(?,?,?,?,?,?,?,?,?)";  
			   pstmt = conn.prepareStatement(sql);
			   
			   pstmt.setString(1, null);
			   pstmt.setInt(2, Challenge);
			   pstmt.setInt(3, Umonday);
			   pstmt.setInt(4, Utuesday);
			   pstmt.setInt(5, Uwednesday);
			   pstmt.setInt(6, Uthursday);
			   pstmt.setInt(7, Ufriday);
			   pstmt.setInt(8, Usaturday);
			   pstmt.setInt(9, Usunday);
			   pstmt.executeUpdate();
		   } catch (Exception e1) {
			   //System.out.println("ü�ߵ����ͻ��Խ���!");
		   } finally{
			   if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			   if(conn!=null) try{conn.close();}catch(SQLException ex){}
		   }	//finally	   
		   try {
			   Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
			   Scene scene = new Scene(members);
			   Stage primaryStage= (Stage)weightsave.getScene().getWindow();
			   primaryStage.setScene(scene);
			 } catch (Exception e2) {}	
		  }	//btnsave()
	   //�ڷΰ���
	   public void btngoBack(ActionEvent event) {   
		   try {
		      Parent foodPicker = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		      Scene scene = new Scene(foodPicker);
		      Stage primaryStage= (Stage)goBack.getScene().getWindow();
		      primaryStage.setScene(scene);
		   } catch (Exception e) {}
	   }
	   
	   //�Ĵܰ���
	   public void btnfood(ActionEvent event) {
		   try {
		     Parent foodList = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
		     Scene scene = new Scene(foodList);
		     Stage primaryStage= (Stage)food.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   //ü�߰�������������
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
	   //����������	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
}
