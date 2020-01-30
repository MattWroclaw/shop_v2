//package pl.mojeprojekty.shop_v2.restControllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import pl.mojeprojekty.shop_v2.entity.Commentary;
//import pl.mojeprojekty.shop_v2.entity.Product;
//import pl.mojeprojekty.shop_v2.entity.User;
//import pl.mojeprojekty.shop_v2.services.CommentaryService;
//import pl.mojeprojekty.shop_v2.services.ProductService;
//import pl.mojeprojekty.shop_v2.services.UserService;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/commentary")
//public class CommentaryRestController {
//
//    private final CommentaryService commentaryService;
//    private final UserService userService;
//    private final ProductService productService;
//
//    @GetMapping("{product_id}/{user_id}")
//    public Commentary showGivenCommentary(@PathVariable long product_id, @PathVariable long user_id){
//        User user = userService.findUserById(user_id);
//        Product product = productService.findProductById(product_id);
//        return commentaryService.findByUserAndProduct(user, product);
//    }
//
//    @PostMapping
//    public void createCommentary(@RequestBody User user, @RequestBody Product product, @RequestBody String text){
//        commentaryService.createCommentary(user, product, text);
//    }
//      TODO finish rest methodst for commentary
//}
