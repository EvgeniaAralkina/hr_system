package org.example.hrService.Services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.hrService.Models.Role;
import org.example.hrService.Models.User;
import org.example.hrService.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User save(User user, String role){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null)
            return null;

        user.setActive(true);
        if (role.equals("ADMIN"))
            user.setRoles(Collections.singleton(Role.ADMIN));
        else
            user.setRoles(Collections.singleton(Role.USER));

        System.out.println(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String generatePassword(){
        return RandomStringUtils.random(32, 0, 20, true, true,
                "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
    }

    public User findByName(String name){
        return userRepository.findByUsername(name);
    }

    public int changePassword(String username, String oldPassword, String newPassword){
        String pass = userRepository.findByUsername(username).getPassword();
        if (BCrypt.checkpw(oldPassword, newPassword)){
            System.out.println("Правильный пароль");
            userRepository.update(username, newPassword);
            return 1;
        }else{
            return 0;
        }
    }
}
