package lk.ijs.my.dep.pos.controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.model.Items;
import lk.ijs.my.dep.pos.model.OrderCustomer;
import lk.ijs.my.dep.pos.model.OrderDetails;
import lk.ijs.my.dep.pos.model.PlaceOrder;

import java.io.IOException;
import java.util.ArrayList;

public class ViewOrderListControler<T> {
    @FXML
    private TextField txtOrderId;
    @FXML
    private  Button btnSearch;
    @FXML
    private Button btnBack;
    @FXML
    private  TableView<OrderCustomer> tblOrderDetails;


    static   ArrayList<Items>items;
    static   ArrayList<PlaceOrder>placeOrders;

    ArrayList<OrderCustomer> orderCustomers = new ArrayList<>();





    @FXML
    private void OnactionBtnBack(ActionEvent actionEvent) throws IOException {

        HomePageControler.items=items;
        HomePageControler.placeOrders=placeOrders;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);

    }

    @FXML
    private  void OnKeyRelesedtxtOrderId(KeyEvent keyEvent) {

        if(placeOrders==null){
            new Alert(Alert.AlertType.CONFIRMATION,"Not Fount Order List In Data Base !", ButtonType.OK).showAndWait();
            return;
        }else{


            OrderListSearch(txtOrderId.getText());

        }



    }

    @FXML
    private  void OnActionBtnSearch(ActionEvent actionEvent) {


    }


    public void initialize() throws IOException {

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("placOrderId"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("name"));

        setOrderCustomerValues();

        if(orderCustomers.size()!=0){
            ObservableList<OrderCustomer> list = FXCollections.observableList(orderCustomers);
            tblOrderDetails.setItems(list);
        }


       tblOrderDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderCustomer>() {
           @Override
           public void changed(ObservableValue<? extends OrderCustomer> observable, OrderCustomer oldValue, OrderCustomer newValue) {

         //if(newValue==null){return;}

           }
       });

       // ViewDetails();

    }



    //////////////////////////////SEARCH ORDER DETAILS/////////////////////////////////////////////////////////////////////////////////


    public void OrderListSearch(String code){


        ArrayList<OrderCustomer>temp =new ArrayList<>();

        if(code.startsWith(code)){


            for (OrderCustomer x:orderCustomers) {

                if(code.length()>x.getPlacOrderId().length()){
                    tblOrderDetails.setItems(null);
                    return;
                }

                if(code.toUpperCase().equals(x.getPlacOrderId().substring(0,code.length()))){

                    temp.add(x);
                }


            }


            if(temp.size()<=0){

                tblOrderDetails.setItems(null);
                return;
            }


            ObservableList<OrderCustomer> list = FXCollections.observableList(temp);
            tblOrderDetails.setItems(list);

        }






        for (PlaceOrder order:placeOrders) {

            if(order.getPlacOrderId().equals(code.trim())){

            }


        }

    }

    public  void setOrderCustomerValues(){

         if(placeOrders==null){return;}

        for (PlaceOrder p:placeOrders ) {
            orderCustomers.add(new OrderCustomer( p.getPlacOrderId(),p.getOrderDate(),p.getCustomerID().getId(),p.getCustomerID().getName(),p.getTotal()));
        }

    }



public void ViewDetails() throws IOException {

    //txtOrderId.setText(newValue.getPlacOrderId());

    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/ViewOrderDetails.fxml"));
    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) btnBack.getScene().getWindow();
    primaryStage.setScene(scene);

    }

    public void onMouseClicktblDeatils(MouseEvent mouseEvent) throws IOException {

        if(tblOrderDetails.getSelectionModel().isEmpty()){return;}

       String v = tblOrderDetails.getSelectionModel().getSelectedItem().getPlacOrderId();

        ViewOrderDetailListControter.placeOrders=placeOrders;
        ViewOrderDetailListControter.items=items;
        ViewOrderDetailListControter.palceOrderID=v;

        if(items==null){}

        ViewDetails();


    }
}



