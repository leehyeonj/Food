package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
    public boolean reset1 = false;
    public boolean reset2 = false;
    public boolean reset3 = false;
    public boolean reset4 = false;
    
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
   
   public void reset1() {
		
  	  //������ �غ�
	   String sql = "delete from workoutTime";

	
	   
	   PreparedStatement pstmt = null;
  

      try {
          pstmt = conn.prepareStatement(sql);
          int result = pstmt.executeUpdate();
          if(result==1) {
              System.out.println("���� ����");
              reset1 = true;

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
   
   public void reset2() {
	
	  String sql = "delete from everydayRoutine";
	   PreparedStatement pstmt = null;
     try {
         pstmt = conn.prepareStatement(sql);
         int result = pstmt.executeUpdate();
         if(result==1) {
             System.out.println("���� ����");
             reset2 = true;
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
   
   public void reset3() {
		
		  String sql = "delete from dailphoto";
		   PreparedStatement pstmt = null;
	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("���� ����");
	             reset3 = true;
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
   
   public void reset4() {
		
		  String sql = "delete from memo";
		   PreparedStatement pstmt = null;
	     try {
	         pstmt = conn.prepareStatement(sql);
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	             System.out.println("���� ����");
	             reset4 = true;
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
}
