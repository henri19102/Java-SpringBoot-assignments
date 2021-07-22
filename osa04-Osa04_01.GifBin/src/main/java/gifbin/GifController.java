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
        List<FileObject> allGifs = filesRepo.findAll();
        int size = allGifs.size();
        FileObject current = filesRepo.getOne(id);
        int currentIndex = allGifs.indexOf(current);
        Long next = null;
        Long previous = null;
        if (currentIndex + 1 < size) {
            next = allGifs.get(currentIndex + 1).getId();
        }
        if (currentIndex - 1 >= 0) {
            previous = allGifs.get(currentIndex - 1).getId();
        }

        model.addAttribute("count", size);
        model.addAttribute("current", id);
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
        if (file.getContentType().equals("image/gif")) {
            FileObject fo = new FileObject();
            fo.setContent(file.getBytes());
            filesRepo.save(fo);
        }

        return "redirect:/gifs";
    }

}
