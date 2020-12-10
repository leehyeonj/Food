package HealthSchedule.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
 
//중섭
public class PiechartController implements Initializable{
 
    @FXML PieChart pieChart;
    @FXML Label full, upper, abs, lower;	//체중별라벨(본 시간)
    
	   private Connection conn;    //DB 커넥션(연결) 객체
	   private static final String USERNAME = "root";   //DB 접속시 ID
	   private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
	   private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//사용자주소/db주소
	   
	   public PiechartController() {
	       // connection객체를 생성해서 DB에 연결함.
	       try {
	    	   //동적 객체를 만들어줌 
	           Class.forName("com.mysql.jdbc.Driver"); 
	           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	           //System.out.println("드라이버 로딩 성공!!");
	           
	       } catch (Exception e) {
	           //System.out.println("드라이버 로드 실패!!");
	       }
	   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	
	}

	
    public void ShowChartbtn(ActionEvent event){  	
      	PiechartController mc = new PiechartController();
    	//piechart데이터표시
        ObservableList<Data> list = FXCollections.observableArrayList(
                new PieChart.Data("전신운동", mc.FullBody(1)),    
                new PieChart.Data("상체운동", mc.UpperBody(1)),
                new PieChart.Data("복근운동", mc.Abs(1)),
                new PieChart.Data("하체운동", mc.LowerBody(1))
                );
        pieChart.setData(list);
        //라벨에 부위별 시간 띄우기
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    full.setText("전신운동\n" + mc.FullBodyTime());
                    upper.setText("상체운동\n" + mc.UpperBodyTime());
                    abs.setText("복근운동\n" + mc.AbsTime());
                    lower.setText("하체운동\n" + mc.LowerBodyTime());
                }
            });	//addEventHandler
        }	//for
    }	//ShowChartbtn
    
	//전신운동 등록된 총 시간 호출
	public Integer FullBody(int fullbody) {
		String sql = "select sum(weightTime) from FullBody";
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {	
				i = rs.getInt("sum(weightTime)");
			}
		} catch (Exception e) {
			//System.out.println("전신운동 시간 호출 실패");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//상체운동 등록된 총 시간 호출
	public Integer UpperBody(int upperbody) {
		String sql = "select sum(weightTime) from UpperBody";
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {	
				i = rs.getInt("sum(weightTime)");
			}
		} catch (Exception e) {
			//System.out.println("상체운동 시간 호출 실패");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//복근운동 등록된 총 시간 호출
	public Integer Abs(int abs) {
		String sql = "select sum(weightTime) from Abs";
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {	
				i = rs.getInt("sum(weightTime)");
			}
		} catch (Exception e) {
			//System.out.println("복근운동 시간 호출 실패");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//하체운동 등록된 총 시간 호출
	public Integer LowerBody(int lowerbody) {
		String sql = "select sum(weightTime) from LowerBody";
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {	
				i = rs.getInt("sum(weightTime)");
			}
		} catch (Exception e) {
			//System.out.println("하체운동 시간 호출 실패");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//전신운동시간설정	00:00:00
    public String FullBodyTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.FullBody(1)) % 60;
		int minute = (int)Math.round(mc.FullBody(1)) / 60;
		int hour = (int)Math.round(mc.FullBody(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
    	return time;
    }
	//상체운동시간설정
    public String UpperBodyTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.UpperBody(1)) % 60;
		int minute = (int)Math.round(mc.UpperBody(1)) / 60;
		int hour = (int)Math.round(mc.UpperBody(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
    	return time;
    }
	//복근운동시간설정
    public String AbsTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.Abs(1)) % 60;
		int minute = (int)Math.round(mc.Abs(1)) / 60;
		int hour = (int)Math.round(mc.Abs(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
    	return time;
    }
	//하체운동시간설정
    public String LowerBodyTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.LowerBody(1)) % 60;
		int minute = (int)Math.round(mc.LowerBody(1)) / 60;
		int hour = (int)Math.round(mc.LowerBody(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
    	return time;
    }  
}
