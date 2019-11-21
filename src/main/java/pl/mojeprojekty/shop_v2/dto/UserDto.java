package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.Address;
import pl.mojeprojekty.shop_v2.entity.Role;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String name;

    private String email;

    private String password;

    private String city;

    private Role roles;

    private AddressDto shippingAddress;

//     private AddressDto shippingAddress;
//
//    private AddressDto homeAddress;
//
//    private AddressDto invoiceAddress;
}
