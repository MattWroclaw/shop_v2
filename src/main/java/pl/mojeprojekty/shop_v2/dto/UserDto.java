package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@Data
public class UserDto {

    private long id;

    @NotNull
    @Size(min = 3, max = 10, message = "Min 3, max 10 chars")
    @Pattern(regexp = "\\w+")
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Email(message = "This is not valid e-mail pattern")
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "\\w*")
    private String password;

    @NotNull
    @Size(min = 2, max = 10,message = "Min 2, max 10 chars")
    private String city;

    @Valid
    @NotNull
    private AddressDto shippingAddress;

//     private AddressDto shippingAddress;
//
//    private AddressDto homeAddress;
//
//    private AddressDto invoiceAddress;
}
