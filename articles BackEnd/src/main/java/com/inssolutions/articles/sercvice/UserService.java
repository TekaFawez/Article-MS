package com.inssolutions.articles.sercvice;

import com.inssolutions.articles.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDTO> findById(Long id) ;

    UserDTO addUser(UserDTO userDTO);

}
