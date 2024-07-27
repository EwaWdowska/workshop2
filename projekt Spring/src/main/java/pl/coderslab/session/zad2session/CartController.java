package pl.coderslab.session.zad2session;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import product.Cart;
import product.Product;

import java.util.Optional;

@Controller
public class CartController {
    private final Cart cart;
    private final ProductDAO productDAO;

    public CartController(Cart cart, ProductDAO productDAO) {
        this.cart = cart;
        this.productDAO = productDAO;
    }

    @RequestMapping("/addtocart/{id}")
    @ResponseBody
    public String addToCart(@PathVariable long id, @RequestParam(required = false, defaultValue = "1") int quantity) {
        Optional<Product> productCandidate = productDAO.findProductById(id);
        if (productCandidate.isPresent()) {
            Product product = productCandidate.get();
            cart.incrementQuantity(product, quantity);
        }
        return "addtocart";
    }

    @RequestMapping(value = "/cart",  produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String listCart() {
        return String.format("W koszyku jest %d pozycji.\nW koszyku jest %d produktów.\nWartość koszyka to: %.2f.", cart.getCurrentPositions(), cart.getNumberOfItems(), cart.getTotalPrice());
    }

}