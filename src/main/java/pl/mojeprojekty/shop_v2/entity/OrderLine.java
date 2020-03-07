package pl.mojeprojekty.shop_v2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;

    private double price;
}
