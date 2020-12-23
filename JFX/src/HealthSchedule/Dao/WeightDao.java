package HealthSchedule.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HealthSchedule.model.Weight;

public class WeightDao {

	Weight weight = new Weight();
	Weight goalweight = new Weight();
	
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    //현주
    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
    //상아 , 중섭
//    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms

    public WeightDao() {
    
    	 try {
         	 //동적 객체를 만들어줌 
             Class.forName("com.mysql.jdbc.Driver"); 
             conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             System.out.println("몸무게 드라이버 로딩 성공!!");

         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("몸무게 드라이버 로드 실패!!");
         }
    	
    }
    
    //몸무게 저장
    public void saveWeight(String everyday, double weight) {
    	
       //쿼리문 준비
 	   String sql = "insert into weight values(?,?)";
 	   
 	   PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            pstmt.setDouble(2, weight);      

            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("몸무게데이터 삽입 성공!");

            }

        } catch (Exception e) {

            System.out.println("몸무게데이터 삽입 실패!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }
    
    //목표몸무게 저장
    public void savegoalWeight(String everyday, double goalweight) {
    	
       //쿼리문 준비
 	   String sql = "insert into goalweight values(?,?)";
 	   
 	   PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            pstmt.setDouble(2, goalweight);      

            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("목표몸무게데이터 삽입 성공!");

            }

        } catch (Exception e) {

            System.out.println("목표몸무게데이터 삽입 실패!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }
    
    //저장된 몸무게가 있나 없나
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
    
    //저장된 목표몸무게가 있나 없나
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
    
    
    //저장된 몸무게 나타내기
    public Weight viewWeight(String everyday) {

        String sql = "select everyday, weight from weight where everyday = ?";
        PreparedStatement pstmt = null;

        //결과 값을 담을 곳
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            
            ResultSet rs = pstmt.executeQuery();

            //있으면
            while(rs.next()) {	
            weight.setEveryday(rs.getString("everyday")); 
            weight.setWeight(rs.getDouble("weight")); 
            }

        } catch (Exception e) {
            System.out.println("몸무게 select 메서드 예외");

        }    finally {
     	   try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
               }
            } catch (Exception e2) { }
        }
        return weight;
    }   
    
  //저장된 목표몸무게 나타내기
    public Weight viewgoalWeight(String everyday) {

        String sql = "select everyday, goalweight from goalweight where everyday = ?";
        PreparedStatement pstmt = null;

        //결과 값을 담을 곳
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, everyday);
            
            ResultSet rs = pstmt.executeQuery();

            //있으면
            while(rs.next()) {	
            goalweight.setEveryday(rs.getString("everyday")); 
            goalweight.setWeight(rs.getDouble("weight")); 
            }

        } catch (Exception e) {
            System.out.println("몸무게 select 메서드 예외");

        }    finally {
     	   try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
               }
            } catch (Exception e2) { }
        }
        return goalweight;
    }    
    
    //몸무게 수정하기
    public void updateWeight(String everyday, double weight) {
        String sql = "update weight set weight=? where everyday=? ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, weight);
            pstmt.setString(2, everyday);
         
            pstmt.executeUpdate();
            System.out.println("몸무게수정 완료");
            
        } catch (Exception e) {
            System.out.println("몸무게예외 발생");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }    
    
  //목표몸무게 수정하기
    public void updategoalWeight(String everyday, double goalweight) {
        String sql = "update goalweight set goalweight=? where everyday=? ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, goalweight);
            pstmt.setString(2, everyday);
         
            pstmt.executeUpdate();
            System.out.println("목표몸무게수정 완료");
            
        } catch (Exception e) {
            System.out.println("목표몸무게예외 발생");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }        
    
    
}
