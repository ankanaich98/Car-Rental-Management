package com.example.Car.Rental.configurations.userdetails;

import com.example.Car.Rental.entities.User;
import com.example.Car.Rental.repositories.UserRepository;
import com.example.Car.Rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetails implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       final User user = userRepository.findUserByUsername(username);
       if(user==null){
           throw new UsernameNotFoundException(username);
       }
       return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(String.valueOf(user.getRole())).build();
    }
}
