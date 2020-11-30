package HealthSchedule.controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignUpController implements Initializable{
	@FXML private AnchorPane login;
	@FXML private TextField name;
	@FXML private TextField id;
	@FXML private TextField email;
    @FXML private PasswordField pwd;
    @FXML private Button membersBtn;
    @FXML private Button cancelBtn;
    
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//사용자주소/db주소
    
    //생성자
    public SignUpController() {
        // connection객체를 생성해서 DB에 연결함.
        try {
        	//동적 객체를 만들어줌 
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("드라이버 로딩 성공!!");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("드라이버 로드 실패!!");
        }
    }  
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancelBtn.setOnAction(e->cancelAction(e));
		membersBtn.setOnAction(e->membersAction(e));
	}
    
	//회원가입취소
	public void cancelAction(ActionEvent e){
		try {
		     Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/LoginPage.fxml"));
		     Scene scene = new Scene(members);
		     Stage primaryStage= (Stage)cancelBtn.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e2) {}
	}
	
	//회원가입완료
	public void membersAction(ActionEvent event){
		String userName = name.getText();
	    String userid = id.getText();
	    String calendarPassword = pwd.getText();  
	    String userEmail = email.getText();
 
		PreparedStatement pstmt = null;
		String sql = "";
		
		new SignUpController();
		String name = userName;
		String id = userid;
		String pwd = calendarPassword;
		String Email = userEmail;
		  
		try { 
		    sql = "insert into calendar values(?,?,?,?)";
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, id);
		    pstmt.setString(2, pwd);
		    pstmt.setString(3, name);
		    pstmt.setString(4, Email);
		    pstmt.executeUpdate();	       
		} catch (Exception e) {
			System.out.println("데이터 삽입 실패!");
		    e.printStackTrace();
		} finally{
		    if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		    if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}	//finally
		try {
		     Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/LoginPage.fxml"));
		     Scene scene = new Scene(members);
		     Stage primaryStage= (Stage)cancelBtn.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}	
	 }	//membersAction()
}
