package lk.ijs.my.dep.pos.controler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.controler.LoginControler.PassWordChangeDashBordControler;
import lk.ijs.my.dep.pos.model.Customer;
import lk.ijs.my.dep.pos.model.Items;
import lk.ijs.my.dep.pos.model.Login.SystemUsers;
import lk.ijs.my.dep.pos.model.PlaceOrder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageControler {

    //public static Object userName;
    @FXML
    private Button btnUserManage;
    @FXML
    private Label lblUser;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button BtnItems;
    @FXML
    private Button BtnPlaceOrder;
    @FXML
    private Button BtnOrderView;
    @FXML
    private Button btnExit;

    //////////////////////////////////////New Attribut Variable Create Here///////////////

   static   ArrayList<Customer> customers;

    static ArrayList <Items> items;
    static ArrayList <PlaceOrder> placeOrders;
    public static String username=null;
    public static String password;
    public static String userType;
   public static ArrayList<SystemUsers>users;



    public void initialize(){

        lblUser.setText(username);




    }


    @FXML
    private void onActionCustomerManage(ActionEvent actionEvent) throws IOException {


        if(customers!=null){
            CustomerControler.customers=customers;
        }

        CustomerControler.username=username;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/Customer.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnCustomer.getScene().getWindow();
        primaryStage.setScene(scene);


    }

    @FXML
    private void OnActionBtnItems(ActionEvent actionEvent) throws IOException {

        if(items!=null){
            ItemsControler.items=items;
        }

        ItemsControler.username=username;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/Items.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnCustomer.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @FXML
    private void OnActionBtnPlaceOrder(ActionEvent actionEvent) throws IOException {


      /*  ArrayList<Customer> customers1 = new ArrayList<>();
        customers1.add(new Customer("1","Gihan Rathnayaka","No 88 Wewala"));
        customers1.add(new Customer("2","Eranga Rathnayaka","No 88 Wewala"));



        ArrayList<Items> it = new ArrayList<>();

        it.add(new Items("1","Dog",10,200));
        it.add(new Items("2","cat",5,200)); */


        PlaceOrderControler.customers=customers;
            PlaceOrderControler.items=items;
            PlaceOrderControler.placeOrders=placeOrders;
           // PlaceOrderControler.items=items;


        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/PlaceOder.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnCustomer.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void onActionExit(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnCustomer.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void OnActionBtnOderView(ActionEvent actionEvent) throws IOException {

         ViewOrderListControler.placeOrders=placeOrders;
         ViewOrderListControler.items=items;
         ViewOrderDetailListControter.items=items;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/ViewOrderList.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnCustomer.getScene().getWindow();
        primaryStage.setScene(scene);

    }

    public void OnActionbtnUserManage(ActionEvent actionEvent) throws IOException {

        PassWordChangeDashBordControler.Username=username;
        PassWordChangeDashBordControler.pasword=password;
        PassWordChangeDashBordControler.userType=userType;
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/login/LoginManageDashBoard.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnCustomer.getScene().getWindow();
        primaryStage.setScene(scene);

    }



}
