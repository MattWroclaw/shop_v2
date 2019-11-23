package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.AddressDto;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.entity.Role;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.services.UserService;

import javax.print.DocFlavor;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = { "/register"}, method = RequestMethod.GET)
    public String goUser(Model model){

        model.addAttribute("newUser", new UserDto());
        model.addAttribute("userAddress", new AddressDto());
        return "register";
    }

    @PostMapping("/newUser")
    public String goEditForm(@ModelAttribute UserDto userForm){

        userService.createUser(userForm);
        return "redirect:/login";
    }

    @GetMapping("/secret")
    public String goSecret(){
        return "top";
    }
}
