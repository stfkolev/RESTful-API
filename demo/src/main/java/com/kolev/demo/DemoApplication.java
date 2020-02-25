package com.kolev.demo;

import com.kolev.demo.exceptions.UserAlreadyExists;
import com.kolev.demo.models.Order;
import com.kolev.demo.models.User;
import com.kolev.demo.repositories.OrderRepository;
import com.kolev.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
////    public CommandLineRunner mappingDemo(UserRepository userRepository, OrderRepository orderRepository) {
////       return args -> {
////           var user = new User("Dinev", "mitko.dinev@gmail.com");
////           var userFound = userRepository.findByEmail(user.getEmail());
////
////           if(userFound != null) {
////               throw new UserAlreadyExists();
////           }
////
////           userRepository.save(user);
////
////           orderRepository.save(new Order("Milk", user));
////           orderRepository.save(new Order("Bread", user));
////           orderRepository.save(new Order("Eggs", user));
////       };
////    }
}
