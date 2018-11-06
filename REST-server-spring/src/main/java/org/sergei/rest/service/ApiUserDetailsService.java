/*
 * Copyright (c) 2018 Sergei Visotsky
 */

package org.sergei.rest.service;

import org.sergei.rest.dao.UserDAO;
import org.sergei.rest.exceptions.ResourceNotFoundException;
import org.sergei.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sergei Visotsky, 2018
 */
@Service
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException("User with this username not found");
        }
        return new ApiUserDetails(user);
    }
}
