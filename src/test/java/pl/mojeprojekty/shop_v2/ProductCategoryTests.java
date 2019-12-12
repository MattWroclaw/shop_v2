package pl.mojeprojekty.shop_v2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.ProductCategoryRepository;
import pl.mojeprojekty.shop_v2.repositories.UserRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WithMockUser(username = "testUser")
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@ExtendWith(SpringExtension.class)
public class ProductCategoryTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void steUp(){
        User user = new User();
        user.setEmail("testEmail");
        user.setName("testUserName");
        user.setPassword("p4ssw0rd");
        userRepository.save(user);
        testUser = user;
    }

    @AfterEach
    public void tearDown(){
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getAllProductCategories_Test() throws Exception {
//        given
        String categoryDescription1 = "description of test1 Category";
        String categoryDescription2 = "description of test2 Category";
        ProductCategory category1 = create(categoryDescription1);
        ProductCategory category2 = create(categoryDescription2);
        categoryRepository.save(category1);
        categoryRepository.save(category2);
//        when
        mockMvc.perform(
                get("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description", is(categoryDescription1)))
                .andExpect(jsonPath("$[1].description", is(categoryDescription2)));
    }

    private ProductCategory create(String description){
        ProductCategory entity = new ProductCategory();
        entity.setDescription(description);
//        entity.setParent(new ProductCategory());
//        entity.setChildrenCategories(Arrays.asList(new ProductCategory()));
        return entity;
    }
}
