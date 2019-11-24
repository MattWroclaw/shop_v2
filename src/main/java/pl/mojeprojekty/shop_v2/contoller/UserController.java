package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mojeprojekty.shop_v2.dto.AddressDto;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.dto.WeatherDataDto;
import pl.mojeprojekty.shop_v2.services.UserService;
import pl.mojeprojekty.shop_v2.services.WeatherRestService;

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


    public void weatherHandler(Model model, String city) {
       String[] wroclawTemp = weatherRestService.weatherData("wroclaw12");
        if (wroclawTemp != null) {
            model.addAttribute("weatherFromOW", wroclawTemp);
        } else {
            model.addAttribute("noWeather", "Not available");
        }
    }

    @GetMapping("/secret")
    public String goSecret(Model model){
        weatherHandler(model, "");
        return "top";
    }
}
