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
import lk.ijs.my.dep.pos.model.OrderDetails;
import lk.ijs.my.dep.pos.model.PlaceOrder;

import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class ItemsControler <T>{
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNewItem;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtUnitePrice;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView <Items> tblItems;
    @FXML
    private TextField txtQty;
    public static String username=null;

    @FXML
    private void onActionbtnBack(ActionEvent actionEvent) throws IOException {
        if(items!=null){
            HomePageControler.items=items;
        }
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnDelete.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @FXML
    private void OnActionbtnNewItem(ActionEvent actionEvent) {
        clearField();
    }
    @FXML
    private void OnActionbtnSave(ActionEvent actionEvent) {


        try {

            float price =Float.parseFloat(txtUnitePrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

        }catch (Exception ex){

            new Alert(Alert.AlertType.CONFIRMATION,"Pleas check Your input  ! ", ButtonType.OK).showAndWait();
            txtQty.setText("");
            txtUnitePrice.setText("");
            return;
        }



        if(txtCode.getText().trim().equals("")||txtUnitePrice.getText().trim().equals("")||txtQty.getText().trim().equals("")||txtDescription.getText().trim().equals("")){
            new Alert(Alert.AlertType.WARNING,"You Need Fill All Relevent Field ! ", ButtonType.OK).showAndWait();
            return;
        }

        if(allReadyExist(txtCode.getText())){
            if(!txtCode.isEditable()){
                updateDetails( txtCode.getText() ,txtDescription.getText(), Float.parseFloat(txtQty.getText()),Integer.parseInt(txtQty.getText()));
                new Alert(Alert.AlertType.CONFIRMATION,"Item Delails Ubdated ! ", ButtonType.OK).showAndWait();
                showCustomers();
                clearField();
            }else {

                new Alert(Alert.AlertType.WARNING,"Customer Code Already In DataBase ! ", ButtonType.OK).showAndWait();
                txtCode.setText("");
                txtCode.requestFocus();
                return;
            }
        }else {

            // ArrayList<Customer>  c = new ArrayList<>();

            String code =txtCode.getText();
            String description = txtDescription.getText();
            float price =Float.parseFloat(txtUnitePrice.getText());
            int qty = Integer.parseInt(txtQty.getText());
            items.add(new Items(code,description,price,qty));
            showCustomers();
            clearField();
            new Alert(Alert.AlertType.CONFIRMATION,"Item Added ! ", ButtonType.OK).showAndWait();

        }

    }



    @FXML
    private void OnMouseClicktblCustomer(MouseEvent mouseEvent) {
    }

    @FXML
    private void OnmouseClickTblItems(MouseEvent mouseEvent) {
    }

    static ArrayList<Items> items =new ArrayList<>();

    public void initialize(){

        if(username.equals("User")){
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
        }

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        if(items!=null){

            showCustomers();
        }

    if(items!=null){

        tblItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Items>() {
            @Override
            public void changed(ObservableValue<? extends Items> observable, Items oldValue, Items newValue) {

                if(newValue==null){
                    return;
                }

                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtUnitePrice.setText(""+newValue.getPrice());
                txtQty.setText(""+newValue.getQty());
                txtCode.setEditable(false);

            }
        });

    }


    }

    private void showCustomers(){

        if(items!=null){

            ObservableList<Items> list = FXCollections.observableList(items);
            tblItems.setItems(list);
        }


    }

    public void clearField(){

        txtCode.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUnitePrice.setText("");
        txtCode.setEditable(true);
    }


    private boolean allReadyExist(String code){

        if (items==null){
            return false;
        }

        for (Items i: items) {

            if(i.getCode().equals(code)){

                return true;
            }

        }

        return false;

    }


    public void updateDetails( String code ,String name,float price, int qty){

        if(items==null){
            return;
        }

        ArrayList<Items>it = new ArrayList<>();

        for (Items i:items) {

            if(i.getCode().equals(code)){

                it.add(new Items(i.getCode(),name,price,qty));

            }else{

                it.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()));
            }

        }

        items=it;
    }

    public void onActionbtnDelete(ActionEvent actionEvent) {

        if(txtCode.getText().trim().equals("")){
            return;
        }



        if(deleteCustomer(txtCode.getText())){

            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted ! ", ButtonType.OK).showAndWait();
            showCustomers();
            clearField();
        }
    }

    public boolean deleteCustomer(String Code){

        ArrayList<Items>it = new ArrayList<>();

        //ArrayList<OrderDetails>lst =

        ArrayList<OrderDetails>temp2=new ArrayList<>();
        ArrayList<PlaceOrder> placeOrders=PlaceOrderControler.placeOrders;

        if(placeOrders!=null) {

            for (PlaceOrder p : placeOrders) {

                temp2 = p.getORDlist();


                for (OrderDetails o : temp2) {

                    if (o.getItemCode().equals(txtCode.getText().trim())) {

                        new Alert(Alert.AlertType.CONFIRMATION, "You Can't Delete ! ", ButtonType.OK).showAndWait();
                        return false;

                    }

                }


            }

        }



        if(items==null){
            return false;
        }else {

            for (Items i: items) {

                if(i.getCode().equals(Code)){

                    continue;
                }

                it.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()));
            }


        }

        items =it;

        return true;
    }

    public void onKeyPressTxtUnitPrice(KeyEvent keyEvent) {

    }
}
