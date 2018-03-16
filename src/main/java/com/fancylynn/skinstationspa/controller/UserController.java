package com.fancylynn.skinstationspa.controller;

/**
 * Created by Lynn on 2018/1/24.
 */
import com.fancylynn.skinstationspa.model.User;
import com.fancylynn.skinstationspa.service.UserService;
import com.fancylynn.skinstationspa.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;


@RestController // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserService userService;

//    user log in
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/login"
    )
    public @ResponseBody ResponseEntity<Object> userLogIn(
            @RequestParam String email,
            @RequestParam String password) throws BadCredentialsException {
        if (!userService.loginVerification(email, password)) {
            throw new BadCredentialsException("Incompatible email or password!");
        }
        return new ResponseEntity<Object>(userService.findByEmail(email), HttpStatus.OK);
//        return userService.findByEmail(email);
    }

//    new user creation
    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public @ResponseBody ResponseEntity<Object> createNewUser (
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            String firstName,
            String lastName,
            String phone
            ) throws EntityExistsException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        // new user creation
        User n = new User();
        n.setUsername(username);
        n.setEmail(email);

        // password should be encrypted before insert into the database
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        n.setPassword(encryptedPassword);

        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setPhone(phone);

        userService.createNewUser(n);

        return new ResponseEntity<Object>(n, HttpStatus.CREATED);

    }





}
