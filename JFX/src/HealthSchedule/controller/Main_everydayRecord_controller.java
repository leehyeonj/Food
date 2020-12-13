package HealthSchedule.controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import net.halowd.saveImg.SaveImg;

public class Main_everydayRecord_controller extends DayController implements Initializable{

   @FXML private AnchorPane pane;
   @FXML private Label backLabel;
   @FXML private Label breakfast;
   
   @FXML private JFXButton myphotos;
   
   @FXML private Label todayDate;//오늘 날짜
   @FXML private Label todayDayOfWeek;//오늘 요일
   
   @FXML private ScrollPane scroll;
//   @FXML private JFXScrollPane scroll;
   @FXML private GridPane grid;
   static int column = 0;
   static int row = 0;
   
   static int totalHour;
   static int totalMinute;
   static int totalSecond;
   @FXML private Label totalTime;//총 운동시간
   
   @FXML private JFXButton makeRoutine; //없앨거임
   RoutineDao routineDao = new RoutineDao();
  
   
   
   
   @FXML private JFXButton uploadBtn;//사진업로드
   @FXML private ImageView todayPhoto; //업로드 버튼 클릭 후 오늘사진 띄우는 이미지뷰
   
     
   
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
//	   System.out.println("maineverydayController initialize실행");
      //stage 조정
        stageDragableMoveWindow();
        
     // 두번째 tab에 운동 페이지 불러오기
        try {
			
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/routine_lowerbody.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();
				grid.add(anchorPane, 0, 0);
				gridpaneSet(anchorPane);
//			
				FXMLLoader fxmlLoader2 = new FXMLLoader();
				fxmlLoader2.setLocation(getClass().getResource("/HealthSchedule/resources/routine_stretch.fxml"));
				AnchorPane anchorPane2 = fxmlLoader2.load();
				grid.add(anchorPane2, 0, 1);
				gridpaneSet(anchorPane2);
				
				FXMLLoader fxmlLoader3 = new FXMLLoader();
				fxmlLoader3.setLocation(getClass().getResource("/HealthSchedule/resources/routine_upperbody.fxml"));
				AnchorPane anchorPane3 = fxmlLoader3.load();
				grid.add(anchorPane3, 0, 2);
				gridpaneSet(anchorPane3);
				FXMLLoader fxmlLoader4 = new FXMLLoader();
				fxmlLoader4.setLocation(getClass().getResource("/HealthSchedule/resources/routine_abs.fxml"));
				AnchorPane anchorPane4 = fxmlLoader4.load();
				grid.add(anchorPane4, 0, 3);
				gridpaneSet(anchorPane4);
				
				FXMLLoader fxmlLoader5 = new FXMLLoader();
				fxmlLoader5.setLocation(getClass().getResource("/HealthSchedule/resources/routine_fullbody.fxml"));
				AnchorPane anchorPane5 = fxmlLoader5.load();
				grid.add(anchorPane5, 0, 4);
				gridpaneSet(anchorPane5);
				
		} catch (Exception e) {
			// TODO: handle exception
		}
        

            
        ////////// 날짜 세팅 /////////////
        setTodayDate(year, month, dayOfMonth);
        todayDayOfWeek.setText(dayofWeek);

        //총 시간 세팅
      if (routineDao.ifexistTime(everyday)) {
    	  settotalTimeLabel();
	}
       
      
   }
   
   public void settotalTimeLabel() {
	   
	   ArrayList<TotalTime> totallist = routineDao.selecTotalTime(everyday);
	   totalHour = totallist.get(0).getTotalTimehour();
	   totalMinute = totallist.get(0).getTotalTimeminute();
	   totalSecond = totallist.get(0).getTotalTimesecond();
	   
	  
       if((totalSecond/60)>0) {
       	totalMinute += totalSecond/60;
       	totalSecond = totalSecond%60;
       }
       if ((totalMinute/60) >0) {
			totalHour += totalMinute/60;
			totalMinute = totalMinute%60;
		}
       String hour = "";
       if (totalHour<10) {
			hour = "0"+totalHour;
		}
       else {
			hour = Integer.toString(totalHour);
		}
       String minute = "";
       if (totalMinute<10) {
       	minute = "0"+totalMinute;
		}
       else {
       	minute = Integer.toString(totalMinute);
		}
       String second = "";
       if (totalSecond<10) {
       	second = "0"+totalSecond;
		}
       else {
       	second = Integer.toString(totalSecond);
		}
       System.out.println("main: totalhour" + totalHour);
       System.out.println("main: totalhour" + totalMinute);
       System.out.println("main: totalhour" + totalSecond);
       String totalTimess = hour+":"+minute+":" + second;
       totalTime.setText(totalTimess);
   }
   
   //사진 비교 페이지
   public void myphotospage(ActionEvent event) {
 	  try {
           //뒤로 가기 버튼을 누르면 뒤로감
           Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/photo.fxml"));
           Scene scene = new Scene(checkOk);
           Stage primaryStage= (Stage)makeRoutine.getScene().getWindow();
           primaryStage.setScene(scene);
        } catch (Exception e2) {}
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

      @FXML
      private void pageMove(MouseEvent event) {
         try {
            //음식 이름을 클릭하면 넘어간다.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_breakfast.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      @FXML
      private void currentWeight(MouseEvent event) {
         try {
            //현재 몸무게를 클릭하면 넘어간다.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      @FXML
      private void goalWeight(MouseEvent event) {
         try {
            //목표 몸무게를 클릭하면 넘어간다.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      
      
//    
      
      
      
    
      
      //운동루틴 만들기버튼
      public void makeRountinebtn(ActionEvent event) {
    	  try {
              //뒤로 가기 버튼을 누르면 뒤로감
              Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/makeRoutine.fxml"));
              Scene scene = new Scene(checkOk);
              Stage primaryStage= (Stage)makeRoutine.getScene().getWindow();
              primaryStage.setScene(scene);
           } catch (Exception e2) {}
      }
      
      
      
      /////////////////////////////////////////////////////////////////////////////////////
    //사진업로드 버튼
      public void uploadPhoto(ActionEvent event) {
          try {
            
                FileChooser fc = new FileChooser();
                  fc.setTitle("이미지 선택");
                  fc.setInitialDirectory(new File("C:/"));
                  ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
                  fc.getExtensionFilters().add(imgType);
                  File selectedFile =  fc.showOpenDialog(null);
                  
                  if(selectedFile!=null) {
                     //upload.setText(selectedFile.toURI().toString());
                     SaveImg saveImg = new SaveImg();
                   
                     String file = selectedFile.toURI().toString();

                     String path = "src/images";
                     
                   int result = saveImg.saveImgFromUrl(file, path);
                   if (result == 1) {
                      String savePath = saveImg.getPath();
                      System.out.println("저장된경로 : " + savePath);
                      String saveFileName = saveImg.getSavedFileName();
                      System.out.println("저장된파일이름 : " + saveFileName);
                      System.out.println((savePath+"/"+saveFileName));
                      
                      todayPhoto.setImage(new Image(getClass().getResource("../images/"+saveFileName).toString()));
           
                   
                   }
                  }
                  
                  
             } catch (Exception e) {
                e.printStackTrace();
             }

      }
   
}