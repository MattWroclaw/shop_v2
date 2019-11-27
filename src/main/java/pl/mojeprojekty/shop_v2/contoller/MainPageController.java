package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.services.CartService;
import pl.mojeprojekty.shop_v2.services.ProductService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping({"/", "/welcome"})
    public String goShop(Model model){
        List<ProductDto> allProductsDto = productService.findAllProductsDto();
        model.addAttribute("productsList", allProductsDto);
        return "index";
    }

    @GetMapping("/buy/{id}")
    public String addToCart(@PathVariable long id){
        cartService.addProductToCart(id);
        return "redirect:/welcome";
    }


}