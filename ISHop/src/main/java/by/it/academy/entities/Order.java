package by.it.academy.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Data
@RequiredArgsConstructor
public class Order {
    private int id;
    private final int userId;
    private final int productId;
    private final int quantity;
    private BigDecimal amount;

}
