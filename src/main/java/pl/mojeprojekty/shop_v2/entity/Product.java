package pl.mojeprojekty.shop_v2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private double price;

    private int stockAmount;

    private ProductType productType;

     @OneToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;
}
