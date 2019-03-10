package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.form.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author panghu
 * @Title: UserController
 * @ProjectName springboot_test
 * @Description: TODO
 * @date 19-3-9 上午9:02
 */
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findById")
    public User findById(@RequestParam(value = "id") Integer id){
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping("/add")
    public void add(User user){
        userService.add(user);
        System.out.printf("OK------------");
    }



}
