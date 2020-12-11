package HealthSchedule.controller;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoutineDao {
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
   
//   public void deleteRoutine(String everyday, String part, String videoname) {
//	   //������ �غ�
//       String sql = "delete from everydayRoutine where everyday = ? and part =? and videoname =?";
//       
//       PreparedStatement pstmt = null;
//       try {
//           pstmt = conn.prepareStatement(sql);
//           pstmt.setString(1, everyday);
//           pstmt.setString(2, part);
//           pstmt.setString(3, videoname);
//          
//           try {
//        	   pstmt.executeUpdate(sql);
//        	   System.out.println("���� ����");
//           } catch (SQLException e) {
//        	   System.out.println("Board������ ���� ����!");
//           }
//       } catch (Exception e) {
//           
//       }    finally {
//           try {
//               if(pstmt!=null && !pstmt.isClosed()) {
//                   pstmt.close();
//               }
//           } catch (Exception e2) {}
//       }
//       
//  }
   
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
   
}
