package poncha.kiet.session01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/sum")
    public String sum(@RequestParam int a, @RequestParam int b, Model model){
        // Nhã tham số gửi trên đường dẫn
        int s = a+b;
        model.addAttribute("s",s);
        return "result";
    }
}
