package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String country;

    private String city;

    private String street;

    private String zipCode;

}
