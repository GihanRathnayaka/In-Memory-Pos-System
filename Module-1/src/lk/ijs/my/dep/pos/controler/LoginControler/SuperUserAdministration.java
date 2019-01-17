package lk.ijs.my.dep.pos.controler.LoginControler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.model.Items;
import lk.ijs.my.dep.pos.model.Login.SystemUsers;

import java.io.IOException;
import java.util.ArrayList;

public class SuperUserAdministration<T> {
    @FXML
    private PasswordField txtAdminNewPassWord;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnChange;
    @FXML
    private PasswordField txtSuperUserNewPassword;
    @FXML
    private PasswordField txtSuperUserConfirmPassword;
    @FXML
    private PasswordField txtSuperUserPassword;
    @FXML
    private TextField txtUserNameAdmin;
    @FXML
    private Button btnAdminSave;
    @FXML
    private TableView<SystemUsers> tblAdminTable;
    @FXML
    private PasswordField txtAdminConfirmPassWord;


    static String superUserPassword;
    static  String username;

    public static ArrayList<SystemUsers>users;


    @FXML
    private void OnActionBtnChange(ActionEvent actionEvent) {

        if(username=="SuperUser"){


            if(!superUserPassword.equals(txtSuperUserPassword.getText())){
                new Alert(Alert.AlertType.ERROR,"Existing PassWord Not Match Pleas Check ! ", ButtonType.OK).showAndWait();
                txtSuperUserPassword.setText("");
                txtSuperUserPassword.requestFocus();
                return;

            }


            ArrayList<SystemUsers>tempUsers= new ArrayList<>();

            if(txtSuperUserNewPassword.getText().equals(txtSuperUserConfirmPassword.getText())){

                for (SystemUsers s:users) {

                    if(s.getUserName().equals(username)){
                        tempUsers.add(new SystemUsers(s.getUserType(),s.getUserType(),txtSuperUserNewPassword.getText(),s.getName(),s.getIsActive()));
                        continue;
                    }
                   tempUsers.add(s);
                }



            }else{
                new Alert(Alert.AlertType.ERROR,"PassWord Not Match ! ", ButtonType.OK).showAndWait();
                txtSuperUserConfirmPassword.requestFocus();
                txtSuperUserConfirmPassword.requestFocus();

                return;
            }

           users=tempUsers;
            superUserPassword=txtSuperUserNewPassword.getText();

            new Alert(Alert.AlertType.CONFIRMATION,"Password Change ! ", ButtonType.OK).showAndWait();
            txtSuperUserConfirmPassword.setText("");
            txtSuperUserPassword.setText("");
            txtSuperUserNewPassword.setText("");
        }


    }

    @FXML
    private void OnActionbtnNewAdmin(ActionEvent actionEvent) {

        txtAdminNewPassWord.setText("");
        txtAdminConfirmPassWord.setText("");
        txtUserNameAdmin.setText("");
        txtUserNameAdmin.requestFocus();


    }

    @FXML
    private void OnActionbtnAdminSave(ActionEvent actionEvent) {


        String userName =txtUserNameAdmin.getText();
        String password =txtAdminNewPassWord.getText();
        String  cpw =txtAdminConfirmPassWord.getText();

        if(!password.equals(cpw)){
            new Alert(Alert.AlertType.ERROR,"PassWord Not Match ! ", ButtonType.OK).showAndWait();
            txtAdminConfirmPassWord.requestFocus();
            txtAdminConfirmPassWord.setText("");
            return;
        }


        int count=0;

        for (SystemUsers s:users ) {

            if(s.getUserType().equals("Admin")){

                count++;
            }

        }

        if(count>=2){

            new Alert(Alert.AlertType.ERROR,"You can't Add More then two Admin Account! ", ButtonType.OK).showAndWait();
            return;
        }

        users.add(new SystemUsers("Admin",userName,password,userName,"Y")); //String userType, String userName, String password, String name, String isActive

        new Alert(Alert.AlertType.CONFIRMATION,"New User Created ! ", ButtonType.OK).showAndWait();
        loadSystemUsers();
        txtAdminConfirmPassWord.setText("");
        txtUserNameAdmin.setText("");
        txtAdminNewPassWord.setText("");
    }

    @FXML
    private void OnActionbtnBack(ActionEvent actionEvent) throws IOException {

        PassWordChangeDashBordControler.pasword=superUserPassword;
        PassWordChangeDashBordControler.users=users;
        LoginPageControler.users=users;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/LoginManageDashBoard.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdminSave.getScene().getWindow();
        primaryStage.setScene(scene);
    }


    public void initialize(){

        if(users==null) {
            users = new ArrayList<>();
            users.add(new SystemUsers("SuperUser", "SuperUser", superUserPassword, "SuperUser", "Y")); //String userType, String userName, String password, String name, String isActive
        }

            tblAdminTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userType"));
            //tblAdminTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
            tblAdminTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("password"));
            tblAdminTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
            tblAdminTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("isActive"));
            tblAdminTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("delete"));
            loadSystemUsers();




    }


    private void loadSystemUsers(){

        ArrayList<SystemUsers> temp= new ArrayList<>();

        for (SystemUsers s:users) {

            if(s.getUserType().equals("SuperUser")||s.getUserType().equals("User")){
                continue;
            }

            temp.add(s);
        }

        if(temp.size()>0) {

            ObservableList<SystemUsers> list = FXCollections.observableList(temp);
            tblAdminTable.setItems(list);

        }
    }




}
