package HealthSchedule.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class DatePickerController implements Initializable {
   
   @FXML private DatePicker datePicker;
   @FXML private ImageView imageView;
   
   @FXML private DatePicker datePicker1;
   @FXML private ImageView imageView1;
   
   @FXML private Label alarmText;
   @FXML private Label alarmText1;
   @FXML private AnchorPane pane;
   PhotoDao photoDao = new PhotoDao();
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   stageDragableMoveWindow();
//	   imageView.setImage(new Image(getClass().getResource(rs.getString("urlName")).toString()));
      // datePicker에서 날짜를 선택했을 때
      datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
         @Override
         public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
               LocalDate newValue) {
            
            //선택 된 값이 있으면 아래 실행
            if(newValue!=null) {
            	String day = newValue.toString().replace("-", "");
            	if (photoDao.ifexistPhoto(day)) {
            		String url = photoDao.selectPhoto(day);
            		imageView.setImage(new Image(getClass().getResource(url).toString()));
            		
				}
            	else {
					alarmText.setText("그 날에 저장된 사진이 없습니다.");
				}
            	
            }
         }
      });
      
      datePicker1.valueProperty().addListener(new ChangeListener<LocalDate>() {
          @Override
          public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
                LocalDate newValue) {
             
             //선택 된 값이 있으면 아래 실행
        	  if(newValue!=null) {
              	String day = newValue.toString().replace("-", "");
              	if (photoDao.ifexistPhoto(day)) {
              		String url = photoDao.selectPhoto(day);
              		imageView1.setImage(new Image(getClass().getResource(url).toString()));
  				}
              	else {
  					alarmText1.setText("그 날에 저장된 사진이 없습니다.");
  				}
              	
              }
          }
       });
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
	   stage.close();
   }
}
