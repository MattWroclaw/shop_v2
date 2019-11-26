package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.entity.ProductType;

@Data
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private double price;

    private int stockAmount;

    private ProductType productType;

    private ProductCategory productCategory;
}
