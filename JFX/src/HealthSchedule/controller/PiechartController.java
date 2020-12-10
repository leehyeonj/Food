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
 
//�߼�
public class PiechartController implements Initializable{
 
    @FXML PieChart pieChart;
    @FXML Label full, upper, abs, lower;	//ü�ߺ���(�� �ð�)
    
	   private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
	   private static final String USERNAME = "root";   //DB ���ӽ� ID
	   private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
	   private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//������ּ�/db�ּ�
	   
	   public PiechartController() {
	       // connection��ü�� �����ؼ� DB�� ������.
	       try {
	    	   //���� ��ü�� ������� 
	           Class.forName("com.mysql.jdbc.Driver"); 
	           conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	           //System.out.println("����̹� �ε� ����!!");
	           
	       } catch (Exception e) {
	           //System.out.println("����̹� �ε� ����!!");
	       }
	   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	
	}

	
    public void ShowChartbtn(ActionEvent event){  	
      	PiechartController mc = new PiechartController();
    	//piechart������ǥ��
        ObservableList<Data> list = FXCollections.observableArrayList(
                new PieChart.Data("���ſ", mc.FullBody(1)),    
                new PieChart.Data("��ü�", mc.UpperBody(1)),
                new PieChart.Data("���ٿ", mc.Abs(1)),
                new PieChart.Data("��ü�", mc.LowerBody(1))
                );
        pieChart.setData(list);
        //�󺧿� ������ �ð� ����
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    full.setText("���ſ\n" + mc.FullBodyTime());
                    upper.setText("��ü�\n" + mc.UpperBodyTime());
                    abs.setText("���ٿ\n" + mc.AbsTime());
                    lower.setText("��ü�\n" + mc.LowerBodyTime());
                }
            });	//addEventHandler
        }	//for
    }	//ShowChartbtn
    
	//���ſ ��ϵ� �� �ð� ȣ��
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
			//System.out.println("���ſ �ð� ȣ�� ����");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//��ü� ��ϵ� �� �ð� ȣ��
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
			//System.out.println("��ü� �ð� ȣ�� ����");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//���ٿ ��ϵ� �� �ð� ȣ��
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
			//System.out.println("���ٿ �ð� ȣ�� ����");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//��ü� ��ϵ� �� �ð� ȣ��
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
			//System.out.println("��ü� �ð� ȣ�� ����");
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {}
		}
		return i;
	}
	//���ſ�ð�����	00:00:00
    public String FullBodyTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.FullBody(1)) % 60;
		int minute = (int)Math.round(mc.FullBody(1)) / 60;
		int hour = (int)Math.round(mc.FullBody(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
    	return time;
    }
	//��ü��ð�����
    public String UpperBodyTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.UpperBody(1)) % 60;
		int minute = (int)Math.round(mc.UpperBody(1)) / 60;
		int hour = (int)Math.round(mc.UpperBody(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
    	return time;
    }
	//���ٿ�ð�����
    public String AbsTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.Abs(1)) % 60;
		int minute = (int)Math.round(mc.Abs(1)) / 60;
		int hour = (int)Math.round(mc.Abs(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
    	return time;
    }
	//��ü��ð�����
    public String LowerBodyTime() {
    	PiechartController mc = new PiechartController();
		int second = (int)Math.round(mc.LowerBody(1)) % 60;
		int minute = (int)Math.round(mc.LowerBody(1)) / 60;
		int hour = (int)Math.round(mc.LowerBody(1)) / 60 / 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��", minute, second);
		}else if(hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
    	return time;
    }  
}
