package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Media_Controller 
{
	@FXML
	public Pane DataPane;
	@FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label aboutDes;
    @FXML
    private JFXTextArea aboutTitle;
    @FXML
    private WebView webView;
    @FXML
    private ImageView face;
    @FXML
    private JFXButton google;
    @FXML
    private JFXButton facebook;
    private ObservableList<ObservableList> data;
	@FXML
	private TableView<ObservableList> listPlayersInfoTable;
    @FXML
    private JFXButton twitter;
    @FXML
    private Label selectedPlayer;
    
    
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
    void facebookLoader(ActionEvent event)
	{
		try
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
			ResultSet rs = stmt.executeQuery("select facebook from playersinfo where name=" + "\'"+ selectedPlayer.getText() + "\'");
			while(rs.next())
			{
				for (int i = 1; i <= 2; i++)
				{
					String website = rs.getString(i);
					WebEngine engine = webView.getEngine();
					System.out.println(website);
					engine.load(website);
	 				if(i == 1)
	 					break;
				}
			}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
		
	}
	
	@FXML
    void googleLoader(ActionEvent event) 
	{
		WebEngine engine = webView.getEngine();
		engine.load("https://www.google.com");
    }


    @FXML
    void twitterLoader(ActionEvent event) 
    {
    	try
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
			ResultSet rs = stmt.executeQuery("select twitter from playersinfo where name=" + "\'"+ selectedPlayer.getText() + "\'");
		
		while(rs.next())
		{
			for (int i = 1; i <= 2; i++)
			{
				String website = rs.getString(i);
				WebEngine engine = webView.getEngine();
				System.out.println(website);
				engine.load(website);
				
 				if(i == 1)
 					break;
			}
		}
}
catch (Exception e)
{
	e.printStackTrace();
}
    }
    
 // to initialize the necessary variable and interface components
 	public void initialize()
 	{
 		fillPlayersInfo();
 	}
    
 // to fill the info about the players in the tableView
 	public void fillPlayersInfo()
 	{
 		
 			data = FXCollections.observableArrayList();
 			//ResultSet appoints = databaseHandler.execQuery("select docID, patID, date, time from appointments");

 			TableColumn<ObservableList, String> name = new TableColumn<>("Name");
 			name.setMinWidth(200);
 			name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(0).toString()));

 						
 			listPlayersInfoTable.getColumns().addAll(name);

 			try
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
	 			ResultSet rs = stmt.executeQuery("select name from playersinfo");
 			
 			data.removeAll(data);
 			while(rs.next())
 			{
 				ObservableList<String> row = FXCollections.observableArrayList();
 				
 				for (int i = 1; i <= 2; i++)
 				{
 					row.add(rs.getString(i));
		 				if(i == 1)
		 					break;
 				}
 				
 				data.add(row);
 			}
 			listPlayersInfoTable.setItems(data);
 	}
 	catch (Exception e)
 	{
 		e.printStackTrace();
 	}
 }
 	
 	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 1) //Checking double click
	    {
	    	String playerName = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(0).toString();
	    	selectedPlayer.setText(playerName);
	    }
	}
    

}




