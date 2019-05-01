package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.configuration.security.PersonDetails;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String u) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(u);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("no username found with login " + u);
        }
        return new PersonDetails(user);
    }
}
