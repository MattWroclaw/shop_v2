package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
public class AllUsersDataController {

    private final UserService userService;

    @RequestMapping(value =  "/userSettings", method = RequestMethod.GET)
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
        return "redirect:/userSettings";
    }

    @GetMapping("/delete-user/{id}")
    public String delUser(@PathVariable long id){
        userService.deleteUser(id);
        return "redirect:/userSettings";
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable long id, Model model){
        User editedUser = userService.findUserById(id);
        model.addAttribute("edited", editedUser);
        return "edit-user";
    }

    @PostMapping("/edit-user-data/{id}")
    public String editForm(@ModelAttribute UserDto edited, @PathVariable long id){
        userService.editUser(id, edited);
        return "redirect:/userSettings";
    }
}
