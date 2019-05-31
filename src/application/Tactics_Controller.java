package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Tactics_Controller {

    @FXML
    private Pane DataPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView hamburger;
    @FXML
    private Circle dp1;
    @FXML
    private Circle dp2;
    @FXML
    private Circle gk;
    @FXML
    private Circle dp4;
    @FXML
    private Circle dp5;
    @FXML
    private Circle dp3;
    @FXML
    private Circle mp1;
    @FXML
    private Circle mp2;
    @FXML
    private Circle mp4;
    @FXML
    private Circle mp5;
    @FXML
    private Circle mp3;
    @FXML
    private Circle ap1;
    @FXML
    private Circle ap2;
    @FXML
    private Circle ap4;
    @FXML
    private Circle ap5;
    @FXML
    private Circle ap3;
    @FXML
    private Label basicFormation1;
    @FXML
    private Label basicFormation2;
    @FXML
    private Label basicFormation3;
    @FXML
    private Label basicFormation4;
    @FXML
    private Label basicFormation5;
    @FXML
    private JFXButton resetFormationButton;
    @FXML
    private JFXButton saveFormationButton;
    @FXML
    private Label finalFormation;
    
    int defense;
    int middle;
    int attack;
    int clicks;
    
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
    	
    	//finalFormation.setText("Your Final Formation is: " + defense +"-"+ middle +"-"+ attack + " clicks" + clicks);
    }

    @FXML
    void playerOpacityChangerAction(MouseEvent event)
    {
    	//Defense
    	dp1.setOnMouseClicked(e ->
    	{
    		dp1.setOpacity(1);
    		defense++;
    		clicks++;
    	});
    	dp2.setOnMouseClicked(e ->
    	{
    		dp2.setOpacity(1);
    		defense++;
    		clicks++;
    	});
    	dp3.setOnMouseClicked(e -> {
    		dp3.setOpacity(1);
    		defense++;
    		clicks++;
    	});
    	dp4.setOnMouseClicked(e -> {
    		dp4.setOpacity(1);
    		defense++;
    		clicks++;
    	});
    	dp5.setOnMouseClicked(e -> {
    		dp5.setOpacity(1);
    		defense++;
    		clicks++;
    	});
    	
    	
    	//Middle
    	mp1.setOnMouseClicked(e -> {
    		mp1.setOpacity(1);
    		middle++;
    		clicks++;
    	});
    	mp2.setOnMouseClicked(e -> {
    		mp2.setOpacity(1);
    		middle++;
    		clicks++;
    	});
    	mp3.setOnMouseClicked(e -> {
    		mp3.setOpacity(1);
    		middle++;
    		clicks++;
    	});
    	mp4.setOnMouseClicked(e -> {
    		mp4.setOpacity(1);
    		middle++;
    		clicks++;
    	});
    	mp5.setOnMouseClicked(e -> {
    		mp5.setOpacity(1);
    		middle++;
    		clicks++;
    	});
    	
    	
    	//Attack
    	ap1.setOnMouseClicked(e -> {
    		ap1.setOpacity(1);
    		attack++;
    		clicks++;
    	});
    	ap2.setOnMouseClicked(e -> {
    		ap2.setOpacity(1);
    		attack++;
    		clicks++;
    	});
    	ap3.setOnMouseClicked(e -> {
    		ap3.setOpacity(1);
    		attack++;
    		clicks++;
    	});
    	ap4.setOnMouseClicked(e -> {
    		ap4.setOpacity(1);
    		attack++;
    		clicks++;
    	});
    	ap5.setOnMouseClicked(e -> {
    		ap5.setOpacity(1);
    		attack++;
    		clicks++;
    	});

    }
    
    @FXML
    void makeBasicFormationAction(MouseEvent event)
    {

    	basicFormation1.setOnMouseClicked(e ->
    	{
    		String formation1 = basicFormation1.getText();
    		int defVal = Integer.parseInt(formation1.substring(0, 1));
    		int midVal = Integer.parseInt(formation1.substring(2, 3));
    		int attVal = Integer.parseInt(formation1.substring(4, 5));
    		finalFormation.setText("Your Teams's Basic Formation is: " + defVal +"-"+ midVal +"-"+ attVal);
    	});
    	
    	basicFormation2.setOnMouseClicked(e ->
    	{
	    	String formation2 = basicFormation2.getText();
	    	int defVal2 = Integer.parseInt(formation2.substring(0, 1));
	    	int midVal2 = Integer.parseInt(formation2.substring(2, 3));
	    	int attVal2 = Integer.parseInt(formation2.substring(4, 5));
	    	finalFormation.setText("Your Teams's Basic Formation is: " + defVal2 +"-"+ midVal2 +"-"+ attVal2);
    	});
    	
    	basicFormation3.setOnMouseClicked(e ->
    	{
	    	String formation3 = basicFormation3.getText();
	    	int defVal3 = Integer.parseInt(formation3.substring(0, 1));
	    	int midVal3 = Integer.parseInt(formation3.substring(2, 3));
	    	int attVal3 = Integer.parseInt(formation3.substring(4, 5));
	    	finalFormation.setText("Your Teams's Basic Formation is: " + defVal3+"-"+ midVal3 +"-"+ attVal3);
    	});
    	
    	basicFormation4.setOnMouseClicked(e ->
    	{
	    	String formation4 = basicFormation4.getText();
	    	int defVal4 = Integer.parseInt(formation4.substring(0, 1));
	    	int midVal4 = Integer.parseInt(formation4.substring(2, 3));
	    	int attVal4 = Integer.parseInt(formation4.substring(4, 5));
	    	finalFormation.setText("Your Teams's Basic Formation is: " + defVal4 +"-"+ midVal4 +"-"+ attVal4);
    	});
    	
    	basicFormation5.setOnMouseClicked(e -> 
    	{
	    	String formation5 = basicFormation5.getText();
	    	int defVal5 = Integer.parseInt(formation5.substring(0, 1));
	    	int midVal5 = Integer.parseInt(formation5.substring(2, 3));
	    	int attVal5 = Integer.parseInt(formation5.substring(4, 5));
	    	finalFormation.setText("Your Teams's Basic Formation is: " + defVal5 +"-"+ midVal5 +"-"+ attVal5);
    	});
    }
    
    

    @FXML
    void resetAction(ActionEvent event) 
    {
    	defense = 0;
    	middle = 0;
    	attack = 0;
    	clicks = 0;
    	
    	//Resetting Defense
    	dp1.setOpacity(0.17);
    	dp2.setOpacity(0.17);
    	dp3.setOpacity(0.17);
    	dp4.setOpacity(0.17);
    	dp5.setOpacity(0.17);
    	gk.setOpacity(0.17);
    	
    	//Resetting Middle
    	mp1.setOpacity(0.17);
    	mp2.setOpacity(0.17);
    	mp3.setOpacity(0.17);
    	mp4.setOpacity(0.17);
    	mp5.setOpacity(0.17);
    	
    	//Resetting Attack
    	ap1.setOpacity(0.17);
    	ap2.setOpacity(0.17);
    	ap3.setOpacity(0.17);
    	ap4.setOpacity(0.17);
    	ap5.setOpacity(0.17);
    	
    }
    
    @FXML
    void saveAction(ActionEvent event) 
    {
    	if(clicks < 9)
    		finalFormation.setText("You need to select 10 players as a formation");
    	else
    		finalFormation.setText("Your Teams's Final Formation is: " + defense +"-"+ middle +"-"+ attack);
    }


    

}
