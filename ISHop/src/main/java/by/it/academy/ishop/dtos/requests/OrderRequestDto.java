package by.it.academy.ishop.dtos.requests;

import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    private ProductDto product;
    private UserRespondDto user;
    @Positive
    private Long quantity;

}
