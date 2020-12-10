package HealthSchedule.controller;

import static javax.swing.JOptionPane.*;
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

public class SignUpController implements Initializable{
	  @FXML private AnchorPane pane;
	  @FXML private JFXButton signupBtn;
	  @FXML private JFXButton checkEmailBtn;
	  @FXML private JFXButton loginBtn;
      @FXML private AnchorPane topBar;

      @FXML private JFXPasswordField passwordTextField;
      @FXML private JFXTextField emailTextField;
      @FXML private JFXPasswordField passwordcheckTextField;
      
      
      private Stage primaryStage ;
      
      //이메일 체크
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
    		
    		//버튼 tooltip
    		Tooltip tooltip = new Tooltip("Login!");
    		loginBtn.setTooltip(tooltip);
    		
    	}

	    //로그인으로 가는 버튼
	    @FXML
	    void gotoLogin(ActionEvent event) {	
	    	try {
	    		
		    	System.out.println("login btn clicked");
	    		Parent parent = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/LoginPage.fxml"));
				Scene scene = new Scene(parent);
				Stage primaryStage= (Stage)loginBtn.getScene().getWindow();
				primaryStage.setScene(scene);
				System.out.println("login 페이지");
				
			} catch (Exception e) {
				System.out.println("페이지 이동 오류");
//				e.printStackTrace();
			}
	    }

	    //emailcheck버튼을 누른다.
	    public void emailCheck(ActionEvent actionevent) {
	    	
	    		System.out.println("email check button clicked!");
	    	    String user = "lmhj11112@gmail.com";; // 보내는 계정/ 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	            String password = "spdlqjroqkfwk88!!";   // 패스워드
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
	            	 
	            	//이메일 정규식 체크
	            	 if(isValidEmail(recieveMail)) { //정규식에 맞다면
		            		 try {
		 		                MimeMessage message = new MimeMessage(session);
			 		                try {
			 							message.setFrom(new InternetAddress(user, fromname));
			 						} catch (UnsupportedEncodingException e) {}
	
		 		                //수신자메일주소
		 		                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recieveMail)); 
	
		 		                // Subject
		 		                message.setSubject("My Health Diary에서 사용자님의 이메일 인증을 원합니다."); //메일 제목을 입력
	
		 		                int verificationCode = (int)(Math.random()*9000 + 1000);
		 		                String mailmessage = "가입 해주셔서 감사합니다!"
		 		                		+ "\n이메일 인증 번호는 " +verificationCode+  "입니다.";
		 		                // Text
		 		                message.setText(mailmessage);    //메일 내용을 입력
	
		 		                // send the message
		 		                Transport.send(message); ////전송
		 		                System.out.println("message sent successfully...");
		 		            } catch (AddressException e) {
		 		             
		 		                e.printStackTrace();
		 		            } catch (MessagingException e) {
		 		                
		 		                e.printStackTrace();
		 		            }
	            	 	}
	            	 else {
	            		
						try {
							showMessageDialog(null,"이메일을 다시 입력해주세요");
						} catch (Exception e) {
							System.out.println("팝업 생성 오류");
						}
					}
	 	            
	 	 
	        	 }else { //emailTextField가 null일때 
	        		 try {
	        			 showMessageDialog(null,"이메일을 입력해주세요");
						} catch (Exception e) {
							System.out.println("팝업 생성 오류");
						}
	 	            }
	               
	        }

	    
	    public void handlePopup(String message) throws Exception{
	    	
			 
			 try {
			        Popup popup = new Popup();
			        Parent parent = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/popup.fxml"));
			        parent.setOnMouseClicked(e -> popup.hide());

			        // == 텍스트 ==
			        Label lbMessage = (Label) parent.lookup("#lblMessage");
			        lbMessage.setText(message);

			        popup.getContent().add(parent);
			        popup.setAutoHide(true);
			       
			        popup.show(primaryStage);
			    } catch (Exception e) {
			    	System.out.println("팝업이 안생김!");
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
