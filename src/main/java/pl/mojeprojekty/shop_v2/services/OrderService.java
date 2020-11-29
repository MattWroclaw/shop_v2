package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.entity.Order;
import pl.mojeprojekty.shop_v2.entity.OrderLine;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.User;
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
    private final UserService userService;
    private final ProductService productService;

    public Order createOrder(String email) {
        User userByEmail = userService.findUserByEmail(email);
        List<OrderLine> orderLines = new ArrayList<>();
        double totalPrice = 0;

        Map<Product, Integer> productsInCart = cartService.showProductsInCart();
        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            Product boughtProduct = entry.getKey();
            Integer quantityOfBoughtProducts = entry.getValue();

            double productPrice = entry.getKey().getPrice();
            double orderLinePrice = (productPrice * quantityOfBoughtProducts);
            totalPrice += orderLinePrice;

            int stockAmount = boughtProduct.getStockAmount();
            int stockAmountAfterPurchase = stockAmount - quantityOfBoughtProducts;
            if (stockAmountAfterPurchase > 0) {
                boughtProduct.setStockAmount(stockAmountAfterPurchase);
                productService.updateProduct(boughtProduct);
            } else {
                productService.deleteProduct(boughtProduct.getId());
            }

            OrderLine orderLine = createOrderLine(quantityOfBoughtProducts, boughtProduct, orderLinePrice);
            orderLines.add(orderLine);
        }

        Order order = setOrder(orderLines, totalPrice, userByEmail);
        saveOrderAndEmptyCart(order);

        return order;
    }

    private OrderLine createOrderLine(int quantityOfBoughtProducts, Product boughtProduct, double orderLinePrice) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(quantityOfBoughtProducts);
        orderLine.setProduct(boughtProduct);
        orderLine.setPrice(orderLinePrice);

        return orderLine;
    }

    private Order setOrder(List<OrderLine> orderLines, double totalPrice, User userByEmail) {
        LocalDate orderDate = LocalDate.now();
        Order order = new Order();

        order.setOrderDate(orderDate);
        order.setOrderLines(orderLines);
        order.setTotalPrice(totalPrice);
        order.setUser(userByEmail);

        return order;
    }

    private void saveOrderAndEmptyCart(Order order) {
        orderRepository.save(order);
        cartService.emptyCart();
    }

    public List<Order> allOrdersOfUser(User user) {
        return orderRepository.findByUser(user);
    }
}
