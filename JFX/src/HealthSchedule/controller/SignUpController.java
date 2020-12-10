package HealthSchedule.controller;

import static javax.swing.JOptionPane.showMessageDialog;

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
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUpController implements Initializable{
	
	//������ȣ�� �´���
	static boolean check = false;
	//������ȣ
	static int verificationCode = (int)(Math.random()*9000 + 1000);
	
	
	  @FXML private AnchorPane pane;
	  @FXML private JFXButton signupBtn;
	  @FXML private JFXButton checkEmailBtn;
	  @FXML private JFXButton loginBtn;
      @FXML private AnchorPane topBar;

      @FXML private JFXPasswordField passwordTextField;
      @FXML private JFXPasswordField passwordcheckTextField;
      @FXML private JFXTextField emailTextField;
      
      
      
      private Stage primaryStage ;
      
      //�̸��� üũ
      public static boolean isValidEmail(String email) { 
    	  boolean err = false; 
    	  String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; 
    	  Pattern p = Pattern.compile(regex); 
    	  Matcher m = p.matcher(email); 
    	  
    	  if(m.matches()) { err = true; } 
    	  	return err;
    	  }

    
     	@Override
    	public void initialize(URL location, ResourceBundle resources) {
    		stageDragableMoveWindow();
    		
    		//��ư tooltip
    		Tooltip tooltip = new Tooltip("Login!");
    		loginBtn.setTooltip(tooltip);
    		
    	}

	    //�α������� ���� ��ư
	    @FXML
	    void gotoLogin(ActionEvent event) {	
	    	try {
	    		
		    	System.out.println("login btn clicked");
	    		Parent parent = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/LoginPage.fxml"));
				Scene scene = new Scene(parent);
				Stage primaryStage= (Stage)loginBtn.getScene().getWindow();
				primaryStage.setScene(scene);
				System.out.println("login ������");
				
			} catch (Exception e) {
				System.out.println("������ �̵� ����");
//				e.printStackTrace();
			}
	    }

	    //emailcheck��ư�� ������.
	    public void emailCheck(ActionEvent actionevent) {
	    	
	    		System.out.println("email check button clicked!");
	    	    String user = "lmhj11112@gmail.com";; // ������ ����/ ���̹��� ��� ���̹� ����, gmail��� gmail ����
	            String password = "�ڱ� �н�����";   // �н�����
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
	            	 
	            	//�̸��� ���Խ� üũ
	            	 if(isValidEmail(recieveMail)) { //���ԽĿ� �´ٸ�
		            		 try {
		 		                MimeMessage message = new MimeMessage(session);
			 		                try {
			 							message.setFrom(new InternetAddress(user, fromname));
			 						} catch (UnsupportedEncodingException e) {}
	
		 		                //�����ڸ����ּ�
		 		                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recieveMail)); 
	
		 		                // Subject
		 		                message.setSubject("My Health Diary���� ����ڴ��� �̸��� ������ ���մϴ�."); //���� ������ �Է�
	
		 		                
		 		                String mailmessage = "���� ���ּż� �����մϴ�!"
		 		                		+ "\n�̸��� ���� ��ȣ�� " +verificationCode+  "�Դϴ�.";
		 		                // Text
		 		                message.setText(mailmessage);    //���� ������ �Է�
	
		 		                // send the message
		 		                Transport.send(message); ////����
		 		                System.out.println("message sent successfully...");
		 		                
		 		               /////////////���� ������ �˾�//////////////////
		 		               FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/emailcheck.fxml") );
				 		      		try {
				 		      		   AnchorPane PickPage = (AnchorPane) another.load();
				 		      		   // �ٸ�â ���� �۾� .... 2
				 		      		   Scene anotherScene = new Scene( PickPage );
				 		      		   Stage stage = new  Stage();
				 		      		   stage.initStyle(StageStyle.UNDECORATED);
				 		      		   stage.setScene(anotherScene);
				 		      		   stage.show();
				 		      		   // �ٸ�â ���� �۾� .... 2 ��.
				 		      		} catch (IOException e) {
				 		      			System.out.println("�ٸ�â ���� ����");
				 		      		}
		 		                
		 		            } catch (AddressException e) {
		 		            	System.out.println("�޼��� ������ ����");
		 		            	
		 		            } catch (MessagingException e) {
		 		                
		 		                e.printStackTrace();
		 		            }
	            	 	}
	            	 else {
	            		
						try { //
						    /////////////�̸��� ���ԽĿ� ���� �ʴ´ٸ� �ߴ� �˾�//////////////////
		 		               FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/popup.fxml") );
				 		      		try {
				 		      		   AnchorPane PickPage = (AnchorPane) another.load();
				 		      		   // �ٸ�â ���� �۾� .... 2
				 		      		   Scene anotherScene = new Scene( PickPage );
				 		      		   Stage stage = new  Stage();
				 		      		   stage.initStyle(StageStyle.UNDECORATED);
				 		      		   stage.setScene(anotherScene);
				 		      		   stage.show();
				 		      		   // �ٸ�â ���� �۾� .... 2 ��.
				 		      		} catch (IOException e) {
				 		      			System.out.println("�ٸ�â ���� ����");
				 		      		}
						} catch (Exception e) {}
					}
	 	            
	 	 
	        	 }else { //emailTextField�� null�϶� 
	        		 try {
	        			 /////////////�̸����� �ƿ� ���� �ʾ����� //////////////////
	 		               FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/popup.fxml") );
			 		      		try {
			 		      		   AnchorPane PickPage = (AnchorPane) another.load();
			 		      		   // �ٸ�â ���� �۾� .... 2
			 		      		   Scene anotherScene = new Scene( PickPage );
			 		      		   Stage stage = new  Stage();
			 		      		   stage.initStyle(StageStyle.UNDECORATED);
			 		      		   stage.setScene(anotherScene);
			 		      		   stage.show();
			 		      		   // �ٸ�â ���� �۾� .... 2 ��.
			 		      		} catch (IOException e) {
			 		      			System.out.println("�ٸ�â ���� ����");
			 		      		}
						} catch (Exception e) {}
	 	            }
	               
	        }

	    
	    ////////////////////////////////////////////////////////////////////
	    //sign up ��ư�� �����ٸ�
	  public void signupBtnHandle(ActionEvent actionevent) {
		//�̸��� ������ �Ϸ�Ǿ�����
		  if(check) { 
			  if(!passwordcheckTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
				  String password = passwordTextField.getText();
				  String passwordcheck = passwordcheckTextField.getText();
				  if(password.length()>=10) {
					  if (password.equals(passwordcheck)) {
							System.out.println("��й�ȣ�� �� �ԷµǾ����ϴ�.");
							
							SignupDao signupDao = new SignupDao();
							signupDao.signup(emailTextField.getText(), password, passwordcheck);
						}
				  }
				  else {
					System.out.println("��й�ȣ�� 10�� �̻� �Է����ּ���");
				}
				  
			  }
			  else {//password�ʵ峪 check�ʵ尡 ������ִٸ� 
				  System.out.println("��й�ȣ�� �Է����ּ���");
			  }
		  }
		  else {
			  System.out.println("�̸��� ������ ���� �ʾҽ��ϴ�.");
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
