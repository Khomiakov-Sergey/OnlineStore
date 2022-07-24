package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.entities.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for carts. It converts Cart to CartDto .
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Component
public class CartMapper {//todo
    private final ModelMapper modelMapper;

    public CartMapper() {
        this.modelMapper = new ModelMapper();
    }

    /**
     * This intermediate method converts User to UserRespondDto (need for cartToDto).
     * @param user - Entity User.
     * @return UserRespondDto - User representation without password in DTO.
     */
    private UserRespondDto convertToUserDto(User user) {
        return modelMapper.map(user, UserRespondDto.class);
    }

    /**
     * This method converts Cart to CartDto.
     * @param cart - Entity Cart.
     * @return cartDto - Cart representation in DTO.
     */
    public CartDto cartToDto(Cart cart) {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        cartDto.setUser(convertToUserDto(cart.getUser()));
        return cartDto;
    }

}
