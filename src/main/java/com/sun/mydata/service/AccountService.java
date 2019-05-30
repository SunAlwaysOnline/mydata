package com.sun.mydata.service;

import com.sun.mydata.domain.Account;

public interface AccountService {
    public Account findById(int id);

    public boolean updateById(Account account);

    public boolean add(Account account);

}
