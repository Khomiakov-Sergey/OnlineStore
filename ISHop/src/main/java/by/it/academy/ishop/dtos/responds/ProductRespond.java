package by.it.academy.ishop.dtos.responds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRespond {
    @NotBlank
    private String model;
    @NotBlank
    private String category;
    @Positive
    private BigDecimal price;
    @Positive
    private int number;
    private String description;
}
