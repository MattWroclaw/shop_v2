package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.repositories.ProductRepository;
import pl.mojeprojekty.shop_v2.utils.DtoToObjectConverters;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final DtoToObjectConverters dtoToObjectConverters;

    public List<ProductDto> findAllProductsDto(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> dtos =  new ArrayList<>();
        for (Product entity : products) {
            ProductDto dto = new ProductDto();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setStockAmount(entity.getStockAmount());
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            dto.setProductType(entity.getProductType());

            dto.setProductCategory(entity.getProductCategory());
           dtos.add(dto);
        }
        return dtos;
    }

    public ProductDto findProductDtoById(long id){
        Product productEntity = productRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("There is no such product in the shop"));
        return dtoToObjectConverters.productEntityToDto(productEntity);
    }

    public void createProduct(ProductDto productDto){
        Product entity = dtoToObjectConverters.productDtoToEntity(productDto);
        productRepository.save(entity);
    }

    public void deletePorduct(long id){
        productRepository.deleteById(id);
    }

    public void editProduct(ProductDto productDto){
        Product edited = productRepository.findById(productDto.getId())
                .orElseThrow(()->new NoSuchElementException("No product with id = " + productDto.getId()));
        edited.setTitle(productDto.getTitle());
        edited.setDescription(productDto.getDescription());
        edited.setPrice(productDto.getPrice());
        edited.setStockAmount(productDto.getStockAmount());
        edited.setProductType(productDto.getProductType());
        edited.setProductCategory(productDto.getProductCategory());
        productRepository.save(edited);
    }

    public Product findProductById(long id){
        Product productWithGivenId = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with " + id + "does not exist.."));
        return productWithGivenId;
    }
}
