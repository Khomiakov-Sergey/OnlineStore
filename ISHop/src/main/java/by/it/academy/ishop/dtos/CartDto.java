package by.it.academy.ishop.dtos;

import by.it.academy.ishop.dtos.responds.UserRespondDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private UserRespondDto user;
    @Positive
    private Long quantity;
    @Positive
    private BigDecimal cost;
}
