package pl.mojeprojekty.shop_v2.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mojeprojekty.shop_v2.dto.*;
import pl.mojeprojekty.shop_v2.entity.*;
import pl.mojeprojekty.shop_v2.repositories.ProductCategoryRepsiotory;
import pl.mojeprojekty.shop_v2.repositories.RoleRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class DtoToObjectConverters {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ProductCategoryRepsiotory productCategoryRepsiotory;

    //      **************** USER *******************


    public Address addressDtoToAddressEntity(AddressDto addressDto) {
        Address entity = new Address();
        if (addressDto == null) {
            return entity;
        }
        entity.setCountry(addressDto.getCountry());
        entity.setCity(addressDto.getCity());
        entity.setStreet(addressDto.getStreet());
        entity.setZipCode(addressDto.getZipCode());
        return entity;
    }

    public User userDtoToUserEntity(UserDto userDto) {
        User entity = new User();

        entity.setName(userDto.getName());
        entity.setEmail(userDto.getEmail());
        entity.setCity(userDto.getCity());

        entity.setShippingAddress(Arrays.asList(
                addressDtoToAddressEntity(userDto.getShippingAddress())));

//        Address home = addressDtoToAddressEntity(userDto.getHomeAddress());
//        Address shipping = addressDtoToAddressEntity(userDto.getShippingAddress());
//        Address invoice = addressDtoToAddressEntity(userDto.getInvoiceAddress());
//        entity.setHomeAddress(home);
//        entity.setShippingAddress(shipping);
//        entity.setInvoiceAddress(invoice);

        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByRole("USER");
        if(userDto.getEmail().equalsIgnoreCase("admin")){
         role = roleRepository.findByRole("ADMIN");
        }
        entity.setRoles(Collections.singleton(role));

        return entity;
    }

    //    **************** PRODUCTS ****************
    public ProductCategory productCategoryDtoToEntity(
            ProductCategoryDto productCategoryDto
    ) {
        ProductCategory entity = new ProductCategory();
        entity.setDescription(productCategoryDto.getDescription());

        if (productCategoryDto.getParentId() != null) {
            ProductCategory parent = productCategoryRepsiotory
                    .findById(productCategoryDto.getParentId())
                    .orElseThrow(() -> new NoSuchElementException("No such parent category"));
            entity.setParent(parent);
        }
        return entity;
    }

    public Product productDtoToEntity(ProductDto productDto) {
        Product entity = new Product();
        entity.setTitle(productDto.getTitle());
        entity.setDescription(productDto.getDescription());
        entity.setPrice(productDto.getPrice());
        entity.setStockAmount(productDto.getStockAmount());
        entity.setProductType(productDto.getProductType());
        entity.setProductCategory(
                productCategoryDtoToEntity(productDto.getProductCategory())
        );
        return entity;
    }
}
