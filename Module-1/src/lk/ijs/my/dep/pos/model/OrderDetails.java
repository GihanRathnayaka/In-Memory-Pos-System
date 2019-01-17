package lk.ijs.my.dep.pos.model;

public class OrderDetails {

    private String itemCode;
    private float unitPrice;
    private float total;
    private int qty;
    private String description;

    public OrderDetails(String itemCode, float unitPrice, float total, int qty, String description) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.total = total;
        this.qty = qty;
        this.description = description;
    }



 /*   public OrderDetails( float unitPrice, float total, int qty, String description) {

        this.setUnitPrice(unitPrice);
        this.setTotal(total);
        this.setQty(qty);
        this.setDescription(description);
    }
*/
    public OrderDetails() {

    }

   // public PlaceOrder getPlaceOrderId() {
    //    return placeOrderId;
   // }

   // public void setPlaceOrderId(PlaceOrder placeOrderId) {
    //    this.placeOrderId = placeOrderId;
   // }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "placeOrderId=" + //placeOrderId +
                ", unitPrice=" + getUnitPrice() +
                ", total=" + getTotal() +
                ", qty=" + getQty() +
                ", description='" + getDescription() + '\'' +
                '}';
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
