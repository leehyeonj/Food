package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import HealthSchedule.Dao.FoodListDao;
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

public class FoodController extends Main_everydayRecord_controller implements Initializable {

	
	  @FXML private ImageView foodImage;
	  @FXML private VBox content;
	  @FXML private Label eattimeLbl;
//    @FXML private Label breakfastlbl;
//    @FXML private VBox breakfastVbox;
//    @FXML private Label breakfastkcal;
//    @FXML private ImageView foodImage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("foodController 이니셜 라이즈 실행");
		
		try {
		foodTableListener = new FoodTableListener() {
			
			@Override
			public void onClickListener(ArrayList<Foodlist> foodlistlist) {
				setFoodData(foodlistlist);
				
			}
		};
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    private Food  food;
    private FoodTableListener foodTableListener;
    FoodListDao foodListDao = new FoodListDao();
    
    ArrayList<Foodlist> foodlistlist = new ArrayList<>();
  public void setFoodData() {
	  
	   if (foodlistdao.ifexistFood(everyday, eatTime)) {
			 foodlistlist = foodlistdao.viewDayFood(everyday, eatTime);
			  int sum = 0;
			  for (int i = 0; i < foodlistlist.size(); i++) {
				  String labeltext = foodlistlist.get(i).getFoodname() + " / " + foodlistlist.get(i).getFoodunit() +" / "
						  + foodlistlist.get(i).getCal();
					Label label = new Label(labeltext);
					content.getChildren().add(label);
//					try {
//						sum +=Integer.parseInt(foodlistlist.get(i).getCal());
//					} catch (Exception e) {}
			}
//			  dinnerkcal.setText(sum+"");
		  }
  }
  
  public void setFoodData(ArrayList<Foodlist> foodlistlist) {
	  foodlistlist = foodlistdao.viewDayFood(everyday, eatTime);
	  content.getChildren().clear();
	  int sum = 0;
	  for (int i = 0; i < foodlistlist.size(); i++) {
		  String labeltext = foodlistlist.get(i).getFoodname() + " / " + foodlistlist.get(i).getFoodunit() +" / "
				  + foodlistlist.get(i).getCal();
			Label label = new Label(labeltext);
			content.getChildren().add(label);
//			try {
//				sum +=Integer.parseInt(foodlistlist.get(i).getCal());
//			} catch (Exception e) {}
	}
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
			   stage.setScene(anotherScene);
			   stage.initStyle(StageStyle.UNDECORATED);
			   stage.show();
			   // 다른창 띄우는 작업 .... 2 끝.
		
			} catch (IOException e) {} 
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
}
