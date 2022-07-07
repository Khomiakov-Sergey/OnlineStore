package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.responds.OrderDtoRespond;
import by.it.academy.ishop.dtos.responds.UserDtoRespond;
import by.it.academy.ishop.entities.order.Order;
import by.it.academy.ishop.entities.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper() {
        this.modelMapper = new ModelMapper();
    }

    private UserDtoRespond convertToUserDto(User user) {
        return modelMapper.map(user, UserDtoRespond.class);
    }

    public OrderDtoRespond orderToDto(Order order) {
        OrderDtoRespond orderDtoRespond = modelMapper.map(order, OrderDtoRespond.class);
        orderDtoRespond.setUser(convertToUserDto(order.getUser()));
        return orderDtoRespond;
    }
}
