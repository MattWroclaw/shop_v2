package pl.mojeprojekty.shop_v2;

import org.junit.jupiter.api.AfterEach;
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

import java.util.List;

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

//    @BeforeEach
//    public void steUp(){
//        User user = new User();
//        user.setEmail("testEmail");
//        user.setName("testUserName");
//        user.setPassword("p4ssw0rd");
//        userRepository.save(user);
//        testUser = user;
//    }

    @AfterEach
    public void tearDown() {
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

    @Test
    public void getSingleCategory_Test() throws Exception {
//       given
        String description = "description of a single category";
        ProductCategory category = create(description);
        Long id = categoryRepository.save(category).getId();
//        when
        mockMvc.perform(
                get("/api/category/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(String.valueOf(id)))))
                .andExpect(jsonPath("$.description", is(description)));
    }

    @Test
    public void createCategory_Test() throws Exception {
//        given
        String categoryDescription = "category description";
        String categoryJSON = ("{\"description\":\"{description}\"}")
                .replace("{description}", categoryDescription);
//        when
        mockMvc.perform(
                post("/api/category").contentType(MediaType.APPLICATION_JSON).content(categoryJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is(categoryDescription)));
//        then
        List<ProductCategory> categories = categoryRepository.findAll();
        assertThat(categories).hasSize(1);

        ProductCategory result = categories.get(0);
        assertThat(result.getDescription()).isEqualTo(categoryDescription);
    }

    @Test
    public void createCategoryWithNotValidDescription_Test() throws Exception {
//        given
        String notValidDescription = "More than 30 chars: 1234567890, 1234567890, 1234567890";
        String categoryJson = ("{ \"id\":{id}, \"description\":   \"{description}\" }")
                .replace("{description}", notValidDescription);
//        when
        mockMvc.perform(
                post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON).content(categoryJson))
                .andExpect(status().isBadRequest());

        List<ProductCategory> categories = categoryRepository.findAll();
        assertThat(categories).hasSize(0);
    }

    @Test
    public void updateCategory() throws Exception {
//        given
        ProductCategory category = create("original description");
        categoryRepository.save(category);
        long id = category.getId();

        String newDescription = "new description";
        String categoryJson = ("{\"id\":\"{id}\",\"description\":\"{description}\"}")
                .replace("{id}", String.valueOf(id))
                .replace("{description}", newDescription);
//        when
        mockMvc.perform(put("/api/category/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson))
                .andExpect(status().isOk());
//          then
//        ProductCategory byId = categoryRepository.findById(id)
//                .orElseThrow(IllegalAccessException::new);
//        assertThat(byId.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void updateCategoryWithNotValidData() throws Exception {
//          given

        long categoryId = 1L;

        String notValidDescr = "too long description 1234567890, 1234567890.";
        String categoryJson = "{\"id\":{id}, \"description\":\"{description}\"}"
                .replace("{id}", String.valueOf(categoryId))
                .replace("{description}", notValidDescr);

//        when
        mockMvc.perform(
                put("/api/category/{id}", categoryId)
                        .contentType(MediaType.APPLICATION_JSON).content(categoryJson))
                .andExpect(status().isBadRequest());

//      then
        List<ProductCategory> categories = categoryRepository.findAll();
//        assertThat(categories).isEmpty();
        assertThat(categories).hasSize(0);
    }

    @Test
    public void deleteCategory() throws Exception {
//        given
        ProductCategory category = create("category to delete");
        categoryRepository.save(category);
        long id = category.getId();
//      when
        mockMvc.perform(delete("/api/category/{id}", id))
                .andExpect(status().isOk());
//        then
        assertThat(categoryRepository.findById(id)).isEmpty();

    }


    // ************* private method for creating category for test purpose
    private ProductCategory create(String description) {
        ProductCategory entity = new ProductCategory();
//        ProductCategory parent = new ProductCategory();
        entity.setDescription(description);
//        entity.setParent(parent);
//        entity.setChildrenCategories(Arrays.asList(new ProductCategory()));
        return entity;
    }
}
