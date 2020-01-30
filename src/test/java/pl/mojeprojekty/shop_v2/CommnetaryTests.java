//package pl.mojeprojekty.shop_v2;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import pl.mojeprojekty.shop_v2.entity.Commentary;
//import pl.mojeprojekty.shop_v2.entity.Product;
//import pl.mojeprojekty.shop_v2.entity.User;
//import pl.mojeprojekty.shop_v2.repositories.CommentaryRepository;
//import pl.mojeprojekty.shop_v2.repositories.ProductRepository;
//import pl.mojeprojekty.shop_v2.repositories.UserRepository;
//import pl.mojeprojekty.shop_v2.services.CommentaryService;
//import pl.mojeprojekty.shop_v2.services.ProductService;
//
//import javax.jws.soap.SOAPBinding;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource("\"classpath:application-integration-test.properties")
//@ExtendWith(SpringExtension.class)
//public class CommnetaryTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    CommentaryRepository commentaryRepository;
//
//    @Autowired
//    CommentaryService commentaryService;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @AfterEach
//    public void tearDown(){
//        commentaryRepository.deleteAll();
//        userRepository.deleteAll();
//        productRepository.deleteAll();
//    }
//
//    @Test
//    public void createComment_Test(){
////        given
//        Product product = new Product();
//        product.setPrice(12);
//        product.setStockAmount(10);
//        product.setDescription("product description");
//        product.setTitle("product test title");
//        productRepository.save(product);
//
//        User user = new User();
//        user.setName("test user");
//        user.setEmail("email@email.com");
//        user.setCity("test user city");
//        userRepository.save(user);
//
//        String text = "This is a test commentary for a given product >TEST PRODUCT<, of test User >TEST USER<";
//
//        Commentary commentary = new Commentary();
//        commentary.setText(text);
//        commentary.setUser(user);
//        commentary.setProduct(product);
//        commentaryRepository.save(commentary);
////        when
//        commentaryService.createCommentary(user, product, text);
////        then
////      TODO finish tests
//    }
//
//}
