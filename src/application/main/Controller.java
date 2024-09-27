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
    private ListView<String>myListView;

    @FXML
    private WebView myWebView;

    private Parent root;
    private Scene scene;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Dictionary words=Main.dictionary;
        for(int i=0;i<words.getSize();i++)
        {
            Word currentWord=words.get(i);
            String target=currentWord.getTarget();
            String definition=currentWord.getExplain();
            myListView.getItems().add(target);
        }
        String test="<html><i>-manship</i><br/><ul><li><font color='#cc0000'><b> hình thái ghép có nghĩa tài nghệ</b></font></li></ul></html>";
        myWebView.getEngine().loadContent(test);
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

    public Object loadFXML(String name)
    {
        String url="/FXML/" + name + ".fxml";
        Object obj=new Object();
        try{
            obj=FXMLLoader.load(this.getClass().getResource(url));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return obj;
    }

    public static Stage loadCurrentStage(MouseEvent mouseEvent)
    {
        System.out.println(mouseEvent.getSource());
        System.out.println(((Node) mouseEvent.getSource()).getScene());
        System.out.println(((Node)mouseEvent.getSource()).getScene().getWindow());
        return (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
    }

    @FXML
    public void showBookmarkScene(MouseEvent mouseEvent)
    {
        root= (Parent) loadFXML("BookmarkScene");
        primaryStage=loadCurrentStage(mouseEvent);
        scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void showBaseScene(MouseEvent mouseEvent)
    {
        root= (Parent) loadFXML("BaseScene");
        primaryStage=loadCurrentStage(mouseEvent);
        scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
