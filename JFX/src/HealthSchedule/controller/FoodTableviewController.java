package HealthSchedule.controller;
 
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
 
public class FoodTableviewController implements Initializable {
 
    private ObservableList<FoodListDao> productList = FXCollections.observableArrayList();	//������ü������� ObservableList
    private ObservableList<FoodListDao> pluslist = FXCollections.observableArrayList();	//�����ؼ� ���̺��2�� �־�ߵǴ� ObservableList
    
	@FXML
	private TableView<FoodListDao> tableview1,tableview2;	//��ȸ���̺��, �߰����̺��

	@FXML
	private TableColumn<FoodListDao, String> foodname, foodunit, cal;	//���̺��1 Į����
	@FXML
	private TableColumn<FoodListDao, String> tableview1_foodname, tableview1_foodunit, tableview1_cal;	//���̺��2 Į����
    @FXML private TextField searchtext;	//�˻�â
    @FXML private Button search, search1, plusList;	//�˻���ư,��ȸ��ư,�߰���ư

    //Į��row������ ���� ����
    public String foodname1;
    public String foodunit1;
    public String cal1;
//    FoodListDao FoodListDao = new FoodListDao();
//    FoodListDao.add();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	productList.clear();
//    	search.setOnAction(e -> handleBtnSelect(e));	//��ȸ �� �˻�, Ȥ�� �˻��Ͽ� �ٷ� ��ȸ�Ϸ��� �ϴ� ���� �ʴ´�
//    	search1.setOnAction(e -> searchbtn(e));

    	//�߰����. ���̺�� row����. Į���� ����
    	tableview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FoodListDao>() {
			@Override
			public void changed(ObservableValue<? extends FoodListDao> observable, FoodListDao oldValue, FoodListDao newValue) {	
				if(newValue!=null) {
					foodname1 = newValue.getName();
					foodunit1 = newValue.getUnit();
					cal1 = newValue.getCal();
				}
			}
		});
    	
    	//tabeview1�� ��ٷ� ������
		Connection conn;
		try {
			conn = FoodListDao.connect();
			ResultSet rs = conn.createStatement().executeQuery("select * from Food");
			System.out.println("���ĸ��db���̺������Ϸ�");
			while(rs.next()) {
				productList.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));		
			}
		} catch (SQLException e1) {
			Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
			System.out.println("ȣ�����");
			e1.printStackTrace();
		}
		//Į���� ���� ���� set
		foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
		foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
		cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
    	
    	//�˻����
    	FilteredList<FoodListDao> filteredData = new FilteredList<>(productList, p -> true);	
    	searchtext.textProperty().addListener((observable, oldValue, newValue) -> {	//�츮�� �ʿ��� ���� �˻����̱⿡ newValue�� ����ϰ� �ȴ�
			filteredData.setPredicate(person -> {
				// �˻�â�� ����ְų� ������ ���
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				//�˻�����
				String lowerCaseFilter = newValue.toLowerCase();
				if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // �����̸�����
				} else if (person.getUnit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;	//���Ĵ�������
				} else if (person.getCal().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;	//Į�θ�����
				}
				return false; //��Ī�Ǵ� ���� ���� ���
			});
		});
		SortedList<FoodListDao> sortedData = new SortedList<>(filteredData);
		//�˻��� ������ tableview1�� ���ε�. �ǽð����� �˻���ư�� ������ ����ä �˻��� �ȴ�
		sortedData.comparatorProperty().bind(tableview1.comparatorProperty());
		
		//���� ��ü��� tableview1�� ���
		tableview1.setItems(sortedData);
    }

 
	
	//���õ� �׸� tableview2�� �߰�
    public void pluslistbtn(ActionEvent event) {
//    	FoodListDao ld = new FoodListDao();
//    	Connection conn;
//		String name = foodname1;
//		String unit = foodunit1;
//		String C = cal1;
//		//System.out.println(name + " " + unit + " " + C);	//������ row�� ����� ���Դ��� Ȯ���ϱ�����
//		try {		
//			conn = ld.saveContent(name, unit, C);
//			ResultSet rs = conn.createStatement().executeQuery("select * from Foodtest");
//			System.out.println("�Ļ�db���̺������Ϸ�");
//			while(rs.next()) {
//				pluslist.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));
//			}
//		} catch (SQLException e1) {
//			Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
//			System.out.println("ȣ�����");
//			e1.printStackTrace();
//		}    	
		//���� �ڵ带 �ΰ� �Ʒ��� �ڵ带 �ּ�ó������ ������ �ΰ��� ����ϰ� �ȴ�
		//����: db�� �ִ� foodtest���̺� �߰��� �� ����->foodtest���̺������Ͽ� ��ȣ��->pluslist�� �� ����->setitems�� tableview2�� �� Į���� ���� �� ���
		//���� ����߰��� ���� �� �� �����ư�׼��� ���� �ξ� db�� �����ϴ� ������ �ڷ� �̷�� �ʹٸ� �Ʒ��� �ڵ带 Ȱ��ȭ��Ű�� ���� �ִ� �ڵ���� �ּ�ó���� �� �����ư�׼ǿ��� db�� �����ϸ� �ȴ�
    	pluslist.add(new FoodListDao(foodname1, foodunit1, cal1));

    	tableview1_foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableview1_foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
    	tableview1_cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
    	tableview2.setItems(pluslist);
    }
}