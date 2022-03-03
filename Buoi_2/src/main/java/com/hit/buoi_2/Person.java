package com.hit.buoi_2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    public Bikini bikini;

    public void wear() {
        System.out.println("Đang mặc....");
    }
}
