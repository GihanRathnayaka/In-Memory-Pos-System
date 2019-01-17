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
import lk.ijs.my.dep.pos.controler.HomePageControler;
import lk.ijs.my.dep.pos.model.Login.SystemUsers;
import lk.ijs.my.dep.pos.model.OrderCustomer;

import java.io.IOException;
import java.util.ArrayList;

public class SystemUserManageControler<T> {

    public TableView<SystemUsers> tblUsers;
    @FXML
    private Button btnchange;
    @FXML
    private  PasswordField txtApassword;
    @FXML
    private PasswordField txtAnewPassword;
    @FXML
    private  PasswordField txtAcpassword;
    @FXML
    private TextField txtUname;
    @FXML
    private  PasswordField txtUnewPassword;
    @FXML
    private  PasswordField btxtUcpassword;
    @FXML
    private  Button btnSave;
    @FXML
    private  Button btnNew;
    @FXML
    private  Button btnBack;


  public static   ArrayList<SystemUsers>users;
    public   static String Username;
    public   static String pasword;



    public void initialize(){

            if (users == null) {
                users = new ArrayList<>();
                users.add(new SystemUsers("SuperUser", "SuperUser", pasword, "SuperUser", "Y")); //String userType, String userName, String password, String name, String isActive
            }



        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userType"));
        //tblAdminTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUsers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("password"));
        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        //tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("isActive"));
        tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("delete"));

        loadTable();
    }

    @FXML
    private  void OnActionbtnchange(ActionEvent actionEvent) {

        if(Username.equals("SuperUser")){
            new Alert(Alert.AlertType.ERROR,"Super User Can't Change Password ! ", ButtonType.OK).showAndWait();
            return;
        }


        if(!pasword.equals(txtApassword.getText())){
            new Alert(Alert.AlertType.ERROR,"Old Password Not Match ! ", ButtonType.OK).showAndWait();
            return;
        }

        if(!txtAnewPassword.getText().equals(txtAcpassword.getText())){
            new Alert(Alert.AlertType.ERROR," Password Not Match ! ", ButtonType.OK).showAndWait();
            txtAcpassword.setText("");
            txtAcpassword.requestFocus();
            return;
        }

        ArrayList<SystemUsers> temp = new ArrayList<>();

        for (SystemUsers s:users) {

            if(s.getUserType().equals("User")){
                pasword=txtAnewPassword.getText();
                temp.add(new SystemUsers(s.getUserType(),s.getUserName(),txtAnewPassword.getText(),s.getName(),s.getIsActive())); //String userType, String userName, String password, String name, String isActive
                continue;
            }
           temp.add(s);
        }


        users=temp;
        new Alert(Alert.AlertType.CONFIRMATION," Password Update ! ", ButtonType.OK).showAndWait();
        loadTable();

    }

    @FXML
    private  void onActionbtnSave(ActionEvent actionEvent) {


        if(txtUname.getText().trim().equals("")||txtUnewPassword.getText().trim().equals("")){

            txtUname.setText("");
            txtUnewPassword.setText("");
            txtUname.requestFocus();
            return;
        }


        if(txtUnewPassword.getText().equals(btxtUcpassword.getText())){

           users.add(new SystemUsers("User",txtUname.getText(),txtUnewPassword.getText(),txtUname.getText(),"Y")); //String userType, String userName, String password, String name, String isActive

        }

        new Alert(Alert.AlertType.CONFIRMATION,"Succesfully Created User! ", ButtonType.OK).showAndWait();
        loadTable();
        txtUname.setText("");
        txtUnewPassword.setText("");
        btxtUcpassword.setText("");
        txtUname.requestFocus();
    }

    @FXML
    private  void onActionBtnNew(ActionEvent actionEvent) {

        txtUname.setText("");
        txtUnewPassword.setText("");
        btxtUcpassword.setText("");
        txtUname.requestFocus();

    }

    @FXML
    private  void OnActionBtnBack(ActionEvent actionEvent) throws IOException {

        LoginPageControler.users=users;
        PassWordChangeDashBordControler.users=users;

       // PassWordChangeDashBordControler.users=users;
        HomePageControler.users=users;
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/LoginManageDashBoard.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);

    }


    private void loadTable(){

        ArrayList<SystemUsers> temp = new ArrayList<>();

        if(users.size()<=0){
          return;
       }

        for (SystemUsers s:users ) {

            if(s.getUserType().equals("User")){
                temp.add(s);
            }

        }


        if(temp.size()<=0){
            return;
        }

        ObservableList<SystemUsers> list = FXCollections.observableList(temp);
        tblUsers.setItems(list);

    }

}
