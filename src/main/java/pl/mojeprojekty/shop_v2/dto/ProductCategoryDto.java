package pl.mojeprojekty.shop_v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private long id;

    @NotNull
    @Size(min = 1, max = 12, message = "Min 1, max 12 chars")
    @NotBlank
    private String description;

    @NotNull
    @Digits(integer = 2, fraction = 0)
    private Long parentId;

}
