package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HealthSchedule.model.Food;
import HealthSchedule.model.Routines;
import HealthSchedule.model.Weight;

public class MainDao {
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
//private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public MainDao() {
	   try {
       	//동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("메인 드라이버 로딩 성공!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("메인 드라이버 로드 실패!!");
       }
       
   }
   //루틴에서 시간 저장되어있는지
   public boolean ifexistTime(String everyday) {
	   boolean result = false;
       String sql = "select * from workoutTime where everyday = ?";
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
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   //루틴에서 총시간 select
   public Routines selecTotalTime(String everyday) {
	   Routines totalTime = new Routines();
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime group by everyday having everyday=?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
          if(rs.next()) {
        	   
        	   totalTime.setEveryday(rs.getString("everyday"));
        	   totalTime.setHour(rs.getInt("sum(timehour)"));
        	   totalTime.setMinute(rs.getInt("sum(timeminute)"));
        	   totalTime.setSecond(rs.getInt("sum(timesecond)"));

        	
        	   System.out.println("토탈 메서드 성공");
           	}
           
       } catch (Exception e) {
           System.out.println("토탈메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               } 
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
       return totalTime;
   }
   
 //메모 툴탑
   public String tooltipMemo(String everyday) {
//	   boolean result = false;
	   //날짜에 마우스를 위치시켜서 메모가 없을 시 "메모를 추가하세요"
	   //메모가 있을 시 메모의 타이틀을 출력
		   String str = "메모를 추가하세요"; 
	       String sql = "select title from memo where everyday = ?";
	       PreparedStatement pstmt = null;
	       try {
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, everyday);
	           ResultSet rs = pstmt.executeQuery();
	           if(rs.next()) {
	        	  str = rs.getString("title");
	           }
	           
	       } catch (Exception e) {
	           System.out.println("select 메서드 예외발생");
	           
	       }    finally {
	           try {
	               if(pstmt!=null && !pstmt.isClosed()) {
	                   pstmt.close();
	                  
	               } 
//	               if(conn != null) {
//	            	   conn.close();
//	               }
	           } catch (Exception e2) { }
	       }
		return str;
	   }
   
   //그날 식단 저장되었는지
   public boolean ifexistFood(String everyday) {
	   boolean result = false;
       String sql = "select * from Foodtest where everyday = ?";
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
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   //총 칼로리 셀렉트
   public Food selecTotalKcal(String everyday) {
	   Food total= new Food();
       String sql = "select everyday, cal from Foodtest where everyday=?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
          while(rs.next()) {
        	   
        	  total.setEveryday(rs.getString("everyday"));
        	  total.setCal(rs.getString("cal"));
        	  total.totalKcal += Integer.parseInt(total.getCal());

        	   System.out.println("토탈 메서드 성공");
           	}
           
       } catch (Exception e) {
           System.out.println("토탈메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
       return total;
   }
   
   //저장된 몸무게가 있나 없나
   public boolean ifexistWeight(String everyday) {
	   boolean result = false;
       String sql = "select * from weight where everyday = ?";
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
   //저장된 몸무게 나타내기
   public Weight viewWeight(String everyday) {
	   Weight weight = new Weight();
       String sql = "select everyday, weight from weight where everyday = ?";
       PreparedStatement pstmt = null;

       //결과 값을 담을 곳
       try {

           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           
           ResultSet rs = pstmt.executeQuery();

           //있으면
           while(rs.next()) {	
           weight.setEveryday(rs.getString("everyday")); 
           weight.setWeight(rs.getDouble("weight")); 
           }

       } catch (Exception e) {
           System.out.println("몸무게 select 메서드 예외");

       }    finally {
    	   try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
              }
           } catch (Exception e2) { }
       }
       return weight;
   }   
}
