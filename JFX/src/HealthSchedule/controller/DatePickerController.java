package HealthSchedule.controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DatePickerController implements Initializable {
   
   @FXML private DatePicker datePicker;
   @FXML private ImageView imageView;
   PhotoDao photoDao = new PhotoDao();
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
//	   imageView.setImage(new Image(getClass().getResource(rs.getString("urlName")).toString()));
      // datePicker에서 날짜를 선택했을 때
      datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
         @Override
         public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
               LocalDate newValue) {
            
            //선택 된 값이 있으면 아래 실행
            if(newValue!=null) {
            	String day = newValue.toString().replace("-", "");
            	String url = photoDao.selectPhoto(day);
            	
            	System.out.println(day);
//            	System.out.println(url);
            	
//            	File file = new File("../FoodCalendarProject999999/src/images/4.htm_201907109504472591.jpg");
//            	imageView.setImage(new Image(file.getAbsolutePath().toString()));
            	
            	imageView.setImage(new Image(getClass().getResource(url).toString()));
            }
         }
      });
   }
}
