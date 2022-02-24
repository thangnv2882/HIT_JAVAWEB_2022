package com.hit.buoi_2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import com.hit.buoi_2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Buoi2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Buoi2Application.class, args);

        User user = context.getBean(User.class);
//        System.out.println(user);

        Bikini bikini = context.getBean(Bikini.class);


        Person person = context.getBean(Person.class);

//        person.wear();
    }

}