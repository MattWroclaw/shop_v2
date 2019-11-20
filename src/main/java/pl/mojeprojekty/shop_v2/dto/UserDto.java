package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.Address;
import pl.mojeprojekty.shop_v2.entity.Role;

@Data
public class UserDto {
    private String name;

    private String email;

    private String password;

    private String city;

    private Role roles;

    private Address address;
}
