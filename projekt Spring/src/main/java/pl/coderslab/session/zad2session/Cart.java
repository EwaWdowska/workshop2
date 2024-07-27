import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.session.zad2session.CartItem;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addToCart(CartItem cartItem) {
        this.items.add(cartItem);
    }

    public boolean containsProductWithId(long productId) {
        return items.stream().map(c -> c.getProduct().getId()).anyMatch(prodId -> prodId == productId);
    }

    public void incrementQuantity(Product product, int quantity) {
        if (containsProductWithId(product.getId())) {
            for (CartItem item : items) {
                if (Objects.equals(item.getProduct().getId(), product.getId())) {
                    item.setQuantity(item.getQuantity() + quantity);
                }
            }
        } else {
            addToCart(new CartItem(quantity, product));
        }
    }

    public int getCurrentPositions() {
        return this.items.size();
    }

    public int getNumberOfItems() {
        return items.stream().map(CartItem::getQuantity).reduce(0, Integer::sum);
    }

    public double getTotalPrice() {
        return items.stream().map(item -> item.getProduct().getPrice() * item.getQuantity()).reduce(0d, Double::sum);
    }
}