package pl.mojeprojekty.shop_v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private long id;

    private String description;

    private Long parentId;

}
