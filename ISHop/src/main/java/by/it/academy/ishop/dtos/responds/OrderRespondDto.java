package by.it.academy.ishop.dtos.responds;

import by.it.academy.ishop.dtos.OrderStatusDto;
import by.it.academy.ishop.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRespondDto {
    private Long id;
    private ProductDto product;
    private UserRespondDto user;
    private Long quantity;
    private BigDecimal amount;
    private OrderStatusDto orderStatus;
    private LocalDateTime createdAt;

}
