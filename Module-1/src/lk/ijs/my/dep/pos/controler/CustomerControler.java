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
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.model.Customer;
import lk.ijs.my.dep.pos.model.OrderCustomer;
import lk.ijs.my.dep.pos.model.OrderDetails;
import lk.ijs.my.dep.pos.model.PlaceOrder;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerControler <T> {
    @FXML
    private Button btnRegistration;
    @FXML
    private TextField txtIdx;
    @FXML
    private TextField txtName;
    @FXML
    private  TextField txtAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Customer> tblCustomer;
    public static String username=null;


    //////////////////////////////////////New Attribut Variable Create Here///////////////

    static ArrayList<Customer> customers =new ArrayList<>();
    public void initialize(){

        if(username.equals("User")){
            btnDelete.setDisable(true);
        }

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        if(customers!=null){

            showCustomers();
        }


        if(customers!=null) {

            tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
                @Override
                public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {

                    if(newValue==null){
                        return;
                    }

                    txtAddress.setText(newValue.getAddress());
                    txtIdx.setText(newValue.getId());
                    txtName.setText(newValue.getName());
                    txtIdx.setEditable(false);
                }
            });
        }
    }

    @FXML
    private void onActionbtnBack(ActionEvent actionEvent) throws IOException {

        if(customers!=null){
            HomePageControler.customers=customers;
        }

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnDelete.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @FXML
    private void OnActionbtnRegistration(ActionEvent actionEvent) {
        clearField();
    }

    @FXML
    private void OnActionbtnSave(ActionEvent actionEvent) {

        if(txtName.getText().trim().equals("")||txtIdx.getText().trim().equals("")||txtAddress.getText().trim().equals("")){
            new Alert(Alert.AlertType.WARNING,"You Need Fill All Relevent Field ! ", ButtonType.OK).showAndWait();
            return;
        }

        if(allReadyExist(txtIdx.getText())){
            if(!txtIdx.isEditable()){
                updateDetails( txtIdx.getText() ,txtName.getText(),txtAddress.getText());
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Delails Ubdated ! ", ButtonType.OK).showAndWait();
                showCustomers();
                clearField();
            }else {

            new Alert(Alert.AlertType.WARNING,"Customer Code Already In DataBase ! ", ButtonType.OK).showAndWait();
            txtIdx.setText("");
            txtIdx.requestFocus();
            return;
            }
        }else {

           // ArrayList<Customer>  c = new ArrayList<>();

            String id =txtIdx.getText();
            String name = txtName.getText();
            String address =txtAddress.getText();
            customers.add(new Customer(id,name,address));
            showCustomers();
            clearField();
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Registered ! ", ButtonType.OK).showAndWait();

        }


    }



    @FXML
    private void OnMouseClicktblCustomer(MouseEvent mouseEvent) {
    }


    private boolean allReadyExist(String code){

        if (customers==null){
            return false;
        }

        for (Customer c: customers) {

            if(c.getId().equals(code)){

                return true;
            }

        }

        return false;

    }


    private void showCustomers(){

        if(customers!=null){

            ObservableList<Customer> list = FXCollections.observableList(customers);
            tblCustomer.setItems(list);
        }


    }



    public void clearField(){

        txtIdx.setText("");
        txtAddress.setText("");
        txtName.setText("");
        txtIdx.setEditable(true);
    }


    public boolean deleteCustomer(String cusCode){

        ArrayList<Customer>cus = new ArrayList<>();



      //  ArrayList<OrderDetails>temp2=new ArrayList<>();
        ArrayList<PlaceOrder> placeOrders=PlaceOrderControler.placeOrders;


        for (PlaceOrder p:placeOrders) {

            if(p.getCustomerID().getId().equals(txtIdx.getText().trim())){
                new Alert(Alert.AlertType.CONFIRMATION,"You Can't Delete Customer ! ", ButtonType.OK).showAndWait();
                return false;
            }

        }






        if(customers==null){
            return false;
        }else {

            for (Customer c: customers) {

                if(c.getId().equals(cusCode)){

                    continue;
                }

                cus.add(new Customer( c.getId(),c.getName(),c.getAddress()));
            }

            //"TEST".startsWith("TEqq")


        }

        customers =cus;

        return true;
    }

    @FXML
    private void onActionbtnDelete(ActionEvent actionEvent) {

        if(txtIdx.getText().trim().equals("")){
            return;
        }



        if(deleteCustomer(txtIdx.getText())){

            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted ! ", ButtonType.OK).showAndWait();
            showCustomers();
            clearField();
        }

    }


    public void updateDetails( String code ,String name,String Address){

        if(customers==null){
            return;
        }

        ArrayList<Customer>cus = new ArrayList<>();

        for (Customer c:customers) {

            if(c.getId().equals(code)){

                cus.add(new Customer(c.getId(),name,Address));

            }else{

                cus.add(new Customer(c.getId(),c.getName(),c.getAddress()));
            }

        }

        customers=cus;
    }

    public void onActionbtnSave(ActionEvent actionEvent) {

        if(txtIdx.getText().trim().equals("")){
            return;
        }

        ArrayList<PlaceOrder>temp =PlaceOrderControler.placeOrders;

        if(temp!=null) {

            for (PlaceOrder p : temp) {

                if (p.getCustomerID().getId().equals(txtName.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Customer Can not Delete He has Order Place ! ", ButtonType.OK).showAndWait();
                    return;
                }
            }
        }


        if(deleteCustomer(txtIdx.getText())){

            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted ! ", ButtonType.OK).showAndWait();
            showCustomers();
            clearField();
        }
    }
}
