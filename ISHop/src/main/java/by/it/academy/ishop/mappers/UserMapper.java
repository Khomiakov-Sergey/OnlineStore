package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for users. It for converts User to UserRespondDto .
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
    }

    /**
     * This method converts user to UserRespondDto.
     * @param user - Entity User.
     * @return UserRespondDto - User representation without password in DTO.
     */
    public UserRespondDto userToDto(User user) {
        return modelMapper.map(user, UserRespondDto.class);
    }

}
