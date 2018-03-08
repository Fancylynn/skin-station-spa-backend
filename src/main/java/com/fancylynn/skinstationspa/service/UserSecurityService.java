package com.fancylynn.skinstationspa.service;

import com.fancylynn.skinstationspa.dao.UserDao;
import com.fancylynn.skinstationspa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Lynn on 2018/3/6.
 */

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        return user;
    }
}
