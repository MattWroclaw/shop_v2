package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mojeprojekty.shop_v2.dto.ProductDto;
import pl.mojeprojekty.shop_v2.entity.Commentary;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.ProductType;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.services.CartService;
//import pl.mojeprojekty.shop_v2.services.CommentaryService;
import pl.mojeprojekty.shop_v2.services.ProductService;
import pl.mojeprojekty.shop_v2.services.UserService;
import pl.mojeprojekty.shop_v2.utils.DtoToObjectConverters;
import pl.mojeprojekty.shop_v2.weather.WeatherRestService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;
    private final WeatherRestService weatherRestService;
//    private final CommentaryService commentaryService;
    private final DtoToObjectConverters converters;

    @GetMapping({"/", "/welcome"})
    public String goShop(Model model, Principal principal) {
        List<ProductDto> allProductsDto = productService.findAllProductsDto();
        model.addAttribute("productsList", allProductsDto);

        weatherHandler(model, principal);
        return "index";
    }

    @GetMapping("/buy/{id}")
    public String addToCart(@PathVariable long id) {
        cartService.addProductToCart(id);
        return "redirect:/welcome";
    }

    @GetMapping("/byType/{type}")
    public String goCategory(@PathVariable String type, Model model) {
        ProductType productType = ProductType.valueOf(type);
        List<ProductDto> productsByType = productService.findProductsByType(productType);
        model.addAttribute("productsOfType", productsByType);
        return "by-type";
    }

    @GetMapping("/product-details/{id}")
    public String goProductDetails(@PathVariable long id, Model model) {
        ProductDto productDto = productService.findProductDtoById(id);
        model.addAttribute("givenProduct", productDto);

//        for the commentary

//  *********      TODO finish commentary feature *****************
//        Product product = converters.productDtoToEntity(productDto);
//        Commentary commentary = commentaryService.getCommentaryForProduct(product);
//        ********************************************
        return "productDetails";
    }

    @GetMapping("test2")
    public String test2() {
        return "test2";
    }


    //    Weather service

    public Model weatherHandler(Model model, Principal principal) {
        String userEmail = "";
        String userCity = "";
        if (principal != null) {
            principal.getName();
            userEmail = principal.getName();
            User loggedUser = userService.findUserByEmail(userEmail);
            userCity = loggedUser.getCity();
        } else {
            userCity = "Wroclaw";
        }

        String[] dataWeather = weatherRestService.weatherData(userCity);
        if (dataWeather != null) {
            model.addAttribute("weatherFromOW", dataWeather);
            model.addAttribute("userCity", userCity);
        } else {
            model.addAttribute("noWeather", "Not available");
        }
        return model;

    }

}
