package pl.mojeprojekty.shop_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
