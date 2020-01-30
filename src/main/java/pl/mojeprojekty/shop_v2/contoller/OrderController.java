package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mojeprojekty.shop_v2.entity.Order;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.UserRepository;
import pl.mojeprojekty.shop_v2.services.OrderService;
import pl.mojeprojekty.shop_v2.services.UserService;

import javax.jws.WebParam;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/order")
    public String goOrder(Model model, Principal principal){
        String email = principal.getName();
        Order order = orderService.createOrder(email);
        User u = userRepository.findUserByEmail(email).get();
        model.addAttribute("order", order);
        model.addAttribute("userEmail", email);
        model.addAttribute("userFromDB", u);
        return "order-page";
    }

    @GetMapping("your-shopping")
    public String goCustomerShopping(Model model, Principal principal){
        principal.getName();
        String userEmail = principal.getName();
        User loggedUser = userService.findUserByEmail(userEmail);
        List<Order> allOrdersOfUser = orderService.allOrdersOfUser(loggedUser);
        model.addAttribute("usersOrders", allOrdersOfUser);
//
        return "customer-page";
    }

}
