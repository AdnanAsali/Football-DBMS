package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageController 
{

    @FXML
    private Pane Pane;
    @FXML
    private JFXButton coaching;
    @FXML
    private JFXButton MyTeam;
    @FXML
    private JFXButton Media;
    @FXML
    private JFXButton Tactics;
    @FXML
    private JFXButton Ads;
    @FXML
    private JFXButton Data_entry;
    @FXML
    private JFXButton Purchase_Players;
    @FXML
    private JFXButton Ultimate_Team;
    @FXML
    private JFXButton Players_Performance;
    @FXML
    private JFXButton Stadium;
    @FXML
    private JFXButton Upcoming_events;
    @FXML
    private JFXButton About;
    @FXML
    private JFXButton exit;
    @FXML
    private ImageView hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox DataPane;

    
    @FXML
    void initialize(MouseEvent event)
    {
    	//AnchorPane DataPane = null;
    	
		try {
			DataPane = FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	drawer.setSidePane(DataPane);
    	
    	if(drawer.isOpened())
    		drawer.close();
    	else
    		drawer.open();
    }

    
    @FXML
    void MyTeamAction(ActionEvent event)
    {
    	Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	Parent root = null;
		try
		{
			root = FXMLLoader.load(getClass().getResource("MyTeam.fxml"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
    }
    
    @FXML
    void Data_entryAction(ActionEvent event) throws IOException
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
		exitAction(event);
    }
    

    @FXML
    void coachingAction(ActionEvent event) 
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CoachingGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load page please try again");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		exitAction(event);
    }
    

    @FXML
    void MediaAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MediaGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load page please try again");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		exitAction(event);
    }

   
    @FXML
    void Purchase_PlayersAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PurchaseGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load Page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		exitAction(event);

    }

    @FXML
    void Ultimate_TeamAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UltimateGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load Page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		exitAction(event);
    }

    @FXML
    void stadiumAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateGUI.fxml"));
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
		exitAction(event);
    }
    
    @FXML
    void Upcoming_eventsAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpcomingEventsGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("cant load page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		exitAction(event);
    }
    
    @FXML
    void aboutAction(ActionEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Can't Load Page please try again ");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		exitAction(event);
    }
    
    @FXML
    void exitAction(ActionEvent event)
    {
    	Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	window.close();
    }
    
}
