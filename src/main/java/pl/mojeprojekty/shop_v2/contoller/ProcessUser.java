package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mojeprojekty.shop_v2.dto.UserDto;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.services.UserService;

@Controller
@RequiredArgsConstructor
public class ProcessUser {

    private  final UserService userService;

    @GetMapping("/delete-user/{id}")
    public String delUser(@PathVariable long id){
        userService.deleteUser(id);
        return "redirect:/shop";
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
        return "redirect:/shop";
    }
}
