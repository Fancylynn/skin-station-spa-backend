package com.fancylynn.skinstationspa.service;

import com.fancylynn.skinstationspa.dao.UserDao;
import com.fancylynn.skinstationspa.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

/**
 * Created by Lynn on 2018/3/7.
 */

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserInfo findByUsername(String username) {

        return userDao.findByUsername(username);
    }

    public UserInfo findByEmail(String email){

        return userDao.findByEmail(email);
    }

    public UserInfo createNewUser(UserInfo newUser) throws EntityExistsException{
        UserInfo localUserEmail = userDao.findByEmail(newUser.getEmail());
        UserInfo localUsername = userDao.findByUsername(newUser.getUsername());

        if (localUserEmail != null) {
            LOG.info("user with this email {} already exists.", newUser.getEmail());
            throw new EntityExistsException("user with this email already exists");
        }

        if (localUsername != null) {
            LOG.info("user with this username {} already exists.", newUser.getUsername());
            throw new EntityExistsException("user with this username already exists");
        }

        return userDao.save(newUser);
    }

    public boolean loginVerification(String username, String password) throws EntityExistsException{
        UserInfo localUser = userDao.findByUsername(username);

        if (localUser == null) {
            throw new EntityExistsException("UserInfo does not exist!");
        }

        //get the encoded password in the database
        String encodedPasswordInDB = localUser.getPassword();

        //re-encode the input password to see if it matches the one in the db
        return passwordEncoder.matches(password, encodedPasswordInDB);

    }

}
