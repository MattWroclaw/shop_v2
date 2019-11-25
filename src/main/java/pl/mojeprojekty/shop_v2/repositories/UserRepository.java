package pl.mojeprojekty.shop_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojeprojekty.shop_v2.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

}
