package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Update_Controller implements Initializable 
{
	
	@FXML
	public Pane DataPane;
    @FXML
    public JFXTextField idfield;
    @FXML
    public JFXTextField travelfield;
    @FXML
    public JFXTextField namefield;
    @FXML
    private JFXTextField salaryfield;
    @FXML
    public JFXTextField nationfield;
    @FXML
    public JFXTextField joinfield;
    @FXML
    public JFXTextField levelfield;
    @FXML
    public JFXTextField hourfield;
    @FXML
    public JFXTextField deptfield;
    @FXML
    public JFXTextField tablefield;
    @FXML
    public JFXButton addbutton;
    @FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label labelpersonal;
    @FXML
    private Label medialabel;
    @FXML
    private JFXTextField facebook;
    @FXML
    private JFXTextField twitter;
    @FXML
    private JFXTextField news;
    @FXML
    private JFXTextField moneyPerAd;
    @FXML
    private ImageView facebookimg;
    @FXML
    private JFXTextField worth;
    @FXML
    private JFXTextField speed;
    @FXML
    private JFXTextField height;
    @FXML
    private JFXTextField rating;
    @FXML
    private JFXTextField position;
    @FXML
    private Label perflabel;
    @FXML
    private JFXTextField weight;
    @FXML
    private JFXTextField moneyWonChamp;
    @FXML
    private JFXTextField wons;
    @FXML
    private JFXTextField location;
    @FXML
    private JFXDatePicker date;
    @FXML
    private Label champlabel;
    @FXML
    private JFXTextField losses;
    @FXML
    private JFXTextField products;
    @FXML
    private Label adsLabel;
    @FXML
    private JFXTextField namesOfCompanies;
    @FXML
    private JFXComboBox<String> deptCombo;
    @FXML
    private JFXComboBox<String> posCombo;
    @FXML
    private VBox drawerBox;
    private ObservableList<ObservableList> data;
	@FXML
	private TableView<ObservableList> listPlayersInfoTable;
	 
	
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
    void AddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException
    {
    	
    	
		
		//Section of Personal Information In Data Entry Page
		String name = namefield.getText();	
		String nationality = nationfield.getText();
		String department = deptCombo.getValue();
		int joinedSince = Integer.parseInt(joinfield.getText());;
		double salary = Double.parseDouble(salaryfield.getText());
		int workHours = Integer.parseInt(hourfield.getText());
		int TravelFee = Integer.parseInt(travelfield.getText());
		int level = Integer.parseInt(levelfield.getText());

		//Section Of Performance in Data Entry page
		int speedPlayer = Integer.parseInt(speed.getText());
		double heightPlayer = Double.parseDouble(height.getText());
		double weightPlayer = Double.parseDouble(weight.getText());
		String pos = posCombo.getValue();
		int rate = Integer.parseInt(rating.getText());
		double worthPlayer = Double.parseDouble(worth.getText());
		
		//Section of Championships in Data Entry page
		int NumOfWons = Integer.parseInt(wons.getText());
		int NumOfLosses = Integer.parseInt(losses.getText());
		String loc = location.getText();
		LocalDate dateSql = date.getValue();
		double moneyWon = Double.parseDouble(moneyWonChamp.getText());
		
		//Section Of Media in Data Entry Page
		String face = facebook.getText();
		String twit = twitter.getText();
		String newsPaper = news.getText();

		//Section Of Ads in Data Entry Page
		String product = products.getText();
		String companyNames = namesOfCompanies.getText();
		double adsMoney = Double.parseDouble(moneyPerAd.getText());
		
		
		
		
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
    	
		
		SQL = "update playersInfo set "
				+ " salary =" + salary
				+ ", nationality = " + "\"" + nationality + "\""
				+ ", workhours = " + workHours
				+ ", joinedsince = " + joinedSince
				+ ", travelfee = " + TravelFee
				+ ", level = " + level 
				+ ",department = " + "\"" + department + "\""
				+ ", name = " +"\"" + name+ "\""
				+ ", products = " +"\"" + product + "\"" 
				+ ", namesOfCompanies = " +"\"" + companyNames + "\"" 
				+ ", moneyPerAd = " + adsMoney
				+ ", newspaper = " +"\"" + newsPaper + "\""
				+ ", facebook = " +"\"" + face + "\""
				+ ", twitter = " +"\"" + twit + "\""
				+ ", location = " +"\"" + loc + "\""
				+ ", date = " +"\"" + dateSql + "\""
				+ ", wons = " + NumOfWons
				+ ", losses = " + NumOfLosses
				+ ", moneyWon = "+ moneyWon
				+ ", speed = " + speedPlayer
				+ ", height = " + heightPlayer
				+ ", weight = " + weightPlayer
				+ ", position = " +"\"" + pos + "\""
				+ ", rating = " + rate 
				+ ", worth = " + worthPlayer
				+ ", type = " +"\"" + department + "\""
				+ " where name = \'" + playerName + "\'";
		
//    	SQL = "insert into " + "playersInfo" + "(id , salary, nationality, workhours,joinedsince, travelfee , level,department, name,products, namesOfCompanies, moneyPerAd, newspaper, facebook, twitter, location, date, wons, losses, moneyWon, speed, height, weight, position, rating, worth, type)" 
//    											+"values(null,"
//    											//+ id + ", " 
//    											+ salary + ", " 
//    											+ "\"" + nationality + "\"" + ", " 
//    											+ workHours +", " 
//    											+ joinedSince + ", " 
//    											+ TravelFee + ", " 
//    											+ level + ", " 
//    											+"\"" + department + "\""  + ", "
//    											+"\"" + name+ "\"" + ", " 
//    											+"\"" + product + "\""  + ", "
//    											+"\"" + companyNames + "\""  + ", "
//    											+ adsMoney + ", " 
//    											+"\"" + newsPaper + "\"" + ", "
//    											+"\"" + face + "\""      	+ ", "	
//    											+"\"" + twit + "\""  + ", "
//    											+"\"" + loc + "\""+ ", "
//    											+"\"" + dateSql + "\""+ ", "
//    											+ NumOfWons + ", "
//    											+ NumOfLosses+ ", "
//    											+ moneyWon + ", "
//    											+ speedPlayer + ", "
//    											+ heightPlayer + ", "
//    											+ weightPlayer + ", "
//    											+"\"" + pos + "\"" + ", "
//    											+ rate + ", "
//    											+ worthPlayer + ", "
//    											+"\"" + department + "\""  + ");"
//    											;
		stmt.executeUpdate(SQL);
    }



	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		deptCombo.getItems().add("Primary Player");
		deptCombo.getItems().add("Secondary Player");
		deptCombo.getItems().add("Injured Player");
		deptCombo.getItems().add("Substitution Player");
		
		//3
		posCombo.getItems().add("Forward");
		posCombo.getItems().add("Right Forward");
		posCombo.getItems().add("Left Forward");
		
		//3
		posCombo.getItems().add("Right Midfielder");
		posCombo.getItems().add("Left Midfielder");
		posCombo.getItems().add("Midfielder");
		
		//4
		posCombo.getItems().add("Right Back");
		posCombo.getItems().add("Left Back");
		posCombo.getItems().add("Right Stopper");
		posCombo.getItems().add("Left Stopper");
		
		posCombo.getItems().add("Goalkeeper");
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
	 				
	 				for (int i = 1; i <= 1; i++)
	 				{
	 					row.add(rs.getString(i));
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
	 	
	 	String playerName;
	 	
	 	@FXML
		public void clickItem(MouseEvent event) throws ClassNotFoundException, SQLException
		{
		    if (event.getClickCount() == 1) //Checking double click
		    {
		    	playerName = listPlayersInfoTable.getSelectionModel().getSelectedItem().get(0).toString();
		    	
		    

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
			 			ResultSet rs = stmt.executeQuery("select * from playersinfo where name = " + "\'" + playerName + "\'");
		 			
		 			while(rs.next())
		 			{
		 				
		 				for (int i = 1; i <= 27; i++)
		 				{
		 					System.out.println(rs.getString(i));
		 				}
		 				
		 				namefield.setText(playerName);
		 				salaryfield.setText(rs.getString(2));
		 				nationfield.setText(rs.getString(3));
		 				hourfield.setText(rs.getString(4));
		 				joinfield.setText(rs.getString(5));
		 				travelfield.setText(rs.getString(6));
		 				levelfield.setText(rs.getString(7));
		 				deptCombo.setValue(rs.getString(8));
		 				products.setText(rs.getString(10));
		 				namesOfCompanies.setText(rs.getString(11));
		 				moneyPerAd.setText(rs.getString(12));
		 				news.setText(rs.getString(13));
		 				facebook.setText(rs.getString(14));
		 				twitter.setText(rs.getString(15));
		 				location.setText(rs.getString(16));
		 				
		 				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 				date.setValue(LocalDate.parse(rs.getString(17), formatter));
		 				wons.setText(rs.getString(18));
		 				losses.setText(rs.getString(19));
		 				moneyWonChamp.setText(rs.getString(20));
		 				speed.setText(rs.getString(21));
		 				weight.setText(rs.getString(23));
		 				height.setText(rs.getString(22));
		 				posCombo.setValue(rs.getString(24));
		 				rating.setText(rs.getString(25));
		 				worth.setText(rs.getString(26));
		 			}
			 	}
			 	catch (Exception e)
			 	{
			 		e.printStackTrace();
			 	}
	 			
	 			
	 			
		    }
		}
}


