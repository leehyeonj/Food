package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HealthSchedule.model.Routines;


public class PieChartDao {
	
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
//    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public PieChartDao() {
	   try {
       	//동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("루틴 드라이버 로딩 성공!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("루틴 드라이버 로드 실패!!");
       }
       
   }
   
   //하체운동 날짜별 시간호출
   public Integer selectLower(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
//		int date = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '하체운동' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();

           //있으면
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("일일하체시간 메서드 성공");
           	}
       } catch (Exception e) {
           System.out.println("일일하체시간메서드 예외발생");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   //전신운동 날짜별 시간 호출
   public Integer selectFullbody(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '전신운동' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //있으면
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("일일하체운동시간 메서드 성공");
           	}
       } catch (Exception e) {
           System.out.println("일일하체운동시간메서드 예외발생");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   //상체운동 날짜별 시간호출
   public Integer selectUpperbody(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '상체운동' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //있으면
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("일일상체운동시간 메서드 성공");
           	}
       } catch (Exception e) {
           System.out.println("일일상체운동시간메서드 예외발생");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   //복근운동 날짜별 시간호출
   public Integer selectAbs(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '복근운동' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //있으면
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("일일복근운동시간 메서드 성공");
           	}
       } catch (Exception e) {
           System.out.println("일일복근운동시간메서드 예외발생");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   
   //스트레칭 날짜별 시간호출
   public Integer selectStretching(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '스트레칭' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //있으면
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("일일스트레칭시간 메서드 성공");
           	}
       } catch (Exception e) {
           System.out.println("일일스트레칭시간메서드 예외발생");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   
   //전신운동 월별 시간호출
   public Integer selectFullbodyAll(int fullbody) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
		//실제 오늘 날짜를 기준으로 -1개월의 데이터를 가져온다  ex) 오늘이 12/21인 경우 어느 날짜를 들어가도 11/21~12/21의 데이터를 가져온다
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '전신운동' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //결과 값을 담을 곳      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //있으면
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("월별전신운동시간 메서드 성공");
           }
        } catch (Exception e) {
        	System.out.println("월별전신시간메서드 예외발생");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //상체운동 월별 시간호출
   public Integer selectUpperbodyAll(int upperbody) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '상체운동' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //결과 값을 담을 곳      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //있으면
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("월별상체운동시간 메서드 성공");
           }
        } catch (Exception e) {
        	System.out.println("월별상체운동시간메서드 예외발생");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //복근운동 월별 시간호출
   public Integer selectAbsAll(int abs) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '복근운동' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //결과 값을 담을 곳      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //있으면
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("월별복근운동 메서드 성공");
           }
        } catch (Exception e) {
        	System.out.println("월별복근시간메서드 예외발생");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //스트레칭 월별 시간호출
   public Integer selectStretchingAll(int stretching) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '스트레칭' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //결과 값을 담을 곳      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //있으면
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("월별스트레칭시간 메서드 성공");
           }
        } catch (Exception e) {
        	System.out.println("월별스트레칭시간메서드 예외발생");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //하체운동 월별 시간호출
   public Integer selectLowerAll(int lower) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '하체운동' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //결과 값을 담을 곳      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //있으면
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("월별하체운동시간 메서드 성공");
           }
        } catch (Exception e) {
        	System.out.println("월별하체운동시간메서드 예외발생");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
}
