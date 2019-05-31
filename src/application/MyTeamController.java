package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MyTeamController
{
	@FXML
	public Pane DataPane;
	@FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton deleteButt;
    
  //Data Base Handler instance variables	
	String dbURL;
	String dbUsername = "root";
	String dbPassword = "root";
	String URL = "127.0.0.1";
	String port = "3306";
	String dbName = "FootballDBMS";
	String SQL;
	Connection con;
	
	
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

			TableColumn<ObservableList, String> id = new TableColumn<>("ID");
			id.setMinWidth(60);
			id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));

			TableColumn<ObservableList, String> salary = new TableColumn<>("Player's Salary");
			salary.setMinWidth(180);
			salary.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1).toString()));

			TableColumn<ObservableList, String> workHours = new TableColumn<>("Work Hours");
			workHours.setMinWidth(60);
			workHours.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(2).toString()));

			TableColumn<ObservableList, String> join = new TableColumn<>("Joined Since");
			join.setMinWidth(100);
			join.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(3).toString()));
			
			TableColumn<ObservableList, String> travelFee = new TableColumn<>("Travel Fee");
			travelFee.setMinWidth(100);
			travelFee.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(4).toString()));
			
			TableColumn<ObservableList, String> level = new TableColumn<>("Level");
			level.setMinWidth(60);
			level.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(5).toString()));
			
			TableColumn<ObservableList, String> dept = new TableColumn<>("Department");
			dept.setMinWidth(180);
			dept.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(6).toString()));
			
			TableColumn<ObservableList, String> name = new TableColumn<>("Name");
			name.setMinWidth(180);
			name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(7).toString()));

			listPlayersInfoTable.getColumns().addAll(id, salary, workHours, join, travelFee, level, dept, name);

			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "false");
			p.setProperty("autoReconnect", "true");

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, p);
			
			SQL = "select * from playersInfo";
			
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select id, salary, workHours, joinedSince, travelFee, level, department, name from playersInfo");
			
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
	
	String playerName;
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 1) //Checking double click
	    {
	    	playerName = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(7).toString();
	    }
	}

	@FXML
    void deleteAction(ActionEvent event) throws ClassNotFoundException, SQLException 
	{
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm the Delete");
		alert.setContentText("Are you sure you want to delete this player from the database ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "false");
			p.setProperty("autoReconnect", "true");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, p);
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate("delete from playersinfo where name = " + "\'" + playerName+ "\';");
			System.out.println("TEST");
			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
			refreshTable();
			
		} 
		else 
		{
			Alert alertAbort = new Alert(AlertType.INFORMATION);
			alertAbort.setTitle("Deletion Canceled");
			alertAbort.setHeaderText(null);
			alertAbort.setContentText("The process of deletion has been aborted ...");

			alertAbort.showAndWait(); 
		}
		
		
    }
	
	
	//Refresh Table after deleting a row from our table
	public void refreshTable()
	{
		data.removeAll(data);
		
		try
		{
		
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
		ResultSet rs = stmt.executeQuery("select id, salary, workHours, joinedSince, travelFee, level, department, name from playersInfo");
		
		data.removeAll(data);
		while(rs.next())
		{
			ObservableList<String> row = FXCollections.observableArrayList();
			
			for (int i = 1; i <= 8; i++)
				row.add(rs.getString(i));
			
			data.add(row);
		}
			listPlayersInfoTable.setItems(data);
			System.out.println("TEST");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
