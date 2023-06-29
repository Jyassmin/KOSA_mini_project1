public class Order {

    private int id;
    private int uid;
    private int pid;
    private String orderDate;
    private double totalAmount;
    private int quantity;


    Order (int id,
         int uid,
         int pid,
         String orderDate,
         double cost,
         int quantity) {

        this.id = id;
        this.uid = uid;
        this.pid = pid;
        this.orderDate = orderDate;
        this.totalAmount = cost;
        this.quantity = quantity;
    }
    // endregion

    // region getters
    public int getId(){
        return this.id;
    }
    public int getUid(){
        return this.uid;
    }
    public int getPid(){
        return this.pid;
    }
    public String getOrderDate(){
        return this.orderDate;
    }
    public double getTotalAmount(){
        return this.totalAmount;
    }
    public int getQuantity(){
        return this.quantity;
    }
    //endregion

    // region setters
    public void setId(int id){ this.id = id; }
    public void setUid(int uid){ this.uid = uid; }
    public void setPid(int pid){
        this.pid = pid;
    }
    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    //endregion

}
