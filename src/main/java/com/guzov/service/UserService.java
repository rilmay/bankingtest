package com.guzov.service;

import com.guzov.domain.Role;
import com.guzov.domain.User;
import com.guzov.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void registerUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<Role> roles = form.keySet()
                .stream()
                .filter(x -> Arrays.stream(Role.values()).map(Enum::name).anyMatch(role -> role.equals(x)))
                .map(Role::valueOf)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        userRepo.save(user);
    }

    public void updateProfile(User user, String password) {
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        userRepo.save(user);
    }
}
