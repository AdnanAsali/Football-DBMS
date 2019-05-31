package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Performance_Controller
{
	@FXML
	public Pane DataPane;
	@FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private CategoryAxis  x;
    @FXML
    private NumberAxis y;
    @FXML
    private Label playerNameLabel;
    
	@FXML
    void initialize(MouseEvent event)
    {
		try
		{
			DataPane = FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
    	drawer.setSidePane(DataPane);
    	
    	if(drawer.isOpened())
    		drawer.close();
    	else
    		drawer.open();
    }
	
	@FXML
	public void clickItem(MouseEvent event)
	{
		
		 
	    if (event.getClickCount() == 2) //Checking double click
	    {
	    	String playerName = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(7).toString();
	    	playerNameLabel.setText(playerName);
	    	
	    	String speed = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(1).toString();
	    	int playerSpeed = Integer.parseInt(speed);
	    	
	    	String weight = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(2).toString();
	    	double playerWeight = Double.parseDouble(weight);
	    	
	    	String height = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(3).toString();
	    	double playerHeight = Double.parseDouble(height);
	    	
	    	XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			series.getData().add(new XYChart.Data<String, Number>("Speed", playerSpeed));
			series.getData().add(new XYChart.Data<String, Number>("Height", playerHeight));
			series.getData().add(new XYChart.Data<String, Number>("Weight", playerWeight));
			lineChart.getData().add(series);
	    }
	}

	private ObservableList<ObservableList> data;
	@FXML
	private TableView<ObservableList> listPlayersInfoTable;

	// to initialize the necessary variable and interface components
	public void initialize()
	{
		fillPlayersInfo();
	}

	// to fill the appointments table
	public void fillPlayersInfo()
	{
		try
		{
			data = FXCollections.observableArrayList();

			//ResultSet appoints = databaseHandler.execQuery("select docID, patID, date, time from appointments");

			TableColumn<ObservableList, String> id = new TableColumn<>("ID");
			id.setMinWidth(20);
			id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));

			TableColumn<ObservableList, String> speed = new TableColumn<>("Player's Speed");
			speed.setMinWidth(40);
			speed.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1).toString()));

			TableColumn<ObservableList, String> weight = new TableColumn<>("Weight");
			weight.setMinWidth(60);
			weight.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(2).toString()));

			TableColumn<ObservableList, String> height = new TableColumn<>("Height");
			height.setMinWidth(60);
			height.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(3).toString()));
			
			TableColumn<ObservableList, String> worth = new TableColumn<>("Worth");
			worth.setMinWidth(100);
			worth.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(4).toString()));
			
			TableColumn<ObservableList, String> level = new TableColumn<>("Level");
			level.setMinWidth(60);
			level.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(5).toString()));
			
			TableColumn<ObservableList, String> dept = new TableColumn<>("Department");
			dept.setMinWidth(70);
			dept.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(6).toString()));
			
			TableColumn<ObservableList, String> name = new TableColumn<>("Name");
			name.setMinWidth(180);
			name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(7).toString()));

			listPlayersInfoTable.getColumns().addAll(id, speed, weight, height, level, dept, name);
			
			//Data Base Handler instance variables
			String dbURL;
			String dbUsername = "root";
			String dbPassword = "root";
			String URL = "127.0.0.1";
			String port = "3306";
			String dbName = "FootballDBMS";
			Connection con;
			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "false");
			p.setProperty("autoReconnect", "true");

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, p);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select id, speed, weight, height, worth, level, department, name from playersInfo");
			
			while(rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				
				for (int i = 1; i <= 8; i++)
					row.add(rs.getString(i));
				
				data.add(row);
			}
			
			listPlayersInfoTable.setItems(data);
			System.out.println("TEST");
			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}

	
}
