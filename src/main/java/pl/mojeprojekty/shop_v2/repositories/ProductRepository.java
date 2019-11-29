package pl.mojeprojekty.shop_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.ProductType;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductType(ProductType productType);
}
