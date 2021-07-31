package euroshopper;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private ItemRepository repo;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("items", cart.getItems());
        model.addAttribute("sum", 1);
        return "cart";
    }

    @PostMapping("/cart/items/{id}")
    public String postCart(@PathVariable Long id) {
        Item item = repo.getOne(id);
        cart.addToCart(item);
        return "redirect:/cart";
    }
}
