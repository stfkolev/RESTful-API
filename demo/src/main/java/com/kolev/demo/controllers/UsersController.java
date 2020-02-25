package com.kolev.demo.controllers;

import com.kolev.demo.exceptions.UserAlreadyExists;
import com.kolev.demo.exceptions.UserNotFound;
import com.kolev.demo.models.User;
import com.kolev.demo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var user = this.userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new UserNotFound();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final User user) {
        var iEmailTest      = this.userRepository.findByEmail(user.getEmail());
        var iUsernameTest   = this.userRepository.findByUsername(user.getUsername());

        if(iUsernameTest != null || iEmailTest != null) {
            throw new UserAlreadyExists();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.saveAndFlush(user));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.userRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        var existingUser = this.userRepository.getOne(id);

        // TODO: 2/22/2020 - Add validation that all props are passed
        BeanUtils.copyProperties(user, existingUser, "id");

        return this.userRepository.saveAndFlush(existingUser);
    }
}