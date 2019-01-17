package lk.ijs.my.dep.pos.model;

import java.time.LocalDate;

public class OrderCustomer {
    private String placOrderId;
    private LocalDate orderDate;
    private String id;
    private String name;
    private float total;

    public OrderCustomer(String placOrderId, LocalDate orderDate, String id, String name) {
        this.setPlacOrderId(placOrderId);
        this.setOrderDate(orderDate);
        this.setId(id);
        this.setName(name);
    }

    public OrderCustomer(String placOrderId, LocalDate orderDate, String id, String name, float total) {
        this.placOrderId = placOrderId;
        this.orderDate = orderDate;
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public String getPlacOrderId() {
        return placOrderId;
    }

    public void setPlacOrderId(String placOrderId) {
        this.placOrderId = placOrderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
