package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Rating_Controller
{
	@FXML
	public Pane DataPane;
	@FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
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

			TableColumn<ObservableList, String> name = new TableColumn<>("Name");
			name.setMinWidth(180);
			name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));
			
			
			TableColumn<ObservableList, String> rate = new TableColumn<>("Rating");
			rate.setMinWidth(50);
			rate.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1).toString()));
			
			
			TableColumn<ObservableList, String> level = new TableColumn<>("Level");
			level.setMinWidth(50);
			level.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(2).toString()));
			
			
			TableColumn<ObservableList, String> worth = new TableColumn<>("Worth");
			worth.setMinWidth(50);
			worth.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(2).toString()));
			
			
			listPlayersInfoTable.getColumns().addAll(name , rate , level, worth);

			
			
			//Data Base Handler instance variables
			String dbURL;
			String dbUsername = "root";
			String dbPassword = "root";
			String URL = "127.0.0.1";
			String port = "3306";
			String dbName = "FootballDBMS";
			String SQL;
			Connection con;
			
			
			System.out.println("TEST");
			

			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

			
			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "false");
			p.setProperty("autoReconnect", "true");

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, p);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select name, rating, level, worth from playersInfo");
			
			while(rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				
				for (int i = 1; i <= 4; i++)
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
