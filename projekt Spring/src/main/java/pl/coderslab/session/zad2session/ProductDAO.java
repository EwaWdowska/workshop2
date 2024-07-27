import org.springframework.stereotype.Component;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProductDAO {

    public List<Product> getList() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1L, "Skateboard", 1999.99d));
        list.add(new Product(2L, "Rollerblades", 999.99d));
        list.add(new Product(3L, "Bike", 11999.99d));
        list.add(new Product(4L, "Scooter", 7999.99d));
        return list;
    }

    public Optional<Product> findProductById(Long id) {
        return getList().stream().filter(product -> Objects.equals(product.getId(), id)).findFirst();
    }
}