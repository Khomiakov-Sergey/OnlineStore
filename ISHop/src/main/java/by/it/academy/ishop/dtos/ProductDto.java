package by.it.academy.ishop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * DTO class for representing product.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    @Positive
    private Long id;
    @NotBlank
    private String model;
    private CategoryDto category;
    @Positive
    private BigDecimal price;
    @Positive
    private Long number;
    @NotBlank
    private String description;
}
