package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoutineDao {
	

	ArrayList<Routines_lower> list = new ArrayList<>();
	   
	   
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
   
   //루틴 저장
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
   

   //루틴 삭제
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
   
   //그 날에 저장된 값이 있냐 없냐
   public boolean ifexistRoutine(String everyday) {
	   boolean result = false;
       String sql = "select * from everydayRoutine where everyday = ?";
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
   
   
   //저장되어있는 루틴을 보여주라
   public ArrayList<Routines_lower> viewDayRoutine(String everyday, String bodypart) {
	 
       String sql = "select everyday, bodypart, videoname from everydayRoutine where everyday = ? and bodypart =?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, bodypart);
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
           while(rs.next()) {
        	Routines_lower routineslower = new Routines_lower();
       
        	routineslower.setEveryday(rs.getString("everyday")); 
        	routineslower.setBodypart(rs.getString("bodypart")); 
        	routineslower.setVideoname(rs.getString("videoname")); 
        	list.add(routineslower);
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
       return list;
   }
   
   
   
}
