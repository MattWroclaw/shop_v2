package pl.mojeprojekty.shop_v2;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.entity.ProductType;
import pl.mojeprojekty.shop_v2.repositories.ProductCategoryRepository;
import pl.mojeprojekty.shop_v2.repositories.ProductRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-integration-test.properties")
@ExtendWith(SpringExtension.class)
 class ProductTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        productCategoryRepository.deleteAll();

    }

    @Test
     void showAllProducts_Test() throws Exception {
//        given

        ProductCategory category1 = createProductCategory("category1");
        ProductCategory category2 = createProductCategory("category2");

        Product product1 = createProduct( "title1", "descr1",
                1.0, 1, ProductType.FREEDOM, new ProductCategory());

        Product product2 = createProduct( "title2", "descr2",
                2.0, 2, ProductType.FRIENDS, new ProductCategory());

//       when
        productCategoryRepository.save(category1);
        productCategoryRepository.save(category2);
        productRepository.save(product1);
        productRepository.save(product2);
//        then

        mockMvc.perform(
                get("/api/product").contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description", is("descr1")))
                .andExpect(jsonPath("$[1].description", is("descr2")))
                .andExpect(jsonPath("$[0].price", is(1.0)))
                .andExpect(jsonPath("$[1].price", is(2.0)))
                .andExpect(jsonPath("$[0].title", is("title1")))
                .andExpect(jsonPath("$[1].title", is("title2")))
                .andExpect(jsonPath("$[0].productType", is("FREEDOM")))
                .andExpect(jsonPath("$[1].productType", is("FRIENDS")))
                .andExpect(jsonPath("$[0].productCategory", is(notNullValue())));
    }

    @Test
    public void showProductWithGivenId_Test() throws Exception {
//        given
        ProductCategory category1 = createProductCategory("category1");
        productCategoryRepository.save(category1);
        Product product1 = createProduct( "title1", "descr1",
                1.0, 1, ProductType.FREEDOM, new ProductCategory());
        productRepository.save(product1);
        long productId = productRepository.save(product1).getId();
//        when
        mockMvc.perform(get("/api/product/{id}", productId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(String.valueOf(productId)))))
                .andExpect(jsonPath("$.description", is("descr1")))
                .andExpect(jsonPath("$.price", is(1.0)))
                .andExpect(jsonPath("$.productType", is("FREEDOM")))
                .andExpect(jsonPath("$.productCategory", is(notNullValue())));
    }

    @Test
    public void createProduct_Test() throws Exception {
//        given
        String title = "test title";
        String description = "test description";
        double price = 100.99;
        int stockAmount = 1;
        String productType = "FREEDOM";
        String productCategoryDescription = "test product category description";

        String productJson = ("{\"title\":\"{title}\"," +
                "\"description\":\"{description}\"," +
                "\"price\":\"{price}\"," +
                "\"stockAmount\":\"{stockAmount}\"," +
                "\"productType\":\"{productType}\"," +
                "\"productCategory\":{\"description\":\"{productCategoryDescription}\"}" +
                "}")
                .replace("{title}", title)
                .replace("{description}", description)
                .replace("{price}", String.valueOf(price))
                .replace("{stockAmount}", String.valueOf(stockAmount))
                .replace("{productType}", productType)
                .replace("{productCategoryDescription}", productCategoryDescription);

//        when
        log.info("TEST FOR TITLE: " + JsonPath.read(productJson, "$.title"));
        mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isCreated());
//      then
        List<Product> list = productRepository.findAll();
        assertThat(list).hasSize(1);

        Product createdProduct = list.get(0);
        assertThat(createdProduct.getTitle()).isEqualTo(title);
        assertThat(createdProduct.getPrice() == 100.99);
        assertThat(createdProduct.getProductType()).isEqualTo(ProductType.valueOf(productType));
    }

    @Test
    public void createProduct_WithInvalidData_ThrowsErrorPage() throws Exception {
//        given
        String title_invalid = "title with > 12 chars 123456789012";
        String description = "test description";
        double price_invalid = 123456789.12345;
        int stockAmount = 1;
        String productType = "FREEDOM";
        String productCategoryDescription_invalid = "";

        String productJson = ("{\"title\":\"{title}\"," +
                "\"description\":\"{description}\"," +
                "\"price\":\"{price}\"," +
                "\"stockAmount\":\"{stockAmount}\"," +
                "\"productType\":\"{productType}\"," +
                "\"productCategory\":{\"description\":\"{productCategoryDescription}\"}" +
                "}")
                .replace("{title}", title_invalid)
                .replace("{description}", description)
                .replace("{price}", String.valueOf(price_invalid))
                .replace("{stockAmount}", String.valueOf(stockAmount))
                .replace("{productType}", productType)
                .replace("{productCategoryDescription}", productCategoryDescription_invalid);
//        when
        mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isOk());
//        then
        List<Product> list = productRepository.findAll();
        assertThat(list).isEmpty();
    }

    @Test
    public void updateProduct_Test() throws Exception {
//        given

        Product product1 = new Product();
        product1.setTitle("original title");
        product1.setDescription("original description");
        product1.setStockAmount(100);
        product1.setPrice(0.99);
        product1.setProductType(ProductType.WEALTH);

        long product_Id = productRepository.save(product1).getId();
//  when
        String title = "test title";
        String description = "test description";
        double price = 100.99;
        int stockAmount = 1;
        String productType = "FREEDOM";

        String productJson = ("{\"id\":\"{id}\"," +
                "\"title\":\"{title}\"," +
                "\"description\":\"{description}\"," +
                "\"price\":\"{price}\"," +
                "\"stockAmount\":\"{stockAmount}\"," +
                "\"productType\":\"{productType}\"" +
                "}")
                .replace("{id}", String.valueOf(product_Id))
                .replace("{title}", title)
                .replace("{description}", description)
                .replace("{price}", String.valueOf(price))
                .replace("{stockAmount}", String.valueOf(stockAmount))
                .replace("{productType}", productType);

        mockMvc.perform(put("/api/product/{id}", product_Id).contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isOk());
//        then

        Product result = productRepository.findById(product_Id).orElseThrow(IllegalArgumentException::new);
        assertThat(result.getTitle()).isEqualTo(title);
    }

    @Test
    public void updateProduct_WithInvalidData_ThrowsErrorPage() throws Exception {
//        given
        Product product1 = new Product();
        product1.setTitle("original title");
        product1.setDescription("original description");
        product1.setStockAmount(100);
        product1.setPrice(0.99);
        product1.setProductType(ProductType.WEALTH);

        long product_Id = productRepository.save(product1).getId();
//        when

        String title_invalid = "title with > 12 chars 123456789012";
        String description = "test description";
        double price_invalid = 123456789.12345;
        int stockAmount = 1;
        String productType = "FREEDOM";
        String productCategoryDescription_invalid = "";

        String productJson = ("{\"title\":\"{title}\"," +
                "\"description\":\"{description}\"," +
                "\"price\":\"{price}\"," +
                "\"stockAmount\":\"{stockAmount}\"," +
                "\"productType\":\"{productType}\"," +
                "\"productCategory\":{\"description\":\"{productCategoryDescription}\"}" +
                "}")
                .replace("{title}", title_invalid)
                .replace("{description}", description)
                .replace("{price}", String.valueOf(price_invalid))
                .replace("{stockAmount}", String.valueOf(stockAmount))
                .replace("{productType}", productType)
                .replace("{productCategoryDescription}", productCategoryDescription_invalid);

        mockMvc.perform(put("/api/product/{id}", product_Id).contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isOk());
//        then
        Product result = productRepository.findById(product_Id)
                .orElseThrow(IllegalArgumentException::new);
        assertThat(result.getTitle()).isEqualTo("original title");
        assertThat(result.getProductType()).isEqualTo(ProductType.WEALTH);
        assertThat(result.getPrice() == 0.99);
        assertThat(result.getStockAmount() == 100);
    }

    @Test
    public void deleteProduct_Test() throws Exception {
//        given
        Product product = new Product();
        long id = productRepository.save(product).getId();
        Product result = productRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Product.class);
//        when

        mockMvc.perform(delete("/api/product/{id}", id))
                .andExpect(status().isOk());
//        then
        List<Product> list = productRepository.findAll();
        assertThat(list).isEmpty();
    }

    // *****************private methods for test purpose ***********
    private ProductCategory createProductCategory(String description) {
        ProductCategory category = new ProductCategory();
        category.setDescription(description);
        return category;
    }

    private Product createProduct(
                                  String title, String description,
                                  Double price, int stockAmount, ProductType type,
                                  ProductCategory category) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setProductCategory(category);
        product.setStockAmount(stockAmount);
        product.setProductType(type);
        product.setPrice(price);
        return product;
    }

}
