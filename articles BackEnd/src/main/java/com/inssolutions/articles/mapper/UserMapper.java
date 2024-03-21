package com.inssolutions.articles.mapper;

import com.inssolutions.articles.dto.UserDTO;
import com.inssolutions.articles.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    User mapToUser(UserDTO userDTO);
    UserDTO mapToUserDTO (User user);


}
