package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DrawerDao {
	private Connection conn;    //DB 커넥션(연결) 객
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    //상아 , 중섭
//    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms

   public DrawerDao() {

	   try {
       	   //동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("리셋 드라이버 로딩 성공!!");

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("리셋 드라이버 로드 실패!!");
       }
   }
   
   public void reset() {
		System.out.println("데이터를 리셋합니다.");
  	  //쿼리문 준비
	   String sql = "delete from workoutTime";
	  String sql2 = "delete from everydayRoutine";
	 
	  String sql3 = "delete from dailphoto";
	  String sql4 = "delete from memo";
	
	   
	   PreparedStatement pstmt = null;
	   PreparedStatement pstmt2 = null;
	   PreparedStatement pstmt3= null;
	   PreparedStatement pstmt4 = null;
	   
	   

      try {
          pstmt = conn.prepareStatement(sql);
          int result = pstmt.executeUpdate();
          if(result==1) {
              System.out.println("리셋 성공");

          }
          pstmt2 = conn.prepareStatement(sql2);
          result = pstmt2.executeUpdate();
          if(result==1) {
              System.out.println("리셋 성공");

          }
          pstmt3 = conn.prepareStatement(sql3);
          result = pstmt3.executeUpdate();
          if(result==1) {
              System.out.println("리셋 성공");

          }
          pstmt4 = conn.prepareStatement(sql4);
          result = pstmt4.executeUpdate();
          if(result==1) {
              System.out.println("리셋 성공");

          }

      } catch (Exception e) {

          System.out.println("리셋 실패");
      }    finally {
          try {
              if(pstmt!=null && !pstmt.isClosed()) {
                  pstmt.close();
              }
          } catch (Exception e2) {}
      }
   }
   
   public void resetAccount() {
		
 	  //쿼리문 준비
	   String sql = "delete from calendar";
	 
	   PreparedStatement pstmt = null;
	 

     try {
         pstmt = conn.prepareStatement(sql);
         int result = pstmt.executeUpdate();
         if(result==1) {
             System.out.println("계정 삭제 성공");

         }
        

     } catch (Exception e) {

         System.out.println("계정 삭제  실패");
     }    finally {
         try {
             if(pstmt!=null && !pstmt.isClosed()) {
                 pstmt.close();
             }
         } catch (Exception e2) {}
     }
  }
}
