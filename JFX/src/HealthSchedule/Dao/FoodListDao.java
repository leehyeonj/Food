package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FoodListDao {
	
	String name, Unit, cal;
	
	public FoodListDao(String foodname, String foodunit, String cal) {
		super();
		this.name = foodname;
		this.Unit = foodunit;
		this.cal = cal;
	}

	public boolean loginsuccess;
	private static Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
//    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public FoodListDao() {
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
   
   //db����
   public static Connection connect() throws SQLException{
	   try {
		   Class.forName("com.mysql.jdbc.Driver"); 
	       conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	       System.out.println("���� ����̹� �ε� ����!!");
	        
	   } catch (Exception e) {
	       e.printStackTrace();
	       System.out.println("����̹� �ε� ����!!");
	   }
       conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
       return conn;
   }
   
   //�߰��� �׸��� db�� �����ϴ� �޼���
   public Connection saveContent(String foodname, String foodunit, String cal)throws SQLException {
	   //������ �غ�	//�����̸�,����,Į�θ�(��¥�߰��ʿ�)
	   String sql = "insert into Foodtest values(?,?,?)";
	   PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, foodname);
           pstmt.setString(2, foodunit);
           pstmt.setString(3, cal);
           int result = pstmt.executeUpdate();
           
           if(result==1) {
               System.out.println("�Ļ絥���� ���� ����!");
           }
       } catch (Exception e) {
           System.out.println("�Ļ絥���� ���� ����!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
       return conn;
   }
   
   //�����ϴ� �޼���
   public Connection deleteFood(String foodname, String foodunit, String cal)throws SQLException  {

	   String sql = "delete from Foodtest where foodname =? and foodunit =? and cal =?";
	   PreparedStatement pstmt = null;
		try {
			  pstmt = conn.prepareStatement(sql);
	          pstmt.setString(1, foodname);
	          pstmt.setString(2, foodunit);
	          pstmt.setString(3, cal);
	          int result = pstmt.executeUpdate();
	           if(result==1) {
	               System.out.println("�Ļ絥���� ���� ����!");
	           }
	       }  catch (Exception e) {
	           System.out.println("�Ļ絥���� ���� ����!");
	       }
	   conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	   return conn;  
  }
   
   	//getter,setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		this.Unit = unit;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
}
