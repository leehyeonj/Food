package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import HealthSchedule.model.Food;
import HealthSchedule.model.FoodTable;
import HealthSchedule.model.Foodlist;


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
    private static final String PASSWORD = "DOALd1120f1gG";    //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";   //dbms
    
    //상아 , 중섭
//    private static final String PASSWORD = "1234";    //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";   //dbms
    
   public FoodListDao() {
      try {
         //동적 객체를 만들어줌 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("푸드 드라이버 로딩 성공!!");   
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("푸드 드라이버 로드 실패!!");
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
//   public Connection saveContent(String foodname, String foodunit, String cal)throws SQLException {
//      //쿼리문 준비   //음식이름,단위,칼로리(날짜추가필요)
//      String sql = "insert into Foodtest values(?,?,?)";
//      PreparedStatement pstmt = null;
//       try {
//           pstmt = conn.prepareStatement(sql);
//           pstmt.setString(1, foodname);
//           pstmt.setString(2, foodunit);
//           pstmt.setString(3, cal);
//           int result = pstmt.executeUpdate();
//           
//           if(result==1) {
//               System.out.println("식사데이터 삽입 성공!");
//           }
//       } catch (Exception e) {
//           System.out.println("식사데이터 삽입 실패!");
//       }    finally {
//           try {
//               if(pstmt!=null && !pstmt.isClosed()) {
//                   pstmt.close();
//               }
//           } catch (Exception e2) {}
//       }
//       conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//       return conn;
//   }
   
   //목록 저장
   public void saveFoodToTable(String foodname, String foodunit, String cal) {
	   
	   //쿼리문 준비
       String sql = "insert into Food values(?,?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, foodname);
           pstmt.setString(2, foodunit);
           pstmt.setString(3, cal);
          
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("음식데이터 삽입 성공!");
               
           }
           
       } catch (Exception e) {
           System.out.println("음식데이터 삽입 실패!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   public Connection saveContent(String everyday, String eattime, String foodname, String foodunit, String cal)throws SQLException {
      //쿼리문 준비 //음식이름,단위,칼로리(날짜추가필요)
      String sql = "insert into Foodtest values(?,?,?,?,?)";
      PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);  
           pstmt.setString(2, eattime);
           pstmt.setString(3, foodname);
           pstmt.setString(4, foodunit);
           pstmt.setString(5, cal);
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
   
   public Foodlist selectfood(String everyday, String eattime) {
      Foodlist foodlist = new Foodlist();
       String sql = "select foodname, foodunit, cal from Foodtest where everyday = ? and eattime = ?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, eattime);
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
          if(rs.next()) {
             foodlist.setEveryday(everyday);
             foodlist.setEattime(eattime);
             foodlist.setFoodname(rs.getString("foodname"));
             foodlist.setFoodunit(rs.getString("foodunit"));
             foodlist.setCal(rs.getString("cal"));
              

              System.out.println("select food 메서드 성공");
              }
           
       } catch (Exception e) {
           System.out.println("select food 예외 ");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return foodlist;
   }
   
   ArrayList<FoodTable> foodtablelist = new ArrayList<>();
   public ArrayList<FoodTable> selectfood() {
	    
	       String sql = "select * from Food";
	       PreparedStatement pstmt = null;
	       //결과 값을 담을 곳
	      
	       try {
	           pstmt = conn.prepareStatement(sql);
	       
	           ResultSet rs = pstmt.executeQuery();
	           
	           //있으면
	          while(rs.next()) {
	        		FoodTable foodtable = new FoodTable();
	        		foodtable.setFoodname(rs.getString("foodname"));
	        		foodtable.setFoodunit(rs.getString("foodunit"));
	        		foodtable.setCal(rs.getString("cal"));
	        		foodtablelist.add(foodtable);

	              System.out.println("select food 메서드 성공");
	              }
	           
	       } catch (Exception e) {
	           System.out.println("select food 예외발생");
	           
	       }    finally {
	           try {
	               if(pstmt!=null && !pstmt.isClosed()) {
	                   pstmt.close();
	                  
	               }
	           } catch (Exception e2) { }
	       }
	       return foodtablelist;
	   }
   
 //그 날에 저장된 음식 값이 있냐 없냐
   public boolean ifexistFood(String everyday, String eattime) {
	   boolean result = false;
       String sql = "select * from Foodtest where everyday = ? and eattime =?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, eattime);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   result = true;
        	  
           }
           
       } catch (Exception e) {
           System.out.println("select 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
 //그 날에 저장된 음식 값이 있냐 없냐
   public boolean ifexistFood(String everyday) {
	   boolean result = false;
       String sql = "select * from Foodtest where everyday = ?";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
         
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
        	   result = true;
        	  
           }
           
       } catch (Exception e) {
           System.out.println("select 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   
   //저장되어있는 음식을 보여달라
  
   public ArrayList<Foodlist> viewDayFood(String everyday, String eattime) {
	   ArrayList<Foodlist> foodlistlist = new ArrayList<>();
       String sql = "select everyday,eattime,foodname,foodunit,cal from Foodtest where everyday = ? and eattime =?";
       PreparedStatement pstmt = null;
       //결과 값을 담을 곳
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, eattime);
           ResultSet rs = pstmt.executeQuery();
           
           //있으면
           while(rs.next()) {
        	Foodlist foodlist = new Foodlist();
       
        	foodlist.setEveryday(rs.getString("everyday")); 
        	foodlist.setEattime(rs.getString("eattime"));
        	foodlist.setFoodname(rs.getString("foodname"));
        	foodlist.setFoodunit(rs.getString("foodunit"));
        	foodlist.setCal(rs.getString("cal"));
        	foodlistlist.add(foodlist);
           	}
           
       } catch (Exception e) {
           System.out.println("select 메서드 예외발생");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return foodlistlist;
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
	
	 //총 칼로리 셀렉트
	   public Food selecTotalKcal(String everyday) {
		   Food total= new Food();
	       String sql = "select everyday, cal from Foodtest where everyday=?";
	       PreparedStatement pstmt = null;
	       //결과 값을 담을 곳
	      
	       try {
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, everyday);
	           ResultSet rs = pstmt.executeQuery();
	           
	           //있으면
	          while(rs.next()) {
	        	   
	        	  total.setEveryday(rs.getString("everyday"));
	        	  total.setCal(rs.getString("cal"));
	        	  total.totalKcal += Integer.parseInt(total.getCal());

	        	   System.out.println("토탈 메서드 성공");
	           	}
	           
	       } catch (Exception e) {
	           System.out.println("토탈메서드 예외발생");
	           
	       }    finally {
	           try {
	               if(pstmt!=null && !pstmt.isClosed()) {
	                   pstmt.close();
	                  
	               }
	           } catch (Exception e2) { }
	       }
	       return total;
	   }
  
}