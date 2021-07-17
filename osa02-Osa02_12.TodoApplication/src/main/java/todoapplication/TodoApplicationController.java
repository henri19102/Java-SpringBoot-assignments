package todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {

    @Autowired
    private ItemRepository itemRepo;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("items", itemRepo.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        Item check = itemRepo.getOne(id);
        check.add();
        itemRepo.save(check);
        model.addAttribute("item", itemRepo.getOne(id));
        return "todo";
    }

    @PostMapping("/")
    public String create(@RequestParam String name) {
        itemRepo.save(new Item(name));
        return "redirect:/";
    }
}
