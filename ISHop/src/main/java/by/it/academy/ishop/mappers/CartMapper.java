package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.dtos.responds.UserDtoRespond;
import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.entities.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;

    public CartMapper() {
        this.modelMapper = new ModelMapper();
    }

    private UserDtoRespond convertToUserDto(User user) {
        return modelMapper.map(user, UserDtoRespond.class);
    }

    public CartDto cartToDto(Cart cart) {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        cartDto.setUser(convertToUserDto(cart.getUser()));
        return cartDto;
    }

}
