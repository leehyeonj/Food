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
   
   @FXML private JFXButton uploadBtn;//�������ε�
   
   @FXML private JFXButton fullbody;//���� ��ư
   @FXML private JFXButton upperbody;//���� ��ư
   @FXML private JFXButton abs;//���� ��ư
   @FXML private JFXButton lowerbody;//��ü ��ư
   @FXML private JFXButton yoga; //�䰡��ư
   
   
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      //stage ����
        stageDragableMoveWindow();
        
        
      
   }

     //////////////////////////
      private double xOffset = 0;
      private double yOffset = 0;
      private Stage stage = null;

      //ȭ�� �����϶� �������� ���ϰ� �ϱ�
      private void stageDragableMoveWindow() {
         pane.setOnMousePressed((event) -> {
         xOffset = event.getSceneX();
         yOffset = event.getSceneY();
         });
         pane.setOnMouseDragged((event) -> {
         // Launcher.stage.setX(event.getScreenX() - xOffset);
         // Launcher.stage.setY(event.getScreenY() - yOffset);
         // Launcher.stage.setOpacity(0.8f); // â ����ȭ
         stage = (Stage) pane.getScene().getWindow();
         stage.setX(event.getScreenX() - xOffset);
         stage.setY(event.getScreenY() - yOffset);
         stage.setOpacity(0.8f); // â ����ȭ
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

      //ȭ�� �����
      @FXML
      private void actionMinWindow(MouseEvent event) {
      // Launcher.stage.setIconified(true);
      stage = (Stage) pane.getScene().getWindow();
      stage.setIconified(true);
      }
      //��ü ȭ������
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
      
      //ȭ�� ����
      @FXML
      private void actionCloseWindow(MouseEvent event) {
      System.exit(0);
      }

      @FXML
      private void actionBackWindow(MouseEvent event) {
         try {
            //�ڷ� ���� ��ư�� ������ �ڷΰ�
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)backLabel.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }

      @FXML
      private void pageMove(MouseEvent event) {
         try {
            //���� �̸��� Ŭ���ϸ� �Ѿ��.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_breakfast.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      @FXML
      private void currentWeight(MouseEvent event) {
         try {
            //���� �����Ը� Ŭ���ϸ� �Ѿ��.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      @FXML
      private void goalWeight(MouseEvent event) {
         try {
            //��ǥ �����Ը� Ŭ���ϸ� �Ѿ��.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      
      
//      //������������ ��ư
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
//      //�������� ��ư
//      public void savePhoto(ActionEvent e){
//
//    	SaveImg saveImg = new SaveImg();
//
//        String file = urlToString;
//        String path = "C:\\HealthSchedule\\test\\src\\images";
// 	   
// 	   final String USERNAME = "root";   //DB ���ӽ� ID
// 	   final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
// 	   final String URL = "jdbc:mysql://localhost:3306/iddb";
// 	    
// 	    try {
//             System.out.println("������");
//             Class.forName("com.mysql.jdbc.Driver"); 
//             conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             System.out.println("����̹� �ε� ����!!");
//         } catch (Exception e2) {
//             e2.printStackTrace();
//             System.out.println("����̹� �ε� ����!!");
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
//             	System.out.println("���� �߰� ����!");
//             	try {
//            			int result2 = saveImg.saveImgFromUrl(file, path);
//            			if (result2 == 1) {
//            				System.out.println("����Ȱ�� : " + saveImg.getPath());
//            				System.out.println("����������̸� : " + saveImg.getSavedFileName());
//            			}
//
//            		} catch (IOException e6) {
//            			e6.printStackTrace();
//            		}
//             }           
//         } catch (SQLException e3) {            
//         	System.out.println("���� �߰� ����!");
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
   
      
      
      
      
      
      
      //���� ��ư
      public void fullbodyEx(ActionEvent event) {
         try {
            Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=���ſ"));
            }
         catch (IOException e) {
            e.printStackTrace(); }
            catch (URISyntaxException e) { 
               e.printStackTrace();
               }
      }
      
      //��ü ��ư
      public void upperbodyEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=��ü�"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }
      }
      
      //���� ��ư
      public void absEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=���ο"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }
      }
      
      //��ü ��ư
      public void lowerbodyEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=��ü�"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }   
      }
      
      //�䰡 ��ư 
      public void yogaEx(ActionEvent event) {
   	   try {
              Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=��Ʈ��Ī+��+�䰡"));
              }
           catch (IOException e) {
              e.printStackTrace(); }
              catch (URISyntaxException e) { 
                 e.printStackTrace();
                 }   
     }
      
   
}