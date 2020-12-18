package HealthSchedule.Dao;

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

    
    public boolean resetAccount = false;
    public boolean reset1 = false;
    public boolean reset2 = false;
    public boolean reset3 = false;
    public boolean reset4 = false;
    
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
   
   public void reset1() {
		
  	  //쿼리문 준비
	   String sql = "delete from workoutTime";

	
	   
	   PreparedStatement pstmt = null;
  

      try {
          pstmt = conn.prepareStatement(sql);
          int result = pstmt.executeUpdate();
          if(result==1) {
              System.out.println("리셋 성공");
              reset1 = true;

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
   
   public void reset2() {
	
	  String sql = "delete from everydayRoutine";
	   PreparedStatement pstmt = null;
     try {
         pstmt = conn.prepareStatement(sql);
         int result = pstmt.executeUpdate();
         if(result==1) {
             System.out.println("리셋 성공");
             reset2 = true;
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
   
   public void reset3() {
		
		  String sql = "delete from dailphoto";
		   PreparedStatement pstmt = null;
	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("리셋 성공");
	             reset3 = true;
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
   
   public void reset4() {
		
		  String sql = "delete from memo";
		   PreparedStatement pstmt = null;
	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("리셋 성공");
	             reset4 = true;
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
             resetAccount = true;

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
