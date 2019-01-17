package lk.ijs.my.dep.pos.controler.LoginControler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.controler.HomePageControler;
import lk.ijs.my.dep.pos.model.Login.SystemUsers;

import java.io.IOException;
import java.util.ArrayList;

public class LoginPageControler {
    @FXML
    private Button btnExit;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;


    private String useName ="SuperUser";
    public static  String pw =null;
    private  String password ="123";

    public static  ArrayList<SystemUsers> users ;
    public static String userType;

    @FXML
    private void onActionLogin(ActionEvent actionEvent) throws IOException {


        if(users!=null){



            HomePageControler.username = useName;
            HomePageControler.password = password;


            for (SystemUsers s:users ) {

                if(s.getUserName().equals(txtUserName.getText())&& txtPassword.getText().equals(s.getPassword())){

                    HomePageControler.username = s.getUserType();
                    HomePageControler.password = s.getPassword();
                    HomePageControler.userType=s.getUserName();////**********////


                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                    primaryStage.setScene(scene);

                    return;

                }
              //  new Alert(Alert.AlertType.ERROR, s.getPassword()+"----!"+s.getUserType(), ButtonType.OK).showAndWait();

            }



            new Alert(Alert.AlertType.ERROR, "Check Your User Name And PassWord !", ButtonType.OK).showAndWait();







        }else {


            if (pw == null) {

                if (useName.equals(txtUserName.getText()) && password.equals(txtPassword.getText())) {
                    HomePageControler.username = useName;
                    HomePageControler.password = password;
                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                    primaryStage.setScene(scene);


                } else {

                    new Alert(Alert.AlertType.ERROR, "Check Your User Name And PassWord !", ButtonType.OK).showAndWait();
                    txtPassword.setText("");
                    txtUserName.setText("");
                    txtUserName.requestFocus();


                }

            } else {
                if (useName.equals(txtUserName.getText()) && pw.equals(txtPassword.getText())) {
                    HomePageControler.username = useName;
                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                    primaryStage.setScene(scene);

                } else {
                    new Alert(Alert.AlertType.ERROR, "Check Your User Name And PassWord !", ButtonType.OK).showAndWait();
                    txtPassword.setText("");
                    txtUserName.setText("");
                    txtUserName.requestFocus();
                }
            }
        }
    }

    public void OnActionPassword(ActionEvent actionEvent) {
        btnLogin.requestFocus();
    }

    public void OnActionUserName(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void OnActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void initialize(){

        txtUserName.requestFocus();

    }

}
