package com.hit.btvn_b3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping(value = {"/", "/login"})
    public String Login(Model model) {
        model.addAttribute("mess", check);
        return "login";
    }

    private static boolean check = false;
    @PostMapping("/users")
    public String getUserPage(@ModelAttribute User user, Model model) {
        User o = new User();
        if (o.Duyet(user)) {
            check = false;
            Store store = new Store();
            model.addAttribute("users", store.users);
            return "users";
        }
        check = true;
        return "redirect:login";
    }
}
