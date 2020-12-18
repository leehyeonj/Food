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
	private static Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //상아 , 중섭
//    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public FoodListDao() {
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
   
   //db연결
   public static Connection connect() throws SQLException{
	   try {
		   Class.forName("com.mysql.jdbc.Driver"); 
	       conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	       System.out.println("음식 드라이버 로딩 성공!!");
	        
	   } catch (Exception e) {
	       e.printStackTrace();
	       System.out.println("드라이버 로드 실패!!");
	   }
       conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
       return conn;
   }
   
   //추가한 항목을 db에 저장하는 메서드
   public Connection saveContent(String foodname, String foodunit, String cal)throws SQLException {
	   //쿼리문 준비	//음식이름,단위,칼로리(날짜추가필요)
	   String sql = "insert into Foodtest values(?,?,?)";
	   PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, foodname);
           pstmt.setString(2, foodunit);
           pstmt.setString(3, cal);
           int result = pstmt.executeUpdate();
           
           if(result==1) {
               System.out.println("식사데이터 삽입 성공!");
           }
       } catch (Exception e) {
           System.out.println("식사데이터 삽입 실패!");
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
   
   //삭제하는 메서드
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
	               System.out.println("식사데이터 삭제 성공!");
	           }
	       }  catch (Exception e) {
	           System.out.println("식사데이터 삭제 실패!");
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
