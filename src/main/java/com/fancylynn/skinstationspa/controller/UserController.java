package com.fancylynn.skinstationspa.controller;

/**
 * Created by Lynn on 2018/1/24.
 */
import com.fancylynn.skinstationspa.dto.JwtAuthenticationResponse;
import com.fancylynn.skinstationspa.dto.NewUserRequestDTO;
import com.fancylynn.skinstationspa.exception.AuthenticationException;
import com.fancylynn.skinstationspa.model.UserInfo;
import com.fancylynn.skinstationspa.service.UserService;
import com.fancylynn.skinstationspa.utility.JwtTokenUtil;
import com.fancylynn.skinstationspa.utility.SecurityUtility;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.EntityExistsException;
import java.util.Objects;


@RestController // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    user log in
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/login"
    )
    public @ResponseBody ResponseEntity<Object> userLogIn(
            @RequestParam String username,
            @RequestParam String password) throws BadCredentialsException {

        authenticate(username, password);
        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userService.findByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));

//        if (!userService.loginVerification(username, password)) {
//            throw new BadCredentialsException("Incompatible email or password!");
//        }
//        return new ResponseEntity<Object>(userService.findByUsername(username), HttpStatus.OK);
//        return userService.findByEmail(email);
    }

//    new user creation
    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public ResponseEntity<Object> createNewUser (
            @RequestBody NewUserRequestDTO newUserRequestDTO
            ) throws EntityExistsException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        // new user creation
        UserInfo n = new UserInfo();
        n.setUsername(newUserRequestDTO.getUsername());
        n.setEmail(newUserRequestDTO.getEmail());

        // password should be encrypted before insert into the database
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(newUserRequestDTO.getPassword());
        n.setPassword(encryptedPassword);

        userService.createNewUser(n);

        return new ResponseEntity<Object>(n, HttpStatus.CREATED);

    }

//    get user information
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/userprofile"
    )
    public @ResponseBody ResponseEntity<Object> getUserProfile(
//            @ApiIgnore
//            @ApiParam(value = "x-foo-g2-ft", required = false, hidden = true, defaultValue = "should be hidden")
            @RequestHeader(value="authorization") String authorizationToken
        ) throws BadCredentialsException {
        System.out.println(authorizationToken);

        String username = null;
        String authToken = null;
        authToken = authorizationToken.substring(7);
        username = jwtTokenUtil.getUsernameFromToken(authToken);
//        return username;
        return new ResponseEntity<Object>(userService.findByUsername(username), HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new AuthenticationException("Bad credentials!", e);
        }
    }




}
