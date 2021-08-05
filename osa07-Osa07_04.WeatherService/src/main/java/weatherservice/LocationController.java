package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationCache locCache;

    @GetMapping("/locations")
    public String list(Model model) {
        model.addAttribute("locations", locCache.all());
        return "locations";
    }

    @GetMapping("/locations/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("location", locCache.findOne(id));
        return "location";
    }

    @PostMapping("/locations")
    public String add(@ModelAttribute Location location) {
        locCache.saveLocation(location);
        return "redirect:/locations";
    }

    @GetMapping("/flushcaches")
    public String flushcaches() {
        locCache.flushAll();
        return "locations";
    }
}
