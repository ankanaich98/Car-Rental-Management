package com.example.Car.Rental.services;

import com.example.Car.Rental.entities.User;
import com.example.Car.Rental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> listAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User get(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            return null;
        }
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
