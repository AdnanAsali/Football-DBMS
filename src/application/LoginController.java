package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController
{

	 	@FXML
	    private Pane Pane;
	    @FXML
	    private ImageView bg;
	    @FXML
	    private Label goodtoseeu;
	    @FXML
	    private Label hello;
	    @FXML
	    private Button Letsgo;
	    @FXML
	    private ImageView man;
	    @FXML
	    private ImageView key;
	    @FXML
	    private Button logInBut;
	    @FXML
	    private CheckBox checkB;
	    @FXML
	    private ImageView close;
	    @FXML
	    private TextField username;
	    @FXML
	    private PasswordField password;
	    @FXML
	    private Button registerButton;
	    
	    
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
    void close(MouseEvent event)
    {
    	Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	window.close();
    }


    @FXML
    void KeepMeSignedIn(ActionEvent event)
    {

    }

    
    
    @FXML
    void registerAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException
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
    		
    		SQL = "insert into login (username , password) values(" +"\"" + username.getText() + "\"" + ", "+ "\"" + password.getText() + "\""  + ");";
    		stmt.executeUpdate(SQL);
    		
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Scene scene = new Scene(root1);
    		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    		window.setScene(scene);
    		window.setFullScreen(true);
    		window.show();
    }
    
    
    
    @FXML
    void logInOnAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException
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
    		
    		SQL = "select username, password from login where username = " + "\'" + username.getText() + "\'" ;
    		ResultSet rs = stmt.executeQuery(SQL);
    		rs.beforeFirst();
    		
    		while(rs.next())
	    		if(rs.getString(1).equals(username.getText()) && rs.getString(2).equals(password.getText()) )
	    		{
	        			System.out.println("successful registration");
		        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
		        		Parent root1 = (Parent) fxmlLoader.load();
		        		Scene scene = new Scene(root1);
		        		Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		        		window.setScene(scene);
		        		window.setFullScreen(true);
		        		window.show();
	    		}
	    		else
	    		{
	    			System.out.println("test error");
	    		}
    	}
    }

