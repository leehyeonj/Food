package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDao {

	public boolean emailexists;
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
//private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public SignupDao() {
	   try {
       	//���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("����̹� �ε� ����!!");
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
        
        
   public void emailalreadyexist(String email) {
	   //�̸����� �����ϴ��� Ȯ��
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
