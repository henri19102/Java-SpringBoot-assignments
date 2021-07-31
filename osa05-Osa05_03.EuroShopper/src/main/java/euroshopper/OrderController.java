package euroshopper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCart cart;

    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {

        List<OrderItem> orderItems = new ArrayList<>();
        for(Item item : cart.getItems().keySet()){
            orderItems.add(new OrderItem(item, cart.getItems().get(item)));
        }
        
        
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setOrderItems(orderItems);

        orderRepository.save(order);
        cart.clearAll();

        return "redirect:/orders";
    }
}
