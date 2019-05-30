package com.sun.mydata.controller;

import com.sun.mydata.domain.Account;
import com.sun.mydata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//@RestController=@Controller+@ResponseBody
//这是主分支内的注释
//这是second分支内的注释
//已经将second分支内代码合并到主分支中
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;


    /**
     * 利用id查询某人的账户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public Account get(@PathVariable("id") int id) {
        return accountService.findById(id);
    }

    /**
     * 更新账户信息
     *
     * @param account
     * @return
     */
    @RequestMapping("/update")
    public HashMap<String, String> update(Account account) {
        HashMap<String, String> map = new HashMap<>();
        boolean flag = accountService.updateById(account);
        if (flag) {
            map.put("success", "true");
            map.put("info", "更新成功");
        } else {
            map.put("success", "false");
            map.put("info", "更新失败");
        }
        return map;
    }

    /**
     * 增加账户信息
     *
     * @param account
     * @return
     */
    @RequestMapping("/add")
    public HashMap<String, String> add(Account account) {
        HashMap<String, String> map = new HashMap<>();
        boolean flag = accountService.add(account);
        if (flag) {
            map.put("success", "true");
            map.put("info", "增加成功");
        } else {
            map.put("success", "false");
            map.put("info", "增加失败");
        }
        return map;
    }

}
