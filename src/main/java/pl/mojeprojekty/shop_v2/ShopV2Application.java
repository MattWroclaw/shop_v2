package pl.mojeprojekty.shop_v2;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class ShopV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ShopV2Application.class, args);
    }

}
