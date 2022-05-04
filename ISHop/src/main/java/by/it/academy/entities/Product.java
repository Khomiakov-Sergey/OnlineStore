package by.it.academy.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Product {
    private int id;
    private final String name;
    private final BigDecimal price;
    private final int number;
    private final String description;

}
