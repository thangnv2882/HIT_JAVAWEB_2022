package com.example.buoi4_1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {


    @Value("${abc.v2}")
    private String appName;

    @GetMapping
    public String getString() {
        return appName;
    }

    @GetMapping("/{id}")
    public String getId1(@PathVariable("id") String id) {
        return id;
    }

    @GetMapping("/{id}/{username}")
    public String getId2(
            @PathVariable("id") Long id,
            @PathVariable("username") String username
    ) {
        return id.toString() + " - " + username;
    }

    // required = false -> Không bắt buộc có trong param.
    // Mặc định required = true.
    @GetMapping("/app")
    public String getParam1(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "name2", required = false) String name2

            ) {

        return name + " - " + name2;
    }

}
