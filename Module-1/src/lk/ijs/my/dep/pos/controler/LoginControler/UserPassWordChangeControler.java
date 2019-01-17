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

public class UserPassWordChangeControler {

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtun;
    @FXML
    private PasswordField txtCp;
    @FXML
    private PasswordField txtNP;
    @FXML
    private PasswordField txtp;

    public   static String Username;
    public   static String pasword;
    public static String userType;

    public static ArrayList<SystemUsers> users;


    public void initialize() {

        if (users == null) {
            users = new ArrayList<>();
            users.add(new SystemUsers("SuperUser", "SuperUser", pasword, "SuperUser", "Y")); //String userType, String userName, String password, String name, String isActive
        }

    }

    public void OnActionbtnUpdate(ActionEvent actionEvent) {


        if(txtun.getText().trim().equals("")||txtp.getText().trim().equals("")||txtNP.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "Should fill relevent Fields !", ButtonType.OK).showAndWait();
            txtun.setText("");
            txtun.requestFocus();
            return;
        }


        if(!pasword.equals(txtp.getText())){
            new Alert(Alert.AlertType.ERROR, "Existing Password Not Match !", ButtonType.OK).showAndWait();
            txtp.setText("");
            txtp.requestFocus();
            return;
        }

        if(!txtNP.getText().equals(txtCp.getText())){
            new Alert(Alert.AlertType.ERROR, "New Password Not Match !", ButtonType.OK).showAndWait();
            txtNP.setText("");
            txtNP.requestFocus();
            return;
        }


        ArrayList<SystemUsers>  temp = new ArrayList<>();

        for (SystemUsers s:users ) {

            if(s.getName().equals(userType)&&s.getPassword().equals(pasword)){
                temp.add(new SystemUsers(s.getUserName(),s.getUserType(),txtNP.getText(),s.getName(),s.getIsActive()));
                pasword=txtNP.getText();
                continue;
            }

            temp.add(s);
        }
        new Alert(Alert.AlertType.CONFIRMATION, "Password Change !", ButtonType.OK).showAndWait();
        txtNP.setText("");
        txtp.setText("");
        txtun.setText("");
        txtCp.setText("");

        users=temp;


    }

    public void OnActionbtnBack(ActionEvent actionEvent) throws IOException {


        HomePageControler.users=users;
        HomePageControler.password=pasword;
       // HomePageControler.
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);

    }
}
