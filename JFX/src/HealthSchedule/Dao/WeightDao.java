package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HealthSchedule.model.Weight;

public class WeightDao {

	Weight weight = new Weight();
	Weight goalweight = new Weight();
	
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    //��� , �߼�
//    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms

    public WeightDao() {
    
    	 try {
         	 //���� ��ü�� ������� 
             Class.forName("com.mysql.jdbc.Driver"); 
             conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             System.out.println("������ ����̹� �ε� ����!!");

         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("������ ����̹� �ε� ����!!");
         }
    	
    }
    
    //������ ����
    public void saveWeight(String everyday, double weight) {
    	
       //������ �غ�
 	   String sql = "insert into weight values(?,?)";
 	   
 	   PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            pstmt.setDouble(2, weight);      

            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("�����Ե����� ���� ����!");

            }

        } catch (Exception e) {

            System.out.println("�����Ե����� ���� ����!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }
    
    //��ǥ������ ����
    public void savegoalWeight(String everyday, double goalweight) {
    	
       //������ �غ�
 	   String sql = "insert into goalweight values(?,?)";
 	   
 	   PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            pstmt.setDouble(2, goalweight);      

            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("��ǥ�����Ե����� ���� ����!");

            }

        } catch (Exception e) {

            System.out.println("��ǥ�����Ե����� ���� ����!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
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
    
    //����� ��ǥ�����԰� �ֳ� ����
    public boolean ifexistgoalWeight(String everyday) {
 	   boolean result = false;
        String sql = "select * from goalweight where everyday = ?";
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
    
  //����� ��ǥ������ ��Ÿ����
    public Weight viewgoalWeight(String everyday) {

        String sql = "select everyday, goalweight from goalweight where everyday = ?";
        PreparedStatement pstmt = null;

        //��� ���� ���� ��
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            
            ResultSet rs = pstmt.executeQuery();

            //������
            while(rs.next()) {	
            goalweight.setEveryday(rs.getString("everyday")); 
            goalweight.setWeight(rs.getDouble("weight")); 
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
        return goalweight;
    }    
    
    //������ �����ϱ�
    public void updateWeight(String everyday, double weight) {
        String sql = "update weight set weight=? where everyday=? ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, weight);
            pstmt.setString(2, everyday);
         
            pstmt.executeUpdate();
            System.out.println("�����Լ��� �Ϸ�");
            
        } catch (Exception e) {
            System.out.println("�����Կ��� �߻�");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }    
    
  //��ǥ������ �����ϱ�
    public void updategoalWeight(String everyday, double goalweight) {
        String sql = "update goalweight set goalweight=? where everyday=? ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, goalweight);
            pstmt.setString(2, everyday);
         
            pstmt.executeUpdate();
            System.out.println("��ǥ�����Լ��� �Ϸ�");
            
        } catch (Exception e) {
            System.out.println("��ǥ�����Կ��� �߻�");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }        
    
    
}
