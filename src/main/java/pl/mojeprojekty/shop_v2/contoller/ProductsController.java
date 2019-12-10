package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductsController {

    private final ProductCategoryService productCategoryService;
    private final ProductService productService;

    @GetMapping("/products-settings")
    public String goProductCategory(Model model){
        List<ProductCategoryDto> productCategoriesDto = productCategoryService.showAllProductCategories();
        model.addAttribute("categoriesDto", productCategoriesDto); //lista kategorii do wyświetlenia

        List<ProductDto> productDtos = productService.findAllProductsDto();
        model.addAttribute("productsDto", productDtos); //lista produktów
        model.addAttribute("singleProductDto", new ProductDto()); //poj. produkt do stworzenia w form.
        return "products";
    }

    @PostMapping("/newProduct")
    public String createProductForm(@Valid @ModelAttribute("singleProductDto") ProductDto productDto,
                                    BindingResult result){
        if(result.hasErrors()){
            return "products";
        }
        productService.createProduct(productDto);
        return "redirect:/products-settings";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deletePorduct(id);
        return "redirect:/products-settings";
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
    public String editProductForm(@Valid @ModelAttribute("editedProduct") ProductDto editedProduct,
    BindingResult result){
        if (result.hasErrors()){
            return "edit-product";
        }
        productService.editProduct(editedProduct);
        return "redirect:/products-settings";
    }

}
