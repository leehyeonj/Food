package HealthSchedule.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PhotoController implements Initializable{
	
	@FXML DatePicker datePicker1;	//before 날짜 선택
	@FXML DatePicker datePicker2;	//after 날짜 선택
	@FXML ImageView beforePhoto;	//before 사진 
	@FXML ImageView afterPhoto;		//after 사진
	@FXML Button goBack; 			//뒤로가기버튼
	
	@Override
   public void initialize(URL location, ResourceBundle resources) {
      
      // datePicker에서 날짜를 선택했을 때
		datePicker1.valueProperty().addListener(new ChangeListener<LocalDate>() {
		
		@Override
		public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
				LocalDate newValue) {
			 
            //선택 된 값이 있으면 아래 실행
            if(newValue!=null) {
               System.out.println(datePicker1.getValue().toString());
               Connection conn;    //DB 커넥션(연결) 객체
                String USERNAME = "root";   //DB 접속시 ID
                String PASSWORD = "1234";    //DB 접속시 패스워드
                String URL = "jdbc:mysql://localhost:3306/iddb";
                
                try {
                     System.out.println("생성자");
                     Class.forName("com.mysql.jdbc.Driver"); 
                     conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                     System.out.println("드라이버 로딩 성공!!");
                     
                     //선택 된 날짜를 기준으로 검색해서 사진파일의 경로를 불러옴
                     String sql = "select urlName from test where date=? ;";
                   PreparedStatement pstmt = null;
                    try {
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, datePicker1.getValue().toString());
                        ResultSet rs = pstmt.executeQuery();
                        if(rs.next()) {
                           System.out.println(rs.getString("urlName"));
                           //이미지뷰에 사진 띄우기
                           beforePhoto.setImage(new Image(getClass().getResource(rs.getString("urlName")).toString()));
                        }
              
                    } catch (Exception e) {            
                       System.out.println("이미지 로드 실패!");
                        e.printStackTrace();
                    } finally {
                        try {
                            if (pstmt != null && !pstmt.isClosed())
                                pstmt.close();
                        } catch (Exception e) {                
                            e.printStackTrace();
                        }
                    }
                 } catch (Exception e) {
                     e.printStackTrace();
                     System.out.println("드라이버 로드 실패!!");
                 }
            }
			
		}
      });
			
			
	//datePicker에서 날짜를 선택했을 때
	datePicker2.valueProperty().addListener(new ChangeListener<LocalDate>() {

	@Override
	public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
			LocalDate newValue) {
		 
        //선택 된 값이 있으면 아래 실행
        if(newValue!=null) {
           System.out.println(datePicker2.getValue().toString());
           Connection conn;    //DB 커넥션(연결) 객체
            String USERNAME = "root";   //DB 접속시 ID
            String PASSWORD = "1234";    //DB 접속시 패스워드
            String URL = "jdbc:mysql://localhost:3306/iddb";
            
            try {
                 System.out.println("생성자");
                 Class.forName("com.mysql.jdbc.Driver"); 
                 conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 System.out.println("드라이버 로딩 성공!!");
                 
                 //선택 된 날짜를 기준으로 검색해서 사진파일의 경로를 불러옴
                 String sql = "select urlName from test where date=? ;";
               PreparedStatement pstmt = null;
                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, datePicker2.getValue().toString());
                    ResultSet rs = pstmt.executeQuery();
                    if(rs.next()) {
                       System.out.println(rs.getString("urlName"));
                       //이미지뷰에 사진 띄우기
                       afterPhoto.setImage(new Image(getClass().getResource(rs.getString("urlName")).toString()));
                    }
          
                } catch (Exception e) {            
                   System.out.println("이미지 로드 실패!");
                    e.printStackTrace();
                } finally {
                    try {
                        if (pstmt != null && !pstmt.isClosed())
                            pstmt.close();
                    } catch (Exception e) {                
                        e.printStackTrace();
                    }
                }
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println("드라이버 로드 실패!!");
             }
        }
		
		}});			
	}
	
    //뒤로가기 버튼
    @FXML
     public void actionBackWindow(MouseEvent event) {
        try {
           //뒤로 가기 버튼을 누르면 뒤로감
           Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
           Scene scene = new Scene(checkOk);
           Stage primaryStage= (Stage)goBack.getScene().getWindow();
           primaryStage.setScene(scene);
        } catch (Exception e2) {}
     }

	
}