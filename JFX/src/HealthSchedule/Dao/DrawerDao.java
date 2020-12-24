package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public boolean resetTime = false;
    public boolean resetRoutine = false;
    public boolean resetPhoto = false;
    public boolean resetMemo = false;
    public boolean resetFood = false;
    public boolean resetFoodTest = false;
    public boolean resetWeight = false;
    public boolean resetgoalWeight = false;
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
   
 //그 날에 저장된 루틴 값이 있냐 없냐
   public boolean ifexistRoutine() {
	   boolean result = false;
       String sql = "select * from everydayRoutine";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
 //그 날에 저장된 푸드 값이 있냐 없냐
   public boolean ifexistFood() {
	   boolean result = false;
       String sql = "select * from Food";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
 //그 날에 저장된 푸드저장 값이 있냐 없냐
   public boolean ifexistFoodtest() {
	   boolean result = false;
       String sql = "select * from Foodtest";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
 //그 날에 저장된 메모 값이 있냐 없냐
   public boolean ifexistMemo() {
	   boolean result = false;
       String sql = "select * from memo";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
   //그 날에 저장된 사진 값이 있냐 없냐
   public boolean ifexistPhoto() {
	   boolean result = false;
       String sql = "select * from dailphoto";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
   //그 날에 저장된 몸무게 값이 있냐 없냐
   public boolean ifexistWeight() {
	   boolean result = false;
       String sql = "select * from weight";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
 //그 날에 저장된 몸무게 값이 있냐 없냐
   public boolean ifexistgoalWeight() {
	   boolean result = false;
       String sql = "select * from goalweight";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
   
 //그 날에 저장된 시간 값이 있냐 없냐
   public boolean ifexistTime() {
	   boolean result = false;
       String sql = "select * from workoutTime";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
 //그 날에 저장된 시간 값이 있냐 없냐
   public boolean ifexistaccount() {
	   boolean result = false;
       String sql = "select * from calendar";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
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
   
   
   
   public void resetWorkoutTime() {
		
  	  //쿼리문 준비
	   String sql = "delete from workoutTime";

	
	   
	   PreparedStatement pstmt = null;
  

      try {
          pstmt = conn.prepareStatement(sql);
          int result = pstmt.executeUpdate();
          if(result==1) {
              System.out.println("리셋 성공");
              resetTime = true;

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
   
   public void resetRoutine() {
	
	  String sql = "delete from everydayRoutine";
	   PreparedStatement pstmt = null;
     try {
         pstmt = conn.prepareStatement(sql);
         int result = pstmt.executeUpdate();
         if(result==1) {
             System.out.println("리셋 성공");
             resetRoutine = true;
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
   
   public void resetPhoto() {
		
		  String sql = "delete from dailphoto";
		   PreparedStatement pstmt = null;
	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("리셋 성공");
	             resetPhoto = true;
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
   
   public void resetMemo() {
		
		  String sql = "delete from memo";
		   PreparedStatement pstmt = null;
	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("리셋 성공");
	             resetMemo = true;
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
   
   public void resetFood() {
		
	  	  //쿼리문 준비
		   String sql = "delete from Food";

		
		   
		   PreparedStatement pstmt = null;
	  

	      try {
	          pstmt = conn.prepareStatement(sql);
	          int result = pstmt.executeUpdate();
	          if(result==1) {
	              System.out.println("리셋 성공");
	              resetFood = true;

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
   
   public void resetFoodtest() {
		
	  	  //쿼리문 준비
		   String sql = "delete from Foodtest";

		
		   
		   PreparedStatement pstmt = null;
	  

	      try {
	          pstmt = conn.prepareStatement(sql);
	          int result = pstmt.executeUpdate();
	          if(result==1) {
	              System.out.println("리셋 성공");
	              resetFoodTest = true;

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
   
   public void resetWeight() {
		
	 	  //쿼리문 준비
		   String sql = "delete from weight";
		 
		   PreparedStatement pstmt = null;
		 

	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("리셋 성공");
	             resetWeight = true;

	         }
	        

	     } catch (Exception e) {

	         System.out.println("리셋  실패");
	     }    finally {
	         try {
	             if(pstmt!=null && !pstmt.isClosed()) {
	                 pstmt.close();
	             }
	         } catch (Exception e2) {}
	     }
	  }
   
   public void resetGoalWeight() {
		
	 	  //쿼리문 준비
		   String sql = "delete from goalweight";
		 
		   PreparedStatement pstmt = null;
		 

	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("리셋 성공");
	             resetgoalWeight = true;

	         }
	        

	     } catch (Exception e) {

	         System.out.println("리셋  실패");
	     }    finally {
	         try {
	             if(pstmt!=null && !pstmt.isClosed()) {
	                 pstmt.close();
	             }
	         } catch (Exception e2) {}
	     }
	  }
}
