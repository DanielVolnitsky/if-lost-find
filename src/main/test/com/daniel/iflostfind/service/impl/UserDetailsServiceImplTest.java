package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository repositoryMock;

    @InjectMocks
    private UserDetailsServiceImpl service;

    @Test
    public void testLoadUserByUsernameUserIsPresent() {
        String email = "test";
        String pass = "pass";
        User userMock = mock(User.class);

        when(userMock.getEmail()).thenReturn(email);
        when(userMock.getPassword()).thenReturn(pass);
        when(repositoryMock.findUserByEmail(email)).thenReturn(userMock);

        UserDetails result = service.loadUserByUsername(email);

        assertEquals(result.getUsername(), email);
        assertEquals(result.getPassword(), pass);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameUserNotExists() {
        String email = "test";

        when(repositoryMock.findUserByEmail(email)).thenReturn(null);

        service.loadUserByUsername(email);
    }
}

