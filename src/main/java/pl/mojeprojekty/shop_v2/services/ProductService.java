package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.ProductType;
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

    public List<ProductDto> findAllProductsDto() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> dtos = new ArrayList<>();
        for (Product entity : products) {
            ProductDto dto = dtoToObjectConverters.productEntityToDto(entity);
            dtos.add(dto);
        }
        return dtos;
    }

    public ProductDto findProductDtoById(long id) {
        Product productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no such product in the shop"));
        return dtoToObjectConverters.productEntityToDto(productEntity);
    }

    public void createProduct(ProductDto productDto) {
        Product entity = dtoToObjectConverters.createNewProductFromProductDto(productDto);
        productRepository.save(entity);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public void editProduct(ProductDto productDto) {
        Product edited = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NoSuchElementException("No product with id = " + productDto.getId()));

        Product productAfterEdit = dtoToObjectConverters.productDtoToProductEntity( productDto, edited );
        productRepository.save(productAfterEdit);
    }

    public void updateProduct(Product product) {
        Product productById = findProductById(product.getId());
        productById.setStockAmount(product.getStockAmount());
        productRepository.save(productById);
    }

    public Product findProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with " + id + "does not exist.."));
    }

    public List<ProductDto> findProductsByType(ProductType productType) {
        List<Product> productsSameType = productRepository.findAllByProductType(productType);
        List<ProductDto> dtos = new ArrayList<>();
        for (Product entity : productsSameType) {
            ProductDto dto = dtoToObjectConverters.productEntityToDto(entity);
            dtos.add(dto);
        }
        return dtos;
    }
}
