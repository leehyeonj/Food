package HealthSchedule;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LowerController implements Initializable {
 
	   @FXML private Button goBack;	//뒤로가기버튼
	   @FXML private ImageView video1;
	   @FXML private ImageView video2;
	   @FXML private ImageView video3;
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
 
	   }
  
	   public void btngoBack(ActionEvent event) {   
		   try {
		      Parent home = FXMLLoader.load(getClass().getResource("homet.fxml"));
		      Scene scene = new Scene(home);
		      Stage primaryStage= (Stage)goBack.getScene().getWindow();
		      primaryStage.setScene(scene);
		   } catch (Exception e) {}
	   }
}
