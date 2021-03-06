package HealthSchedule.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import HealthSchedule.Dao.FoodListDao;
import HealthSchedule.Dao.PhotoDao;
import HealthSchedule.Dao.RoutineDao;
import HealthSchedule.Dao.WeightDao;
import HealthSchedule.Interface.ControllerSettable;
import HealthSchedule.Interface.ControllerSettable2;
import HealthSchedule.Interface.ControllerSettable3;
import HealthSchedule.Interface.FoodTableListener;
import HealthSchedule.Interface.TotalListener;
import HealthSchedule.model.Food;
import HealthSchedule.model.Foodlist;
import HealthSchedule.model.Routines;
import HealthSchedule.model.Weight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.halowd.saveImg.SaveImg;

public class Main_everydayRecord_controller extends DayController implements Initializable{

	  private TotalListener totalListener;
	  private FoodTableListener foodtablelistener;
	  DayController dayController;
	  Foodlist foodlist;
	  FoodListDao foodlistdao = new FoodListDao();
	   @FXML private Label main_totalKcal;
	    
//	    @FXML private ScrollPane foodScroll; 
	    @FXML private GridPane foodGrid;
	  
	  //////////////////////////////
	  static Routines routine;
	  static List<String> partnames = new ArrayList<>();
	  static int totalHour;
	  static int totalMinute;
	  static int totalSecond;
	  
	  ////////////////////////////////////
	  public static List<Food> foodListlist = new ArrayList<>();
   @FXML private AnchorPane pane;
   @FXML private Label backLabel;//뒤로 가기 버튼
   @FXML private JFXButton memoButton;//메모버튼
   @FXML private Label breakfastlbl;//뒤로 가기 버튼
   
   @FXML private JFXButton myphotos;//사진 비교 페이지로 가는 버튼
   
   @FXML private Label todayDate;//오늘 날짜
   @FXML private Label todayDayOfWeek;//오늘 요일
   /////////////////////////////////////첫번째 탭
   public static String eatTime; //식사시간
//   @FXML  private VBox breakfastDiet;
   ////////////////////////////////////
   @FXML private ScrollPane scroll; //두번째 탭
   @FXML private GridPane grid;
   static int column = 0;
   static int row = 0;
   @FXML private Label totalTime;//총 운동시간
   
   @FXML private JFXButton viewMonthlyChart; //한달 차트 보기 버튼
//   RoutineDao routineDao = new RoutineDao();
  
 
  ////////////////////////////////////세번쨰 탭///////////////////
   @FXML private JFXButton uploadBtn;//사진업로드
   @FXML private ImageView todayPhoto; //업로드 버튼 클릭 후 오늘사진 띄우는 이미지뷰
   
   public static List<Routines> routineslist = new ArrayList<>();
   RoutineDao routineDao = new RoutineDao();
   PhotoDao photoDao = new PhotoDao();
   //총 운동시간에 계속 변하게 하기
  static String timelabel_main; //총 운동시간라벨
   public void setTotalworkoutTime(Routines routine) {
	   routine = routineDao.selecTotalTime(everyday);
	   System.out.println(everyday);
	  totalHour = routine.getHour();
	  totalMinute = routine.getMinute();
	  totalSecond = routine.getSecond();
	  String timelabel = timeview(totalHour, totalMinute, totalSecond);			  
	  totalTime.setText(timelabel);
	  timelabel_main= timelabel;
   }
   
   public void setTotalKcal(Food food) {
	   food = foodlistdao.selecTotalKcal(everyday);
	   main_totalKcal.setText(food.totalKcal+"");
   }
   
   public void setTotalKcal2(Food food) {
	   main_totalKcal.setText(food.totalKcal+"");
   }
   public void setTotalKcal() {
	   food = foodlistdao.selecTotalKcal(everyday);
	   main_totalKcal.setText(food.totalKcal+"");
   }

   //시간 정리
   public String timeview(int hour, int minute, int second) {
	   if (second>60) {
		
		minute += second/60;
		second = second%60;
	}
	   if (minute>60) {
		
		hour += minute/60;
		minute = minute%60;
	   }
	   String hourstr ="";
	   if (hour<10) {
		hourstr = "0"+ hour;
		}else {
			hourstr = hour+"";
		}
		   String minutestr ="";
		   if (minute<10) {
			   minutestr = "0"+ minute;
		}else {
			minutestr = minute+"";
		}
		   String secondstr ="";
		   if (second<10) {
			   secondstr = "0"+ second;
		}else {
			secondstr = second+"";
		}
		   return hourstr+":"+minutestr+":"+secondstr;
   }
   //루틴 fxml에 데이터 넣기
   private List<Routines> getData(){
	   List<Routines> routines = new ArrayList<>();
	   
	   
	   routine = new Routines();
	   routine.setName("하체운동");
	   routine.setColor("EAF4FE");
	   routine.setButtonColor("33539E");
	   routine.setImgSrc("/HealthSchedule/resources/images/lowerbody.jpg");
	   routines.add(routine);
	   partnames.add(routine.getName());
	   
	   
	   routine = new Routines();
	   routine.setName("스트레칭");
	   routine.setColor("ffeebb");
	   routine.setButtonColor("FECC35");
	   routine.setImgSrc("/HealthSchedule/resources/images/stretch.jpg");
	   routines.add(routine);
	   partnames.add(routine.getName());
	  
	
	   
	   routine = new Routines();
	   routine.setName("복근운동");
	   routine.setColor("F8E9EE");
	   routine.setButtonColor("D67E9B");
	   routine.setImgSrc("/HealthSchedule/resources/images/abs.jpg");
	   routines.add(routine);
	   partnames.add(routine.getName());
	  
	   
	   routine = new Routines();
	   routine.setName("상체운동");
	   routine.setColor("DCC9E9");
	   routine.setButtonColor("8B4BB6");
	   routine.setImgSrc("/HealthSchedule/resources/images/upperbody.jpg");
	   routines.add(routine);
	   partnames.add(routine.getName());
	  
	   
	   routine = new Routines();
	   routine.setName("전신운동");
	   routine.setColor("C5E5D6");
	   routine.setButtonColor("285942");
	   routine.setImgSrc("/HealthSchedule/resources/images/fullbody.jpg");
	   routines.add(routine);
	   partnames.add(routine.getName());
	   
	   
	   
	   return routines;
   }
   
   //루틴 fxml에 데이터 넣기
   Food food;
   private List<Food> getFoodData(){
	   List<Food> foods = new ArrayList<>();
	   
	   
	   food = new Food();
	  food.setEattime("breakfast");
	  food.setImgSrc("/HealthSchedule/resources/images/free-icon-breakfast-2612118.png");
	  foods.add(food);
	  
	  
	   food = new Food();
	  food.setEattime("lunch");
	  food.setImgSrc("/HealthSchedule/resources/images/free-icon-vegan-food-894889.png");
	  foods.add(food);
	  
	  
	   food = new Food();
	  food.setEattime("snack");
	  food.setImgSrc("/HealthSchedule/resources/images/free-icon-biscuit-2612109.png");
	  foods.add(food);
	  
	  
	   food = new Food();
	  food.setEattime("dinner");
	  food.setImgSrc("/HealthSchedule/resources/images/free-icon-vegan-food-894901.png");
	  foods.add(food);
	
	  return foods;
   }
     
  

   
   ////////
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
	   System.out.println("maineverydayController initialize실행");

      //stage 조정
        stageDragableMoveWindow();
        
        //food부분에 fxml로드하기
        foodListlist.addAll(getFoodData());
        
        foodtablelistener = new FoodTableListener() {
			
			@Override
			public void onClickListener(Food food) {
				setTotalKcal(food);
				
			}
		};
		
        
    
        	  int column1 = 0;
              int row1 = 1;
              try {
                  for (int i = 0; i < 4; i++) {
                      FXMLLoader fxmlLoader = new FXMLLoader();
                      fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/foodTime.fxml"));
                      AnchorPane anchorPane = fxmlLoader.load();
                      FoodController foodController = fxmlLoader.getController();
                      foodController.setData(foodListlist.get(i), foodtablelistener);
                      foodController.setFoodData();
                      
                      ControllerSettable2 con2 = fxmlLoader.getController();
                      con2.setController2(this);
                     

                      if (column1 == 4) {
                          column1 = 0;
                          row1++;
                      }

                      foodGrid.add(anchorPane, column1++, row1); //(child,column,row)
                      //set grid width
                      foodGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                      foodGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                      foodGrid.setMaxWidth(Region.USE_PREF_SIZE);

                      //set grid height
                      foodGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                      foodGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                      foodGrid.setMaxHeight(Region.USE_PREF_SIZE);

                      GridPane.setMargin(anchorPane, new Insets(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
      if (foodlistdao.ifexistFood(everyday)) {
    	 setTotalKcal();
	}
       
        //////////////////////////////////////////////////////////////////////
       //루틴 fxml에 데이터 넣기
        routineslist.addAll(getData());
        
        //총 운동시간 리스너 설정
        totalListener = new TotalListener() {
			
			@Override
			public void onClickListener(Routines routine) {
				setTotalworkoutTime(routine);
				
				}
			};
        
        int column = 0;
        int row =1;
        //두번쨰 탭에 루틴 fxml 5개 불러옴
        try {
			for (int i = 0; i < routineslist.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/routine_lowerbody.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();
				
				RoutineController routineController = fxmlLoader.getController();
				routineController.setData(routineslist.get(i), totalListener);
//				routineController.setData(routineslist.get(i));
				routineController.changeColor(routineslist.get(i).getColor());
				routineController.changebtnColor(routineslist.get(i).getButtonColor());
				routineController.setDatas();
				routineController.setTimedata();
				
				ControllerSettable con =fxmlLoader.getController();
				con.setController(this);
//				System.out.println("main에서 total 시간" + totalHour);
//				System.out.println("main에서 total 시간" + totalMinute);
//				System.out.println("main에서 total 시간" + totalSecond);
				if(column == 1) {
					column = 0;
					row ++;
				}
				
				 	grid.add(anchorPane, column++, row); //(child,column,row)
	                //set grid width
	                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
	                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
	                grid.setMaxWidth(Region.USE_PREF_SIZE);

	                //set grid height
	                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
	                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
	                grid.setMaxHeight(Region.USE_PREF_SIZE);

	                GridPane.setMargin(anchorPane, new Insets(10));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

            
        ////////// 날짜 세팅 /////////////
        setTodayDate(year, month, dayOfMonth);
        todayDayOfWeek.setText(dayofWeek);

        //////총 시간 세팅
      if (routineDao.ifexistTime(everyday)) {
    	  settotalTimeLabel();
	}
      
//      if (photoDao.ifexistPhoto(everyday)) {
//		String photoinit =photoDao.selectPhoto(everyday);
//		todayPhoto.setImage(new Image(getClass().getResource(photoinit).toString()));
//	}
      try { 
    	  if (photoDao.ifexistPhoto(everyday)) {
    			String photoinit =photoDao.selectPhoto(everyday);
    		  
    			todayPhoto.setImage(new Image(getClass().getResource(photoinit).toString()));
    		}
    	       
	      else {
			
		}
      }catch (Exception e) {
		System.out.println("init 실행 예외");
	}
      
      if (weightdao.ifexistWeight(everyday)) {
			Weight weights = weightdao.viewWeight(everyday);
			double weightinit = weights.getWeight();
			weight.setText(weightinit + "");
		} 
		
		if (weightdao.ifexistgoalWeight(everyday)) {
			Weight weights = weightdao.viewgoalWeight(everyday);
			double goalweightinit = weights.getWeight();			
			goalweight.setText(goalweightinit + "");
		} 
      
   }
   //총 운동 시간 세팅
   public void settotalTimeLabel() {
	   routine = routineDao.selecTotalTime(everyday);
	   System.out.println(everyday);
	  totalHour = routine.getHour();
	  totalMinute = routine.getMinute();
	  totalSecond = routine.getSecond();
	  String timelabel = timeview(totalHour, totalMinute, totalSecond);		
       totalTime.setText(timelabel);
   }
   
   
   
   //오늘 날짜
   public void setTodayDate(String year, String month, String dayOfMonth) {
	   todayDate.setText(year+"."+month+"."+dayOfMonth);
   }
   
   //운동루틴 로더
   	public void gridpaneSet(AnchorPane anchorPane) {
//   		if(column == 1) {
//			column = 0;
//			row++;
//		}
//		     grid.add(anchorPane, column++, row);
			  //set grid width
		     grid.setMinWidth(Region.USE_COMPUTED_SIZE);
		     grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
		     grid.setMaxWidth(Region.USE_PREF_SIZE);
		
		     //set grid height
		     grid.setMinHeight(Region.USE_COMPUTED_SIZE);
		     grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
		     grid.setMaxHeight(Region.USE_PREF_SIZE);
			
			GridPane.setMargin(anchorPane, new Insets(10));
   	}

     //////////////////////////화면 조정//////////////////
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

      @FXML
      private void actionBackWindow(MouseEvent event) {
         try {
            //뒤로 가기 버튼을 누르면 뒤로감
        	
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)backLabel.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      ////////////////////////////////////////////////////////////////////////////////////////////////////////

    
      
      
      
//    
      
      
      
    
      
      //한달 차트 보기 버튼
      public void makeRountinebtn(ActionEvent event) {
    	  FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/piechartDay.fxml") );
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
      
      
      
      /////////////////////////////////////////////////////////////////////////////////////
    //사진업로드 버튼
      public void uploadPhoto(ActionEvent event) {
    	  
    	 
          // 사진 선택 창
          FileChooser fc = new FileChooser();
          fc.setTitle("이미지 선택");
          fc.setInitialDirectory(new File("C:/")); // default 디렉토리 설정
          // 선택한 파일 정보 추출
          // 확장자 제한
          ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
//        fc.getExtensionFilters().add(imgType);
          ExtensionFilter txtType = new ExtensionFilter("text file", "*.txt", "*.doc");
          fc.getExtensionFilters().addAll(imgType, txtType);
           
          File selectedFile =  fc.showOpenDialog(null); // showOpenDialog는 창을 띄우는데 어느 위치에 띄울건지 인자를 받고
                                                                  // 그리고 선택한 파일의 경로값을 반환한다.
          System.out.println(selectedFile);               // 선택한 경로가 출력된다.
//           
          // 파일을 InputStream으로 읽어옴
          // 이미지 뷰에 표시함
          try {
              // 파일 읽어오기
              FileInputStream fis = new FileInputStream(selectedFile);
              BufferedInputStream bis = new BufferedInputStream(fis);
              // 이미지 생성하기
              Image img = new Image(bis);
              // 이미지 띄우기
              todayPhoto.setImage(img);
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
          String fileurl = null;
          if(selectedFile!=null) {
           
              SaveImg saveImg = new SaveImg();
            
              String file = selectedFile.toURI().toString();
             
              String path = "../../Food/JFX/src/images";
              
            int result;
        	
			try {
				result = saveImg.saveImgFromUrl(file, path);
				if (result == 1) {
		               System.out.println("저장된경로 : " + saveImg.getPath());
		               System.out.println("저장된파일이름 : " + saveImg.getSavedFileName());
		            }
				fileurl = "../../images/"+saveImg.getSavedFileName();
				System.out.println(fileurl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
           }

           if (!photoDao.ifexistPhoto(everyday)) {
        	   photoDao.savePhoto(everyday, fileurl);
		}else {
			photoDao.updatePhoto(everyday, fileurl);
		}
          
//          photoDao.selectPhoto(everyday, selectedFile);

      }
   
      //메모페이지로 이동

      public void memoBtn(ActionEvent event) {

    	  FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/memo.fxml") );

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
      
      //사진 비교 페이지
      public void comparePhotos(ActionEvent event) {

    	  FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/DatePicker.fxml") );

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
      
      ////////////////////////////
      public void setDataLabel(Foodlist foodlist, FoodTableListener foodTableListener) {
          this.foodlist = foodlist;
          this.foodtablelistener= foodTableListener;
         Label label = new Label(foodlist.getFoodname());
       }
      
      
      
      ///////////////////////////////////////////////////////////////////////////
      @FXML private JFXTextField weight;
  	  @FXML private JFXButton saveWeight;
      @FXML private TextField goalweight;
      @FXML private JFXButton savegoalWeightBtn;
  	
  	
  	//String weight; //몸무게 저장한거
  	
  	WeightDao weightdao = new WeightDao();
  	
  	

  	
  	public void saveWeight(ActionEvent event) {

  		if(!weight.getText().isEmpty()) {
  	
  				String saveWeight = weight.getText();			
  				
  				if (!weightdao.ifexistWeight(everyday)) {
  					weightdao.saveWeight(everyday, Integer.parseInt(saveWeight));				
  	
  				}else {
  					weightdao.updateWeight(everyday, Integer.parseInt(saveWeight));
  					
  				}				
  	
  		}else {
  			System.out.println("몸무게를 입력하지 않았습니다.");
  		}
  				
  	}	
  	
  	public void savegoalWeight(ActionEvent event) {

  		if(!goalweight.getText().isEmpty()) {
  	
  				String savegoalWeight = goalweight.getText();			
  				
  				if (!weightdao.ifexistgoalWeight(everyday)) {
  					weightdao.savegoalWeight(everyday, Integer.parseInt(savegoalWeight));				
  	
  				}else {
  					weightdao.updategoalWeight(everyday, Integer.parseInt(savegoalWeight));
  					
  				}				
  	
  		}else {
  			System.out.println("목표 몸무게를 입력하지 않았습니다.");
  		}
  				
  	}

  	

	
      
}