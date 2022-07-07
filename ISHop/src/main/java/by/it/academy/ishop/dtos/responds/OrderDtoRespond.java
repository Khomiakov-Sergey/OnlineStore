package by.it.academy.ishop.dtos.responds;

import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.OrderStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoRespond {
    @Positive
    private Long id;
    private ProductDto product;
    private UserDtoRespond user;
    @Positive
    private Long quantity;
    private BigDecimal amount;
    private OrderStatusDto orderStatus;
    private LocalDateTime createdAt;

}
