package application;

import javafx.scene.paint.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.scene.shape.Rectangle;

public class Training_Controller
{
	private ObservableList<ObservableList> data;
	@FXML
	private TableView<ObservableList> listPlayersInfoTable;
	@FXML
    private Pane DataPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView hamburger;
    @FXML
    private ImageView success;
    @FXML
    private Label excercise1;
    @FXML
    private Label excercise2;
    @FXML
    private Label excercise3;
    @FXML
    private Label excercise4;
    @FXML
    private Label excercise5;
    @FXML
    private Label excercise6;
    @FXML
    private Rectangle box1;
    @FXML
    private Rectangle box2;
    @FXML
    private Rectangle box3;
    @FXML
    private Rectangle box4;
    @FXML
    private Rectangle box6;
    @FXML
    private Rectangle box5;
    @FXML
    private JFXTimePicker fromField;
    @FXML
    private JFXTimePicker toField;
    @FXML
    private JFXTextField typeTraining;
    @FXML
    private TableView<ObservableList> agenda;
    @FXML
    private JFXButton addEventbutt;
    @FXML
    private JFXTextField playerNameFromTable;

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
	
	
    @FXML
    void addEventAction(ActionEvent event) throws SQLException, ClassNotFoundException
    {
    	
    	//Data Base Handler instance variables
		String dbURL;
		String dbUsername = "root";
		String dbPassword = "root";
		String URL = "127.0.0.1";
		String port = "3306";
		String dbName = "FootballDBMS";
		String SQL;
		Connection con;
		
		
		
		String name = playerNameFromTable.getText();
		LocalTime fromTimeSql = fromField.getValue();
		LocalTime toTimeSql = toField.getValue();
		String trainingType = typeTraining.getText();
		
		
		System.out.println("TEST");
		

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
  
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);
    	
    	SQL = "insert into " + "trainingSchedule" + "(trainid, name ,fromtime, totime, trainingType)" 
    											+"values(null,"
    											//+ id + ", "
    											+"\"" + name + "\""+ ", "
    											+"\"" + fromTimeSql + "\""+ ", "
    											+"\"" + toTimeSql + "\""+ ", "
    											+"\"" + trainingType + "\""+ ");"
    											;
    	
    	
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate(SQL);
		refreshTable();
		resetEventFields();
		success.setVisible(true);
    }
	
    @FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	    	String playerName = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(1).toString();
	    	playerNameFromTable.setText(playerName);
	    }
	}
	
    @FXML
    void editExcercise(MouseEvent event)
    {
    	box1.setOnMouseClicked(e -> { typeTraining.setText(excercise1.getText()); } );
    	box2.setOnMouseClicked(e -> { typeTraining.setText(excercise2.getText()); } );
    	box3.setOnMouseClicked(e -> { typeTraining.setText(excercise3.getText()); } );
    	box4.setOnMouseClicked(e -> { typeTraining.setText(excercise4.getText()); } );
    	box5.setOnMouseClicked(e -> { typeTraining.setText(excercise5.getText()); } );
    	box6.setOnMouseClicked(e -> { typeTraining.setText(excercise6.getText()); } );    	
    }
    
    
	// to initialize the necessary variable and interface components
	public void initialize()
	{
		fillPlayersInfo();
		fillAgenda();
	}

	// to fill the info about the players in the tableView
	public void fillPlayersInfo()
	{
		
			box1.setOnMouseEntered(e -> { box1.setStroke(Color.YELLOW); });
			box1.setOnMouseExited(e -> { box1.setStroke(Color.DARKTURQUOISE); });
			
			box2.setOnMouseEntered(e -> { box2.setStroke(Color.YELLOW); });
			box2.setOnMouseExited(e -> { box2.setStroke(Color.DARKTURQUOISE); });
			
			box3.setOnMouseEntered(e -> { box3.setStroke(Color.YELLOW); });
			box3.setOnMouseExited(e -> { box3.setStroke(Color.DARKTURQUOISE); });
			
			box4.setOnMouseEntered(e -> { box4.setStroke(Color.YELLOW); });
			box4.setOnMouseExited(e -> { box4.setStroke(Color.DARKTURQUOISE); });
			
			box5.setOnMouseEntered(e -> { box5.setStroke(Color.YELLOW); });
			box5.setOnMouseExited(e -> { box5.setStroke(Color.DARKTURQUOISE); });
			
			box6.setOnMouseEntered(e -> { box6.setStroke(Color.YELLOW); });
			box6.setOnMouseExited(e -> { box6.setStroke(Color.DARKTURQUOISE); });
			
		
			data = FXCollections.observableArrayList();
			//ResultSet appoints = databaseHandler.execQuery("select docID, patID, date, time from appointments");

			TableColumn<ObservableList, String> id = new TableColumn<>("ID");
			id.setMinWidth(60);
			id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));
			

			TableColumn<ObservableList, String> name = new TableColumn<>("Name");
			name.setMinWidth(200);
			name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1).toString()));

						
			listPlayersInfoTable.getColumns().addAll(id, name);

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
			ResultSet rs = stmt.executeQuery("select id, name from playersinfo");
			
			data.removeAll(data);
			while(rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				
				for (int i = 1; i <= 2; i++)
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
		ResultSet rs = stmt.executeQuery("select name, fromtime,totime,trainingtype from trainingschedule");
		
		data.removeAll(data);
		while(rs.next())
		{
			ObservableList<String> row = FXCollections.observableArrayList();
			
			for (int i = 1; i <= 4; i++)
				row.add(rs.getString(i));
			
			data.add(row);
		}
			agenda.setItems(data);
			System.out.println("TEST");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void resetEventFields()
	{
		playerNameFromTable.clear();
		typeTraining.clear();
		fromField.setValue(null);
		toField.setValue(null);
	}
	
	
	public void fillAgenda()
	{
		
			data = FXCollections.observableArrayList();
			//ResultSet appoints = databaseHandler.execQuery("select docID, patID, date, time from appointments");

			TableColumn<ObservableList, String> name = new TableColumn<>("Name");
			name.setMinWidth(20);
			name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));

			TableColumn<ObservableList, String> from = new TableColumn<>("From");
			from.setMinWidth(200);
			from.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1).toString()));
			
			TableColumn<ObservableList, String> to = new TableColumn<>("To");
			to.setMinWidth(200);
			to.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(2).toString()));
			
			TableColumn<ObservableList, String> type = new TableColumn<>("Type");
			type.setMinWidth(200);
			type.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(3).toString()));

						
			agenda.getColumns().addAll(name, from, to, type);

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
				ResultSet rs = stmt.executeQuery("select name, fromtime,totime,trainingtype from trainingschedule");
				
				data.removeAll(data);
				
			while(rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				
				for (int i = 1; i <= 4; i++)
					row.add(rs.getString(i));
				
				data.add(row);
			}
				agenda.setItems(data);
				refreshTable();
				System.out.println("TEST");
			}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}
	
}
