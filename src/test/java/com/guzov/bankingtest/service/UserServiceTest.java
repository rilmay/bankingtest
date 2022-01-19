package com.guzov.bankingtest.service;

import com.guzov.bankingtest.domain.Role;
import com.guzov.bankingtest.domain.User;
import com.guzov.bankingtest.repository.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void registerUser() {
        User user = new User();

        boolean isUserCreated = userService.registerUser(user);

        assertTrue(isUserCreated);
        assertTrue(user.isActive());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }


    @Test
    void registerUserFailTest() {
        User user = new User();
        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("John");

        boolean isUserCreated = userService.registerUser(user);

        assertFalse(isUserCreated);
        Mockito.verify(userRepo, Mockito.times(0)).save(user);
    }


}