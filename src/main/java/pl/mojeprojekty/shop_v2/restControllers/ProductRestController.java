package pl.mojeprojekty.shop_v2.restControllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.services.ProductService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> showAllProducts() {
        return productService.findAllProductsDto();
    }

    @GetMapping("/{id}")
    public ProductDto showProductById(@PathVariable long id) {
        return productService.findProductDtoById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProduct(@Valid @RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @Valid @RequestBody ProductDto dto) {
        dto.setId(id);
        productService.editProduct(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }

}
