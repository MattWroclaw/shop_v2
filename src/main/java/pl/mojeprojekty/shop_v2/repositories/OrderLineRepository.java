package pl.mojeprojekty.shop_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojeprojekty.shop_v2.entity.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
