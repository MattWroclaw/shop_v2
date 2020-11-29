package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.repositories.ProductRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final Map<Long, Integer> productsIdInCart = new HashMap<>();
    private final ProductRepository productRepository;

    public Map<Long, Integer> addProductToCart(long productId) {
        boolean isExistProduct = productRepository.existsById(productId);
        if (isExistProduct) {
            boolean isProductInCart = productsIdInCart.containsKey(productId);
            if (isProductInCart) {
                int increasedNumberOfProductsInCart = productsIdInCart.get(productId) + 1;
                productsIdInCart.put(productId, increasedNumberOfProductsInCart);
            } else {
                productsIdInCart.put(productId, 1);
            }
        }
        return productsIdInCart;
    }

    public Map<Product, Integer> showProductsInCart() {
        Map<Product, Integer> productObjectCartMap = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : productsIdInCart.entrySet()) {
            long productId = entry.getKey();
            Product productAdded = productService.findProductById(productId);
            productObjectCartMap.put(productAdded, entry.getValue());
        }
        return productObjectCartMap;
    }

    public void removeProductFormCart(long productId) {
        boolean isProductExist = productRepository.existsById(productId);
        if (isProductExist) {
            productsIdInCart.remove(productId);
        }
    }

    public void emptyCart() {
        productsIdInCart.clear();
    }
}
