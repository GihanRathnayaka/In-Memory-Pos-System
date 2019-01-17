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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class ViewOrderDetailListControter<T> {

    @FXML
    private Label lblPID;
    @FXML
    private AnchorPane lblPlaceOrderID;
    @FXML
    private Button btnBack;
    @FXML
    private  TableView<OrderDetails> lblViewDetails;
    @FXML
    private  Button btnDelete;

   // private  Label lblPid;

   static ArrayList<PlaceOrder> placeOrders;
   static ArrayList<Items>items;
   static OrderCustomer PorderID;
   static  String palceOrderID;
   String itemcode=null;
   ArrayList<OrderDetails>temp = new ArrayList<>();
    public static String username=null;






    @FXML
    private  void onActionBtnBack(ActionEvent actionEvent) throws IOException {


        ViewOrderListControler.items=items;
        ViewOrderListControler.placeOrders=placeOrders;

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/ViewOrderList.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(scene);


    }
    @FXML
    private  void onActionBtndelete(ActionEvent actionEvent) {

        if(itemcode==null){return;}

        deleteRequest();
        new Alert(Alert.AlertType.CONFIRMATION,"Deleted Place Order Details Line !", ButtonType.OK).showAndWait();
        loadOrderDetails();
    }


    private void loadOrderDetails(){


        ArrayList<OrderDetails>temp =new ArrayList<>();
      //  if(PorderID==null){return;}

        if(placeOrders.size()<=0){lblViewDetails.setItems(null);}

        for (PlaceOrder p:placeOrders  ) {

            if(p.getPlacOrderId().trim().equals(lblPID.getText().trim())){

                temp=p.getORDlist();
                this.temp=temp;

                ObservableList<OrderDetails> list = FXCollections.observableList(this.temp);
                lblViewDetails.setItems(list);

               // return;
            }
        }


    }

    public void initialize(){



        lblViewDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        lblViewDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        lblViewDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        lblViewDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        lblViewDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));/// float , float , int, String


        lblPID.setText(palceOrderID);
        loadOrderDetails();
        lblViewDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetails>() {
            @Override
            public void changed(ObservableValue<? extends OrderDetails> observable, OrderDetails oldValue, OrderDetails newValue) {

              //  if(newValue==null){return;}

               // itemcode =newValue.getItemCode();


            }
        });



    }





    ///////////////////////////DeleteRequestListDetails////////////////////////////////////////////



    private void deleteRequest(){

        float total=0;

        if(itemcode==null){return;}

        ArrayList<OrderDetails>od = new ArrayList<>();
        int qty=0;

        for (OrderDetails or:temp) {

            if(or.getItemCode().equals(itemcode.trim())){


                qty=or.getQty();
                total=or.getTotal();
                continue;

            }

            od.add(or);

        }

        temp=od;

        ArrayList<Items>tempItems = new ArrayList<>();

        for (Items i:items) {


            //if(items==null){return;}

            if(i.getCode().equals(itemcode.trim())){

                tempItems.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()+qty));
                continue;
            }

            tempItems.add(i);
        }


        items=tempItems;


        ArrayList<PlaceOrder>tempPlaceOrders = new ArrayList<>();

        for (PlaceOrder p:placeOrders) {

            if(p.getPlacOrderId().equals(palceOrderID.trim())){

                if(temp.size()<=0){
                    //tempPlaceOrders.add(new PlaceOrder(p.getCustomerID(),p.getOrderDate(),p.getTotal(),p.getPlacOrderId(),temp));
                    continue;
                }

                tempPlaceOrders.add(new PlaceOrder(p.getCustomerID(),p.getOrderDate(),p.getTotal()-total,p.getPlacOrderId(),temp));
                continue;
            }

            tempPlaceOrders.add(p);
        }

        placeOrders=tempPlaceOrders;


    }


    public void OnMouseClickPlaceOrderDetails(MouseEvent mouseEvent) {

        if(lblViewDetails.getSelectionModel().isEmpty()){return;}

        String v = lblViewDetails.getSelectionModel().getSelectedItem().getItemCode();
        itemcode=v;


    }
}
