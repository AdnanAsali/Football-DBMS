package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField idfield;

    @FXML
    private TextField travelfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField salaryfield;

    @FXML
    private TextField nationfield;

    @FXML
    private TextField joinfield;

    @FXML
    private TextField levelfield;

    @FXML
    private TextField hourfield;

    @FXML
    private TextField deptfield;

    @FXML
    private TextField tablefield;

    @FXML
    private Button addbutton;

    
    //Instance Variables for the text fields
   
    
	
    @FXML
    void AddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException
    {
    		String table = tablefield.getText();
    	 	int id = Integer.parseInt(idfield.getText());
    		int salary = Integer.parseInt(salaryfield.getText());
    		String nationality = nationfield.getText();
    		int workHours = Integer.parseInt(hourfield.getText());
    		int joinedSince = Integer.parseInt(joinfield.getText());
    		int TravelFee = Integer.parseInt(travelfield.getText());
    		int level = Integer.parseInt(levelfield.getText());
    		String department = deptfield.getText();
    		String name = namefield.getText();
    		
    	System.out.println("TEST");
		String dbURL;
		String dbUsername = "root";
		String dbPassword = "root";
		String URL = "127.0.0.1";
		String port = "3306";
		String dbName = "FootballDBMS";
		Connection con;
		String SQL;

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);
    	
		//insert into student values(1160076 , '0542175812' , 'Adnan' , '1998-07-18' , 'Computer Science');
    	SQL = "insert into " + table + "(id , salary , nationality, workhours,joinedsince, travelfee , level,department, name)" +"values(" + id
    											+ ", " + salary + ", " + nationality + ", " 
    											 + workHours +", " + joinedSince + ", " + TravelFee + ", " + level + ", " + department + ", " + name + ")";
		Statement stmt = con.createStatement();
		int rs = stmt.executeUpdate(SQL);
//		int numCols = rs.getMetaData().getColumnCount();
//		int j = 0;
//
//		while (rs.next()) {
//			for (int i = 1; i <= numCols; i++) {
//
//				System.out.print(rs.getString(i) + " ");
//			} // end for
//			j++;
//			System.out.println("");
//		} // end while

    }

}

