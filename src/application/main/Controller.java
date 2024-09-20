package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<String>myListView = new ListView<>();

    String currentFood;
    String[] food={"pizza", "kfc", "ramen"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        myListView.getItems().addAll(food);
        if(myListView==null)
        {
            System.out.println("NULL !!!");
        }
    }

    @FXML
    public void showSettingScene()
    {

    }
}
