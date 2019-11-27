package pl.mojeprojekty.shop_v2.entity;

public enum OrderStatus {
    ORDER_PALCED ("1"),
    PREPARED_TO_SEND ("2"),
    SENT ("3")
    ;

    private final String orderStatus;

     OrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
     }

     public String getOrderStatus(){
         return this.orderStatus;
     }
}
