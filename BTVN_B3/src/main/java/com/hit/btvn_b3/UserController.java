package com.hit.btvn_b3;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getListUsers() {
        Store store = new Store();
        return ResponseEntity.ok(store.users);
    }
}
