package com.muhlenxi.dao;

import com.muhlenxi.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface IUserDao {

    @Select("select * from user")
    List<User> findAll();

    @Select("select count(*) from user")
    int findTotal();

    @Select("select * from user where id=#{uid}")
    User findUserById(int id);

    @Select("select * from user where name like #{name}")
    List<User> findUserByName(String name);

    @Insert("insert into user (name, sex, birthday, address) values (#{name}, #{sex}, #{birthday}, #{address})")
    void saveUser(User user);

    @Update("update user set name=#{name},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);
}
