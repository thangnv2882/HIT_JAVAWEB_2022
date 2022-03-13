package com.hit.btvn_b4.controller;

import com.hit.btvn_b4.model.User;
import com.hit.btvn_b4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private static Long idDel;

    @GetMapping(value = {"/", "/login"})
    public String Login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("err", "");
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute User user, Model model) {
        List<User> users = userRepository.findAll();

        if (users.contains(user)) {
            return "redirect:/users";
        }
        model.addAttribute("err", "Tên đăng nhập hoặc mật khẩu không hợp lệ!");

        return "login";
    }


//    @RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/users")
    public String showListUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add-user")
    public String showFormAdd() {
        return "addUser";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "updateUser";
    }

    @RequestMapping(value = "/save-user", method = {RequestMethod.PATCH, RequestMethod.POST, RequestMethod.GET})
    public String updateUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        idDel = id;
        return "commitDelete";
    }

    @GetMapping("/commit-delete")
    public String commitDelete() {
        userRepository.deleteById(idDel);
        return "redirect:/users";
    }

}
