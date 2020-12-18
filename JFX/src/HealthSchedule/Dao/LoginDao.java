package HealthSchedule.Dao;

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
	public boolean loginsuccess;
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
          System.out.println("�α��� ����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("����̹� �ε� ����!!");
       }
       
   }
   
   public void login(String email, String password) {
	   String sql = "select * from calendar where email = ? and calendarPassword =?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1,email);
           pstmt.setString(2,password);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   System.out.println("�α��� ����");
        	   loginsuccess = true;
        	   
           }
           else {
        	   System.out.println("�α��� ����");
           }
           
           
           
       } catch (Exception e) {
           System.out.println("select �޼��� ���ܹ߻�");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   }
   
   public String findpassword(String email) {
	   String sql = "select calendarPassword from calendar where email=?";
	   PreparedStatement pstmt = null;
	   try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("��й�ȣ ã��");
			return rs.getString("calendarPassword");
		}else {
			return "no";
		}
		
	} catch (Exception e) {
		return null;
	}
   }
        
        
}
