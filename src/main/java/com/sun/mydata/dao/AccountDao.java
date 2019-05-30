package com.sun.mydata.dao;

import com.sun.mydata.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountDao {
    public Account findById(int id);

    public int updateById(Account account);

    public int add(Account account);

    public int incrMoney(@Param("id") int id, @Param("money") double money );

    public int decrMoney(@Param("id") int id, @Param("money") double money );
}
