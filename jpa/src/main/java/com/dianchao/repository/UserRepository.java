package com.dianchao.repository;

import com.dianchao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepositoy来完成对数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {

}
