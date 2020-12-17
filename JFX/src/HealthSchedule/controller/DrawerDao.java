package HealthSchedule.controller;

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
   
   public void reset() {
		System.out.println("�����͸� �����մϴ�.");
  	  //������ �غ�
	   String sql = "delete from workoutTime";
	  String sql2 = "delete from everydayRoutine";
	 
	  String sql3 = "delete from dailphoto";
	  String sql4 = "delete from memo";
	
	   
	   PreparedStatement pstmt = null;
	   PreparedStatement pstmt2 = null;
	   PreparedStatement pstmt3= null;
	   PreparedStatement pstmt4 = null;
	   
	   

      try {
          pstmt = conn.prepareStatement(sql);
          int result = pstmt.executeUpdate();
          if(result==1) {
              System.out.println("���� ����");

          }
          pstmt2 = conn.prepareStatement(sql2);
          result = pstmt2.executeUpdate();
          if(result==1) {
              System.out.println("���� ����");

          }
          pstmt3 = conn.prepareStatement(sql3);
          result = pstmt3.executeUpdate();
          if(result==1) {
              System.out.println("���� ����");

          }
          pstmt4 = conn.prepareStatement(sql4);
          result = pstmt4.executeUpdate();
          if(result==1) {
              System.out.println("���� ����");

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
