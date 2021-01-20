package com.example.demo.services;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class UserService implements IUser {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);

    }
    @Override
    public User updateById(int id, HttpServletRequest data) throws IOException {
        User user = userRepository.findById(id);
        User updated = objectMapper.readerForUpdating(user).readValue(data.getReader());
        userRepository.saveAndFlush(updated);
        return user;
    }
}
