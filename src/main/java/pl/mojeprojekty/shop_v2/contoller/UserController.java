package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.AddressDto;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.dto.WeatherDataDto;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.services.UserService;
import pl.mojeprojekty.shop_v2.services.WeatherRestService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WeatherRestService weatherRestService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String goUser(Model model) {

        model.addAttribute("newUser", new UserDto());
        model.addAttribute("userAddress", new AddressDto());
        return "register";
    }

    @PostMapping("/newUser")
    public String goEditForm(@ModelAttribute UserDto userForm) {

        userService.createUser(userForm);
        return "redirect:/login";
    }

    public  Model weatherHandler(Model model, Principal principal) {
        String userEmail = principal.getName();
        User loggedUser = userService.findUserByEmail(userEmail);
        String userCity = loggedUser.getCity();
        String[] dataWeather = weatherRestService.weatherData(userCity);
        if (dataWeather != null) {
           model.addAttribute("weatherFromOW", dataWeather);
           model.addAttribute("userCity", userCity);
        } else {
           model.addAttribute("noWeather", "Not available");
        }
        return model;
    }

//    @GetMapping("/secret")
    @GetMapping("/fragment")
    public String goSecret(Model model, Principal principal){
        weatherHandler(model, principal);
//        return "top";
        return "fragments";
    }
}
