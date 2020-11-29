package pl.mojeprojekty.shop_v2.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mojeprojekty.shop_v2.dto.AddressDto;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.entity.*;
import pl.mojeprojekty.shop_v2.repositories.RoleRepository;

import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DtoToObjectConverters {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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

        Address addressUserDto = addressDtoToAddressEntity(userDto.getShippingAddress());
        entity.setShippingAddress(Arrays.asList(addressUserDto));

        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByRole("USER");
        entity.setRoles(Collections.singleton(role));

        return entity;
    }

    //    **************** PRODUCT CATEGORY ****************
    public ProductCategory productCategoryDtoToEntity(ProductCategoryDto productCategoryDto) {
        ProductCategory entity = new ProductCategory();
        if (productCategoryDto == null) {
            productCategoryDto = new ProductCategoryDto();
        }
        entity.setDescription(productCategoryDto.getDescription());

        if (productCategoryDto.getParentId() != null) {
            entity.setParentId(productCategoryDto.getParentId());
        }
        return entity;
    }

    public ProductCategoryDto productCategoryEntityToCategoryDto(ProductCategory categoryEntity) {
        ProductCategoryDto dto = new ProductCategoryDto();
        if (categoryEntity == null) {
            categoryEntity = new ProductCategory();
        }
        dto.setDescription(categoryEntity.getDescription());
        dto.setId(categoryEntity.getId());
        if (categoryEntity.getParentId() != null) {
            dto.setParentId(categoryEntity.getParentId());
        }
        return dto;
    }

    //  **************** PRODUCTS ****************
    public Product createNewProductFromProductDto(ProductDto productDto) {
        Product entity = new Product();

        if (productDto.getId() == null) {
            productDto.setId(100L);
        }
        return  productDtoToProductEntity( productDto,  entity );
    }

    public Product productDtoToProductEntity(ProductDto productDto, Product edited ){
        edited.setTitle(productDto.getTitle());
        edited.setDescription(productDto.getDescription());
        edited.setPrice(productDto.getPrice());
        edited.setStockAmount(productDto.getStockAmount());
        edited.setProductType(productDto.getProductType());
        edited.setProductCategory(productDto.getProductCategory());
        return edited;
    }

    public ProductDto productEntityToDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setStockAmount(entity.getStockAmount());
        dto.setProductType(entity.getProductType());
        dto.setProductCategory(entity.getProductCategory());
        return dto;
    }
}
