package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.AddressType;

@Data
public class AddressDto {

    private String country;

    private String city;

    private String street;

    private String zipCode;

    private AddressType addressType;
}
