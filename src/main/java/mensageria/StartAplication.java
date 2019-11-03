package mensageria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class StartAplication extends Application {
    public Stage window;
    public TextField textFieldFullName;
    public Scene scene1, scene2;
    public Button buttonInit;

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResource("/layout/main.fxml"));

        scene1 = new Scene(root);

//        Parent root2 = loader.load(getClass().getResource("main-layout.fxml"));
//        scene2 = new Scene(root2);
        window.setScene(scene2);


        window.setTitle("Super Chat");
        window.setScene(scene1);

        window.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
