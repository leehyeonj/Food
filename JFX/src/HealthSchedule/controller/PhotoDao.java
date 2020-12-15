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
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
//    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public PhotoDao() {
	   try {
       	//동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("포토 드라이버 로딩 성공!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("포토 로드 실패!!");
       }
       
   }
   
 //사진 저장
   public void savePhoto(String everyday, String fileurl) {
	   //쿼리문 준비
       String sql = "insert into dailphoto values(?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, fileurl);
          
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("사진 데이터 삽입 성공!");
               
           }
           
       } catch (Exception e) {
           System.out.println("사진 데이터 삽입 실패!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   
   //사진 업데이트
   public void updatePhoto(String everyday, String fileurl) {
	   String sql ="update dailphoto set fileurl =? where everyday=? ";
	   PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, fileurl);
           pstmt.setString(2,everyday);
          
           pstmt.executeUpdate();
           System.out.println("사진 수정 완료");
           
       } catch (Exception e) {
           System.out.println("사진 수정예외 발생");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   
   
   
   
   }
   
   //사진이 그날에 저장되어있냐
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
           System.out.println("사진 있는지 없는지 확인 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   //사진셀렉트
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
           System.out.println("사진 있는지 없는지 확인 메서드 예외발생");
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
//           System.out.println("파일 select 성공");
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
