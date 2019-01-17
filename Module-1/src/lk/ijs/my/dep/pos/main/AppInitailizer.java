package lk.ijs.my.dep.pos.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitailizer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/LoginPage.fxml"));
        Parent root = loader.load();

//        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Login Page ");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        Image img =new Image("/lk/ijs/my/dep/pos/resources/MAIN.png");
        primaryStage.getIcons().add(img);
        
        primaryStage.show();



    }

}
