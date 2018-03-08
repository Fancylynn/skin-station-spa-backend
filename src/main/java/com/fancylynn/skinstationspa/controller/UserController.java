package com.fancylynn.skinstationspa.controller;

/**
 * Created by Lynn on 2018/1/24.
 */
import com.fancylynn.skinstationspa.model.User;
import com.fancylynn.skinstationspa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserService userService;


//    user log in
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/login"
    )
    public User userLogIn(@RequestParam String username) {
        return userService.findByUsername(username);
    }

//    new user creation
    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public @ResponseBody ResponseEntity<Object> createNewUser (
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            String phone
            ) throws EntityExistsException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request


        User n = new User();
        n.setUsername(username);
        n.setEmail(email);
        n.setPassword(password);
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setPhone(phone);

        userService.createNewUser(n);

        n.setPassword("");
        return new ResponseEntity<Object>(n, HttpStatus.CREATED);

    }





}
