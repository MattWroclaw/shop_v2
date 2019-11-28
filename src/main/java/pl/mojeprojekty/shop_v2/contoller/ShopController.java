package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.entity.Role;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.UserRepository;
import pl.mojeprojekty.shop_v2.services.ApplicationUserDetailService;
import pl.mojeprojekty.shop_v2.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final UserService userService;

    @RequestMapping(value =  "/shop", method = RequestMethod.GET)
    public String goShop(Model model){
        List<User> users = userService.findAllUsers();
        Role role = new Role();
        model.addAttribute("newUser", new User());
        model.addAttribute("usersList", users);
        model.addAttribute("userRole", role);


        return "usersData";
    }

    @PostMapping("/createUser")
    public String goEditForm(@ModelAttribute UserDto userDto){
        userService.createUser(userDto);
        return "redirect:/shop";
    }

}
