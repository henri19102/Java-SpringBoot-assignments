/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Henri
 */
@Controller
public class FileController {
     @Autowired
    private FilesRepo filesRepo;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/files";
    }

    @GetMapping("/files")
    public String home(Model model) {
        model.addAttribute("files", filesRepo.findAll());
        return "files";
    }


@PostMapping("/files")
public String save(@RequestParam("file") MultipartFile file) throws IOException {
    FileObject fo = new FileObject();

    fo.setName(file.getOriginalFilename());
    fo.setMediaType(file.getContentType());
    fo.setSize(file.getSize());
    fo.setContent(file.getBytes());

    filesRepo.save(fo);

    return "redirect:/files";
}

}
