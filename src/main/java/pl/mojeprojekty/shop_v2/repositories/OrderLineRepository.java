package pl.mojeprojekty.shop_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojeprojekty.shop_v2.entity.OrderLine;
import sun.rmi.runtime.Log;

public interface OrderLineRepository extends JpaRepository<OrderLine, Log> {
}
