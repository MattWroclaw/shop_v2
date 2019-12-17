package pl.mojeprojekty.shop_v2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "product_category")
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private Long parentId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private ProductCategory parent;

//    @OneToMany(mappedBy = "parent")
//    private List<ProductCategory> childrenCategories = new ArrayList<>();
}
