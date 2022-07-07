package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.responds.UserDtoRespond;
import by.it.academy.ishop.dtos.UserRoleDto;
import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.entities.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
    }

    public UserDtoRespond userToDto(User user) {
        return modelMapper.map(user, UserDtoRespond.class);
    }

}
