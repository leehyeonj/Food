package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import HealthSchedule.model.Routines;

public class RoutineDao {
	

	ArrayList<Routines_lower> list = new ArrayList<>();
	ArrayList<RoutineTime> timeList = new ArrayList<>();
	ArrayList<TotalTime> totaltimeList = new ArrayList<>();   
	   
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
 //시간 저장
   public void saveTime(String everyday, String part, int timehour , int timeminute, int timesecond) {
	   //쿼리문 준비
       String sql = "insert into workoutTime values(?,?,?,?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, part);
           pstmt.setInt(3, timehour);
           pstmt.setInt(4, timeminute);
           pstmt.setInt(5, timesecond);
           
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("타임데이터 삽입 성공!");
               
           }
           
       } catch (Exception e) {
           System.out.println("타임데이터 삽입 실패!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   //시간수정
   public void updateTime(String everyday, String bodypart, int hour, int minute, int second) {
       String sql = "update workoutTime set timehour =?, timeminute =?, timesecond=? where everyday=? and bodypart=? ";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, hour);
           pstmt.setInt(2,minute);
           pstmt.setInt(3,second);
           pstmt.setString(4, everyday);
           pstmt.setString(5, bodypart);
           pstmt.executeUpdate();
           System.out.println("time 수정 완료");
           
       } catch (Exception e) {
           System.out.println("update time예외 발생");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   }
   
   //총 시간 셀렉트
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
           } catch (Exception e2) { }
       }
       return totalTime;
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
   
   //그 날에 저장된 루틴 값이 있냐 없냐
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
   
   //루틴+ 파트 저장된 값이 있는지
   public boolean ifexistRoutine(String everyday, String bodypart) {
	   boolean result = false;
       String sql = "select * from everydayRoutine where everyday = ? and bodypart=?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, bodypart);
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
   
 //그 날에 저장된 시간 값이 있냐 없냐 + 파트
   public boolean ifexistTime(String everyday, String bodypart) {
	   boolean result = false;
       String sql = "select * from workoutTime where everyday = ? and bodypart =?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, bodypart);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   result = true;
        	  
           }
           
       } catch (Exception e) {
           System.out.println("select time 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
 //그 날에 저장된 시간 값이 있냐 없냐
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
   //저장되어있는 루틴을 보여주라
   public ArrayList<Routines_lower> viewDayRoutine(String everyday) {
	 
       String sql = "select everyday, bodypart, videoname from everydayRoutine where everyday = ? ";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
        
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
   
   
   //그날 저장된 시간을 보여줘라
   RoutineTime routineTime;
   public RoutineTime viewDayTime(String everyday, String bodypart) {
	   routineTime = new RoutineTime();
       String sql = "select everyday, bodypart, timehour, timeminute,timesecond from workoutTime where everyday = ? and bodypart =?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, bodypart);
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
           while(rs.next()) {
        	
       
        	routineTime.setEverday(rs.getString("everyday")); 
        	routineTime.setBodypart(rs.getString("bodypart")); 
        	routineTime.setTimehour(rs.getInt("timehour")); 
        	routineTime.setTimeminute(rs.getInt("timeminute")); 
        	routineTime.setTimesecond(rs.getInt("timesecond")); 
        	
        	timeList.add(routineTime);
           	}
           
       } catch (Exception e) {
           System.out.println("timeselect 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return routineTime;
   }
   
   public ArrayList<RoutineTime> viewDayTime(String everyday) {
		 
       String sql = "select everyday, bodypart, timehour, timeminute,timesecond from workoutTime where everyday = ? and bodypart =?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
          
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
           while(rs.next()) {
        	RoutineTime routineTime = new RoutineTime();
       
        	routineTime.setEverday(rs.getString("everyday")); 
        	routineTime.setBodypart(rs.getString("bodypart")); 
        	routineTime.setTimehour(rs.getInt("timehour")); 
        	routineTime.setTimeminute(rs.getInt("timeminute")); 
        	routineTime.setTimesecond(rs.getInt("timesecond")); 
        	
        	timeList.add(routineTime);
           	}
           
       } catch (Exception e) {
           System.out.println("timeselect 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return timeList;
   }
}
