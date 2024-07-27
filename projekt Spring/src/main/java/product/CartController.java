package product;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class CartController {
    private final Cart cart;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    @RequestMapping("/addtocart")
    @ResponseBody
    public String addtocart() {
        Random rand = new Random();
        cart.addToCart(new CartItem(1, new Product("prod" + rand.nextInt(10), rand.nextDouble())));
        return "addtocart";
    }

    @RequestMapping(value = "/cart",  produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String listCart() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CartItem cartItem : cart.getItems()) {
            stringBuilder.append(cartItem).append("\n");
        }
        return stringBuilder.toString();
    }

}