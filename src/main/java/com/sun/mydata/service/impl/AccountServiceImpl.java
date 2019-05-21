package com.sun.mydata.service.impl;

import com.sun.mydata.dao.AccountDao;
import com.sun.mydata.domain.Account;
import com.sun.mydata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findById(int id) {
        return accountDao.findById(id);
    }
}
