package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddressDto {

    @NotNull
    @Size(min = 2, max = 12)
    @NotBlank
    private String country;

    @NotNull
    @Size(min = 2, max = 10)
    @NotBlank
    private String city;

    @NotNull
    @Size(min = 2, max = 15)
    @NotBlank
    private String street;

    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Zip-code pattern xx-xxx")
    private String zipCode;

}
