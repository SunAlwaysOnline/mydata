package com.sun.mydata.service.impl;

import com.sun.mydata.dao.AccountDao;
import com.sun.mydata.domain.Account;
import com.sun.mydata.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

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
            logger.debug("redis中不存在id为" + id + "的账户信息!");
        } else {
            account = (Account) o;
            logger.debug("redis中存在id为" + id + "的账户信息!");
        }

        return account;
    }

    //首先让数据库执行更新操作，之后更新redis
    @Override
    public boolean updateById(Account account) {
        boolean flag = false;
        Integer i = accountDao.updateById(account);
        if (i > 0) {
            redisTemplate.opsForValue().set(account.getId(), account);
            logger.debug("数据库中更改了ID为" + account.getId() + "的账户信息,Redis中同样更新了此ID");
            flag = true;
        }
        return true;
    }

    //新增账户信息
    @Override
    public boolean add(Account account) {
        boolean flag = false;
        Integer i = accountDao.add(account);
        if (i > 0) {
            redisTemplate.opsForValue().set(account.getId(), account);
            logger.debug("数据库中新增ID为" + account.getId() + "的账户信息,Redis中同样新增了此ID");
            flag = true;
        }
        return flag;
    }

}
