package pl.mojeprojekty.shop_v2.restControllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.services.ProductCategoryService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private final ProductCategoryService categoryService;

    @GetMapping
    List<ProductCategoryDto> findAll(){
        log.info("fetching all categories (DTO) from db...");
        return categoryService.showAllProductCategories();
    }

    @GetMapping("/{id}")
    public ProductCategoryDto findCategoryById(@PathVariable Long id){
        return categoryService.findProductCategoryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductCategoryDto addCategory(@Valid @RequestBody ProductCategoryDto categoryDto){
        return categoryService.createProductCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public ProductCategoryDto updateExistingCategory(@Valid @RequestBody ProductCategoryDto categoryDto,
                                       @PathVariable Long id){
        categoryDto.setId(id);
        categoryService.editProductCategory(categoryDto);
        return  categoryDto;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable long id){
        categoryService.deleteProductCategory(id);
    }
}
