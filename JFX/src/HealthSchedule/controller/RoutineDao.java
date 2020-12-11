package HealthSchedule.controller;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoutineDao {
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
//private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public RoutineDao() {
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
   
   public void saveRoutine(String everyday, String part, String videoname) {
	   //쿼리문 준비
       String sql = "insert into everydayRoutine values(?,?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, part);
           pstmt.setString(3, videoname);
          
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("Board데이터 삽입 성공!");
               
           }
           
       } catch (Exception e) {
           System.out.println("Board데이터 삽입 실패!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   
//   public void deleteRoutine(String everyday, String part, String videoname) {
//	   //쿼리문 준비
//       String sql = "delete from everydayRoutine where everyday = ? and part =? and videoname =?";
//       
//       PreparedStatement pstmt = null;
//       try {
//           pstmt = conn.prepareStatement(sql);
//           pstmt.setString(1, everyday);
//           pstmt.setString(2, part);
//           pstmt.setString(3, videoname);
//          
//           try {
//        	   pstmt.executeUpdate(sql);
//        	   System.out.println("삭제 성공");
//           } catch (SQLException e) {
//        	   System.out.println("Board데이터 삭제 실패!");
//           }
//       } catch (Exception e) {
//           
//       }    finally {
//           try {
//               if(pstmt!=null && !pstmt.isClosed()) {
//                   pstmt.close();
//               }
//           } catch (Exception e2) {}
//       }
//       
//  }
   
   public boolean deleteRoutine(String everyday, String part, String videoname) {
	 boolean result = false;
	
		String sql = "delete from everydayRoutine where everyday =? and bodypart =? and videoname =?";
		PreparedStatement pstmt = null;
		 try {
			   pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, everyday);
	           pstmt.setString(2, part);
	           pstmt.setString(3, videoname);
	           
	           int rs = pstmt.executeUpdate();
	           if (rs>0) {
				result = true;
				System.out.println("데이터 삭제 성공");
	           }
	           else {
				result = false;
				System.out.println("데이터 삭제 실패");
			}
	           pstmt.close();
	           
		 } catch (Exception e) {
			// TODO: handle exception
		}
	   
	   return result;
       
  }
   
}
