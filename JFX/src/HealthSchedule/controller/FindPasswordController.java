package HealthSchedule.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FindPasswordController implements Initializable {

	@FXML private JFXButton loginBtn;
    @FXML private Label alarmTextLabel;
    @FXML private JFXTextField emailTextField;
    @FXML private JFXButton sendmailBtn;
    @FXML private AnchorPane pane;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	stageDragableMoveWindow();
    	
    	Tooltip tooltip = new Tooltip("Login");
    	loginBtn.setTooltip(tooltip);
		
	}
    
    


    //이메일 보내기 - 비밀번호 이메일
    @FXML
   public void sendMail(ActionEvent event) {
    	System.out.println("email check button clicked!");
	    String user = "자기 gmail";; // 보내는 계정/ 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "자기 비번";   // 패스워드
        String fromname = "**My Health Diary**";
        //구글에서 보안 낮은 액세스 허용해야함 
        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        
        
       
        if(!emailTextField.getText().isEmpty()) { //emailTextField가 비어있지 않다면
        	 String recieveMail = emailTextField.getText(); //email에 써진 글자를 가져온다.
        	 
        	 LoginDao loginDao = new LoginDao();
        	 String passwordfinded = loginDao.findpassword(recieveMail);
        	
        	 if(!passwordfinded.equals("no")) {
            		 try {
 		                MimeMessage message = new MimeMessage(session);
	 		                try {
	 							message.setFrom(new InternetAddress(user, fromname));
	 						} catch (UnsupportedEncodingException e) {}

 		                //수신자메일주소
 		                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recieveMail)); 

 		                // Subject
 		                message.setSubject("My Health Diary에서 사용자님 비밀번호를 보내드립니다."); //메일 제목을 입력

 		                
 		                String mailmessage = "회원님의 비밀번호는  "+ passwordfinded +"  입니다.";
 		                // Text
 		                message.setText(mailmessage);    //메일 내용을 입력

 		                // send the message
 		                Transport.send(message); ////전송
 		                System.out.println("message sent successfully...");
 		               alarmTextLabel.setText("비밀번호를 메일로 보냈습니다.");
 		                
 		              
            		 }
            		 catch (Exception e) {}
        	 }else {
        		 alarmTextLabel.setText("가입된 이메일이 아닙니다.");
			}
        		}
        else {
			alarmTextLabel.setText("이메일을 입력해주세요");
		}
    	
    }

   

    @FXML
    void gotoLogin(ActionEvent event) {
    	try {
    	
    		Parent parent = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/LoginPage.fxml"));
			Scene scene = new Scene(parent);
			Stage primaryStage= (Stage)loginBtn.getScene().getWindow();
			primaryStage.setScene(scene);
			
			
		} catch (Exception e) {
			System.out.println("페이지 이동 오류");
			e.printStackTrace();
		}
    }


    //////////////////////////
	   private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //화면 움직일때 투명으로 변하게 하기
	   private void stageDragableMoveWindow() {
		   pane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   pane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // 창 투명화
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // 창 투명화
		   });
		   pane.setOnDragDone((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
		   pane.setOnMouseReleased((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
	   }

	   //화면 숨기기
	   @FXML
	   private void actionMinWindow(MouseEvent event) {
	   // Launcher.stage.setIconified(true);
	   stage = (Stage) pane.getScene().getWindow();
	   stage.setIconified(true);
	   }

	   
	   //화면 끄기
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }


	
}
