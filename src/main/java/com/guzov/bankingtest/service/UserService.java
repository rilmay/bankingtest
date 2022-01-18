package com.guzov.bankingtest.service;

import com.guzov.bankingtest.domain.Role;
import com.guzov.bankingtest.domain.User;
import com.guzov.bankingtest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passEnc;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void updateUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<Role> roles = form.keySet()
                .stream()
                .filter(x -> Arrays.stream(Role.values()).map(Enum::name).anyMatch(role -> role.equals(x)))
                .map(Role::valueOf)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        userRepo.save(user);
    }

    public boolean registerUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passEnc.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public void updateProfile(User user, String password) {
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        userRepo.save(user);
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepo.save(user);
    }
}
