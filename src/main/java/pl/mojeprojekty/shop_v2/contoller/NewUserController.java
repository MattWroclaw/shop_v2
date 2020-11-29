package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mojeprojekty.shop_v2.dto.AddressDto;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.services.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class NewUserController {

    private final UserService userService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String goRegisterNewUser(Model model) {
        model.addAttribute("newUser", new UserDto());
        model.addAttribute("userAddress", new AddressDto());
        return "register";
    }

    @PostMapping("/newUser")
    public String goEditFormForNewUser(@Valid @ModelAttribute("newUser") UserDto userForm, BindingResult errors) {

        if (errors.hasErrors()) {
            return "register";
        }
        userService.createUser(userForm);
        return "redirect:/login";
    }

}
