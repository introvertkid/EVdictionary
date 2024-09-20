package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<String>myListView;

    @FXML
    private ImageView settingButton;

    private Parent root;
    private Scene scene;
    private Stage primaryStage;

    String[] food={"pizza", "kfc", "ramen"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        myListView.getItems().addAll(food);
    }

    @FXML
    public void showSettingScene(MouseEvent mouseEvent)
    {
        root= (Parent) loadFXML("SettingScene");
        primaryStage=loadCurrentStage(mouseEvent);
        scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Object loadFXML(String name)
    {
        String url="/FXML/" + name + ".fxml";
        Object temp=new Object();
        try{
            temp=FXMLLoader.load(this.getClass().getResource(url));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return temp;
    }

    private static Stage loadCurrentStage(MouseEvent mouseEvent)
    {
        return (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
    }
}
