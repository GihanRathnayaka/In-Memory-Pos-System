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
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import lk.ijs.my.dep.pos.model.Customer;
import lk.ijs.my.dep.pos.model.Items;
import lk.ijs.my.dep.pos.model.OrderDetails;
import lk.ijs.my.dep.pos.model.PlaceOrder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrderControler<T> {

    @FXML
    private  Label lblTotal;
    @FXML
    private TextField txtCusNo;
    @FXML
    private TextField txtItemCode;
    @FXML
    private TableView<OrderDetails> tblPlaceOrder;
    @FXML
    private Label lblOrderId;
    @FXML
    private TextField txtOrderedDate;
    @FXML
    private ComboBox cmbCusCode;
    @FXML
    private TextField txtCusName;
    @FXML
    private ComboBox btnItemCode;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private Label lblQty;
    @FXML
    private TextField txtQty;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    private Button btnBack;

    public static String username=null;
    static ArrayList<PlaceOrder> placeOrders;
    static ArrayList<OrderDetails> orderDetails;
    static ArrayList<Customer> customers;
    static ArrayList<Items> items;
    Customer customer;
    Items item;
    float Total=0;

     ArrayList<Items> tempItems =new ArrayList<>();

    {tempItems=items;}


    ArrayList<OrderDetails> ORDlist ;// = new ArrayList<>();


    @FXML
    private void onActionbtnAdd(ActionEvent actionEvent) {

        if(txtCusNo.getText().trim().equals("")||txtCusName.getText().trim().equals("")||txtItemCode.getText().trim().equals("")||txtDescription.getText().trim().equals("")||txtQty.getText().trim().equals("")){

            if(txtCusNo.getText().trim().equals("")){
                txtCusNo.requestFocus();
                return;
            }
            if(txtItemCode.getText().trim().equals("")){
                txtItemCode.requestFocus();
                return;
            }

            if(txtQty.getText().trim().equals("")){
                txtQty.requestFocus();
                return;
            }

        }else {


            String placeOrderId = lblOrderId.getText();
            LocalDate date = LocalDate.now();
            String description = txtDescription.getText();
            float unitePrice = Float.parseFloat(lblUnitPrice.getText());
            int qtyOnHand = Integer.parseInt(lblQty.getText());
            int qtyRequested = Integer.parseInt(txtQty.getText());

            String cusId = txtCusNo.getText();
            String cusName = txtCusName.getText();
            String itemCode = txtItemCode.getText();

            float total = (qtyRequested * unitePrice);

            // Total =Total+total;

            OrderDetails od = new OrderDetails(itemCode, unitePrice, total, qtyRequested, description);



            if(getCustomerprofile(txtCusNo.getText().trim())==null){
                new Alert(Alert.AlertType.ERROR,"customer Not Fount Check  !",ButtonType.OK).showAndWait();
                txtCusNo.requestFocus();
                return;
            }

            if(isItemInDB(txtItemCode.getText()).trim()==null){

                new Alert(Alert.AlertType.ERROR,"ItemCODE Not Found !",ButtonType.OK).showAndWait();
                txtItemCode.requestFocus();

                return;
            }


            if(qtyRequested<0){

                new Alert(Alert.AlertType.ERROR,"Nagative Quantity !",ButtonType.OK).showAndWait();
                txtQty.requestFocus();
                return;

            }




            if(ORDlist ==null){

                ORDlist = new ArrayList<>();

                ORDlist.add(od);



                ArrayList<Items>it = new ArrayList<>();
                for (Items i:tempItems) {

                    if(i.getCode().equals(od.getItemCode())){

                        it.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()-od.getQty()));
                    } else {

                        it.add(i);
                    }


                }


                tempItems=it;





                new Alert(Alert.AlertType.CONFIRMATION,"Order Details Add !",ButtonType.OK).showAndWait();
                loadAddItems();
                getTotal();
                clearfild();
                getTotal();

            }else{

                int qty =0;

               // ORDlist  = new ArrayList<>();

                ArrayList<OrderDetails> temp = new ArrayList<>();
                loadAddItems();


                if(SaveUpdate(od)){


                    for (OrderDetails o : ORDlist) {

                        if(o.getItemCode().equals(od.getItemCode())){

                            temp.add(od);
                            qty=o.getQty();

                        }else{

                            temp.add(o);
                        }

                    }

                    ORDlist =temp;

                    ArrayList<Items>it = new ArrayList<>();


                    for (Items i:tempItems) {


                        if(i.getCode().equals(txtItemCode.getText().trim())){

                            if(qty>od.getQty()){

                                it.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()+(qty-od.getQty()))) ;
                            }else{
                                it.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()-(od.getQty()-qty))) ;
                            }



                        }else {

                            it.add(i);

                        }


                    }


                    tempItems=it;

                    new Alert(Alert.AlertType.CONFIRMATION,"Order Details Updated!",ButtonType.OK).showAndWait();
                    loadAddItems();
                    getTotal();
                    clearfild();
                    getTotal();

                    btnAdd.setText("Add");


                   /////////////////////////////////////

                }else{

                    ORDlist.add(od);

                    ArrayList<Items>it = new ArrayList<>();
                    for (Items i:tempItems) {

                       if(i.getCode().equals(od.getItemCode())){

                           it.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()-od.getQty()));
                       } else {

                           it.add(i);
                       }


                    }


                    tempItems=it;


                    new Alert(Alert.AlertType.CONFIRMATION,"Order Details Add !",ButtonType.OK).showAndWait();
                    loadAddItems();
                    getTotal();
                    clearfild();

                }



            }


        }

        txtCusNo.setEditable(false);
        btnAdd.setText("Add");
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
    }




    @FXML
    private void OnActionbtnRemove(ActionEvent actionEvent) {


        if(txtItemCode.getText().trim().equals("")){ return;}

        ArrayList<OrderDetails> temp = new ArrayList<>();
        ArrayList<Items> tempItem = new ArrayList<>();
        int qty =0;

        for (OrderDetails o:ORDlist) {

           if (o.getItemCode().equals(txtItemCode.getText())){

               qty =o.getQty();

               continue;
           }
           else {
               temp.add(o);

           }


        }




        for (Items i:tempItems) {

            if(i.getCode().equals(txtItemCode.getText())){

                tempItem.add(new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty()+qty));

            }else{

                tempItem.add(i);

            }

        }



        ORDlist =temp;
        tempItems =tempItem;

        loadAddItems();
        getTotal();

        new Alert(Alert.AlertType.ERROR,"Order Details Line Removd !",ButtonType.OK).showAndWait();
        btnAdd.setText("Add");
        btnRemove.setDisable(true);

    }

    ///////////////////////////////////////////////PLACEORDER///////////////////////////////////////////////////

    @FXML
    private void OnActionbtnPlaceOrder(ActionEvent actionEvent) {

      //  ArrayList<PlaceOrder> temp =new ArrayList<>();
        LocalDate date = LocalDate.now();
        float total =Float.parseFloat(lblTotal.getText());
        String poId =lblOrderId.getText();

        Customer c = getCustomerprofile(txtCusNo.getText());

        if(placeOrders==null){

            placeOrders= new ArrayList<>();



            placeOrders.add(new PlaceOrder(c,date,total,poId,ORDlist)) ; //Customer customerID,LocalDate orderDate, float total, String placOrderId



        }else{

            placeOrders.add(new PlaceOrder(c,date,total,poId,ORDlist));


        }


            items =tempItems;
            clearAll();
            setOrderId();
        //    tempItems=null;


        new Alert(Alert.AlertType.CONFIRMATION,"Palced Ordered !",ButtonType.OK).showAndWait();
        ORDlist=null;
        txtCusNo.requestFocus();

    }

    @FXML
    private void OnActionbtnBack(ActionEvent actionEvent) throws IOException {


        HomePageControler.items = items;
        HomePageControler.customers = customers;
        HomePageControler.placeOrders = placeOrders;


        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/my/dep/pos/view/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnRemove.getScene().getWindow();
        primaryStage.setScene(scene);

    }


    public void OnDragEnterGetCustomer(KeyEvent keyEvent) {

        if (keyEvent.getCode().toString().equals("ENTER")) {
           // new Alert(Alert.AlertType.ERROR,"No customers Found !",ButtonType.OK).showAndWait();
            searchCustomer(txtCusNo.getText());
            txtItemCode.requestFocus();
        }
    }

    public void OnkeyPresstxtItemCode(KeyEvent keyEvent) {

        if (keyEvent.getCode().toString().equals("ENTER")) {

            setItemSearchDetails(txtItemCode.getText());
            txtQty.requestFocus();

        }

    }


    public void initialize() {

        tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        setOrderId();
        txtCusNo.requestFocus();

        txtOrderedDate.setText(""+LocalDate.now());
        txtCusNo.requestFocus();
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);





            tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetails>() {
                @Override
                public void changed(ObservableValue<? extends OrderDetails> observable, OrderDetails oldValue, OrderDetails newValue) {

                    if(newValue==null){
                        return;
                    }

                    txtQty.setText(""+newValue.getQty());
                    txtItemCode.setText(newValue.getItemCode());
                    txtDescription.setText(newValue.getDescription());
                   // lblUnitPrice.setText(""+newValue.getUnitPrice());
                   // lblQty.setText(""+newValue.getQty());
                    btnAdd.setText("Update");
                    btnRemove.setDisable(false);


                    for (Items i:tempItems) {

                        if(i.getCode().equals(newValue.getItemCode())){
                            lblQty.setText(""+i.getQty());
                            lblUnitPrice.setText(""+i.getPrice());
                        }



                    }




                }
            });






    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setOrderId(){

        if(placeOrders==null){

            lblOrderId.setText("PC001");
        }else{

            int max=0;
            String textPart;
            int numberPart = 0;


            for (PlaceOrder p:placeOrders ) {

                numberPart =Integer.parseInt( p.getPlacOrderId().substring(3));
                textPart = p.getPlacOrderId().substring(0,3);

                if(max<numberPart){
                    max=numberPart;
                }

            }

            numberPart++;

            lblOrderId.setText("PC00"+numberPart);


        }

    }


    ////////////////////////////////////////////Search Customer///////////////////////////////

    public void searchCustomer(String cusId){

        if(customers==null){

             new Alert(Alert.AlertType.ERROR,"No customers Found !",ButtonType.OK).showAndWait();
            return;
        }else{
            Customer cus = new Customer();


            for (Customer c:customers) {

                if(c.getId().equals(cusId.trim())){
                    cus = new Customer(c.getId(),c.getName(),c.getAddress());
                    customer =cus;
                    txtCusNo.setText(cus.getId());
                    txtCusName.setText(cus.getName());
                    return;
                }

            }

            new Alert(Alert.AlertType.ERROR,"No customers Found Pleace Check Customer ID !",ButtonType.OK).showAndWait();

        }

    }

    ///////////////////////////////////////Get Items Details////////////////////////////////////////////////////////////////////////

    public void setItemSearchDetails(String code){

        if(items==null){
            new Alert(Alert.AlertType.ERROR,"No Items In DataBase !",ButtonType.OK).showAndWait();
            return;
        }else{

           Items it = new Items();

            for (Items i: tempItems) {

                if(i.getCode().equals(code.trim())){

                    it= new Items(i.getCode(),i.getDescription(),i.getPrice(),i.getQty());
                    txtItemCode.setText(""+i.getCode());
                    txtDescription.setText(i.getDescription());
                    lblQty.setText(""+i.getQty());
                    lblUnitPrice.setText(""+i.getPrice());
                    item= it;
                    return;


                }
            }

            new Alert(Alert.AlertType.ERROR,"Incorrect Item Code Pleace Cheack Item Code Agian!",ButtonType.OK).showAndWait();
        }

    }

    @FXML
    private void onActiontxtQty(ActionEvent actionEvent) {
        validateQuantity();
    }

    ////////////////////////////////////////////////// Validate Quantity///////////////////////////////////////////

    public void validateQuantity(){

        if(txtQty.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Pleace Enter Quantity ",ButtonType.OK).showAndWait();
        }

        int qty=0;


        try {
            int available= Integer.parseInt(lblQty.getText());
            qty =Integer.parseInt(txtQty.getText());

            if(qty>available){

                new Alert(Alert.AlertType.ERROR,"Quantity Is Not Enough !",ButtonType.OK).showAndWait();
                txtQty.setText("");
                txtQty.requestFocus();
                return;
            }else{
                btnAdd.setDisable(false);
                btnRemove.setDisable(false);
            }
        }catch (Exception e){

            new Alert(Alert.AlertType.ERROR,"You Should Enter Numbers !",ButtonType.OK).showAndWait();
            txtQty.setText("");
            txtQty.requestFocus();
            return;
        }

    }



    /////////////////////////////////Check Already Add Or Not/////////////////////////////////////

    public boolean checkAlreadyExcist(String code){


        ArrayList<OrderDetails> temp = new ArrayList<>();

        if(ORDlist==null){
            return false;
        }else{

            for ( OrderDetails o:ORDlist) {

                if(o.getItemCode().equals(code)){
                  //  new Alert(Alert.AlertType.ERROR,"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx !",ButtonType.OK).showAndWait();
                    return true;
                }else{ return  false;}

            }

        }

        return false;
    }


    ////////////////////////////////Lod Add Items////////////////////////////////////////////////


    public void loadAddItems(){

      //  tblPlaceOrder.setItems();

        if(ORDlist==null){
         //   new Alert(Alert.AlertType.ERROR,"Hello !",ButtonType.OK).showAndWait();
            txtCusNo.setEditable(true);
            return;
        }else{
          //  tblPlaceOrder.;
            ObservableList<OrderDetails> list = FXCollections.observableList(ORDlist);
            tblPlaceOrder.setItems(list);
        }

    }

    ///////////////////////////////GetTotal///////////////////////

    public void getTotal(){

        double total =0;

        if(ORDlist==null){
            lblTotal.setText(""+total);
            return ;
        }else{

            for (OrderDetails l:ORDlist) {

                total=total+l.getTotal();
            }

            lblTotal.setText(""+total);
            return;
        }

    }




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////SaveUpdate?/////////////////////////////////////////



    public boolean SaveUpdate(OrderDetails od){


        for (OrderDetails o :ORDlist) {


            if(o.getItemCode().equals(od.getItemCode())){
            return true;
            }
        }

           return false;
    }




     public void updateQty(OrderDetails od,float qty) {


         ArrayList<Items> tempIt = new ArrayList<>();

         for (Items i: tempItems ) {

             if(i.getCode().equals(od.getItemCode())){


                 if(qty>od.getQty()){


                     tempItems.add(new Items(i.getCode(),i.getDescription(),i.getPrice(), (int) (i.getQty()+(qty-i.getQty()))));

                 }else if(qty<od.getQty()) {

                     tempItems.add(new Items(i.getCode(),i.getDescription(),i.getPrice(), (int) (i.getQty()-(qty-i.getQty()))));

                 }else{

                     tempItems.add(i);

                 }


             }



         }



    }


////////////////////////////////////////////////////////Search Clear fild//////////////////////////////////////////


    public  void clearfild(){

        txtItemCode.setText("");
        txtDescription.setText("");
        lblQty.setText("");
        lblUnitPrice.setText("");
        txtItemCode.requestFocus();
        txtQty.setText("");

    }

/////////////////////////////////////GetCustomer///////////////////////////////////////////////////////////////////


    public Customer getCustomerprofile(String cusId){


        if(customers==null){

            return null;
        }else{


            for (Customer c: customers ) {

                if(c.getId().equals(cusId)){

                    return c;
                }

            }


        }

        return null;

    }


    ////////////////////////////////////////////ITEMISORNOT////////////////////////////////////////////////////

    public String isItemInDB(String code){

        for (Items i:items) {

            if(code.trim().equals(i.getCode())){

                return "xxxx";
            }

        }
       return null;
    }





/////////////////////////////////////////////////////clearAll/////////////////////////////////////////////////

    public void clearAll(){

        txtQty.setText("");
        txtItemCode.setText("");
        txtCusName.setText("");
        txtCusNo.setText("");
        lblUnitPrice.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        lblQty.setText("");
        lblTotal.setText("..........................");
        lblOrderId.setText("");
        btnAdd.setText("Add");
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        tblPlaceOrder.setItems(null);
        txtCusNo.setEditable(true);

    }


}