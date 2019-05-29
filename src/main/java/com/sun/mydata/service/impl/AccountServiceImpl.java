package com.sun.mydata.service.impl;

import com.sun.mydata.dao.AccountDao;
import com.sun.mydata.domain.Account;
import com.sun.mydata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private AccountDao accountDao;

    //首先在redis中查询，查询不到时，前往数据库中查找，然后将数据保存在redis中。
    @Override
    public Account findById(int id) {
        Account account = null;
        Object o = redisTemplate.opsForValue().get(id);
        if (null == o) {
            account = accountDao.findById(id);
            redisTemplate.opsForValue().set(id, account);
            System.out.println("redis中不存在id为" + id + "的账户信息!");
        } else {
            account = (Account) o;
            System.out.println("redis中存在id为" + id + "的账户信息!");
        }

        return account;
    }

    //首先让数据库执行更新操作，之后更新redis
    @Override
    public Integer updateById(Account account) {
        Integer i = accountDao.updateById(account);
        if (i > 0) {
            redisTemplate.opsForValue().set(account.getId(), account);
            System.out.println("Redis中新增ID"+account.getId()+"的账户信息!");
        }
        return i;
    }

    //新增账户信息
    @Override
    public Integer add(Account account) {
        Integer i = accountDao.add(account);
        if (i > 0) {
            redisTemplate.opsForValue().set(account.getId(), account);
        }
        return i;
    }


}
