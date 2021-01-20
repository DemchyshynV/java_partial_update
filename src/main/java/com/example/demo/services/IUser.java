package com.example.demo.services;

import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IUser {
    List<User> getAll();
    User createUser(User user);
    User getById(int id);
    void deleteById(int id);
    User updateById(int id, HttpServletRequest data) throws IOException;
}
