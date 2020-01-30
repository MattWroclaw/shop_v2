package pl.mojeprojekty.shop_v2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
//@Entity
//@Table
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;
}
