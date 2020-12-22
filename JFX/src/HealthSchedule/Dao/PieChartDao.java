package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HealthSchedule.model.Routines;


public class PieChartDao {
	
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
//    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
//    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    
    //��� , �߼�
private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
   public PieChartDao() {
	   try {
       	//���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          System.out.println("��ƾ ����̹� �ε� ����!!");
           
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("��ƾ ����̹� �ε� ����!!");
       }
       
   }
   
   //��ü� ��¥�� �ð�ȣ��
   public Integer selectLower(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
//		int date = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '��ü�' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();

           //������
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("������ü�ð� �޼��� ����");
           	}
       } catch (Exception e) {
           System.out.println("������ü�ð��޼��� ���ܹ߻�");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   //���ſ ��¥�� �ð� ȣ��
   public Integer selectFullbody(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '���ſ' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //������
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("������ü��ð� �޼��� ����");
           	}
       } catch (Exception e) {
           System.out.println("������ü��ð��޼��� ���ܹ߻�");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   //��ü� ��¥�� �ð�ȣ��
   public Integer selectUpperbody(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '��ü�' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //������
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("���ϻ�ü��ð� �޼��� ����");
           	}
       } catch (Exception e) {
           System.out.println("���ϻ�ü��ð��޼��� ���ܹ߻�");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   //���ٿ ��¥�� �ð�ȣ��
   public Integer selectAbs(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '���ٿ' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //������
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("���Ϻ��ٿ�ð� �޼��� ����");
           	}
       } catch (Exception e) {
           System.out.println("���Ϻ��ٿ�ð��޼��� ���ܹ߻�");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   
   //��Ʈ��Ī ��¥�� �ð�ȣ��
   public Integer selectStretching(String everyday) {
	   Routines lowerTime = new Routines();
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
       String sql = "select everyday, sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '��Ʈ��Ī' group by everyday having everyday=?;";
       PreparedStatement pstmt = null;
       //��� ���� ���� ��      
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           ResultSet rs = pstmt.executeQuery();
           //������
          if(rs.next()) {
        	  lowerTime.setEveryday(rs.getString("everyday"));    	  
        	  h = rs.getInt("sum(timehour)");
        	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("���Ͻ�Ʈ��Ī�ð� �޼��� ����");
           	}
       } catch (Exception e) {
           System.out.println("���Ͻ�Ʈ��Ī�ð��޼��� ���ܹ߻�");        
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
       }
       return result;
   }
   
   //���ſ ���� �ð�ȣ��
   public Integer selectFullbodyAll(int fullbody) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
		//���� ���� ��¥�� �������� -1������ �����͸� �����´�  ex) ������ 12/21�� ��� ��� ��¥�� ���� 11/21~12/21�� �����͸� �����´�
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '���ſ' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //��� ���� ���� ��      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //������
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("�������ſ�ð� �޼��� ����");
           }
        } catch (Exception e) {
        	System.out.println("�������Žð��޼��� ���ܹ߻�");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //��ü� ���� �ð�ȣ��
   public Integer selectUpperbodyAll(int upperbody) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '��ü�' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //��� ���� ���� ��      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //������
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("������ü��ð� �޼��� ����");
           }
        } catch (Exception e) {
        	System.out.println("������ü��ð��޼��� ���ܹ߻�");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //���ٿ ���� �ð�ȣ��
   public Integer selectAbsAll(int abs) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '���ٿ' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //��� ���� ���� ��      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //������
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("�������ٿ �޼��� ����");
           }
        } catch (Exception e) {
        	System.out.println("�������ٽð��޼��� ���ܹ߻�");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //��Ʈ��Ī ���� �ð�ȣ��
   public Integer selectStretchingAll(int stretching) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '��Ʈ��Ī' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //��� ���� ���� ��      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //������
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("������Ʈ��Ī�ð� �޼��� ����");
           }
        } catch (Exception e) {
        	System.out.println("������Ʈ��Ī�ð��޼��� ���ܹ߻�");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
   
   //��ü� ���� �ð�ȣ��
   public Integer selectLowerAll(int lower) {
		int h = 0;
		int m = 0;
		int s = 0;
		int result = 0;
        String sql = "select sum(timehour), sum(timeminute), sum(timesecond) from workoutTime where bodypart = '��ü�' and everyday between DATE_ADD(now(),INTERVAL -1 MONTH ) and now();";
        PreparedStatement pstmt = null;
        //��� ���� ���� ��      
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //������
           if(rs.next()) {
         	  h = rs.getInt("sum(timehour)");
         	  m = rs.getInt("sum(timeminute)");
        	  s = rs.getInt("sum(timesecond)");
        	  result = (h*60*60) + (m*60) +s;
        	  System.out.println("������ü��ð� �޼��� ����");
           }
        } catch (Exception e) {
        	System.out.println("������ü��ð��޼��� ���ܹ߻�");
        	} finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) { }
        }
//        System.out.println(result);
        return result;
   }
}
