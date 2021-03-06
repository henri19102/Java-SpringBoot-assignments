package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DefaultController {

    @Autowired
    private FilesRepo filesRepo;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/files";
    }

    @GetMapping("/files")
    public String files(Model model) {
        model.addAttribute("files", filesRepo.findAll());
        return "files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        FileObject fo = filesRepo.getOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.add("Content-Disposition", "attachment; filename=" + fo.getName());

        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }

    @PostMapping("/files")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fo = new FileObject();

        fo.setName(file.getOriginalFilename());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());

        filesRepo.save(fo);

        return "redirect:/files";
    }
}
