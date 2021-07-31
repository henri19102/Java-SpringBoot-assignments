package hellosession;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSessionController {
    
    @RequestMapping("*")
    @ResponseBody
    public String sayHello(HttpSession ses) {
        if (ses.getAttribute("visit")==null){
            ses.setAttribute("visit", true);
            return "Hello there!";
        }
        return "Hello again!";
    }
}
