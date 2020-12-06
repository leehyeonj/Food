package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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


public class Main_everydayRecord_controller implements Initializable{

   @FXML private AnchorPane pane;
   @FXML private Label backLabel;
   @FXML private Label breakfast;
   
   @FXML private JFXButton uploadBtn;//사진업로드
   
   @FXML private JFXButton fullbody;//전신 버튼
   @FXML private JFXButton upperbody;//전신 버튼
   @FXML private JFXButton abs;//복부 버튼
   @FXML private JFXButton lowerbody;//하체 버튼
   @FXML private JFXButton yoga; //요가버튼
   
   
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      //stage 조정
        stageDragableMoveWindow();
        
        
      
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
      
   
}