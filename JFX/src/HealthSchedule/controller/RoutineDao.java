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
	   
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
//private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public RoutineDao() {
	   try {
       	//���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("��ƾ ����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("����̹� �ε� ����!!");
       }
       
   }
   
   //��ƾ ����
   public void saveRoutine(String everyday, String part, String videoname) {
	   //������ �غ�
       String sql = "insert into everydayRoutine values(?,?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, part);
           pstmt.setString(3, videoname);
          
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("Board������ ���� ����!");
               
           }
           
       } catch (Exception e) {
           System.out.println("Board������ ���� ����!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
 //�ð� ����
   public void saveTime(String everyday, String part, int timehour , int timeminute, int timesecond) {
	   //������ �غ�
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
               System.out.println("Ÿ�ӵ����� ���� ����!");
               
           }
           
       } catch (Exception e) {
           System.out.println("Ÿ�ӵ����� ���� ����!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   //�ð�����
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
           System.out.println("time ���� �Ϸ�");
           
       } catch (Exception e) {
           System.out.println("update time���� �߻�");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   }
   
   //�� �ð� ����Ʈ
   public Routines selecTotalTime(String everyday) {
	   Routines totalTime = new Routines();
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime group by everyday having everyday=?";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           
           //������
          if(rs.next()) {
        	   
        	   totalTime.setEveryday(rs.getString("everyday"));
        	   totalTime.setHour(rs.getInt("sum(timehour)"));
        	   totalTime.setMinute(rs.getInt("sum(timeminute)"));
        	   totalTime.setSecond(rs.getInt("sum(timesecond)"));

        	
        	   System.out.println("��Ż �޼��� ����");
           	}
           
       } catch (Exception e) {
           System.out.println("��Ż�޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return totalTime;
   }


   //��ƾ ����
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
				System.out.println("������ ���� ����");
	           }
	           else {
				result = false;
				System.out.println("������ ���� ����");
			}
	           pstmt.close();
	           
		 } catch (Exception e) {
			// TODO: handle exception
		}
	   
	   return result;
       
  }
   
   //�� ���� ����� ��ƾ ���� �ֳ� ����
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
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   //��ƾ+ ��Ʈ ����� ���� �ִ���
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
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
 //�� ���� ����� �ð� ���� �ֳ� ���� + ��Ʈ
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
           System.out.println("select time �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
 //�� ���� ����� �ð� ���� �ֳ� ����
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
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   
   //����Ǿ��ִ� ��ƾ�� �����ֶ�
   public ArrayList<Routines_lower> viewDayRoutine(String everyday, String bodypart) {
	 
       String sql = "select everyday, bodypart, videoname from everydayRoutine where everyday = ? and bodypart =?";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, bodypart);
           ResultSet rs = pstmt.executeQuery();
           
           //������
           while(rs.next()) {
        	Routines_lower routineslower = new Routines_lower();
       
        	routineslower.setEveryday(rs.getString("everyday")); 
        	routineslower.setBodypart(rs.getString("bodypart")); 
        	routineslower.setVideoname(rs.getString("videoname")); 
        	list.add(routineslower);
           	}
           
       } catch (Exception e) {
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return list;
   }
   //����Ǿ��ִ� ��ƾ�� �����ֶ�
   public ArrayList<Routines_lower> viewDayRoutine(String everyday) {
	 
       String sql = "select everyday, bodypart, videoname from everydayRoutine where everyday = ? ";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
        
           ResultSet rs = pstmt.executeQuery();
           
           //������
           while(rs.next()) {
        	Routines_lower routineslower = new Routines_lower();
       
        	routineslower.setEveryday(rs.getString("everyday")); 
        	routineslower.setBodypart(rs.getString("bodypart")); 
        	routineslower.setVideoname(rs.getString("videoname")); 
        	list.add(routineslower);
           	}
           
       } catch (Exception e) {
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return list;
   }
   
   
   //�׳� ����� �ð��� �������
   RoutineTime routineTime;
   public RoutineTime viewDayTime(String everyday, String bodypart) {
	   routineTime = new RoutineTime();
       String sql = "select everyday, bodypart, timehour, timeminute,timesecond from workoutTime where everyday = ? and bodypart =?";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, bodypart);
           ResultSet rs = pstmt.executeQuery();
           
           //������
           while(rs.next()) {
        	
       
        	routineTime.setEverday(rs.getString("everyday")); 
        	routineTime.setBodypart(rs.getString("bodypart")); 
        	routineTime.setTimehour(rs.getInt("timehour")); 
        	routineTime.setTimeminute(rs.getInt("timeminute")); 
        	routineTime.setTimesecond(rs.getInt("timesecond")); 
        	
        	timeList.add(routineTime);
           	}
           
       } catch (Exception e) {
           System.out.println("timeselect �޼��� ���ܹ߻�");
           
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
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
          
           ResultSet rs = pstmt.executeQuery();
           
           //������
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
           System.out.println("timeselect �޼��� ���ܹ߻�");
           
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
