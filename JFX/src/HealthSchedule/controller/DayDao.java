package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DayDao {

	
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
//private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public DayDao() {
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
   
  
        
        
    
}
