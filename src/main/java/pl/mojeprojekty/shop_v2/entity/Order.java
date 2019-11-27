package pl.mojeprojekty.shop_v2.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customers_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAdress;

    private LocalDate orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderLine_id")
    List<OrderLine> orderLines = new ArrayList<>();

    private OrderStatus orderStatus;
}
