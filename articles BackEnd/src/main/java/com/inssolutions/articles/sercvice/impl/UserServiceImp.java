package com.inssolutions.articles.sercvice.impl;

import com.inssolutions.articles.dto.UserDTO;
import com.inssolutions.articles.entity.User;
import com.inssolutions.articles.mapper.UserMapper;
import com.inssolutions.articles.repo.UserRepo;
import com.inssolutions.articles.sercvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public  ResponseEntity<UserDTO> findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(UserMapper.userMapper.mapToUserDTO(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = UserMapper.userMapper.mapToUser(userDTO);
        User saveUser = userRepo.save(user);
        return UserMapper.userMapper.mapToUserDTO(saveUser);
    }
}
