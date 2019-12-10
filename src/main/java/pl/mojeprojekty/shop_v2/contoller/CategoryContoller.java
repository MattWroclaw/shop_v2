package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.services.ProductCategoryService;

import javax.validation.Valid;
import java.awt.event.WindowAdapter;
import java.sql.PreparedStatement;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryContoller {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public String goCategories(Model model){
        List<ProductCategoryDto> categoriesDto = productCategoryService.showAllProductCategories();
        model.addAttribute("categories", categoriesDto);  //lista kategorii
        model.addAttribute("singleCategory", new ProductCategoryDto()); // pojedyncza kategoria - tworzenie
        return "category-list";
    }

    @PostMapping("/newCategory")
    public String makeCategoryFrom(@Valid @ModelAttribute ("singleCategory") ProductCategoryDto categoryDto,
                                   BindingResult result){
        if(result.hasErrors()){
            return "category-list";
        }
        productCategoryService.createProductCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable long id){
        productCategoryService.deleteProductCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/edit-category/{id}")
    public String showEditCategoryPage(@PathVariable long id, Model model){
        ProductCategoryDto editedCategoryDto = productCategoryService.findProductCategoryById(id);
        model.addAttribute("editedCategory", editedCategoryDto);

        List<ProductCategoryDto> categoryDtos = productCategoryService.showAllProductCategories();
        model.addAttribute("allCategories", categoryDtos);
        return "edit-category";
    }

    @PostMapping("/editCategory")
    public String processProductCategory(@ModelAttribute("singleProductDto") ProductCategoryDto editedDto){
        productCategoryService.editProductCategory(editedDto);
        return "redirect:/categories";
    }
}
