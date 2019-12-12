package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.services.ProductCategoryService;

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
    public ProductCategoryDto findGivenCategory(@PathVariable Long id){
        return categoryService.findProductCategoryById(id);
    }


}
