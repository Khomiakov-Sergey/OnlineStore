package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.requests.UserDto;
import by.it.academy.ishop.dtos.requests.UserRoleDto;
import by.it.academy.ishop.dtos.requests.UserStatusDto;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.entities.user.UserRole;
import by.it.academy.ishop.entities.user.UserStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(UserRole.class, UserRoleDto.class);
        modelMapper.createTypeMap(UserStatus.class, UserStatusDto.class);
    }

    public UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
