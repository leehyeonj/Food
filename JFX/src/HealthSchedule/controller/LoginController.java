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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
    @FXML private TextField id;
    @FXML private PasswordField pwd;
    @FXML private Button membersBtn;	//ȸ�����Թ�ư
    @FXML private Button loginBtn;	//�α��ι�ư
    @FXML private Button exit;	//�����ư
    
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
    
    //������
    public LoginController() {
        // connection��ü�� �����ؼ� DB�� ������.
        try {
        	//���� ��ü�� ������� 
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("����̹� �ε� ����!!");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("����̹� �ε� ����!!");
        }
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		membersBtn.setOnAction(e->membersAction(e));
		loginBtn.setOnAction(e->loginAction(e));
	}
    
	//ȸ������
	public void membersAction(ActionEvent event){
		
		try {
		     Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/signupPage.fxml"));
		     Scene scene = new Scene(members);
		     Stage primaryStage= (Stage)membersBtn.getScene().getWindow();
		     primaryStage.setScene(scene);
		} catch (Exception e) {}
	}
	
	//�α���
	public void loginAction(ActionEvent event){
		
		String uId = id.getText();
		String uPwd = pwd.getText();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ""; 
		
		try {
			 sql = "select userid,calendarPassword from calendar where userid = ? and calendarPassword = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, uId);
			 pstmt.setString(2, uPwd);
			 rs = pstmt.executeQuery(); 
			 if(rs.next()){			     
			 if(rs.getString("userid").equals(uId) && rs.getString("calendarPassword").equals(uPwd)){
			 Parent mainPage = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
			 Scene scene = new Scene(mainPage);
		     Stage primaryStage= (Stage)loginBtn.getScene().getWindow();
		     primaryStage.setScene(scene);
							
			     }	//����if
			}	//if
		} catch (Exception e) {
			   e.printStackTrace();
		} finally{
			   if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			   if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}	//finally
	}
	
	public void handleBtnAction(ActionEvent e){
		Platform.exit();
	}
}
