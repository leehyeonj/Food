package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HealthSchedule.model.Food;
import HealthSchedule.model.Routines;
import HealthSchedule.model.Weight;

public class MainDao {
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
//private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public MainDao() {
	   try {
       	//���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("���� ����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("���� ����̹� �ε� ����!!");
       }
       
   }
   //��ƾ���� �ð� ����Ǿ��ִ���
   public boolean ifexistTime(String everyday) {
	   boolean result = false;
       String sql = "select * from workoutTime where everyday = ?";
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
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
	return result;
   }
   
   //��ƾ���� �ѽð� select
   public Routines selecTotalTime(String everyday) {
	   Routines totalTime = new Routines();
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime group by everyday having everyday=?";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��
      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           
           //������
          if(rs.next()) {
        	   
        	   totalTime.setEveryday(rs.getString("everyday"));
        	   totalTime.setHour(rs.getInt("sum(timehour)"));
        	   totalTime.setMinute(rs.getInt("sum(timeminute)"));
        	   totalTime.setSecond(rs.getInt("sum(timesecond)"));

        	
        	   System.out.println("��Ż �޼��� ����");
           	}
           
       } catch (Exception e) {
           System.out.println("��Ż�޼��� ���ܹ߻�");
           
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
                  
               } 
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
       return totalTime;
   }
   
 //�޸� ��ž
   public String tooltipMemo(String everyday) {
//	   boolean result = false;
	   //��¥�� ���콺�� ��ġ���Ѽ� �޸� ���� �� "�޸� �߰��ϼ���"
	   //�޸� ���� �� �޸��� Ÿ��Ʋ�� ���
		   String str = "�޸� �߰��ϼ���"; 
	       String sql = "select title from memo where everyday = ?";
	       PreparedStatement pstmt = null;
	       try {
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, everyday);
	           ResultSet rs = pstmt.executeQuery();
	           if(rs.next()) {
	        	  str = rs.getString("title");
	           }
	           
	       } catch (Exception e) {
	           System.out.println("select �޼��� ���ܹ߻�");
	           
	       }    finally {
	           try {
	               if(pstmt!=null && !pstmt.isClosed()) {
	                   pstmt.close();
	                  
	               } 
//	               if(conn != null) {
//	            	   conn.close();
//	               }
	           } catch (Exception e2) { }
	       }
		return str;
	   }
   
   //�׳� �Ĵ� ����Ǿ�����
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
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
	return result;
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
//               if(conn != null) {
//            	   conn.close();
//               }
           } catch (Exception e2) { }
       }
       return total;
   }
   
   //����� �����԰� �ֳ� ����
   public boolean ifexistWeight(String everyday) {
	   boolean result = false;
       String sql = "select * from weight where everyday = ?";
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
   //����� ������ ��Ÿ����
   public Weight viewWeight(String everyday) {
	   Weight weight = new Weight();
       String sql = "select everyday, weight from weight where everyday = ?";
       PreparedStatement pstmt = null;

       //��� ���� ���� ��
       try {

           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           
           ResultSet rs = pstmt.executeQuery();

           //������
           while(rs.next()) {	
           weight.setEveryday(rs.getString("everyday")); 
           weight.setWeight(rs.getDouble("weight")); 
           }

       } catch (Exception e) {
           System.out.println("������ select �޼��� ����");

       }    finally {
    	   try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
              }
           } catch (Exception e2) { }
       }
       return weight;
   }   
}
