package com.hit.btvn_b3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthController {

    @GetMapping(value = {"/", "/login"})
    public String Login(Model model) {
        model.addAttribute("mess", check);
        return "login";
    }

    private static boolean check = false;

    @GetMapping("/check")
    public String getRes(@ModelAttribute User user) {
        User o = new User();
        if (o.Duyet(user)) {
            check = false;
            return "redirect:/users";
        }
        check = true;
        return "redirect:login";
    }

    @GetMapping("/users")
    public String getUserPage(Model model) {
        Store store = new Store();
        model.addAttribute("users", store.users);
        return "users";
    }

}
