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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Purchase_Controller 
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
    

    @FXML
    private JFXButton dataEntryRedirection;

    @FXML
    void dataEntryRedirectionAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Data_Entry_GUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Exception at ListPrescriptionsUI.fxml");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
    }

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
    void googleLoader(ActionEvent event) 
	{
		WebEngine engine = webView.getEngine();
		engine.load("https://www.ranker.com/list/best-current-soccer-players/ranker-sports");
    }


    ActionEvent event;
    
 	public void initialize()
 	{
 		googleLoader(event);
 	}
    
 	

}



