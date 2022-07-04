/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.repository;

import com.ldn.pojo.User;
import java.util.List;

/**
 *
 * @author three
 */
public interface UserRepository {
    public List<User> getUsers(String email);
    public boolean addUser(User user);
}
