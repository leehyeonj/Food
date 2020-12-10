package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

//import java.awt.Image;
////import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//
//
//import net.halowd.saveImg.SaveImg;
//
//import java.io.File;
//import java.io.IOException;


public class Main_everydayRecord_controller extends DayController implements Initializable{

   @FXML private AnchorPane pane;
   @FXML private Label backLabel;
   @FXML private Label breakfast;
   
   @FXML private ScrollPane scroll;
   @FXML private GridPane grid;
   int column = 0;
   int row = 1;
   
   @FXML private JFXButton fullbody;//전신 버튼
   @FXML private JFXButton upperbody;//전신 버튼
   @FXML private JFXButton abs;//복부 버튼
   @FXML private JFXButton lowerbody;//하체 버튼
   @FXML private JFXButton yoga; //요가버튼
   @FXML private JFXButton makeRoutine; //요가버튼
   
   @FXML private JFXButton uploadBtn;//사진업로드
   @FXML private ImageView todayPhoto; //업로드 버튼 클릭 후 오늘사진 띄우는 이미지뷰
   
   //중섭
   @FXML private Label PieChart, WeightTime;	//부위별 운동비율차트, 운동시간
   @FXML private TextField FullbodyTime, UpperbodyTime, AbsTime, LowerbodyTime;
   @FXML private TextArea FullbodyText, UpperbodyText, AbsText, LowerbodyText;
   @FXML private Button Fullbodysave, Upperbodysave, Abssave, Lowerbodysave, piechart;
   
   //중섭
   private Connection conn;    //DB 커넥션(연결) 객체
   private static final String USERNAME = "root";   //DB 접속시 ID
//   private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
//   private static String URL = "jdbc:mysql://localhost:3306/calendardb";	//dbms
   
   
   //현주
   private static final String PASSWORD = "DOALd1120f1gG";	 //DB 접속시 패스워드
   private static String URL = "jdbc:mysql://localhost:3305/calendardb";	//dbms
   public Main_everydayRecord_controller() {
	  // connection객체를 생성해서 DB에 연결함.
	  try {
	  	  //동적 객체를 만들어줌 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	      //System.out.println("드라이버 로딩 성공!!");
	      
	  } catch (Exception e) {
	      //System.out.println("드라이버 로드 실패!!");
	  }
   }
   
   
   
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      //stage 조정
        stageDragableMoveWindow();
        
      // 두번째 tab에 불러오기
        try {
			for(int i = 0; i< 5; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/exercise_routine.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();
				
				if(column == 1) {
					column = 0;
					row++;
				}
				
				grid.add(anchorPane, column++, row);
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
      //전체 화면으로
//      @FXML
//      private void actionMaxWindow(MouseEvent event) {
//      // Launcher.stage.setFullScreen(true);
//      // if (Launcher.stage.isMaximized()) {
//      // Launcher.stage.setMaximized(false);
//      // } else {
//      // Launcher.stage.setMaximized(true);
//      // }
//      stage = (Stage) pane.getScene().getWindow();
//      if (stage.isMaximized()) {
//      stage.setMaximized(false);
//      } else {
//      stage.setMaximized(true);
//      }
//      }
      
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
      
      
      
//      //사진가져오기 버튼
//      public void actionPerformed(ActionEvent e){
//          JFileChooser fileChooser = new JFileChooser();
//          fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png", "jpeg");
//          fileChooser.addChoosableFileFilter(filter);
//          int result = fileChooser.showSaveDialog(null);
//          if(result == JFileChooser.APPROVE_OPTION){
//              File selectedFile = fileChooser.getSelectedFile();
//              String path = selectedFile.getAbsolutePath();
//              fileName = selectedFile.getName();
//              url=path;
//              urlToString=selectedFile.toURI().toString();
//              label.setIcon(ResizeImage(path));
//              s = path;
//               }
//          else if(result == JFileChooser.CANCEL_OPTION){
//              System.out.println("No Data");
//          }
//      }
//     });
//      
//      
//      
//      //사진저장 버튼
//      public void savePhoto(ActionEvent e){
//
//    	SaveImg saveImg = new SaveImg();
//
//        String file = urlToString;
//        String path = "C:\\HealthSchedule\\test\\src\\images";
// 	   
// 	   final String USERNAME = "root";   //DB 접속시 ID
// 	   final String PASSWORD = "1234";	 //DB 접속시 패스워드
// 	   final String URL = "jdbc:mysql://localhost:3306/iddb";
// 	    
// 	    try {
//             System.out.println("생성자");
//             Class.forName("com.mysql.jdbc.Driver"); 
//             conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             System.out.println("드라이버 로딩 성공!!");
//         } catch (Exception e2) {
//             e2.printStackTrace();
//             System.out.println("드라이버 로드 실패!!");
//         }
// 	   
// 	    String sql = "insert into test values(?);";
//     	PreparedStatement pstmt = null;
//         try {
//             pstmt = conn.prepareStatement(sql);
//             String url = "C:\\HealthSchedule\\test\\src\\images\\" + fileName;
//             pstmt.setString(1, url);
//
//             int result = pstmt.executeUpdate();
//             if(result == 1) {
//             	System.out.println("사진 추가 성공!");
//             	try {
//            			int result2 = saveImg.saveImgFromUrl(file, path);
//            			if (result2 == 1) {
//            				System.out.println("저장된경로 : " + saveImg.getPath());
//            				System.out.println("저장된파일이름 : " + saveImg.getSavedFileName());
//            			}
//
//            		} catch (IOException e6) {
//            			e6.printStackTrace();
//            		}
//             }           
//         } catch (SQLException e3) {            
//         	System.out.println("사진 추가 실패!");
//             e3.printStackTrace();
//         } finally {
//             try {
//                 if (pstmt != null && !pstmt.isClosed())
//                     pstmt.close();
//             } catch (SQLException e4) {                
//                 e4.printStackTrace();
//             }
//         }
// 	  }
   
  	  //중섭
      //piechart띄우기
      public void PieChartbtn(ActionEvent e){
		   try {
			   FXMLLoader members = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/piechart.fxml"));
			   Parent parent = (Parent)members.load();
			   Stage stage = new Stage();
			   stage.setScene(new Scene(parent));
			   stage.setTitle("부위별 운동 비율");
			   stage.show();
			 } catch (Exception e2) {}  
      }	//ShowChartbtn
      
      //전신운동데이터 저장
      public void Fullbodysavebtn(ActionEvent event) {
     		String sql = "insert into Fullbody values(?,?,?)";
 		    String text = FullbodyText.getText();
 		    int time = Integer.parseInt(FullbodyTime.getText());
       		PreparedStatement pstmt = null;
       		try {
       			pstmt = conn.prepareStatement(sql);
       			pstmt.setString(1, null);
       			pstmt.setString(2, text);
       			pstmt.setInt(3, time);
       			int result = pstmt.executeUpdate();
       			if(result==1) {
       				System.out.println("Fullbody데이터 삽입 성공!");
       			}
       		} catch (Exception e) {
       			System.out.println("Fullbody데이터 삽입 실패!");
       		}    finally {
       			try {
       				if(pstmt!=null && !pstmt.isClosed()) {
       					pstmt.close();
       				}
       			} catch (Exception e2) {}
       		}
       		Main_everydayRecord_controller mec = new Main_everydayRecord_controller();
       		WeightTime.setText(mec.Alltime());
      }
      
      //상체운동데이터 저장
      public void Upperbodysavebtn(ActionEvent event) {
     		String sql = "insert into Upperbody values(?,?,?)";
 		    String text = UpperbodyText.getText();
 		    int time = Integer.parseInt(UpperbodyTime.getText());
       		PreparedStatement pstmt = null;
       		try {
       			pstmt = conn.prepareStatement(sql);
       			pstmt.setString(1, null);
       			pstmt.setString(2, text);
       			pstmt.setInt(3, time);
       			int result = pstmt.executeUpdate();
       			if(result==1) {
       				System.out.println("Upperbody데이터 삽입 성공!");
       			}
       		} catch (Exception e) {
       			System.out.println("Upperbody데이터 삽입 실패!");
       		}    finally {
       			try {
       				if(pstmt!=null && !pstmt.isClosed()) {
       					pstmt.close();
       				}
       			} catch (Exception e2) {}
       		}
       		Main_everydayRecord_controller mec = new Main_everydayRecord_controller();
       		WeightTime.setText(mec.Alltime());
      }
      
      //복근운동데이터 저장
      public void Abssavebtn(ActionEvent event) {
     		String sql = "insert into Abs values(?,?,?)";
 		    String text = AbsText.getText();
 		    int time = Integer.parseInt(AbsTime.getText());
       		PreparedStatement pstmt = null;
       		try {
       			pstmt = conn.prepareStatement(sql);
       			pstmt.setString(1, null);
       			pstmt.setString(2, text);
       			pstmt.setInt(3, time);
       			int result = pstmt.executeUpdate();
       			if(result==1) {
       				System.out.println("Abs데이터 삽입 성공!");
       			}
       		} catch (Exception e) {
       			System.out.println("Abs데이터 삽입 실패!");
       		}    finally {
       			try {
       				if(pstmt!=null && !pstmt.isClosed()) {
       					pstmt.close();
       				}
       			} catch (Exception e2) {}
       		}
       		Main_everydayRecord_controller mec = new Main_everydayRecord_controller();
       		WeightTime.setText(mec.Alltime());
      }
      
      //하체운동데이터 저장
      public void Lowerbodysavebtn(ActionEvent event) {
     		String sql = "insert into Lowerbody values(?,?,?)";
 		    String text = AbsText.getText();
 		    int time = Integer.parseInt(AbsTime.getText());
       		PreparedStatement pstmt = null;
       		try {
       			pstmt = conn.prepareStatement(sql);
       			pstmt.setString(1, null);
       			pstmt.setString(2, text);
       			pstmt.setInt(3, time);
       			int result = pstmt.executeUpdate();
       			if(result==1) {
       				System.out.println("Lowerbody데이터 삽입 성공!");
       			}
       		} catch (Exception e) {
       			System.out.println("Lowerbody데이터 삽입 실패!");
       		}    finally {
       			try {
       				if(pstmt!=null && !pstmt.isClosed()) {
       					pstmt.close();
       				}
       			} catch (Exception e2) {}
       		}
       		Main_everydayRecord_controller mec = new Main_everydayRecord_controller();
       		WeightTime.setText(mec.Alltime());
      }
      
  	  //전신운동 등록된 총 시간 호출
  	  public Integer FullBody(int fullbody) {
  		  String sql = "select sum(weightTime) from FullBody";
  		  PreparedStatement pstmt = null;
  		  int i = 0;
  		  try {
  			  pstmt = conn.prepareStatement(sql);
  			  ResultSet rs = pstmt.executeQuery();
  			  if(rs.next()) {	
  				  i = rs.getInt("sum(weightTime)");
  			  }
  		  } catch (Exception e) {
  			  //System.out.println("전신운동 시간 호출 실패");
  		  }finally {
  			  try {
  				  if(pstmt != null && !pstmt.isClosed()) {
  					  pstmt.close();
  				  }
  			  } catch (Exception e2) {}
  		  }
  		  return i;
  	  }
  	  
  	  //상체운동 등록된 총 시간 호출
  	  public Integer UpperBody(int upperbody) {
  		  String sql = "select sum(weightTime) from Upperbody";
  		  PreparedStatement pstmt = null;
  		  int i = 0;
  		  try {
  			  pstmt = conn.prepareStatement(sql);
  			  ResultSet rs = pstmt.executeQuery();
  			  if(rs.next()) {	
  				  i = rs.getInt("sum(weightTime)");
  			  }
  		  } catch (Exception e) {
  			  //System.out.println("전신운동 시간 호출 실패");
  		  }finally {
  			  try {
  				  if(pstmt != null && !pstmt.isClosed()) {
  					  pstmt.close();
  				  }
  			  } catch (Exception e2) {}
  		  }
  		  return i;
  	  }
  	  
  	  //복근운동 등록된 총 시간 호출
  	  public Integer Abs(int abs) {
  		  String sql = "select sum(weightTime) from Abs";
  		  PreparedStatement pstmt = null;
  		  int i = 0;
  		  try {
  			  pstmt = conn.prepareStatement(sql);
  			  ResultSet rs = pstmt.executeQuery();
  			  if(rs.next()) {	
  				  i = rs.getInt("sum(weightTime)");
  			  }
  		  } catch (Exception e) {
  			  //System.out.println("전신운동 시간 호출 실패");
  		  }finally {
  			  try {
  				  if(pstmt != null && !pstmt.isClosed()) {
  					  pstmt.close();
  				  }
  			  } catch (Exception e2) {}
  		  }
  		  return i;
  	  }
  	  
  	  //하체운동 등록된 총 시간 호출
  	  public Integer Lowerbody(int lowerbody) {
  		  String sql = "select sum(weightTime) from Lowerbody";
  		  PreparedStatement pstmt = null;
  		  int i = 0;
  		  try {
  			  pstmt = conn.prepareStatement(sql);
  			  ResultSet rs = pstmt.executeQuery();
  			  if(rs.next()) {	
  				  i = rs.getInt("sum(weightTime)");
  			  }
  		  } catch (Exception e) {
  			  //System.out.println("전신운동 시간 호출 실패");
  		  }finally {
  			  try {
  				  if(pstmt != null && !pstmt.isClosed()) {
  					  pstmt.close();
  				  }
  			  } catch (Exception e2) {}
  		  }
  		  return i;
  	  }
      
  	  //전체 운동한 시간 호출	00:00:00
  	  public String Alltime() {
  		  Main_everydayRecord_controller mec = new Main_everydayRecord_controller();
  		  int second = (int)Math.round(mec.FullBody(1)+mec.UpperBody(1)+mec.Abs(1)+mec.Lowerbody(1)) % 60;
  		  int minute = (int)Math.round(mec.FullBody(1)+mec.UpperBody(1)+mec.Abs(1)+mec.Lowerbody(1)) / 60;
  		  int hour = (int)Math.round(mec.FullBody(1)+mec.UpperBody(1)+mec.Abs(1)+mec.Lowerbody(1)) / 60 / 60;
  		  String time = null;
  		  if(hour == 0) {
  			   time = String.format("%02d시간%02d분", minute, second);
  		  }else if(hour < 10) {
  			   time = String.format("%1d시간%02d분%02d초", hour, minute, second);
  		  }else if(hour < 100) {
  			   time = String.format("%2d시간%02d분%02초", hour, minute, second);
  		  }else if(hour < 1000) {
  			   time = String.format("%3d시간%02d분%02d초", hour, minute, second);
  		  }
  		  return time;
  	  }

  	  //여기까지 추가
      
      
      
      
      
      //전신 버튼
      public void fullbodyEx(ActionEvent event) {
         try {
            Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=전신운동"));
            }
         catch (IOException e) {
            e.printStackTrace(); }
            catch (URISyntaxException e) { 
               e.printStackTrace();
               }
      }
      
      //상체 버튼
      public void upperbodyEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=상체운동"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }
      }
      
      //복부 버튼
      public void absEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=복부운동"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }
      }
      
      //하체 버튼
      public void lowerbodyEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=하체운동"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }   
      }
      
      //요가 버튼 
      public void yogaEx(ActionEvent event) {
   	   try {
              Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=스트레칭+및+요가"));
              }
           catch (IOException e) {
              e.printStackTrace(); }
              catch (URISyntaxException e) { 
                 e.printStackTrace();
                 }   
     }
      
      
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