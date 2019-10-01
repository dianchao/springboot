package com.dianchao.entity;

import javax.persistence.*;

//使用JPA注解配置映射关系
//@Entity：告诉JPA这是一个实体类，（这是一个和数据表映射的类，而不是一个普通的JavaBean）
@Entity
//@Table来指定和哪个数据表对应，如果缺省默认表名就是user;
@Table(name = "tbl_user")
public class User {
    //这是一个主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增主键
    private Integer id;

    //这是和数据表对应的一列
    @Column(name = "last_name", length = 50)
    private String lastName;

    //省略默认就是属性名
    @Column
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}