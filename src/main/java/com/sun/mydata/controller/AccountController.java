package com.sun.mydata.controller;

import com.sun.mydata.domain.Account;
import com.sun.mydata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController=@Controller+@ResponseBody
//这是主分支内的注释
//这是second分支内的注释
//已经将second分支内代码合并到主分支中
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/{id}")
    public Account get(@PathVariable("id") int id){
        return accountService.findById(id);
    }
}
