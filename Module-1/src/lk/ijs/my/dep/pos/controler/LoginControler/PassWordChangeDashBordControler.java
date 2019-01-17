package lk.ijs.my.dep.pos.controler.LoginControler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.model.Login.SystemUsers;

import java.io.IOException;
import java.util.ArrayList;

public class PassWordChangeDashBordControler {
    //public static final String Username = ;
    @FXML
    private Button btnAdministration;
    @FXML
    private Button btnUserCreation;
    @FXML
    private Button btnPassWordChange;
    @FXML
    private Button btnBack;

    public   static String Username;
    public   static String pasword;
    public static String userType;

    public static ArrayList<SystemUsers>users;


    public void initialize(){

        if(Username.equals("SuperUser")){

            btnAdministration.setDisable(false);
            btnUserCreation.setDisable(false);
            btnUserCreation.setDisable(false);
        }else if (Username.equals("Admin")) {
            btnUserCreation.setDisable(false);
            btnPassWordChange.setDisable(false);
            btnAdministration.setDisable(true);
        }else if (Username.equals("User")){
            btnPassWordChange.setDisable(false);
            btnAdministration.setDisable(true);
            // btnUserCreation.setDisable(true);
            btnUserCreation.setDisable(true);
        }else {
            btnAdministration.setDisable(true);
             btnPassWordChange.setDisable(true);
            btnUserCreation.setDisable(true);

        }


    }

    @FXML
    private void OnActionAdministration(ActionEvent actionEvent) throws IOException {

        if(Username!="SuperUser"){
            new Alert(Alert.AlertType.ERROR,"You Can't Access this Option !", ButtonType.OK).showAndWait();
            return;
        }

        SuperUserAdministration.superUserPassword=pasword;
        SuperUserAdministration.username=Username;
        SuperUserAdministration.users=users;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/SuperUserAdministration.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnUserCreation.getScene().getWindow();
        primaryStage.setScene(scene);


    }

    @FXML
    private void OnActionbtnUserCreation(ActionEvent actionEvent) throws IOException {

        SystemUserManageControler.users=users;
        SystemUserManageControler.pasword=pasword;
        SystemUserManageControler.Username=Username;
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/SystemUsers.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnUserCreation.getScene().getWindow();
        primaryStage.setScene(scene);


    }

    @FXML
    private void onActionbtnPassWordChange(ActionEvent actionEvent) throws IOException {


        UserPassWordChangeControler.pasword=pasword;
        UserPassWordChangeControler.users=users;
        UserPassWordChangeControler.userType=userType;
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/UserPasswordChange.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnUserCreation.getScene().getWindow();
        primaryStage.setScene(scene);

    }

    @FXML
    private void OnActionbtnBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnUserCreation.getScene().getWindow();
        primaryStage.setScene(scene);

    }
}
