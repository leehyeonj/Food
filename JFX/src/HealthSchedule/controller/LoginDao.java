package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginDao {
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
//private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public LoginDao() {
	   try {
       	//���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("����̹� �ε� ����!!");
       }
       
   }
   
   public void login(String id, String password) {
	 
   }
        
        
}
