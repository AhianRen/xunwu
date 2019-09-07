package com.ren.xunwu.entity;

import com.ren.xunwu.XunwuApplicationTests;
import com.ren.xunwu.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserTest extends XunwuApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryTest(){
        User user = userRepository.findById(1).get();
        System.out.println(user.toString());
        Assert.assertEquals("wali", user.getName());
    }


}
