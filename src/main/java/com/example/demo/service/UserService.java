package com.example.demo.service;

import com.example.demo.form.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author panghu
 * @Title: UserService
 * @ProjectName springboot_test
 * @Description: TODO
 * @date 19-3-9 上午8:51
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Synchronized
    public User findById(Integer id) {
        User user =  userRepository.getOne(id);
        return user;
    }

    public void add(User user){
        userRepository.save(user);
    }
}
