package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import HealthSchedule.model.Memo;

public class MemoDao {

	private Connection conn;    //DB 커넥션(연결) 객
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    //private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    //private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    //상아 , 중섭
    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms

   public MemoDao() {

	   try {
       	   //동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("루틴 드라이버 로딩 성공!!");

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("드라이버 로드 실패!!");
       }
   }

   public void saveContent(String everyday, String title, String content) {

	   //쿼리문 준비
	   String sql = "insert into memo values(?,?,?)";
	   
	   PreparedStatement pstmt = null;

       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, title);
           pstmt.setString(3, content);

           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("메모데이터 삽입 성공!");

           }

       } catch (Exception e) {

           System.out.println("메모데이터 삽입 실패!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   }

   public boolean ifexistMemo(String everyday) {
	   boolean result = false;
       String sql = "select * from memo where everyday = ?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   result = true;
        	   
           }
       } catch (Exception e) {
           System.out.println("select 메서드 예외발생");

       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
	return result;
   }

   Memo memo = new Memo();

   public Memo viewMemo(String everyday) {

       String sql = "select everyday, title, content from memo where everyday = ?";
       PreparedStatement pstmt = null;

       //결과 값을 담을 곳
       try {

           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           
           ResultSet rs = pstmt.executeQuery();

           //있으면
           while(rs.next()) {
        	
        	memo.setEveryday(rs.getString("everyday")); 
        	memo.setTitle(rs.getString("title")); 
        	memo.setContent(rs.getString("content")); 

           	}

       } catch (Exception e) {
           System.out.println("메모 select 메서드 예외");

       }    finally {
    	   try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
              }
           } catch (Exception e2) { }
       }
       return memo;
   }

}