package pl.mojeprojekty.shop_v2;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@SpringBootApplication
public class ShopV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ShopV2Application.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
