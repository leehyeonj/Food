package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController2 implements Initializable{
	   @FXML
	    private JFXPasswordField passwordTextField;

	    @FXML
	    private JFXButton loginBtn;

	    @FXML
	    private JFXButton signUpBtn;

	    @FXML
	    private JFXTextField emailTextField;

	    @FXML
	    private AnchorPane topBar;

	    @FXML
	    private JFXButton forgetpasswordBtn;
	    
	    @FXML
	    private AnchorPane pane;

	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	System.out.println("login");
			stageDragableMoveWindow();
			
			Tooltip tooltip = new Tooltip("Sign Up");
	    	signUpBtn.setTooltip(tooltip);
		}

	    @FXML
	    void loginAction(ActionEvent event) {

	    }

	    @FXML
	    void findPassword(ActionEvent event) {

	    }

	    

	    @FXML
	    public void gotoSignup(ActionEvent event) {
	    	
	    	try {
	    		
		    	System.out.println("sign up btn clicked");
	    		Parent parent = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/signupPage.fxml"));
				Scene scene = new Scene(parent);
				Stage primaryStage= (Stage)signUpBtn.getScene().getWindow();
				primaryStage.setScene(scene);
				System.out.println("sign up 페이지");
				
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
