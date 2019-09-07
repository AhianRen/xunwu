package com.ren.xunwu.web;

import com.ren.xunwu.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model){
        System.out.println("xx");
        model.addAttribute("msg", "thymeleaf");
        return "index";
    }







    //----------------测试---------------------
    @RequestMapping("/thymeleaftest")
    public String thymeleaftest(Model model){
        User user = new User();
        user.setName("姓名");
        user.setId(1);
        model.addAttribute("user", user);
        model.addAttribute("msg", "thymeleaf");
        return "thymeleaftest";
    }
}
