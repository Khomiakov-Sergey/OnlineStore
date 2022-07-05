package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.CartDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.UserDto;
import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;

    public CartMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Product.class, ProductDto.class);
        modelMapper.createTypeMap(User.class, UserDto.class);
    }

    public CartDto cartToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

}
