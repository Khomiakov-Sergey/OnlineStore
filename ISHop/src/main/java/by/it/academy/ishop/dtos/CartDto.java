package by.it.academy.ishop.dtos;

import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import lombok.*;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    @Positive
    private Long id;
    private ProductDto product;
    private UserDto user;
    @Positive
    private Long quantity;
    @Positive
    private BigDecimal cost;
}
