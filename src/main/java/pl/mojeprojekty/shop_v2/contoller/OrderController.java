package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mojeprojekty.shop_v2.entity.Order;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.UserRepository;
import pl.mojeprojekty.shop_v2.services.OrderService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @GetMapping("/order")
    public String goOrder(Model model, Principal principal){
        String email = principal.getName();
        Order order = orderService.createOrder(email);
        User u = userRepository.findUserByEmail(email).get();
        model.addAttribute("order", order);
        model.addAttribute("userEmail", email);
        model.addAttribute("userZBazy", u);
        return "order-page";
    }
}
