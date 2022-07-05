package by.it.academy.ishop.dtos;

import by.it.academy.ishop.entities.product.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Positive
    private Long id;

    @Enumerated(EnumType.STRING)
    CategoryType categoryType;
}
