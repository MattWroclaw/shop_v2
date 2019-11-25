package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.services.ProductCategoryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductsController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/product-category")
    public String goProductCategory(Model model){
        List<ProductCategory> productCategories = productCategoryService.showAllProductCategories();
        model.addAttribute("categories", productCategories);
        model.addAttribute("singleCategorie", new ProductCategoryDto());
        return "products";
    }

    @PostMapping("/createCategory")
    public String createFormular(ProductCategoryDto productCategoryDto){
        productCategoryService.createProductCategory(productCategoryDto);
        return "redirect:/product-category";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteProdctCategory(@PathVariable long id){
        productCategoryService.deleteProductCategory(id);
        return "redirect:/product-category";
    }

    @GetMapping("/edit-category/{id}")
    public String showEditCategoryPage(@PathVariable long id, Model model){
        ProductCategory editedCategory = productCategoryService.findProductCategoryById(id);
        model.addAttribute("editedCategory", editedCategory);
        return "edit-category";
    }

    @PostMapping("/editCategory")
    public String processProductCategory(ProductCategoryDto editedDto){
        productCategoryService.editProductCategory(editedDto);
        return "redirect:/product-category";
    }
}
