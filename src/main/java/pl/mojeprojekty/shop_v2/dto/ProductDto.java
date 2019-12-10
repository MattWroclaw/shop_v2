package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.entity.ProductType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductDto {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 12, message = "Min 1, max 12 chars")
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 300)
    private String description;

    @NotNull

//    @Digits(integer = 5, fraction = 2)
    private double price;

//    @NotNull
//    @NotBlank

    private int stockAmount;

    private ProductType productType;

    private ProductCategory productCategory;
}
