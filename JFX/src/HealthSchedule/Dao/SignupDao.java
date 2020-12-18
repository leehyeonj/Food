package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDao {

	public boolean emailexists;
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
//private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public SignupDao() {
	   try {
       	//동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("드라이버 로딩 성공!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("드라이버 로드 실패!!");
       }
       
   }
   
   public void signup(String email, String password, String passwordcheck) {
	   String sql = "insert into calendar values(?,?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, email);
           pstmt.setString(2, password);
           pstmt.setString(3, passwordcheck);
         
           
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
        
        
   public void emailalreadyexist(String email) {
	   //이메일이 존재하는지 확인
	   String sql = "select * from calendar where email =?";
	   PreparedStatement pstmt = null;
	   try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			emailexists = true;
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
   }
    
}
