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
    
    


    //�̸��� ������ - ��й�ȣ �̸���
    @FXML
   public void sendMail(ActionEvent event) {
    	System.out.println("email check button clicked!");
	    String user = "�ڱ� gmail";; // ������ ����/ ���̹��� ��� ���̹� ����, gmail��� gmail ����
        String password = "�ڱ� ���";   // �н�����
        String fromname = "**My Health Diary**";
        //���ۿ��� ���� ���� �׼��� ����ؾ��� 
        // SMTP ���� ������ �����Ѵ�.
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

        
        
       
        if(!emailTextField.getText().isEmpty()) { //emailTextField�� ������� �ʴٸ�
        	 String recieveMail = emailTextField.getText(); //email�� ���� ���ڸ� �����´�.
        	 
        	 LoginDao loginDao = new LoginDao();
        	 String passwordfinded = loginDao.findpassword(recieveMail);
        	
        	 if(!passwordfinded.equals("no")) {
            		 try {
 		                MimeMessage message = new MimeMessage(session);
	 		                try {
	 							message.setFrom(new InternetAddress(user, fromname));
	 						} catch (UnsupportedEncodingException e) {}

 		                //�����ڸ����ּ�
 		                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recieveMail)); 

 		                // Subject
 		                message.setSubject("My Health Diary���� ����ڴ� ��й�ȣ�� �����帳�ϴ�."); //���� ������ �Է�

 		                
 		                String mailmessage = "ȸ������ ��й�ȣ��  "+ passwordfinded +"  �Դϴ�.";
 		                // Text
 		                message.setText(mailmessage);    //���� ������ �Է�

 		                // send the message
 		                Transport.send(message); ////����
 		                System.out.println("message sent successfully...");
 		               alarmTextLabel.setText("��й�ȣ�� ���Ϸ� ���½��ϴ�.");
 		                
 		              
            		 }
            		 catch (Exception e) {}
        	 }else {
        		 alarmTextLabel.setText("���Ե� �̸����� �ƴմϴ�.");
			}
        		}
        else {
			alarmTextLabel.setText("�̸����� �Է����ּ���");
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
			System.out.println("������ �̵� ����");
			e.printStackTrace();
		}
    }


    //////////////////////////
	   private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //ȭ�� �����϶� �������� ���ϰ� �ϱ�
	   private void stageDragableMoveWindow() {
		   pane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   pane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // â ����ȭ
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // â ����ȭ
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

	   //ȭ�� �����
	   @FXML
	   private void actionMinWindow(MouseEvent event) {
	   // Launcher.stage.setIconified(true);
	   stage = (Stage) pane.getScene().getWindow();
	   stage.setIconified(true);
	   }

	   
	   //ȭ�� ����
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }


	
}
