package pl.mojeprojekty.shop_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojeprojekty.shop_v2.entity.Commentary;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.User;

import java.util.Optional;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    Commentary findByUserAndProduct(User user, Product product);

    Optional<Commentary> findByProduct(Product product);
}
