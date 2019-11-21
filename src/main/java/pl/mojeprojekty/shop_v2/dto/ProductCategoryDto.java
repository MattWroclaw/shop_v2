package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;

@Data
public class ProductCategoryDto {

    private long id;

    private String description;

    private Long parentId;

}
