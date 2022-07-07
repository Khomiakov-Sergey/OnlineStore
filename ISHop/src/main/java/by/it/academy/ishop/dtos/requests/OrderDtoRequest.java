package by.it.academy.ishop.dtos.requests;

import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.responds.UserDtoRespond;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoRequest {
    @Positive
    private Long id;
    private ProductDto product;
    private UserDtoRespond user;
    @Positive
    private Long quantity;

}
