package application;

import java.io.IOException;

import com.jfoenix.controls.JFXDrawer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Coaching_Controller {

	@FXML
    private Pane DataPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView hamburger;
    @FXML
    private Rectangle rect1;
    @FXML
    private Rectangle rect2;
    @FXML
    private Rectangle rect3;
    @FXML
    private Rectangle rect4;

    
    @FXML
    void initialize(MouseEvent event)
    {
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
    void changeColor(MouseEvent event)
    {
    	rect1.setOnMouseEntered( e -> rect1.setStroke(Color.AQUA));
    	rect1.setOnMouseExited(e -> rect1.setStroke(Color.BLACK));
    	
    	rect2.setOnMouseEntered( e -> rect2.setStroke(Color.AQUA));
    	rect2.setOnMouseExited(e -> rect2.setStroke(Color.BLACK));
    	
    	rect3.setOnMouseEntered( e -> rect3.setStroke(Color.AQUA));
    	rect3.setOnMouseExited(e -> rect3.setStroke(Color.BLACK));
    	
    	rect4.setOnMouseEntered( e -> rect4.setStroke(Color.AQUA));
    	rect4.setOnMouseExited(e -> rect4.setStroke(Color.BLACK));
    }
    
    
    @FXML
    void tacticsRectAction(MouseEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TacticsGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		Stage window2 = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	window2.close();
    }
    
    @FXML
    void performanceRectAction(MouseEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PerformenceGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		Stage window2 = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	window2.close();
    }

    @FXML
    void trainingRectAction(MouseEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainingGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		Stage window2 = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	window2.close();
    }
    
    @FXML
    void ratingRectAction(MouseEvent event)
    {
    	Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingGUI.fxml"));
		Parent root = null;

		try
		{
			root = loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Cant load page");
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setFullScreen(true);
		window.show();
		Stage window2 = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    	window2.close();
    }
    

}
