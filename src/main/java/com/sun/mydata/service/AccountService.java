package com.sun.mydata.service;

import com.sun.mydata.domain.Account;

public interface AccountService {
    public Account findById(int id);

    public Integer updateById(Account account);

    public Integer add(Account account);
}
