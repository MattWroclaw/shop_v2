package pl.mojeprojekty.shop_v2.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShopController {

    @RequestMapping(value = {"/", "/shop"}, method = RequestMethod.GET)
    public String goShop(){
        return "index";
    }
}
