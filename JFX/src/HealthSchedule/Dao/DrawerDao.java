package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DrawerDao {
	private Connection conn;    //DB Ŀ�ؼ�(����) ��
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    //��� , �߼�
//    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
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
       	   //���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("���� ����̹� �ε� ����!!");

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("���� ����̹� �ε� ����!!");
       }
   }
   
 //�� ���� ����� ��ƾ ���� �ֳ� ����
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
 //�� ���� ����� Ǫ�� ���� �ֳ� ����
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
   
 //�� ���� ����� Ǫ������ ���� �ֳ� ����
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
   
 //�� ���� ����� �޸� ���� �ֳ� ����
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
   
   //�� ���� ����� ���� ���� �ֳ� ����
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
   
   //�� ���� ����� ������ ���� �ֳ� ����
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
   
 //�� ���� ����� ������ ���� �ֳ� ����
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
   
   
 //�� ���� ����� �ð� ���� �ֳ� ����
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
   
 //�� ���� ����� �ð� ���� �ֳ� ����
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
   
   
   
   public void resetWorkoutTime() {
		
  	  //������ �غ�
	   String sql = "delete from workoutTime";

	
	   
	   PreparedStatement pstmt = null;
  

      try {
          pstmt = conn.prepareStatement(sql);
          int result = pstmt.executeUpdate();
          if(result==1) {
              System.out.println("���� ����");
              resetTime = true;

          }

      } catch (Exception e) {

          System.out.println("���� ����");
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
             System.out.println("���� ����");
             resetRoutine = true;
         }
      
     } catch (Exception e) {

         System.out.println("���� ����");
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
	             System.out.println("���� ����");
	             resetPhoto = true;
	         }
	      
	     } catch (Exception e) {

	         System.out.println("���� ����");
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
	             System.out.println("���� ����");
	             resetMemo = true;
	         }
	      
	     } catch (Exception e) {

	         System.out.println("���� ����");
	     }    finally {
	         try {
	             if(pstmt!=null && !pstmt.isClosed()) {
	                 pstmt.close();
	             }
	         } catch (Exception e2) {}
	     }
	  }
   
   public void resetFood() {
		
	  	  //������ �غ�
		   String sql = "delete from Food";

		
		   
		   PreparedStatement pstmt = null;
	  

	      try {
	          pstmt = conn.prepareStatement(sql);
	          int result = pstmt.executeUpdate();
	          if(result==1) {
	              System.out.println("���� ����");
	              resetFood = true;

	          }

	      } catch (Exception e) {

	          System.out.println("���� ����");
	      }    finally {
	          try {
	              if(pstmt!=null && !pstmt.isClosed()) {
	                  pstmt.close();
	              }
	          } catch (Exception e2) {}
	      }
	   }
   
   public void resetFoodtest() {
		
	  	  //������ �غ�
		   String sql = "delete from Foodtest";

		
		   
		   PreparedStatement pstmt = null;
	  

	      try {
	          pstmt = conn.prepareStatement(sql);
	          int result = pstmt.executeUpdate();
	          if(result==1) {
	              System.out.println("���� ����");
	              resetFoodTest = true;

	          }

	      } catch (Exception e) {

	          System.out.println("���� ����");
	      }    finally {
	          try {
	              if(pstmt!=null && !pstmt.isClosed()) {
	                  pstmt.close();
	              }
	          } catch (Exception e2) {}
	      }
	   }
   public void resetAccount() {
		
 	  //������ �غ�
	   String sql = "delete from calendar";
	 
	   PreparedStatement pstmt = null;
	 

     try {
         pstmt = conn.prepareStatement(sql);
         int result = pstmt.executeUpdate();
         if(result==1) {
             System.out.println("���� ���� ����");
             resetAccount = true;

         }
        

     } catch (Exception e) {

         System.out.println("���� ����  ����");
     }    finally {
         try {
             if(pstmt!=null && !pstmt.isClosed()) {
                 pstmt.close();
             }
         } catch (Exception e2) {}
     }
  }
   
   public void resetWeight() {
		
	 	  //������ �غ�
		   String sql = "delete from weight";
		 
		   PreparedStatement pstmt = null;
		 

	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("���� ����");
	             resetWeight = true;

	         }
	        

	     } catch (Exception e) {

	         System.out.println("����  ����");
	     }    finally {
	         try {
	             if(pstmt!=null && !pstmt.isClosed()) {
	                 pstmt.close();
	             }
	         } catch (Exception e2) {}
	     }
	  }
   
   public void resetGoalWeight() {
		
	 	  //������ �غ�
		   String sql = "delete from goalweight";
		 
		   PreparedStatement pstmt = null;
		 

	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("���� ����");
	             resetgoalWeight = true;

	         }
	        

	     } catch (Exception e) {

	         System.out.println("����  ����");
	     }    finally {
	         try {
	             if(pstmt!=null && !pstmt.isClosed()) {
	                 pstmt.close();
	             }
	         } catch (Exception e2) {}
	     }
	  }
}
