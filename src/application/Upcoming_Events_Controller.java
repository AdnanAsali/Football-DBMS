package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Upcoming_Events_Controller
{
	private ObservableList<ObservableList> data;
	@FXML
	private TableView<ObservableList> listPlayersInfoTable;
	@FXML
	public Pane DataPane;
	@FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField locF;
    @FXML
    private JFXDatePicker dateF;
    @FXML
    private JFXTextField tickF;
    @FXML
    private JFXTextField numOfaudF;
    @FXML
    private ImageView success;
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
    private JFXButton addEventbutt;

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
		
		
		String loc = locF.getText();
		LocalDate dateSql = dateF.getValue();
		int ticket = Integer.parseInt(tickF.getText());
		int numOfAudience = Integer.parseInt(numOfaudF.getText());
		
		
		System.out.println("TEST");
		

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);
    	
    	SQL = "insert into " + "upcomingevents" + "(id ,location, date, ticketPrice, numofaud)" 
    											+"values(null,"
    											//+ id + ", " 
    											+"\"" + loc + "\""+ ", "
    											+"\"" + dateSql + "\""+ ", "
    											+ ticket + ", "
    											+ numOfAudience  + ");"
    											;
    	
    	
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate(SQL);
		refreshTable();
		resetEventFields();
		success.setVisible(true);
    }
	
	
	


	// to initialize the necessary variable and interface components
	public void initialize()
	{
		fillPlayersInfo();
	}

	// to fill the appointments table
	public void fillPlayersInfo()
	{
			data = FXCollections.observableArrayList();
			//ResultSet appoints = databaseHandler.execQuery("select docID, patID, date, time from appointments");

			TableColumn<ObservableList, String> id = new TableColumn<>("ID");
			id.setMinWidth(60);
			id.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));
			
			TableColumn<ObservableList, String> loc = new TableColumn<>("Location");
			loc.setMinWidth(200);
			loc.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(1).toString()));

			TableColumn<ObservableList, String> date = new TableColumn<>("Date");
			date.setMinWidth(200);
			date.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(2).toString()));

			TableColumn<ObservableList, String> tick = new TableColumn<>("Ticket Price");
			tick.setMinWidth(200);
			tick.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(3).toString()));
			
			TableColumn<ObservableList, String> numOfAud = new TableColumn<>("Number Of Audience");
			numOfAud.setMinWidth(200);
			numOfAud.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(4).toString()));
			
			listPlayersInfoTable.getColumns().addAll(id, loc, date, tick, numOfAud);

			
			
			
			
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
			ResultSet rs = stmt.executeQuery("select id, location, date, ticketPrice, numofaud from upcomingevents");
			
			data.removeAll(data);
			while(rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				
				for (int i = 1; i <= 5; i++)
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
		ResultSet rs = stmt.executeQuery("select id, location, date, ticketPrice, numofaud from upcomingevents");
		
		data.removeAll(data);
		while(rs.next())
		{
			ObservableList<String> row = FXCollections.observableArrayList();
			
			for (int i = 1; i <= 5; i++)
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
	
	public void resetEventFields()
	{
		locF.clear();
		numOfaudF.clear();
		tickF.clear();
		dateF.setValue(null);
	}
	
	
	
	
}
