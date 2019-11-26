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
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.services.ProductCategoryService;
import pl.mojeprojekty.shop_v2.services.ProductService;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductsController {

    private final ProductCategoryService productCategoryService;
    private final ProductService productService;

    @GetMapping("/product-category")
    public String goProductCategory(Model model){
        List<ProductCategoryDto> productCategoriesDto = productCategoryService.showAllProductCategories();
        model.addAttribute("categoriesDto", productCategoriesDto);
        model.addAttribute("singleCategorieDto", new ProductCategoryDto());

        List<ProductDto> productDtos = productService.findAllProductsDto();
        model.addAttribute("productsDto", productDtos);
        model.addAttribute("singleProductDto", new ProductDto());
        return "products";
    }
// ******************** CATEGORIES ***************************
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
        ProductCategoryDto editedCategory = productCategoryService.findProductCategoryById(id);
        model.addAttribute("editedCategory", editedCategory);
        return "edit-category";
    }

    @PostMapping("/editCategory")
    public String processProductCategory(@ModelAttribute ProductCategoryDto editedDto){
        productCategoryService.editProductCategory(editedDto);
        return "redirect:/product-category";
    }
//    ****************** PRODUCTS ********************

    @PostMapping("/createProduct")
    public String createProductForm(ProductDto productDto){
        productService.createProduct(productDto);
        return "redirect:/product-category";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deletePorduct(id);
        return "redirect:/product-category";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable long id, Model model){
        ProductDto editedProductDto = productService.findProductDtoById(id);
        List <ProductCategoryDto> categoriesDto = productCategoryService.showAllProductCategories();
        model.addAttribute("editedProduct", editedProductDto);
        model.addAttribute("categories" , categoriesDto);
        return "edit-product";
    }

    @PostMapping("/editProduct")
    public String editProductForm(@ModelAttribute ProductDto editedProduct){
        productService.editProduct(editedProduct);
        return "redirect:/product-category";
    }

}
