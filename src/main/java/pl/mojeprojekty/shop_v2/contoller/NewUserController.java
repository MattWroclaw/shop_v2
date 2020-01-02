package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.AddressDto;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.services.UserService;
import pl.mojeprojekty.shop_v2.weather.WeatherRestService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class NewUserController {

    private final UserService userService;
    private final WeatherRestService weatherRestService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String goUser(Model model) {

        model.addAttribute("newUser", new UserDto());
        model.addAttribute("userAddress", new AddressDto());
        return "register";
    }

    @PostMapping("/newUser")
    public String goEditForm( @Valid @ModelAttribute("newUser") UserDto userForm, BindingResult errors) {

        if(errors.hasErrors()){
            return "register";
        }
        userService.createUser(userForm);
        return "redirect:/login";
    }

}
