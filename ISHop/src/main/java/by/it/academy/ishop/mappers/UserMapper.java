package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.UserDto;
import by.it.academy.ishop.dtos.UserRoleDto;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.entities.user.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(UserRole.class, UserRoleDto.class);
    }

    public UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
