package reloadheroes;

import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    public static String generateName(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    @RequestMapping("*")
    public String reload(Model model) {
        if (session.getAttribute("name") == null) {
            String name = generateName(5);
            session.setAttribute("name", name);

            ReloadStatus status = new ReloadStatus(name, 1);
            reloadStatusRepository.save(status);
            model.addAttribute("status", status);
            Pageable sorted = PageRequest.of(0, 5, Sort.by("reloads").descending());
            model.addAttribute("scores", reloadStatusRepository.findAll(sorted));
            return "index";

        }
        ReloadStatus reload = reloadStatusRepository.findByName(session.getAttribute("name").toString());
        int newReload = reload.getReloads() + 1;
        reload.setReloads(newReload);
        reloadStatusRepository.save(reload);
        model.addAttribute("status", reload);
        Pageable sorted = PageRequest.of(0, 5, Sort.by("reloads").descending());
        model.addAttribute("scores", reloadStatusRepository.findAll(sorted));
        return "index";

    }
}
