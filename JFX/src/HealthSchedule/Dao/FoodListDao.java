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
   private static Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";    //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";   //dbms
    
    //��� , �߼�
//    private static final String PASSWORD = "1234";    //DB ���ӽ� �н�����
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";   //dbms
    
   public FoodListDao() {
      try {
         //���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("Ǫ�� ����̹� �ε� ����!!");   
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Ǫ�� ����̹� �ε� ����!!");
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
//   public Connection saveContent(String foodname, String foodunit, String cal)throws SQLException {
//      //������ �غ�   //�����̸�,����,Į�θ�(��¥�߰��ʿ�)
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
//               System.out.println("�Ļ絥���� ���� ����!");
//           }
//       } catch (Exception e) {
//           System.out.println("�Ļ絥���� ���� ����!");
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
   
   //��� ����
   public void saveFoodToTable(String foodname, String foodunit, String cal) {
	   
	   //������ �غ�
       String sql = "insert into Food values(?,?,?)";
       
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, foodname);
           pstmt.setString(2, foodunit);
           pstmt.setString(3, cal);
          
           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("���ĵ����� ���� ����!");
               
           }
           
       } catch (Exception e) {
           System.out.println("���ĵ����� ���� ����!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
       
   }
   public Connection saveContent(String everyday, String eattime, String foodname, String foodunit, String cal)throws SQLException {
      //������ �غ� //�����̸�,����,Į�θ�(��¥�߰��ʿ�)
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
   
   public Foodlist selectfood(String everyday, String eattime) {
      Foodlist foodlist = new Foodlist();
       String sql = "select foodname, foodunit, cal from Foodtest where everyday = ? and eattime = ?";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, eattime);
           ResultSet rs = pstmt.executeQuery();
           
           //������
          if(rs.next()) {
             foodlist.setEveryday(everyday);
             foodlist.setEattime(eattime);
             foodlist.setFoodname(rs.getString("foodname"));
             foodlist.setFoodunit(rs.getString("foodunit"));
             foodlist.setCal(rs.getString("cal"));
              

              System.out.println("select food �޼��� ����");
              }
           
       } catch (Exception e) {
           System.out.println("select food ���� ");
           
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
	       //��� ���� ���� ��
	      
	       try {
	           pstmt = conn.prepareStatement(sql);
	       
	           ResultSet rs = pstmt.executeQuery();
	           
	           //������
	          while(rs.next()) {
	        		FoodTable foodtable = new FoodTable();
	        		foodtable.setFoodname(rs.getString("foodname"));
	        		foodtable.setFoodunit(rs.getString("foodunit"));
	        		foodtable.setCal(rs.getString("cal"));
	        		foodtablelist.add(foodtable);

	              System.out.println("select food �޼��� ����");
	              }
	           
	       } catch (Exception e) {
	           System.out.println("select food ���ܹ߻�");
	           
	       }    finally {
	           try {
	               if(pstmt!=null && !pstmt.isClosed()) {
	                   pstmt.close();
	                  
	               }
	           } catch (Exception e2) { }
	       }
	       return foodtablelist;
	   }
   
 //�� ���� ����� ���� ���� �ֳ� ����
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
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
 //�� ���� ����� ���� ���� �ֳ� ����
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
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   
   //����Ǿ��ִ� ������ �����޶�
  
   public ArrayList<Foodlist> viewDayFood(String everyday, String eattime) {
	   ArrayList<Foodlist> foodlistlist = new ArrayList<>();
       String sql = "select everyday,eattime,foodname,foodunit,cal from Foodtest where everyday = ? and eattime =?";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, eattime);
           ResultSet rs = pstmt.executeQuery();
           
           //������
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
           System.out.println("select �޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               }
           } catch (Exception e2) { }
       }
       return foodlistlist;
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
	
	 //�� Į�θ� ����Ʈ
	   public Food selecTotalKcal(String everyday) {
		   Food total= new Food();
	       String sql = "select everyday, cal from Foodtest where everyday=?";
	       PreparedStatement pstmt = null;
	       //��� ���� ���� ��
	      
	       try {
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, everyday);
	           ResultSet rs = pstmt.executeQuery();
	           
	           //������
	          while(rs.next()) {
	        	   
	        	  total.setEveryday(rs.getString("everyday"));
	        	  total.setCal(rs.getString("cal"));
	        	  total.totalKcal += Integer.parseInt(total.getCal());

	        	   System.out.println("��Ż �޼��� ����");
	           	}
	           
	       } catch (Exception e) {
	           System.out.println("��Ż�޼��� ���ܹ߻�");
	           
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