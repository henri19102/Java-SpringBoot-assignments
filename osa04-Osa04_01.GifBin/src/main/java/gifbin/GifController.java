package gifbin;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GifController {

    @Autowired
    private FilesRepo filesRepo;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/gifs";
    }

    @GetMapping("/gifs")
    public String home() {
        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String gifs(Model model, @PathVariable Long id) {
        int count = filesRepo.findAll().size() > 0 ? filesRepo.findAll().size() : 0;
        FileObject current = null;
        FileObject next = null;
        FileObject previous = null;

        if (count > 0) {
            current = filesRepo.getOne(id);
        }


        model.addAttribute("count", count);
        model.addAttribute("current", current);
        model.addAttribute("next", next);
        model.addAttribute("previous", previous);

        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return filesRepo.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fo = new FileObject();
        fo.setContent(file.getBytes());

        filesRepo.save(fo);

        return "redirect:/gifs";
    }

}
