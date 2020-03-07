package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.entity.Order;
import pl.mojeprojekty.shop_v2.entity.OrderLine;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.OrderLineRepository;
import pl.mojeprojekty.shop_v2.repositories.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final UserService userService;
    private final ProductService productService;

    public Order createOrder(String email) {
        Order order = new Order();

        LocalDate orderDate = LocalDate.now();
        List<OrderLine> orderLines = new ArrayList<>();
        double totalPrice = 0;
        User userByEmail = userService.findUserByEmail(email);

        Map<Product, Integer> productsInCart = cartService.showProductsInCart();
        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            Product aProduct = entry.getKey();
            Integer quantity = entry.getValue();
            double orderLinePrice = (entry.getKey().getPrice() * quantity);

            int stockAmount = aProduct.getStockAmount();
            int newStockAmount = stockAmount - quantity;
            if (newStockAmount > 0) {
                aProduct.setStockAmount(newStockAmount);
                productService.updateProduct(aProduct);
            } else {
                productService.deletePorduct(aProduct.getId());
            }

            OrderLine orderLine = new OrderLine();
            orderLine.setQuantity(quantity);
            orderLine.setProduct(aProduct);
            orderLine.setPrice(orderLinePrice);
            orderLines.add(orderLine);
            totalPrice += orderLinePrice;
        }

        order.setOrderDate(orderDate);
        order.setOrderLines(orderLines);
        order.setTotalPrice(totalPrice);
        order.setUser(userByEmail);

        orderRepository.save(order);
        cartService.emptyCart();
        return order;
    }

    public List<Order> allOrdersOfUser(User user){
        return orderRepository.findByUser(user);
    }
}
