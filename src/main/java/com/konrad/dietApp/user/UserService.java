package com.konrad.dietApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
   UserRepository userRepository;

    public int findIdByEmail(String email){
       return  userRepository.findIdByEmail(email);
    }

    public User findUserByEmail(String email){
        return  userRepository.findByEmail(email);
    }

}
