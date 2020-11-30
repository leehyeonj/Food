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
    
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
    private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//������ּ�/db�ּ�
    
    //������
    public SignUpController() {
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
		cancelBtn.setOnAction(e->cancelAction(e));
		membersBtn.setOnAction(e->membersAction(e));
	}
    
	//ȸ���������
	public void cancelAction(ActionEvent e){
		try {
		     Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/LoginPage.fxml"));
		     Scene scene = new Scene(members);
		     Stage primaryStage= (Stage)cancelBtn.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e2) {}
	}
	
	//ȸ�����ԿϷ�
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
			System.out.println("������ ���� ����!");
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
