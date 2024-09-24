package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<String> targetList;

    @FXML
    private WebView myWebView;

    private Parent root;
    private Scene scene;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Dictionary words = Main.dictionary;
        for (int i = 0; i < words.getSize(); i++)
        {
            targetList.getItems().add(words.get(i).getTarget());
        }
        //add listener to targetList for loading definition
        targetList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
        {
            String definition = Main.trie.search(newValue).meaning;
            myWebView.getEngine().loadContent(definition);
        });
    }

    @FXML
    public void showSettingScene(MouseEvent mouseEvent)
    {
        root = (Parent) loadFXML("SettingScene");
        primaryStage = loadCurrentStage(mouseEvent);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Object loadFXML(String name)
    {
        String url = "/FXML/" + name + ".fxml";
        Object obj = new Object();
        try {
            obj = FXMLLoader.load(this.getClass().getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Stage loadCurrentStage(MouseEvent mouseEvent)
    {
        return (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    }
}
