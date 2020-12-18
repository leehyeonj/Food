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
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
//private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public LoginDao() {
	   try {
       	//동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("로그인 드라이버 로딩 성공!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("드라이버 로드 실패!!");
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
        	   System.out.println("로그인 성공");
        	   loginsuccess = true;
        	   
           }
           else {
        	   System.out.println("로그인 실패");
           }
           
           
           
       } catch (Exception e) {
           System.out.println("select 메서드 예외발생");
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
			System.out.println("비밀번호 찾음");
			return rs.getString("calendarPassword");
		}else {
			return "no";
		}
		
	} catch (Exception e) {
		return null;
	}
   }
        
        
}
