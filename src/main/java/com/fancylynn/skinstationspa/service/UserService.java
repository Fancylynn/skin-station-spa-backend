package com.fancylynn.skinstationspa.service;

import com.fancylynn.skinstationspa.dao.UserDao;
import com.fancylynn.skinstationspa.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }

    public User createNewUser(User newUser) throws EntityExistsException{
        User localUserEmail = userDao.findByEmail(newUser.getEmail());

        if (localUserEmail != null) {
            LOG.info("user with this email {} already exists.", newUser.getEmail());
            throw new EntityExistsException("user with this email already exists");
        }

        return userDao.save(newUser);

    }

}
