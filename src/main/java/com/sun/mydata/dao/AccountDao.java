package com.sun.mydata.dao;

import com.sun.mydata.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountDao {
    public Account findById(int id);
}
