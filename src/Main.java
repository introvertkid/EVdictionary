import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Tải giao diện từ file main.fxml
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

            // Tạo Scene và thêm giao diện vào Scene
            Scene scene = new Scene(root, 300, 200);

            // Thiết lập Stage (cửa sổ)
            primaryStage.setTitle("Hello World App");
            primaryStage.setScene(scene);

            // Hiển thị cửa sổ
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Khởi chạy ứng dụng JavaFX
        launch(args);
    }
}
