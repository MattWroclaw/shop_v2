package pl.mojeprojekty.shop_v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private long id;

    @NotNull
    @Size(min = 1, max = 30, message = "Min 1, max 30 chars")

    @NotBlank
    private String description;

    private Long parentId;
}
