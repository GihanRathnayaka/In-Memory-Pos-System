package lk.ijs.my.dep.pos.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrder {

    private Customer customerID;
    private LocalDate orderDate;
    private float total;
    private String placOrderId;
    private ArrayList<OrderDetails> ORDlist;


 /*   public PlaceOrder(Customer customerID,LocalDate orderDate, float total, String placOrderId) {
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.total = total;
        this.placOrderId = placOrderId;
    }*/

    public PlaceOrder(Customer customerID, LocalDate orderDate, float total, String placOrderId, ArrayList<OrderDetails> ORDlist) {
        this.setCustomerID(customerID);
        this.setOrderDate(orderDate);
        this.setTotal(total);
        this.setPlacOrderId(placOrderId);
        this.setORDlist(ORDlist);
    }




    public PlaceOrder() {

    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }




    public String getPlacOrderId() {
        return placOrderId;
    }

    public void setPlacOrderId(String placOrderId) {
        this.placOrderId = placOrderId;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "customerID=" + getCustomerID() +
                ", orderDate=" + getOrderDate() +
                ", total=" + getTotal() +
                ", placOrderId='" + getPlacOrderId() + '\'' +
                '}';
    }

    public ArrayList<OrderDetails> getORDlist() {
        return ORDlist;
    }

    public void setORDlist(ArrayList<OrderDetails> ORDlist) {
        this.ORDlist = ORDlist;
    }
}
