package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.services.CartService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String goCartPage(Model model){
        Map<Product, Integer> productMap = cartService.showProductsInCart();
        model.addAttribute("cart", productMap);
        return "cart";
    }

    @GetMapping("/removeItems/{id}")
    public String removeFromCart(@PathVariable long id){
        cartService.removeProductFormCart(id);
        return "redirect:/cart";
    }
}
