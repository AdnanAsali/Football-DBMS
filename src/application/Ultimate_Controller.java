package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jfoenix.controls.JFXDrawer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Ultimate_Controller
{
	@FXML
    private Pane DataPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView hamburger;
    @FXML
    //3
    private Label forward;
    @FXML
    private Label rightForward;
    @FXML
    private Label leftForward;
    
    //3
    @FXML
    private Label rightWingMid;
    @FXML
    private Label leftWingMid;
    @FXML
    private Label midMid;
    
    //4
    @FXML
    private Label backHalfRight;
    @FXML
    private Label backHalfLeft;
    @FXML
    private Label backLeft;
    @FXML
    private Label backRight;
    @FXML
    private Label gk;


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
    
    
    public void initialize() throws ClassNotFoundException, SQLException 
	{
    	fillUltimateTeam();
	}
    
    
    public void fillUltimateTeam() throws ClassNotFoundException, SQLException
    {
    	dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);
		
		SQL = "select * from playersInfo";
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Statement stmt = con.createStatement();
		
		
		//3
		ResultSet rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'forward' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); forward.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'right forward' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); rightForward.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'left forward' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); leftForward.setText(rs.getString(1));}
		
		
		//3
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'left midfielder' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); leftWingMid.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'right midfielder' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); rightWingMid.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'midfielder' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); midMid.setText(rs.getString(1));}

		
		//4
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'right stopper' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); backHalfRight.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'left stopper' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); backHalfLeft.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'right back' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); backRight.setText(rs.getString(1));}
		
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'left back' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); backLeft.setText(rs.getString(1));}
		
		
		//GK
		rs = stmt.executeQuery("select name, speed \r\n" + 
				"from \r\n" + 
				"	(select name, speed \r\n" + 
				"			from playersinfo \r\n" + 
				"				where position = 'goalkeeper' \r\n" + 
				"					group by name) as beta \r\n" + 
				"						group by name \r\n" + 
				"						having max(speed) > 90 	\r\n" + 
				"						order by speed desc\r\n" + 
				"                        limit 1; ");
		while(rs.next()) {  System.out.println(rs.getString(1)); gk.setText(rs.getString(1));}
		
		
		
    }
    
}
