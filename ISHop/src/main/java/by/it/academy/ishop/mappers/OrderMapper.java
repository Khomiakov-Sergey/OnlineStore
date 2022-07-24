package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.responds.OrderRespondDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.order.Order;
import by.it.academy.ishop.entities.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for orders. It for converts Order to OrderDto .
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper() {
        this.modelMapper = new ModelMapper();
    }

    /**
     * This intermediate method converts User to UserRespondDto (need for orderToDto).
     * @param user - Entity User.
     * @return UserRespondDto - User representation without password in DTO.
     */
    private UserRespondDto convertToUserDto(User user) {
        return modelMapper.map(user, UserRespondDto.class);
    }

    /**
     * This method converts Order to OrderDto.
     * @param order - Entity Order.
     * @return orderToDto - Order representation in DTO.
     */
    public OrderRespondDto orderToDto(Order order) {
        OrderRespondDto orderRespondDto = modelMapper.map(order, OrderRespondDto.class);
        orderRespondDto.setUser(convertToUserDto(order.getUser()));
        return orderRespondDto;
    }
}
