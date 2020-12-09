package HealthSchedule.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LoginController implements Initializable{
    @FXML private TextField id;
    @FXML private PasswordField password;
    @FXML private Button membersBtn;	//회원가입버튼
    @FXML private Button loginBtn;	//로그인버튼
    @FXML private Button exit;	//종료버튼
    
    
    private Stage PrimaryStage ;
    
    
	private Connection conn;    //DB 커넥션(연결) 객체
	
	
//    private static final String USERNAME = "root";   //DB 접속시 ID
//    //현주
//    private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
//    private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
//    
////    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
////    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
//    
//    //생성자
//    public LoginController() {
//        // connection객체를 생성해서 DB에 연결함.
//        try {
//        	//동적 객체를 만들어줌 
//            Class.forName("com.mysql.jdbc.Driver"); 
//            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//           System.out.println("드라이버 로딩 성공!!");
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("드라이버 로드 실패!!");
//        }
//    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		membersBtn.setOnAction(e->membersAction(e));
		loginBtn.setOnAction(e->loginAction(e));
	}
    
	//회원가입
	public void membersAction(ActionEvent event){
		
		try {
		     Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/signupPage.fxml"));
		     Scene scene = new Scene(members);
		     Stage primaryStage= (Stage)membersBtn.getScene().getWindow();
		     primaryStage.setScene(scene);
		} catch (Exception e) {}
	}
	
	//로그인
	public void loginAction(ActionEvent event){
		
		String userId = id.getText();
		String userPwd = password.getText();
////		
//		DayDao dayDao = new DayDao();
//		dayDao.insertone(userId, userPwd);//로그인을 눌렀을때 dao를 통해서 database로 정보가 넘어간다.
//		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ""; 
		
		try {
			 sql = "select userid,calendarPassword from calendar where userid = ? and calendarPassword = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, userId);
			 pstmt.setString(2, userPwd);
			 rs = pstmt.executeQuery(); 
			 
			 if(rs.next()){			     
				 if(rs.getString("userid").equals(userId) && rs.getString("calendarPassword").equals(userPwd)){
					System.out.println("로그인 성공");
					//알람!
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
				     alert.setTitle("뭐지");
				     alert.setHeaderText("로그인 성공");

				     alert.show();
					 Parent mainPage = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
					 Scene scene = new Scene(mainPage);
				     Stage primaryStage= (Stage)loginBtn.getScene().getWindow();
				     primaryStage.setScene(scene);
								
				     }	//이중if
				 
				 
			}	//if
			 //아이디나 비밀번호가 틀린 경우
			 else {
					System.out.println("아이디나 비밀번호를 확인해주세요");
					
				}
			 
		} catch (Exception e) {
			   e.printStackTrace();
		} finally{
			   if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			   if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}	//finally
	}
	
	public void exitbtn(ActionEvent e){
		Platform.exit();
	}
	
	
	
}
