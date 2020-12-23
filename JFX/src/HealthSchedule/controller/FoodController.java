package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import HealthSchedule.Dao.FoodListDao;
import HealthSchedule.Interface.ControllerSettable;
import HealthSchedule.Interface.ControllerSettable2;
import HealthSchedule.Interface.ControllerSettable3;
import HealthSchedule.Interface.FoodTableListener;
import HealthSchedule.model.Food;
import HealthSchedule.model.Foodlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FoodController extends Main_everydayRecord_controller implements Initializable, ControllerSettable2 {

	
	  @FXML private ImageView foodImage;
	  @FXML private VBox content;
	  @FXML private Label eattimeLbl;
	  @FXML private Label eatTimeTotalKcal;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("foodController 이니셜 라이즈 실행");
		
		
	}

     Food  food;
     FoodTableListener foodTableListener;
    FoodListDao foodListDao = new FoodListDao();
    Main_everydayRecord_controller main_everydayRecord_controller;
    DayController dayController;
    
    ArrayList<Foodlist> foodlistlist = new ArrayList<>();
    //처음 시작할때 실행됨
  public void setFoodData() {
	  
	   if (foodlistdao.ifexistFood(everyday, eatTime)) {
			 foodlistlist = foodlistdao.viewDayFood(everyday, eatTime);
			  int sum = 0;
			  for (int i = 0; i < foodlistlist.size(); i++) {
				  String labeltext = foodlistlist.get(i).getFoodname() + " / " + foodlistlist.get(i).getFoodunit() +" / "
						  + foodlistlist.get(i).getCal();
					Label label = new Label(labeltext);
					 label.setStyle("-fx-font-family: 'SeoulNamsan EB';");
					content.getChildren().add(label);
					try {
						sum +=Integer.parseInt(foodlistlist.get(i).getCal());
					} catch (Exception e) {}
			}
			  eatTimeTotalKcal.setText(sum+"");
		  }
  }
  
  //tableview에서 저장 눌렀을 경우 실행됨
  public void setFoodData(ArrayList<Foodlist> foodlistlist) {
	  
	  content.getChildren().clear();
	  int sum = 0;
	  for (int i = 0; i < foodlistlist.size(); i++) {
		  String labeltext = foodlistlist.get(i).getFoodname() + " / " + foodlistlist.get(i).getFoodunit() +" / "
				  + foodlistlist.get(i).getCal();
			Label label = new Label(labeltext);
			 label.setStyle("-fx-font-family: 'SeoulNamsan EB';");
			content.getChildren().add(label);
			try {
				sum +=Integer.parseInt(foodlistlist.get(i).getCal());
				
			} catch (Exception e) {}
	}eatTimeTotalKcal.setText(sum+"");
  }
   
 
   
  	
 
    	// foodTableView 페이지로 넘어간다.
    public void moveToTable(MouseEvent event) {
		    	eatTime = food.getEattime();
		    	
			  FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/FoodTableview.fxml") );
			 
			  
				try {
		
			   AnchorPane PickPage = (AnchorPane) another.load();
			   // 다른창 띄우는 작업 .... 2
			   Scene anotherScene = new Scene( PickPage );
			   Stage stage = new  Stage();
			   
			   ControllerSettable con = another.getController();
			   con.setController(this);
			   ControllerSettable2 con2 = another.getController();
			   con2.setController2(main_everydayRecord_controller);
			 
			   
			   stage.setScene(anotherScene);
			   stage.initStyle(StageStyle.UNDECORATED);
			   stage.show();
			   // 다른창 띄우는 작업 .... 2 끝.
		
			} catch (IOException e) {} 
    }
    
    
    
    //food fxml 에 데이터 set
    public void setData(Food food) {
    	System.out.println("fooddata setdata 실행");
    	eatTime = food.getEattime();
    	this.food = food;
    	
    	eattimeLbl.setText(food.getEattime());
    	Image image = new Image(getClass().getResourceAsStream(food.getImgSrc()));
    	foodImage.setImage(image);
    	
    }
    
    //food fxml 에 데이터 set
    public void setData(Food food, FoodTableListener foodTableListener) {
    	System.out.println("fooddata setdata 실행");
    	eatTime = food.getEattime();
    	this.food = food;
    	this.foodTableListener = foodTableListener;
    	eattimeLbl.setText(food.getEattime());
    	Image image = new Image(getClass().getResourceAsStream(food.getImgSrc()));
    	foodImage.setImage(image);
    	
    }

	@Override
	public void setController2(Initializable controller) {
		main_everydayRecord_controller =(Main_everydayRecord_controller)controller;
		
	}
	
   
}
