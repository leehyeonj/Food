package HealthSchedule.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import HealthSchedule.model.Memo;

public class MemoDao {

	private Connection conn;    //DB Ŀ�ؼ�(����) ��
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    //����
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    //��� , �߼�
//    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms

   public MemoDao() {

	   try {
       	   //���� ��ü�� ������� 
           Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("��ƾ ����̹� �ε� ����!!");

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("�޸� ����̹� �ε� ����!!");
       }
   }

   public void saveContent(String everyday, String title, String content) {

	   //������ �غ�
	   String sql = "insert into memo values(?,?,?)";
	   
	   PreparedStatement pstmt = null;

       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           pstmt.setString(2, title);
           pstmt.setString(3, content);

           int result = pstmt.executeUpdate();
           if(result==1) {
               System.out.println("�޸����� ���� ����!");

           }

       } catch (Exception e) {

           System.out.println("�޸����� ���� ����!");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   }

   public boolean ifexistMemo(String everyday) {
	   boolean result = false;
       String sql = "select * from memo where everyday = ?";
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

   Memo memo = new Memo();

   public Memo viewMemo(String everyday) {

       String sql = "select everyday, title, content from memo where everyday = ?";
       PreparedStatement pstmt = null;

       //��� ���� ���� ��
       try {

           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, everyday);
           
           ResultSet rs = pstmt.executeQuery();

           //������
           while(rs.next()) {
        	
        	memo.setEveryday(rs.getString("everyday")); 
        	memo.setTitle(rs.getString("title")); 
        	memo.setContent(rs.getString("content")); 

           	}

       } catch (Exception e) {
           System.out.println("�޸� select �޼��� ����");

       }    finally {
    	   try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
              }
           } catch (Exception e2) { }
       }
       return memo;
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
	           } catch (Exception e2) { }
	       }
		return str;
	   }
   
   
   public void updateMemo(String everyday, String title, String content) {
       String sql = "update memo set title=?, content=? where everyday=? ";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, title);
           pstmt.setString(2,content);
           pstmt.setString(3,everyday);
        
           pstmt.executeUpdate();
           System.out.println("�޸���� �Ϸ�");
           
       } catch (Exception e) {
           System.out.println("�޸𿹿� �߻�");
       }    finally {
           try {
               if(pstmt!=null && !pstmt.isClosed()) {
                   pstmt.close();
               }
           } catch (Exception e2) {}
       }
   }

}