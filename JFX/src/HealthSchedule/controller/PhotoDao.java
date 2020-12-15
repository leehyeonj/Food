package HealthSchedule.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Blob;

public class PhotoDao {
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
//    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
//    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public PhotoDao() {
	   try {
       	//���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("���� ����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("���� �ε� ����!!");
       }
       
   }
   
 //���� ����
   public void savePhoto(String everyday, String fileurl) {
	   //������ �غ�
       String sql = "insert into dailphoto values(?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, fileurl);
          
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("���� ������ ���� ����!");
               
           }
           
       } catch (Exception e) {
           System.out.println("���� ������ ���� ����!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   
   //���� ������Ʈ
   public void updatePhoto(String everyday, String fileurl) {
	   String sql ="update dailphoto set fileurl =? where everyday=? ";
	   PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, fileurl);
           pstmt.setString(2,everyday);
          
           pstmt.executeUpdate();
           System.out.println("���� ���� �Ϸ�");
           
       } catch (Exception e) {
           System.out.println("���� �������� �߻�");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   
   
   
   
   }
   
   //������ �׳��� ����Ǿ��ֳ�
   public boolean ifexistPhoto(String everyday) {
	   boolean result = false;
       String sql = "select * from dailphoto where everyday = ?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   result = true;
        	  
           }
           
       } catch (Exception e) {
           System.out.println("���� �ִ��� ������ Ȯ�� �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   //��������Ʈ
   public String selectPhoto(String everyday) {
	   String filepath = "";
       String sql = "select fileurl from dailphoto where everyday = ?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   filepath = rs.getString("fileurl");
        	   return filepath;
           }
           return "";
       } catch (Exception e) {
           System.out.println("���� �ִ��� ������ Ȯ�� �޼��� ���ܹ߻�");
           return "";
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	
   }
//   public void selectPhoto(String everyday, String fileurl) {
//	   String sql = "select * from dailphoto where everyday=?";
//	   
//       PreparedStatement pstmt = null;
//       FileOutputStream fos = null;
//       try {
//   
//           pstmt = conn.prepareStatement(sql);
//           pstmt.setString(1, everyday);
//           ResultSet rs = pstmt.executeQuery();
//           rs.next();
//           InputStream is = rs.getBinaryStream("image");
//           fos = new FileOutputStream(
//                   file);
//           byte[] b = new byte[1024];
//           int n;
//           while ((n = is.read(b)) > 0) {
//               fos.write(b, 0, n);
//           }
//           System.out.println("���� select ����");
//       } catch (SQLException e) {
//           e.printStackTrace();
//       } catch (FileNotFoundException e) {
//           e.printStackTrace();
//       } catch (IOException e) {
//           e.printStackTrace();
//       } finally {
//           if (conn != null) {
//               try {
//                   conn.close();
//               } catch (SQLException e) {
//                   e.printStackTrace();
//               }
//           }
//
//           if (pstmt != null) {
//               try {
//            	   pstmt.close();
//               } catch (SQLException e) {
//                   e.printStackTrace();
//               }
//           }
//
//           if (fos != null) {
//               try {
//                   fos.close();
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//           }
//       }
//   }
   
}
